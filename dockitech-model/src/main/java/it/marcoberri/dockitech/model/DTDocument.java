package it.marcoberri.dockitech.model;

import it.marcoberri.dockitech.resources.CollectionNames;
import it.marcoberri.dockitech.resources.FieldsName;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.Oneway;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.PostPersist;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;
import org.mongodb.morphia.annotations.Version;

@Entity(value = CollectionNames.COLLECTION_DOCUMENT, noClassnameStored = true)
public class DTDocument extends DTBase {

    public DTDocument() {
	super();
    }

    public DTDocument(DTClient client) {
	this.client = client;
    }

    @Reference(FieldsName.DOC_TITLE)
    private DTText title;

    @Reference(FieldsName.DOC_DESCRIPTION)
    private DTText description;

    @Property(FieldsName.DOC_TAGS)
    private List<DTTag> tags;

    @Property(FieldsName.DOC_LAST_SYSTEM_UPDATE)
    private Date lastsystemUpdate = new Date();

    @Reference(FieldsName.DOC_CLIENT)
    private DTClient client;

    @Reference(FieldsName.DOC_CREATOR)
    private DTSecurityUser creator;

    @Property(FieldsName.DOC_CREATOR_DATE)
    private Date cretorDate = new Date();

    @Reference(FieldsName.DOC_LAST_MODIFIER)
    private DTSecurityUser lastModifier;

    @Property(FieldsName.DOC_LAST_MODIFIER_DATE)
    private Date lastModifierDate = new Date();

    @Reference(FieldsName.DOC_SECURITY_GROUP)
    private List<DTSecurityGroup> securityGroup;

    @Reference(FieldsName.DOC_SECURITY_USER)
    private List<DTSecurityUser> securityUser;

    @Property(FieldsName.DOC_PUBLISH)
    private boolean publish = false;

    @Reference(FieldsName.DOC_PUBLISHER)
    private DTSecurityUser publisher;

    @Property(FieldsName.DOC_PUBLISHER_DATE)
    private Date publisherDate = new Date();

    @Property(FieldsName.DOC_CONTENT_TYPE)
    private String contentType;

    @Property(FieldsName.DOC_FILE_NAME)
    private String fileName;

    @Property(FieldsName.DOC_FILE_ID)
    private ObjectId fileId;

    @Property(FieldsName.DOC_LENGTH)
    private long length;

    @Transient
    private File file;

    @Transient
    private byte[] byteFile;

    @Version(FieldsName.DOC_VERSION)
    private Long version;

    @Property(FieldsName.DOC_HISTORY)
    private List<DTHistory> history;

    public String getFileName() {
	return decrypt(fileName, this.client);
    }

    public String getFileNameEncrypt() {
	return fileName;
    }

    public void setFileName(String fileName) {
	this.fileName = encrypt(fileName, this.client);
    }

    public void setFileNameEnrypt(String fileName) {
	this.fileName = fileName;
    }

    public void addSecurityGroup(DTSecurityGroup group) {
	if (this.securityGroup == null)
	    securityGroup = new ArrayList<DTSecurityGroup>();
	securityGroup.add(group);
    }

    public List<DTSecurityGroup> getSecurityGroup() {
	return securityGroup;
    }

    public String getContentType() {
	return decrypt(this.contentType, this.client);
    }

    public String getContentTypeEncrypt() {
	return this.contentType;
    }

    public void setContentType(String contentType) {
	this.contentType = encrypt(contentType, this.client);
    }

    public void setSecurityGroup(List<DTSecurityGroup> securityGroup) {
	this.securityGroup = securityGroup;
    }

    public void addSecurityUser(DTSecurityUser user) {
	if (this.securityUser == null)
	    securityUser = new ArrayList<DTSecurityUser>();
	securityUser.add(user);
    }

    public List<DTSecurityUser> getSecurityUser() {
	return securityUser;
    }

    public void setSecurityUser(List<DTSecurityUser> securityUser) {
	this.securityUser = securityUser;
    }

