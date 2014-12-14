package it.marcoberri.dockitech;

import it.marcoberri.adapter.MongoAdapter;
import it.marcoberri.dockitech.model.DTClient;
import it.marcoberri.dockitech.model.DTDocument;
import it.marcoberri.dockitech.model.DTSecurityUser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;

import org.junit.Test;

public class ClientTest {

    @Test
    public void addDocument() throws IOException{
	
	

	
	
	final MongoAdapter adapter = new MongoAdapter();
	adapter.dropWorld();
	
	final DTClient client = new DTClient();
	client.setTitle("WORLD");
	DTClient result = adapter.createWorld(client);
	Assert.assertNotNull(result);	
	
	final DTSecurityUser user = adapter.getUserByNick("admin");
	Assert.assertNotNull(user);	
	
	DTDocument doc = new DTDocument(result.getEncryptClass());
	
	doc.addSecurityUser(user);
	doc.setSecurityGroup(doc.getSecurityGroup());

	
	doc.setTitle("first Document data");
	File file = new File(this.getClass().getResource("/sunrise.jpeg").getPath());
	doc.setFile(file);
	
	Path path = Paths.get(file.getAbsolutePath());
	byte[] data = Files.readAllBytes(path);
	
	doc.setByteFile(data);
	doc = adapter.addDocument(doc);	
	
	
	
	Assert.assertNotNull(doc);
	
	//adapter.dropWorld();
    }
    
    
    
    
}
