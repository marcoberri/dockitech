package it.marcoberri.dockitech.model;

import it.marcoberri.dockitech.resources.CollectionNames;
import it.marcoberri.dockitech.resources.FieldsName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;
import org.mongodb.morphia.utils.IndexDirection;

@Entity(value = CollectionNames.PERMISSION_GROUP, noClassnameStored = true)
public class DTPermissionGroup extends DTBase {

    @Transient
    private DTEncryptionMethod encryptClass;

    @Reference(FieldsName.PERMISSION_GROUP_USER)
    @Indexed
    private DTSecurityGroup group;
    
    @Reference(FieldsName.PERMISSION_GROUP_CLIENT)
    @Indexed
    private DTClient client;
    
    @Property(FieldsName.PERMISSION_GROUP_OBJECT)
    private String object;
    
    @Property(FieldsName.PERMISSION_GROUP_OBJECT_ID)
    private ObjectId objectId;
    
    @Property(FieldsName.PERMISSION_GROUP_ACTION)
    private String action;
    
    @Property(FieldsName.PERMISSION_GROUP_AUTH)
    private boolean authorized;
    
    @Property(FieldsName.PERMISSION_GROUP_TYPE)
    private String type = "USER";
    
    

    public DTEncryptionMethod getEncryptClass() {
        return encryptClass;
    }

    public void setEncryptClass(DTEncryptionMethod encryptClass) {
        this.encryptClass = encryptClass;
    }


    public DTClient getClient() {
        return client;
    }

    public void setClient(DTClient client) {
        this.client = client;
    }

    public String getObject() {
	return decrypt(object, client);
    }

    public void setObject(String object) {
	this.object = object;
        
    }

    public ObjectId getObjectId() {
	return objectId;
    }

    public void setObjectId(ObjectId objectId) {
	this.objectId = objectId;
        
    }

    public String getAction() {
	return decrypt(action, client);
    }

    public void setAction(String action) {
	this.action = encrypt(action, this.client);
    }

    public boolean isAuthorized() {
        return authorized;
    }

    public void setAuthorized(boolean authorized) {
        this.authorized = authorized;
    }

    public String getType() {
	return decrypt(type, client);
    }

    public void setType(String type) {
	this.type = encrypt(type, this.client);
    }

    public DTPermissionGroup() {
	super();
    }

    public DTPermissionGroup(DTClient client) {
	this.client = client;
    }

}
