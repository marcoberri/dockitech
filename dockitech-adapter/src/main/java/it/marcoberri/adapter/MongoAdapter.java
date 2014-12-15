package it.marcoberri.adapter;

import it.marcoberri.dockitech.model.DTClient;
import it.marcoberri.dockitech.model.DTDocument;
import it.marcoberri.dockitech.model.DTEncryptionMethod;
import it.marcoberri.dockitech.model.DTSecurityGroup;
import it.marcoberri.dockitech.model.DTSecurityUser;
import it.marcoberri.dockitech.resources.CollectionNames;
import it.marcoberri.dockitech.resources.FieldsName;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSInputFile;

public class MongoAdapter extends AbstractAdapter {

    private Datastore datastore;

    private String defaultEncrypt = "it.marcoberri.dockitech.security.Base64Security";

    private GridFS gridFS;

    @Override
    public void initAdapter(Properties p) {

	// TODO

	try {
	    //MongoClient mongoClient = new MongoClient("localhost");
	    MongoClient mongoClient = new MongoClient("192.168.1.89");
	    final Morphia morphia = new Morphia();
	    morphia.mapPackage("it.marcoberri.dockitech.model");
	    datastore = morphia.createDatastore(mongoClient, "test");

	    this.gridFS = new GridFS(datastore.getDB(), CollectionNames.COLLECTION_FILES);

	} catch (final UnknownHostException e) {
	    e.printStackTrace();
	}
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
	DTEncryptionMethod enc = new DTEncryptionMethod();
	enc = datastore.createQuery(DTEncryptionMethod.class).filter("encryptClass =", "it.marcoberri.dockitech.security.Base64Security").get();
	client.setEncryptClass(enc);
	client.setEncryptKey("secrekeysecrekey1234567890!!");
	datastore.save(client);
	
	
	final DTSecurityGroup groupAdmin = new DTSecurityGroup(client);
	groupAdmin.setTitle("ADMIN");
	datastore.save(groupAdmin);

	final DTSecurityUser userAdmin = new DTSecurityUser(client);
	userAdmin.setNickname("admin");
	userAdmin.setPassword("admin123!");
	userAdmin.setName("Global Admin System");
	userAdmin.setSurname("Dockitech Software");
	userAdmin.addSecurityGroup(groupAdmin);
	datastore.save(userAdmin);

	client.addSecurityGroup(groupAdmin);
	
	datastore.save(client);

	return client;
    }

    @Override
    public void dropWorld() {

	// TODO
	if (datastore == null) {
	    initAdapter(null);
	}

	datastore.getMongo().dropDatabase("test");

    }

    public DTDocument addDocument(DTDocument doc) {

	if (datastore == null) {
	    initAdapter(null);
	}
	

	if (doc.getByteFile() != null && doc.getFile() != null) {
	    final GridFSInputFile gfsFile = gridFS.createFile(doc.getByteFile());
	    gfsFile.setFilename(doc.getFileNameCrypt());
	    gfsFile.setContentType(doc.getContentTypeCrypt());
	    gfsFile.save();
	    doc.setFileId(new ObjectId(gfsFile.getId().toString()));
	}

	datastore.save(doc);

	return doc;

    }

    
    public DTDocument getDocumentByTitle(DTClient client,DTDocument doc) {

	if (datastore == null) {
	    initAdapter(null);
	}
	
	if(doc.getTitle() == null){
	    return null;
	    //TODO exception
	}
	
	if(doc.getClient() == null){
	    doc.setClient(client);
	}

	
	return datastore.createQuery(DTDocument.class).filter(FieldsName.DOC_TITLE + " = ", doc.getTitleCrypt()).filter(FieldsName.DOC_CLIENT + " = ", client).get();

    }
    
    @Override
    public DTSecurityUser getUserByNick(DTClient client, String nickname) {
	final DTSecurityUser user = new DTSecurityUser(client);
	user.setNickname(nickname);
	return datastore.createQuery(DTSecurityUser.class).filter(FieldsName.SECURITYUSER_NICKNAME + " = ", user.getNicknameCrypt()).filter(FieldsName.SECURITYUSER_CLIENT + " = ", client).get();
    }

}
