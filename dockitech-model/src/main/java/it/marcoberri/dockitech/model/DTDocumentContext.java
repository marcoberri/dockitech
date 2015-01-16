package it.marcoberri.dockitech.model;

import it.marcoberri.dockitech.resources.CollectionNames;
import it.marcoberri.dockitech.resources.FieldsName;

import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Version;

@Entity(value = CollectionNames.COLLECTION_DOCUMENT_CONTEXT, noClassnameStored = true)
public class DTDocumentContext extends DTBase {

    public DTDocumentContext() {
	super();
    }

    public DTDocumentContext(DTClient client) {
	this.client = client;
    }

    @Reference(FieldsName.DOC_CONTEXT_TITLE)
    private DTText title;

    @Reference(FieldsName.DOC_CONTEXT_PARENT)
    private DTDocumentContext parent;

    @Property(FieldsName.DOC_CONTEXT_TAGS)
    private List<DTTag> tags;

    @Property(FieldsName.DOC_CONTEXT_LAST_SYSTEM_UPDATE)
    private Date lastsystemUpdate = new Date();

    @Reference(FieldsName.DOC_CONTEXT_CLIENT)
    @Indexed
    private DTClient client;

    @Reference(FieldsName.DOC_CONTEXT_CREATOR)
    private DTSecurityUser creator;

    @Property(FieldsName.DOC_CONTEXT_CREATOR_DATE)
    private Date cretorDate = new Date();

    @Reference(FieldsName.DOC_CONTEXT_LAST_MODIFIER)
    private DTSecurityUser lastModifier;

    @Property(FieldsName.DOC_CONTEXT_LAST_MODIFIER_DATE)
    private Date lastModifierDate = new Date();

    @Reference(FieldsName.DOC_CONTEXT_SECURITY_GROUP)
    private List<DTSecurityGroup> securityGroup;

    @Reference(FieldsName.DOC_CONTEXT_SECURITY_USER)
    private List<DTSecurityUser> securityUser;

    @Property(FieldsName.DOC_CONTEXT_PUBLISH)
    private boolean publish = false;

    @Reference(FieldsName.DOC_CONTEXT_PUBLISHER)
    private DTSecurityUser publisher;

    @Property(FieldsName.DOC_CONTEXT_PUBLISHER_DATE)
    private Date publisherDate = new Date();

    @Property(FieldsName.DOC_CONTEXT_SIZE)
    private long size;

    @Property(FieldsName.DOC_CONTEXT_DOCUMENT_SIZE)
    private long docsSize;

    @Property(FieldsName.DOC_CONTEXT_CONTEXT_SIZE)
    private long contextSize;

    @Version(FieldsName.DOC_CONTEXT_VERSION)
    private Long version;

}
