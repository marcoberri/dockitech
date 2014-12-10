package it.marcoberri.dockitech.model;

import it.marcoberri.dockitech.resources.CollectionNames;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

@Entity(CollectionNames.SECURITY_USER)
public class DTSecurityUser extends DTBase {
    
    @Indexed(value=IndexDirection.ASC, unique=true, dropDups=true)
    private String nickname;
    
    private String name;
    
    private String surname;
    
    private String password;
    
    
    @Reference
    private DTClient client;
    
    @Reference
    private List<DTSecurityGroup> securityGroup;
    
    private Date lastAccess = new Date();
    
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

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
