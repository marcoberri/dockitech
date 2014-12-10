package it.marcoberri.dockitech.model;

import it.marcoberri.dockitech.resources.CollectionNames;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.utils.IndexDirection;

@Entity(CollectionNames.SECURITY_GROUP)
public class DTSecurityGroup extends DTBase {


    @Indexed(value=IndexDirection.ASC, unique=true, dropDups=true)
    private String title;
    
    private Date lastsystemUpdate = new Date();
    
    
    private boolean read = true;
    private boolean write = true;
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
