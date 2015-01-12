package it.marcoberri.dockitech.adapter;

import it.marcoberri.dockitech.model.DTClient;
import it.marcoberri.dockitech.model.DTDocument;
import it.marcoberri.dockitech.model.DTEncryptionMethod;
import it.marcoberri.dockitech.model.DTLanguage;
import it.marcoberri.dockitech.model.DTSecurityGroup;
import it.marcoberri.dockitech.model.DTSecurityUser;
import it.marcoberri.dockitech.model.DTText;
import it.marcoberri.dockitech.model.DTToken;
import it.marcoberri.dockitech.resources.FieldsName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;

import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class MongoAdapter extends AbstractAdapter {

    private Datastore datastore;
    private GridFS gridFS;
    private String dbName;

    static Logger log = LogManager.getLogger(MongoAdapter.class);

    // set default language
    private final List<String> languagesDefault = new ArrayList<String>() {
	private static final long serialVersionUID = 1L;

	{
	    add("IT");
	    add("EN");
	    add("FR");
	    add("DE");
	}
    };

    // setting enc Method
    private final ArrayList<String> encMethod = new ArrayList<String>() {
	private static final long serialVersionUID = 1L;

	{
	    add("it.marcoberri.dockitech.security.NullSecurity");
	    add("it.marcoberri.dockitech.security.Base64Security");
	    add("it.marcoberri.dockitech.security.CipherSecurity");
	}
    };

    @Override
    public AbstractAdapter getSession() {

	if (datastore == null) {
	     datastore = MongoDBHelper.INSTANCE.getDatastore();
	     gridFS = MongoDBHelper.INSTANCE.getGridFS();
	     dbName = MongoDBHelper.INSTANCE.getDbName();
	}

	return this;
    }

    

    public DTDocument addDocument(DTDocument doc) {

	if (datastore == null) {
	    getSession();
	}

	if (doc.getByteFile() != null && doc.getFile() != null) {
	    final GridFSInputFile gfsFile = this.gridFS.createFile(doc.getByteFile());
	    gfsFile.setFilename(doc.getFileNameEncrypt());
	    gfsFile.setContentType(doc.getContentTypeEncrypt());
	    gfsFile.save();
	    doc.setFileId(new ObjectId(gfsFile.getId().toString()));
	}

	final DTText title = doc.getTitle();
	title.setClient(doc.getClient());
	datastore.save(title);

	final DTText desc = doc.getDescription();
	desc.setClient(doc.getClient());
	datastore.save(desc);

	datastore.save(doc);

	title.setDocument(doc);
	datastore.save(title);

	desc.setDocument(doc);
	datastore.save(desc);

	datastore.save(doc);

	return doc;

    }

    
    public DTClient createWorld(String client) {
	DTClient clientObj = new DTClient(client);
	return createWorld(clientObj);
    }
    @Override
    public DTClient createWorld(DTClient client) {

	if (datastore == null) {
	    getSession();
	}

	for (String s : encMethod) {
	    final DTEncryptionMethod enc = new DTEncryptionMethod();
	    enc.setEncryptClass(s);
	    datastore.save(enc);
	}

	// load encrypt method default
	final DTEncryptionMethod enc = datastore.createQuery(DTEncryptionMethod.class).filter("encryptClass =", "it.marcoberri.dockitech.security.Base64Security").get();
	client.setEncryptClass(enc);
	client.setEncryptKey("secrekeysecrekey1234567890!!");
	datastore.save(client);

	final DTSecurityGroup groupAdmin = new DTSecurityGroup(client);
	groupAdmin.setTitle("ADMIN");
	datastore.save(groupAdmin);

	final DTSecurityUser userAdmin = new DTSecurityUser(client);
	userAdmin.setNickname("admin");
	userAdmin.setTokenTypeUser(true);
	userAdmin.setPassword("admin123!");
	userAdmin.setName("Global Admin System");
	userAdmin.setSurname("Dockitech Software");

	userAdmin.addSecurityGroup(groupAdmin);
	datastore.save(userAdmin);

	client.addSecurityGroup(groupAdmin);

	userAdmin.setClient(client);
	datastore.save(userAdmin);

	datastore.save(client);

	for (String l : languagesDefault) {
	    DTLanguage lang = new DTLanguage();
	    lang.setClient(client);
	    lang.setLanguage(l);
	    lang.setEnable(true);
	    datastore.save(lang);
	}

	return client;
    }

    @Override
    public void dropWorld() {

	// TODO
	if (datastore == null) {
	    getSession();
	}

	datastore.getMongo().dropDatabase(this.dbName);

    }

    @Override
    public DTDocument getDocumentByTitle(DTClient client, String titlePlain) {
	return getDocumentByTitle(client, titlePlain, false);
    }

    @Override
    public DTDocument getDocumentByTitle(DTClient client, String titlePlain, boolean withFile) {
	return getDocumentByTitle(client, titlePlain, null, withFile);
    }

    @Override
    public DTDocument getDocumentByTitle(DTClient client, String titlePlain, String lang) {

	return getDocumentByTitle(client, titlePlain, lang, false);
    }

    @Override
    public DTDocument getDocumentByTitle(DTClient client, String titlePlain, String lang, boolean withFile) {

	if (datastore == null) {
	    getSession();
	}

	if (titlePlain == null) {
	    log.error("getDocumentByTitle --> title is null");
	    return null;
	    // TODO exception
	}

	if (lang == null){
	    log.warn("MongoAdapter.getDocumentByTitle --> Lang is null set to default:" + client.getDefaultLang());
	    lang = client.getDefaultLang();
	}
	
	final DTDocument docFilter = new DTDocument(client);
	docFilter.setTitle(new DTText(client, titlePlain));
	lang = docFilter.encrypt(lang, client);

	final Query<DTText> query = datastore.createQuery(DTText.class);
	query.filter(FieldsName.TEXT_CLIENT + " = ", client).filter(FieldsName.TEXT_VALUE + "." + lang + "=", docFilter.getTitle().getValueEncryptFromEncryptKey(lang));

	log.debug("getDocumentByTitle.query -->" + query.toString());

	DTText title = query.get();

	log.debug("id -->" + title.getDocument().getId().toString());

	final DTDocument docResult = datastore.createQuery(DTDocument.class).filter("_id = ", title.getDocument().getId()).get();

	if (withFile) {
	    final GridFSDBFile file = this.gridFS.findOne(docResult.getFileId());
	    docResult.setFileNameEnrypt(file.getFilename());
	    try {
		docResult.setByteFileEncrypt(IOUtils.toByteArray(file.getInputStream()));
	    } catch (final IOException e) {
		log.error("MongoAdapter.getDocumentByTitle -->", e);
	    }

	    docResult.setContentTypeEncrypt(file.getContentType());
	    docResult.setFileLength(file.getLength());

	}
	return docResult;

    }

    @Override
    public DTSecurityUser getUserByNick(DTClient client, String nickname) {
	final DTSecurityUser user = new DTSecurityUser(client);
	user.setNickname(nickname);
	return datastore.createQuery(DTSecurityUser.class).filter(FieldsName.SECURITYUSER_NICKNAME + " = ", user.getNicknameEncrypt()).filter(FieldsName.SECURITYUSER_CLIENT + " = ", client).get();
    }

   

    @Override
    public DTText saveText(DTClient client, String text) {
	return saveText(client, null, text);
    }

    @Override
    public DTText saveText(DTClient client, String lang, String text) {

	if (isLangEnable(client, lang) == null) {
	    log.error("Lang [" + lang + "] for client [" + client.getTitle() + "] not found or not enable");
	    return null;
	}

	final DTText textObject = new DTText(client, lang, text);
	datastore.save(textObject);
	return textObject;
    }

    @Override
    public DTLanguage isLangEnable(DTClient client, String lang) {
	return datastore.createQuery(DTLanguage.class).filter(FieldsName.LANGUAGE_CLIENT + " = ", client.getTitle()).filter(FieldsName.LANGUAGE_ENABLE + " = ", true).filter(FieldsName.LANGUAGE_LANG + " = ", lang.toUpperCase()).get();
    }

    @Override
    public DTToken autenticate(DTClient client, String nickname, String password) {

	final DTSecurityUser user = getUserByNick(client, nickname);

	if (user == null)
	    return null;

	if (!user.verifyPasswordPlain(password))
	    return null;

	return saveToken(client, user);

    }

    @Override
    public DTToken autenticate(String client, String nickname, String password) {
	final DTClient dtclient = this.getClientByTitle(client);
	return autenticate(dtclient, nickname, password);
    }

    @Override
    public DTToken autenticate(DTClient client, String token) {
	// TODO Auto-generated method stub
	return null;
    }

    public DTToken autenticate(String token) {
	return autenticate(null, token);
    }

    @Override
    public DTToken saveToken(DTClient client, DTSecurityUser user) {

	final DTToken verifyToken = getToken(client, user);

	if (verifyToken != null) {
	    return verifyToken;
	}

	final DTToken token = new DTToken();
	token.setClient(client);
	token.setUser(user);
	datastore.save(token);

	return token;
    }

    public DTToken getToken(DTClient client, DTSecurityUser user) {

	final DTToken verifyToken = datastore.createQuery(DTToken.class).filter(FieldsName.SYSTEM_TOKEN_USER + " = ", user).filter(FieldsName.SYSTEM_TOKEN_CLIENT + " = ", client).filter(FieldsName.SYSTEM_TOKEN_VALID + " = ", true).get();

	if (verifyToken != null) {
	    return verifyToken;
	}

	return null;
    }

    @Override
    public DTClient getClientByTitle(String title) {
	return datastore.createQuery(DTClient.class).filter(FieldsName.CLIENT_TITLE + " = ", title).get();
    }

}
