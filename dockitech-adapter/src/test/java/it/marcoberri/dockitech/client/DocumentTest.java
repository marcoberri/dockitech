package it.marcoberri.dockitech.client;

import it.marcoberri.adapter.MongoAdapter;
import it.marcoberri.dockitech.model.DTClient;
import it.marcoberri.dockitech.model.DTDocument;
import it.marcoberri.dockitech.model.DTSecurityUser;
import it.marcoberri.dockitech.model.DTText;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class DocumentTest {

	@Test
	public void addDocument() throws IOException {

		final MongoAdapter adapter = new MongoAdapter();
		adapter.dropWorld();

		final String title = "first document title test";
		final String description = "first document title test";

		DTClient client = new DTClient();
		client.setTitle("WORLD");
		client = adapter.createWorld(client);
		Assert.assertNotNull(client);

		final DTSecurityUser user = adapter.getUserByNick(client, "admin");
		Assert.assertNotNull(user);

		final DTDocument doc = new DTDocument(client);
		doc.addSecurityUser(user);
		doc.setSecurityGroup(doc.getSecurityGroup());

		doc.setTitle(new DTText(client, title));
		doc.setDescription(new DTText(client, description));

		final File file = new File(this.getClass().getResource("/sunrise.jpeg")
				.getPath());
		doc.setFile(file);

		Path path = Paths.get(file.getAbsolutePath());
		byte[] data = Files.readAllBytes(path);

		doc.setByteFile(data);

		final DTDocument docResult = adapter.addDocument(doc);
		Assert.assertNotNull("doc is null", docResult);

		final DTDocument resultFindDoc = adapter.getDocumentByTitle(client,
				title);
		Assert.assertNotNull("doc retirved is null", resultFindDoc);

		Assert.assertTrue(
				"title not match"
						+ title
						+ " !="
						+ resultFindDoc.getTitle().getValueFromDecryptKey(
								client.getDefaultLang()),
				title.equals(resultFindDoc.getTitle().getValueFromDecryptKey(
						client.getDefaultLang())));

		Assert.assertTrue(
				"description not match"
						+ description
						+ " !="
						+ resultFindDoc
								.getDescription()
								.getValueFromDecryptKey(client.getDefaultLang()),
				description.equals(resultFindDoc.getDescription()
						.getValueFromDecryptKey(client.getDefaultLang())));

		adapter.dropWorld();
	}

}
