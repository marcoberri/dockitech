package it.marcoberri.dockitech.model;

import it.marcoberri.dockitech.resources.CollectionNames;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.utils.IndexDirection;

@Entity(value = CollectionNames.SYSTEM_ENCRYPT_METHOD, noClassnameStored = true)
public class DTEncryptionMethod {

    @Id
    private ObjectId id;

    @Indexed(value = IndexDirection.ASC, unique = true, dropDups = true)
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
