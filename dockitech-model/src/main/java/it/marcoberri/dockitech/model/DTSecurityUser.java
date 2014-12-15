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

@Entity(value = CollectionNames.SECURITY_USER, noClassnameStored = true)
public class DTSecurityUser extends DTBase {

	@Indexed(value = IndexDirection.ASC, unique = true, dropDups = true)
	@Property(FieldsName.SECURITYUSER_NICKNAME)
	private String nickname;

	@Transient
	private DTEncryptionMethod encryptClass;

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

	    
	public DTSecurityUser() {
		super();
	}

	public DTSecurityUser(DTClient client) {
		this.client = client;
	}

	public void addSecurityGroup(DTSecurityGroup group) {
		if (securityGroup == null)
			securityGroup = new ArrayList<DTSecurityGroup>();

		securityGroup.add(group);
	}

	public void setPassword(String password) {
		this.password = encrypt(password, client);
	}

	@PrePersist
	void prePersist() {
		lastsystemUpdate = new Date();
	}

	public DTEncryptionMethod getEncryptClass() {
		return encryptClass;
	}

	public void setEncryptClass(DTEncryptionMethod encryptClass) {
		this.encryptClass = encryptClass;
	}

	public List<DTSecurityGroup> getSecurityGroup() {
		return securityGroup;
	}

	public void setSecurityGroup(List<DTSecurityGroup> securityGroup) {
		this.securityGroup = securityGroup;
	}

	public String getPassword() {
		return password;
	}

	public String getNickname() {
		return decrypt(nickname, this.client);
	}

	public String getNicknameCrypt() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = encrypt(nickname, client);
	}

	public String getName() {
		return decrypt(name, client);
	}

	public void setName(String name) {
		this.name = encrypt(name, client);
	}

	public String getSurname() {
		return decrypt(surname, client);
	}

	public void setSurname(String surname) {
		this.surname = encrypt(surname,client);
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

	public boolean verifyPassword(String plain) {
		return true;
	}

}
