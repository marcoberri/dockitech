package it.marcoberri.dockitech.model;

import it.marcoberri.dockitech.resources.CollectionNames;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Reference;

@Entity(CollectionNames.COLLECTION_DOCUMENT)
public class DTDocument extends DTBase {

    
    private String title;
    
    private List<DTTag> tags;
    
    private Date lastsystemUpdate = new Date();
    
    @Reference
    private DTClient client;
    
    @Reference 
    private DTSecurityUser creator;
    
    private Date cratorDate = new Date();
    
    @Reference 
    private DTSecurityUser lastModifier;
    
    private Date lastModifierDate = new Date();
    
    @Reference
    private List[] DTSecurityGroup;
    
    @Reference
    private List[] DTSecurityUser;
    
    
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

    public Date getCratorDate() {
        return cratorDate;
    }

    public void setCratorDate(Date cratorDate) {
        this.cratorDate = cratorDate;
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

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public List<DTTag> getTags() {
        return tags;
    }
    public void setTags(List<DTTag> tags) {
        this.tags = tags;
    }

 
    public void addTag(String value){
	addTag(value,null);
    }
    
    public void addTag(String value, Integer size){
	
	DTTag t = new DTTag();
	t.setValue(value);
	t.setSize(size);
	if(this.tags == null)
	    this.tags = new ArrayList<DTTag>();
	this.tags.add(t);
    }
    
    
    
}
