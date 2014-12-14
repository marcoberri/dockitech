package it.marcoberri.dockitech.model;

import it.marcoberri.dockitech.resources.CollectionNames;
import it.marcoberri.dockitech.resources.FieldsName;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;
import org.mongodb.morphia.utils.IndexDirection;

@Entity(value = CollectionNames.SYSTEM_CLIENT, noClassnameStored = true)
public class DTClient extends DTBase {


   
    public DTClient() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Indexed(value = IndexDirection.ASC, unique = true, dropDups = true)
    @Property(FieldsName.CLIENT_TITLE)
    private String title;

    @Reference(FieldsName.CLIENT_SECURITY_GROUP)
    private List<DTSecurityGroup> securityGroup;

    
    
    @Transient
    private DTEncryptionMethod encryptClass;
    
    
    @Property(FieldsName.CLIENT_LAST_SYSTEM_UPDATE)
    private Date lastsystemUpdate = new Date();

    public List<DTSecurityGroup> getSecurityGroup() {
	return securityGroup;
    }

 
    
    
    public DTEncryptionMethod getEncryptClass() {
    	return encryptClass;
        }

        public void setEncryptClass(DTEncryptionMethod encryptClass) {
    	this.encryptClass = encryptClass;
        }
        
        
    public void setSecurityGroup(List<DTSecurityGroup> securityGroup) {
	this.securityGroup = securityGroup;
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

    @PrePersist
    void prePersist() {
	lastsystemUpdate = new Date();
    }

    public void addSecurityGroup(DTSecurityGroup group) {
	if (securityGroup == null)
	    securityGroup = new ArrayList<DTSecurityGroup>();

	securityGroup.add(group);

    }

}
