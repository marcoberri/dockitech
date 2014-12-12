package it.marcoberri.dockitech.model;

import it.marcoberri.dockitech.resources.CollectionNames;
import it.marcoberri.dockitech.resources.FieldsName;

import java.util.Date;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.utils.IndexDirection;

@Entity(value=CollectionNames.SECURITY_GROUP,noClassnameStored = true)
public class DTSecurityGroup extends DTBase {


    public DTSecurityGroup(DTEncryptionMethod encryptClass) {
	super(encryptClass);
    }


    @Indexed(value=IndexDirection.ASC, unique=true, dropDups=true)
    private String title;
    
     @Property(FieldsName.SECURITYGROUP_LAST_SYSTEM_UPDATE)
    private Date lastsystemUpdate = new Date();
    
    
     @Property(FieldsName.SECURITYGROUP_READ)
    private boolean read = true;
    
     @Property(FieldsName.SECURITYGROUP_WRITE)
    private boolean write = true;
    
     @Property(FieldsName.SECURITYGROUP_DELETE)
    private boolean delete = true;
    
    
    @PrePersist 
    void prePersist() {
	lastsystemUpdate = new Date();
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public Date getLastsystemUpdate() {
        return lastsystemUpdate;
    }


    public void setLastsystemUpdate(Date lastsystemUpdate) {
        this.lastsystemUpdate = lastsystemUpdate;
    }


    public boolean isRead() {
        return read;
    }


    public void setRead(boolean read) {
        this.read = read;
    }


    public boolean isWrite() {
        return write;
    }


    public void setWrite(boolean write) {
        this.write = write;
    }


    public boolean isDelete() {
        return delete;
    }


    public void setDelete(boolean delete) {
        this.delete = delete;
    }
    
    
}
