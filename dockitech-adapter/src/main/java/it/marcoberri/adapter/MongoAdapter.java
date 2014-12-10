package it.marcoberri.adapter;

import it.marcoberri.dockitech.model.DTClient;
import it.marcoberri.dockitech.model.DTEncryptionMethod;
import it.marcoberri.dockitech.model.DTSecurityGroup;
import it.marcoberri.dockitech.model.DTSecurityUser;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Properties;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

public class MongoAdapter extends AbstractAdapter {

    private Datastore datastore;

    @Override
    public void initAdapter(Properties p) {

	// TODO

	try {
	    MongoClient mongoClient = new MongoClient("192.168.1.89");
	    final Morphia morphia = new Morphia();
	    morphia.mapPackage("it.marcoberri.dockitech.model");
	    datastore = morphia.createDatastore(mongoClient, "test");
	} catch (UnknownHostException e) {
	    e.printStackTrace();
	}
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
	
	final DTSecurityGroup groupAdmin = new DTSecurityGroup();
	groupAdmin.setTitle("ADMIN");
	datastore.save(groupAdmin);
	
	final DTSecurityUser userAdmin = new DTSecurityUser();
	userAdmin.setEncryptClass(enc);
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

	//TODO
	if (datastore == null) {
	    initAdapter(null);
	}
	
    }

}
