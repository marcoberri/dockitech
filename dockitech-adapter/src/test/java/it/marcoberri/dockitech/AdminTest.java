package it.marcoberri.dockitech;

import junit.framework.Assert;
import it.marcoberri.adapter.MongoAdapter;
import it.marcoberri.dockitech.model.DTClient;

import org.junit.Test;

public class AdminTest {

    @Test
    public void generateWorld(){
	
	MongoAdapter adapter = new MongoAdapter();
	final DTClient client = new DTClient();
	client.setTitle("WORLD");
	DTClient result = adapter.createWorld(client);
	Assert.assertNotNull(result);
	
    }
    
}
