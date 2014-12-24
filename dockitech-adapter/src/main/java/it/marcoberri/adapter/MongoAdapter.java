package it.marcoberri.adapter;

import it.marcoberri.dockitech.model.DTClient;
import it.marcoberri.dockitech.model.DTDocument;
import it.marcoberri.dockitech.model.DTEncryptionMethod;
import it.marcoberri.dockitech.model.DTSecurityGroup;
import it.marcoberri.dockitech.model.DTSecurityUser;
import it.marcoberri.dockitech.resources.CollectionNames;
import it.marcoberri.dockitech.resources.Configuration;
import it.marcoberri.dockitech.resources.FieldsName;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSFile;
import com.mongodb.gridfs.GridFSInputFile;

public class MongoAdapter extends AbstractAdapter {

	private Datastore datastore;

	private GridFS gridFS;

	private String dbName;

	@Override
	public void initAdapter(Properties p) {

		try {
			Properties properties = Configuration
					.readPropertiesFile("/store.properties");
			initMongo(properties);
		} catch (final IOException e) {
			// TODO
			e.printStackTrace();
		}

	}

	private void initMongo(Properties p) {

		final String s = p.getProperty("dockitech.db.hosts", "localhost");

		final List<String> servers_host = Arrays.asList(s.split(","));

		final List<ServerAddress> serveHostList = new ArrayList<ServerAddress>();

		for (String sName : servers_host) {
			String name = sName.split(":")[0];
			int port = 27017;
			if (sName.indexOf(":") > -1) {
				port = Integer.parseInt(sName.split(":")[1]);
			}

			try {
				serveHostList.add(new ServerAddress(name, port));
			} catch (final UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		this.dbName = p.getProperty("dockitech.db.name", "test");

		final MongoClient mongoClient = new MongoClient(serveHostList);

		final Morphia morphia = new Morphia();
		morphia.mapPackage("it.marcoberri.dockitech.model");
		this.datastore = morphia.createDatastore(mongoClient, this.dbName);
		this.gridFS = new GridFS(datastore.getDB(),
				CollectionNames.COLLECTION_FILES);

	}

	@Override
	public DTClient createWorld(DTClient client) {

		if (datastore == null) {
			initAdapter(null);
		}

		// setting enc Method
		final ArrayList<String> encMethod = new ArrayList<String>() {
			{
				add("it.marcoberri.dockitech.security.NullSecurity");
				add("it.marcoberri.dockitech.security.Base64Security");
				add("it.marcoberri.dockitech.security.CipherSecurity");
			}
		};

		for (String s : encMethod) {
			final DTEncryptionMethod enc = new DTEncryptionMethod();
			enc.setEncryptClass(s);
			datastore.save(enc);
		}

		// load encrypt method default
		final DTEncryptionMethod enc = datastore
				.createQuery(DTEncryptionMethod.class)
				.filter("encryptClass =",
						"it.marcoberri.dockitech.security.Base64Security")
				.get();
		client.setEncryptClass(enc);
		client.setEncryptKey("secrekeysecrekey1234567890!!");
		datastore.save(client);

		final DTSecurityGroup groupAdmin = new DTSecurityGroup(client);
		groupAdmin.setTitle("ADMIN");
		datastore.save(groupAdmin);

		final DTSecurityUser userAdmin = new DTSecurityUser(client);
		userAdmin.setNickname("admin");
		userAdmin.setTokenTypeUser(true);
		userAdmin.setPassword("admin123!");
		userAdmin.setName("Global Admin System");
		userAdmin.setSurname("Dockitech Software");

		userAdmin.addSecurityGroup(groupAdmin);
		datastore.save(userAdmin);

		client.addSecurityGroup(groupAdmin);

		userAdmin.setClient(client);
		datastore.save(userAdmin);

		datastore.save(client);

		return client;
	}

	@Override
	public void dropWorld() {

		// TODO
		if (datastore == null) {
			initAdapter(null);
		}

		datastore.getMongo().dropDatabase(this.dbName);

	}

	public DTDocument addDocument(DTDocument doc) {

		if (datastore == null) {
			initAdapter(null);
		}

		if (doc.getByteFile() != null && doc.getFile() != null) {
			final GridFSInputFile gfsFile = gridFS
					.createFile(doc.getByteFile());
			gfsFile.setFilename(doc.getFileNameEncrypt());
			gfsFile.setContentType(doc.getContentTypeEncrypt());
			gfsFile.save();
			doc.setFileId(new ObjectId(gfsFile.getId().toString()));
		}

		datastore.save(doc);

		return doc;

	}

	@Override
	public DTDocument getDocumentByTitle(DTClient client, String titlePlain) {
		return getDocumentByTitle(client,  titlePlain, false);
	}

	@Override
	public DTDocument getDocumentByTitle(DTClient client, String titlePlain, boolean withFile) {

		if (datastore == null) {
			initAdapter(null);
		}

		if (titlePlain == null) {
			return null;
			// TODO exception
		}

		final DTDocument docFilter = new DTDocument(client);
		docFilter.setTitle(titlePlain);
		final DTDocument docResult =  datastore.createQuery(DTDocument.class)
				.filter(FieldsName.DOC_TITLE + " = ", docFilter.getTitleEncrypt())
				.filter(FieldsName.DOC_CLIENT + " = ", client).get();
		
		if(withFile){
			GridFSFile file = this.gridFS.findOne(docResult.getFileId());
			
		}
		return docResult;

	}

	
	
	@Override
	public DTSecurityUser getUserByNick(DTClient client, String nickname) {
		final DTSecurityUser user = new DTSecurityUser(client);
		user.setNickname(nickname);
		return datastore
				.createQuery(DTSecurityUser.class)
				.filter(FieldsName.SECURITYUSER_NICKNAME + " = ",
						user.getNicknameEncrypt())
				.filter(FieldsName.SECURITYUSER_CLIENT + " = ", client).get();
	}

}
