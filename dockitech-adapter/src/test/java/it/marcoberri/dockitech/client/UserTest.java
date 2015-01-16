package it.marcoberri.dockitech.client;

import it.marcoberri.dockitech.adapter.MongoAdapter;
import it.marcoberri.dockitech.model.DTClient;
import it.marcoberri.dockitech.model.DTDocument;
import it.marcoberri.dockitech.model.DTSecurityUser;
import it.marcoberri.dockitech.model.DTText;
import it.marcoberri.dockitech.model.DTToken;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

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

	doc.setTitle(new DTText(client, title));
	doc.setDescription(new DTText(client, description));

	final File file = new File(this.getClass().getResource("/sunrise.jpeg").getPath());
	doc.setFile(file);

	Path path = Paths.get(file.getAbsolutePath());
	byte[] data = Files.readAllBytes(path);

	doc.setByteFile(data);

	final DTDocument docResult = adapter.addDocument(doc);
	Assert.assertNotNull("doc is null", docResult);

	final DTDocument resultFindDoc = adapter.getDocumentByTitle(client, title);
	Assert.assertNotNull("doc retirved is null", resultFindDoc);

	Assert.assertTrue("title not match" + title + " !=" + resultFindDoc.getTitle().getValueFromDecryptKey(client.getDefaultLang()), title.equals(resultFindDoc.getTitle().getValueFromDecryptKey(client.getDefaultLang())));

	Assert.assertTrue("description not match" + description + " !=" + resultFindDoc.getDescription().getValueFromDecryptKey(client.getDefaultLang()), description.equals(resultFindDoc.getDescription().getValueFromDecryptKey(client.getDefaultLang())));

    }

    @Test
    public void addMassiveDocument() throws IOException {

	adapter.getSession();

	final String title = "first document title test";
	final String description = "first document title test";

	final DTSecurityUser user = adapter.getUserByNick(client, "admin");
	Assert.assertNotNull(user);

	final File file = new File(this.getClass().getResource("/sunrise.jpeg").getPath());
	Path path = Paths.get(file.getAbsolutePath());
	byte[] data = Files.readAllBytes(path);

	for (int i = 0; i < 100; i++) {

	    final String title_ext = title + " - " + i;
	    final String description_ext = description + " - " + i;

	    final DTDocument doc = new DTDocument(client);
	    doc.addSecurityUser(user);
	    doc.setSecurityGroup(doc.getSecurityGroup());
	    doc.setTitle(new DTText(client, title_ext));
	    doc.setDescription(new DTText(client, description + " - " + i));
	    doc.setFile(file);
	    doc.setByteFile(data);

	    final DTDocument docResult = adapter.addDocument(doc);
	    Assert.assertNotNull("doc is null", docResult);

	    final DTDocument resultFindDoc = adapter.getDocumentByTitle(client, title_ext);
	    Assert.assertNotNull("doc retirved is null", resultFindDoc);

	    Assert.assertTrue("title not match" + title_ext + " !=" + resultFindDoc.getTitle().getValueFromDecryptKey(client.getDefaultLang()), title_ext.equals(resultFindDoc.getTitle().getValueFromDecryptKey(client.getDefaultLang())));

	    Assert.assertTrue("description not match" + description_ext + " !=" + resultFindDoc.getDescription().getValueFromDecryptKey(client.getDefaultLang()), description_ext.equals(resultFindDoc.getDescription().getValueFromDecryptKey(client.getDefaultLang())));
	}

    }

    @Test
    public void autenticate() throws IOException {

	adapter.getSession();

	final DTClient clientLoaded = adapter.getClientByTitle("WORLD");
	Assert.assertNotNull(clientLoaded);

	final DTToken tokenTrue = adapter.autenticate(clientLoaded, "admin", "admin123!");
	Assert.assertNotNull("token is null", tokenTrue);

	final DTToken tokenFalse = adapter.autenticate(clientLoaded, "admadasdin", "admin12asd3!");
	Assert.assertNull("token is null", tokenFalse);

	final DTToken tokenTrue2 = adapter.autenticate(clientLoaded, "admin", "admin123!");
	Assert.assertNotNull("token2 is null", tokenTrue2);

	final String firstToken = tokenTrue.getId().toString();
	final String secondToken = tokenTrue2.getId().toString();

	Assert.assertTrue("tokens not match", firstToken.equals(secondToken));

    }

}
