package it.marcoberri.dockitech;

import junit.framework.Assert;
import it.marcoberri.adapter.MongoAdapter;
import it.marcoberri.dockitech.model.DTClient;
import it.marcoberri.dockitech.model.DTDocument;

import org.junit.Test;

public class ClientTest {

    @Test
    public void addDocument(){
	
	MongoAdapter adapter = new MongoAdapter();
	final DTClient client = new DTClient();
	client.setTitle("WORLD");
	DTClient result = adapter.createWorld(client);
	Assert.assertNotNull(result);	
	
	
	
	DTDocument doc = new DTDocument();
	doc.setEncryptClass(client.getEncryptClass());
	doc.setTitle("first Document data");
	doc = adapter.addDocument(doc);
	
	Assert.assertNotNull(doc);
	
	//adapter.dropWorld();
    }
    
    
    
    
}
