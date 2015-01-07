package it.marcoberri.dockitech.model;

import it.marcoberri.dockitech.resources.CollectionNames;
import it.marcoberri.dockitech.resources.FieldsName;

import java.util.HashMap;
import java.util.Map;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity(value = CollectionNames.COLLECTION_TEXT, noClassnameStored = true)
public class DTText extends DTBase {

    public DTText(DTClient client) {
	this.client = client;
    }

    public DTText(DTClient client, String lang, String value) {
	this.client = client;
	this.putValue(lang, value);

    }

    public DTText(DTClient client, String value) {
	this.client = client;
	this.putValue(client.getDefaultLang(), value);
    }

    public DTText() {
	super();
    }

    @Reference(FieldsName.TEXT_CLIENT)
    private DTClient client;

    @Reference(FieldsName.TEXT_DOCUMENT)
    private DTDocument document;

    public DTDocument getDocument() {
	return document;
    }

    public void setDocument(DTDocument document) {
	this.document = document;
    }

    public Map<String, String> getMap() {
	return map;
    }

    public void setMap(Map<String, String> map) {
	this.map = map;
    }

    @Property(FieldsName.TEXT_VALUE)
    private Map<String, String> map;

    public DTClient getClient() {
	return client;
    }

    public void setClient(DTClient client) {
	this.client = client;
    }

    public void putValue(String value) {
	putValue(null, value);
    }

    public void putValue(String lang, String value) {

	if (lang == null && client != null) {
	    lang = client.getDefaultLang();
	}

	if (map == null)
	    map = new HashMap<String, String>();
	lang = lang.toUpperCase();

	map.put(encrypt(lang, client), encrypt(value, client));
    }

    public String getValueFromEncryptKey(String key) {
	key = key.toUpperCase();
	return decrypt(map.get(key), client);
    }

    public String getValueFromDecryptKey(String key) {
	key = key.toUpperCase();
	return decrypt(map.get(encrypt(key, client)), client);
    }

    public String getValueEncryptFromEncryptKey(String key) {

	return map.get(key);
    }

    public String getValueEncryptFromDecryptKey(String key) {
	key = key.toUpperCase();
	return map.get(encrypt(key, client));
    }

    public String getLangEncrypt(String lang) {
	lang = lang.toUpperCase();
	return encrypt(lang, client);
    }

}
