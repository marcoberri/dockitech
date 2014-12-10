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

@Entity(CollectionNames.SYSTEM_ENCRYPT_METHOD)
public class DTEncryptionMethod {

    @Id 
    private ObjectId id;
    
    @Indexed(value=IndexDirection.ASC, unique=true, dropDups=true)
    private String encryptClass = "it.marcoberri.dockitech.security.NullSecurity";

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getEncryptClass() {
        return encryptClass;
    }

    public void setEncryptClass(String encryptClass) {
        this.encryptClass = encryptClass;
    }

    
    

   
    
}
