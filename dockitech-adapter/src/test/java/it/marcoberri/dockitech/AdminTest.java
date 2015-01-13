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
	final DTClient client = new DTClient("WORLD");
	final DTClient result = adapter.createWorld(client);
	Assert.assertNotNull(result);
	adapter.dropUniverse();

    }

}
