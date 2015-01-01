package it.marcoberri.dockitech;

import it.marcoberri.dockitech.adapter.MongoAdapter;
import it.marcoberri.dockitech.model.DTClient;

import org.junit.Assert;
import org.junit.Test;

public class AdminTest {

	@Test
	public void generateAndDeleteWorld() {
		MongoAdapter adapter = new MongoAdapter();
		final DTClient client = new DTClient();
		client.setTitle("WORLD");
		final DTClient result = adapter.createWorld(client);
		Assert.assertNotNull(result);
		adapter.dropWorld();

	}

}
