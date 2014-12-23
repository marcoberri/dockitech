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

@Entity(value = CollectionNames.SYSTEM_TOKEN, noClassnameStored = true)
public class DTToken extends DTBase {

    @Indexed(name = FieldsName.SYSTEM_TOKEN_TOKEN, value = IndexDirection.ASC, unique = true, dropDups = true)
    private String token;
    
    @Property(FieldsName.SYSTEM_TOKEN_CREATED)
    private Date created;
    private String ip;
    
    @Property(FieldsName.SYSTEM_TOKEN_VALID)
    private Boolean valid=true;
    
    @Reference(FieldsName.SYSTEM_TOKEN_USER)
    DTSecurityUser user;
    
    @Reference(FieldsName.SYSTEM_TOKEN_CLIENT)
    private DTClient client;

    public DTToken(DTClient client) {
	this.client = client;
    }
    
    public DTToken() {
	super();
    }
    
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public DTClient getClient() {
        return client;
    }

    public void setClient(DTClient client) {
        this.client = client;
    }

    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public DTSecurityUser getUser() {
        return user;
    }

    public void setUser(DTSecurityUser user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }



    
    
}
