package it.marcoberri.dockitech.model;

import it.marcoberri.dockitech.resources.CollectionNames;
import it.marcoberri.dockitech.resources.FieldsName;

import java.util.HashMap;
import java.util.Map;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;

@Entity(value = CollectionNames.COLLECTION_LANGUAGE, noClassnameStored = true)
public class DTLanguage extends DTBase {

	public DTLanguage(DTClient client) {
		this.client = client;
	}

	public DTLanguage(DTClient client, String language, boolean enable) {
		this.client = client;
		this.language = language;
		this.enable = enable;

	}

	public DTLanguage() {
		super();
	}

	@Reference(FieldsName.LANGUAGE_CLIENT)
	private DTClient client;

	@Property(FieldsName.LANGUAGE_LANG)
	private String language;

	@Property(FieldsName.LANGUAGE_ENABLE)
	private boolean enable;
	
}
