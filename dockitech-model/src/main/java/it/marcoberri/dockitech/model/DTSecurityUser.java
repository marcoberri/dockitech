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
import org.mongodb.morphia.utils.IndexDirection;

@Entity(value=CollectionNames.SECURITY_USER,noClassnameStored = true)
public class DTSecurityUser extends DTBase {
    
    public DTSecurityUser(DTEncryptionMethod encryptClass) {
	super(encryptClass);
    }

    @Indexed(value=IndexDirection.ASC, unique=true, dropDups=true)
    @Property(FieldsName.SECURITYUSER_NICKNAME)
    private String nickname;
    
    @Property(FieldsName.SECURITYUSER_NAME)
    private String name;
    
    @Property(FieldsName.SECURITYUSER_USERNAME)
    private String surname;
    
    @Property(FieldsName.SECURITYUSER_PASSWORD)
    private String password;
    
    
    @Reference(FieldsName.SECURITYUSER_CLIENT)
   private DTClient client;
    
    @Reference(FieldsName.SECURITYUSER_GROUP)
    private List<DTSecurityGroup> securityGroup;
    
    @Property(FieldsName.SECURITYUSER_LASTACCESS)
    private Date lastAccess = new Date();
    
    @Property(FieldsName.SECURITYUSER_LAST_SYSTEM_UPDATE)
    private Date lastsystemUpdate = new Date();
    

    public void addSecurityGroup(DTSecurityGroup group){
	if(securityGroup == null)
	    securityGroup = new ArrayList<DTSecurityGroup>();

	securityGroup.add(group);
    }
    
    public void setPassword(String password) {
	this.password = encrypt(password,this.getEncryptClass().getEncryptClass());
    }


    @PrePersist 
    void prePersist() {
	lastsystemUpdate = new Date();
    }

    public String getNickname() {
        return decrypt(nickname,this.getEncryptClass().getEncryptClass());
    }
    
    public String getNicknameCrypt() {
        return nickname;
    }

    public void setNickname(String nickname) {
	this.nickname = encrypt(nickname,this.getEncryptClass().getEncryptClass());
    }


    public String getName() {
        return decrypt(name,this.getEncryptClass().getEncryptClass());
    }

    public void setName(String name) {
        this.name = encrypt(name,this.getEncryptClass().getEncryptClass());
    }

    public String getSurname() {
        return decrypt(surname,this.getEncryptClass().getEncryptClass());
    }

    public void setSurname(String surname) {
        this.surname = encrypt(surname,this.getEncryptClass().getEncryptClass());
    }

    public DTClient getClient() {
        return client;
    }

    public void setClient(DTClient client) {
        this.client = client;
    }

    public Date getLastsystemUpdate() {
        return lastsystemUpdate;
    }

    public void setLastsystemUpdate(Date lastsystemUpdate) {
        this.lastsystemUpdate = lastsystemUpdate;
    }

    public Date getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Date lastAccess) {
        this.lastAccess = lastAccess;
    }
    
    public boolean verifyPassword(String plain){
	return true;
    }

   
    
}
