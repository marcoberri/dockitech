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
    public DTEncryptionMethod getEncryptMethodByClass(String classname){
	if(classname == null)
	    classname = defaultEncrypt;
	
	final DTEncryptionMethod enc = new DTEncryptionMethod();
	return datastore.createQuery(DTEncryptionMethod.class).filter("encryptClass =", classname ).get();
    }
    
    @Override
     public DTEncryptionMethod getEncryptMethodByClass(){
	return  getEncryptMethodByClass(null);
     }
    
    @Override
    public DTClient createWorld(DTClient client) {

	if (datastore == null) {
	    initAdapter(null);
	}
	

	//setting enc Method
	final ArrayList<String> encMethod =  new ArrayList<String>() {{
	    add("it.marcoberri.dockitech.security.NullSecurity");
	    add("it.marcoberri.dockitech.security.Base64Security");
	}};
	
	for(String s : encMethod){
	    final DTEncryptionMethod enc = new DTEncryptionMethod();
	    enc.setEncryptClass(s);
	    datastore.save(enc);
	}
	
	
	//load encrypt method default
	DTEncryptionMethod enc = new DTEncryptionMethod();
	enc = datastore.createQuery(DTEncryptionMethod.class).filter("encryptClass =", "it.marcoberri.dockitech.security.Base64Security").get();
	
	final DTSecurityGroup groupAdmin = new DTSecurityGroup(enc);
	groupAdmin.setEncryptClass(enc);
	groupAdmin.setTitle("ADMIN");
	datastore.save(groupAdmin);
	
	final DTSecurityUser userAdmin = new DTSecurityUser(enc);
	userAdmin.setEncryptClass(enc);
	userAdmin.setNickname("admin");
	userAdmin.setPassword("admin123!");
	userAdmin.setName("Global Admin System");
	userAdmin.setSurname("Dockitech Software");
	userAdmin.addSecurityGroup(groupAdmin);
	datastore.save(userAdmin);

	client.addSecurityGroup(groupAdmin);
	client.setEncryptClass(enc);
	datastore.save(client);

	
	
	
	return client;
    }

    @Override
    public void dropWorld() {

	//TODO
	if (datastore == null) {
	    initAdapter(null);
	}
	
	datastore.getMongo().dropDatabase("test");
	
	
	
    }
    
    public DTDocument addDocument(DTDocument doc){

	if (datastore == null) {
	    initAdapter(null);
	}
	
	if(doc.getByteFile() != null && doc.getFile() != null) {
	    final GridFSInputFile gfsFile = gridFS.createFile(doc.getByteFile());
	    gfsFile.setFilename(doc.getFileNameCrypt());
	    gfsFile.setContentType(doc.getContentTypeCrypt());
	    gfsFile.save();
	    doc.setFileId(new ObjectId(gfsFile.getId().toString()));
	}
	
	datastore.save(doc);
	
	return doc;
	
    }
    
    @Override
    public DTSecurityUser getUserByNick(String nickname){
	final DTSecurityUser user = new DTSecurityUser(getEncryptMethodByClass());
	user.setEncryptClass(getEncryptMethodByClass());
	user.setNickname(nickname);
	return datastore.createQuery(DTSecurityUser.class).filter(FieldsName.SECURITYUSER_NICKNAME + " = ", user.getNicknameCrypt()).get();
    }

}
