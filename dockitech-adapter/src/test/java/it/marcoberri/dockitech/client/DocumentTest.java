package it.marcoberri.dockitech.client;

import it.marcoberri.dockitech.adapter.MongoAdapter;
import it.marcoberri.dockitech.model.DTClient;
import it.marcoberri.dockitech.model.DTDocument;
import it.marcoberri.dockitech.model.DTSecurityUser;
import it.marcoberri.dockitech.model.DTText;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DocumentTest {

    final MongoAdapter adapter = new MongoAdapter();
    DTClient client;

    @After
    public void after() {
	adapter.dropUniverse();
    }

    @Before
    public void before() {
	adapter.createUniverse();
	client = new DTClient("WORLD");
	client = adapter.createWorld(client);
	Assert.assertNotNull(client);
    }

    @Test
    public void addDocument() throws IOException {

	adapter.getSession();

	final String title = "first document title test";
	final String description = "first document title test";

	final DTSecurityUser user = adapter.getUserByNick(client, "admin");
	Assert.assertNotNull(user);

	final DTDocument doc = new DTDocument(client);
	doc.addSecurityUser(user);
	doc.setSecurityGroup(doc.getSecurityGroup());

	doc.setTitle(new DTText(client, "EN", title));
	doc.setDescription(new DTText(client, "EN", description));

	final File file = new File(this.getClass().getResource("/sunrise.jpeg").getPath());
	doc.setFile(file);

	Path path = Paths.get(file.getAbsolutePath());
	byte[] data = Files.readAllBytes(path);

	doc.setByteFile(data);

	final DTDocument docResult = adapter.addDocument(doc);
	Assert.assertNotNull("doc is null", docResult);

	final DTDocument resultFindDoc = adapter.getDocumentByTitle(client, title, "EN");
	Assert.assertNotNull("doc retirved is null", resultFindDoc);

	Assert.assertTrue("title not match" + title + " !=" + resultFindDoc.getTitle().getValueFromDecryptKey("EN"), title.equals(resultFindDoc.getTitle().getValueFromDecryptKey("EN")));

	Assert.assertTrue("description not match" + description + " !=" + resultFindDoc.getDescription().getValueFromDecryptKey("EN"), description.equals(resultFindDoc.getDescription().getValueFromDecryptKey("EN")));

    }

}