    public byte[] getByteFile() {
	return decrypt(byteFile, this.client);
    }

    public void setByteFile(byte[] byteFile) {
	this.byteFile = encrypt(byteFile, this.client);
    }

    public void setByteFileEncrypt(byte[] byteFile) {
	this.byteFile = byteFile;
    }

    public File getFile() {
	return file;
    }

    public void setFile(File file) {
	this.file = file;
	setFileName(file.getName());
	try {
	    setContentType(Files.probeContentType(file.toPath()));
	} catch (final IOException e) {
	    // TODO Log
	}
    }

    public Date getCretorDate() {
	return cretorDate;
    }

    public void addHistory(ObjectId id, double v) {
	final DTHistory h = new DTHistory();
	h.setFileId(id.toString());
	h.setVersion(v);
	if (history == null)
	    history = new ArrayList<DTHistory>();
	history.add(h);
    }

    public void setCretorDate(Date cretorDate) {
	this.cretorDate = cretorDate;
    }

    public boolean isPublish() {
	return publish;
    }

    public void setPublish(boolean publish) {
	this.publish = publish;
    }

    public DTSecurityUser getPublisher() {
	return publisher;
    }

    public void setPublisher(DTSecurityUser publisher) {
	this.publisher = publisher;
    }

    public Date getPublisherDate() {
	return publisherDate;
    }

    public void setPublisherDate(Date publisherDate) {
	this.publisherDate = publisherDate;
    }

    public ObjectId getFileId() {
	return fileId;
    }

    public void setFileId(ObjectId fileId) {
	if (this.fileId != null) {
	    //addHistory(this.fileId, this.version);
	    //TODO
	}
	// this.version += 1;
	this.fileId = fileId;
    }

    public Long getVersion() {
	return version;
    }

    public void setVersion(Long version) {
	this.version = version;
    }

    public List<DTHistory> getHistory() {
	return history;
    }

    public void setHistory(List<DTHistory> history) {
	this.history = history;
    }

    @PrePersist
    void prePersist() {
	lastsystemUpdate = new Date();
    }

    public Date getLastsystemUpdate() {
	return lastsystemUpdate;
    }

    public void setLastsystemUpdate(Date lastsystemUpdate) {
	this.lastsystemUpdate = lastsystemUpdate;
    }

    public DTClient getClient() {
	return client;
    }

    public void setClient(DTClient client) {
	this.client = client;
    }

    public DTSecurityUser getCreator() {
	return creator;
    }

    public void setCreator(DTSecurityUser creator) {
	this.creator = creator;
    }

    public DTSecurityUser getLastModifier() {
	return lastModifier;
    }

    public void setLastModifier(DTSecurityUser lastModifier) {
	this.lastModifier = lastModifier;
    }

    public Date getLastModifierDate() {
	return lastModifierDate;
    }

    public void setLastModifierDate(Date lastModifierDate) {
	this.lastModifierDate = lastModifierDate;
    }

    public List<DTTag> getTags() {
	return tags;
    }

    public void setTags(List<DTTag> tags) {
	this.tags = tags;
    }

    public void addTag(String value) {
	addTag(value, null);
    }

    public void addTag(String value, Integer size) {

	final DTTag t = new DTTag(client);
	t.setValue(value);
	t.setSize(size);
	if (this.tags == null)
	    this.tags = new ArrayList<DTTag>();
	this.tags.add(t);
    }

    public void setContentTypeEncrypt(String contentType) {
	this.contentType = contentType;

    }

    public void setFileLength(long length) {
	this.length = length;

    }

    public DTText getTitle() {
	return title;
    }

    public void setTitle(DTText title) {
	this.title = title;
    }

    public DTText getDescription() {
	return description;
    }

    public void setDescription(DTText description) {
	this.description = description;
    }

    public long getLength() {
	return length;
    }

    public void setLength(long length) {
	this.length = length;
    }

}
