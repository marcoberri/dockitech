package it.marcoberri.dockitech.adapter;

import it.marcoberri.dockitech.model.DTClient;
import it.marcoberri.dockitech.model.DTDocument;
import it.marcoberri.dockitech.model.DTEncryptionMethod;
import it.marcoberri.dockitech.model.DTSecurityGroup;
import it.marcoberri.dockitech.model.DTSecurityUser;
import it.marcoberri.dockitech.model.DTText;
import it.marcoberri.dockitech.model.DTToken;
import it.marcoberri.dockitech.resources.CollectionNames;
import it.marcoberri.dockitech.resources.Configuration;
import it.marcoberri.dockitech.resources.FieldsName;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class MongoAdapter extends AbstractAdapter {

	private Datastore datastore;

	private GridFS gridFS;

	private String dbName;

	static Logger log = LogManager.getLogger(MongoAdapter.class);

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

		final DTText title = doc.getTitle();
		title.setClient(doc.getClient());
		datastore.save(title);

		final DTText desc = doc.getDescription();
		desc.setClient(doc.getClient());
		datastore.save(desc);

		datastore.save(doc);

		title.setDocument(doc);
		datastore.save(title);

		desc.setDocument(doc);
		datastore.save(desc);

		datastore.save(doc);

		return doc;

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

	@Override
	public DTDocument getDocumentByTitle(DTClient client, String titlePlain) {
		return getDocumentByTitle(client, titlePlain, false);
	}

	@Override
	public DTDocument getDocumentByTitle(DTClient client, String titlePlain,
			boolean withFile) {
		return getDocumentByTitle(client, titlePlain, null, withFile);
	}

	@Override
	public DTDocument getDocumentByTitle(DTClient client, String titlePlain,
			String lang) {

		return getDocumentByTitle(client, titlePlain, lang, false);
	}

	@Override
	public DTDocument getDocumentByTitle(DTClient client, String titlePlain,
			String lang, boolean withFile) {

		if (datastore == null) {
			initAdapter(null);
		}

		if (titlePlain == null) {
			return null;
			// TODO exception
		}

		if (lang == null)
			lang = client.getDefaultLang();

		final DTDocument docFilter = new DTDocument(client);
		docFilter.setTitle(new DTText(client, titlePlain));
		lang = docFilter.encrypt(lang, client);

		final Query<DTText> query = datastore.createQuery(DTText.class);
		query.filter(FieldsName.TEXT_CLIENT + " = ", client).filter(
				FieldsName.TEXT_VALUE + "." + lang + " = ",
				docFilter.getTitle().getValueEncryptFromEncryptKey(lang));

		System.out.println("query -->" + query.toString());

		DTText title = query.get();

		System.out.println("id -->" + title.getDocument().getId().toString());

		final DTDocument docResult = datastore.createQuery(DTDocument.class)
				.filter("_id = ", title.getDocument().getId()).get();

		if (withFile) {
			final GridFSDBFile file = this.gridFS
					.findOne(docResult.getFileId());
			docResult.setFileNameEnrypt(file.getFilename());
			try {
				docResult.setByteFileEncrypt(IOUtils.toByteArray(file
						.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			docResult.setContentTypeEncrypt(file.getContentType());
			docResult.setFileLength(file.getLength());

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

	@Override
	public void initAdapter(Properties p) {

		try {
			Properties properties = Configuration
					.readPropertiesFile("/store.properties");
			log.info("Load properties:" + properties);
			initMongo(properties);
		} catch (final IOException e) {
			log.fatal(e);
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
				log.fatal(e);
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
	public DTText saveText(DTClient client, String text) {
		return saveText(client, null, text);
	}

	@Override
	public DTText saveText(DTClient client, String lang, String text) {
		if (lang == null)
			lang = client.getDefaultLang();
		final DTText textObject = new DTText(client, lang, text);
		datastore.save(textObject);
		return textObject;
	}

	@Override
	public DTSecurityUser autenticate(DTClient client, String nickname,String password) {
		/*
		log.info("try autenticate nickname ["+nickname+ "]  password ["+password+"] client ["+client.getTitle()+"]");
		
		DTSecurityUser user = new DTSecurityUser(client);
		user.setPassword(password);
		user.setNickname(nickname);
		DTSecurityUser userResult = datastore.get(user);
		
		if(userResult == null){
			log.error("try autenticate nickname ["+nickname+ "]  password ["+password+"] not found");
			return null;
		}
			DTToken token = new DTToken();
		
		
		// TODO Auto-generated method stub
		return user;
	*/
		
		return null;
	}

	@Override
	public DTSecurityUser autenticate(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveToken(DTClient client, DTSecurityUser user) {
		
		
		//devo verificare se esiste.
		//se esiste è il client.tokenapp == true devo restituire quello sempre in base alla scadenza
		//se esiste verifico la scadenza se è scaduto ne restituisco uno nuovo.
		DTToken token = new DTToken();
		token.setClient(client);
		token.setUser(user);
		datastore.save(token);
		
		return token.getId().toString();
	}

}
