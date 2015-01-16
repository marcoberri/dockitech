package it.marcoberri.dockitech;

import it.marcoberri.dockitech.adapter.MongoAdapter;
import it.marcoberri.dockitech.model.DTClient;

import org.junit.Assert;
import org.junit.Test;

public class AdminTest {

    MongoAdapter adapter = new MongoAdapter();
    
    @Test
    public void generatewordAndDropUniverse() {
	adapter.getSession();
	adapter.createUniverse();
	
	final DTClient client = new DTClient("WORLD");
	final DTClient result = adapter.createWorld(client);

	Assert.assertNotNull(result);
	
	final DTClient client1 = new DTClient("ITALY");
	final DTClient result1 = adapter.createWorld(client1);

	Assert.assertNotNull(result1);
	
	final DTClient client2 = new DTClient("USA");
	final DTClient result2 = adapter.createWorld(client2);

	Assert.assertNotNull(result2);
	
	adapter.dropUniverse();

    }

}
