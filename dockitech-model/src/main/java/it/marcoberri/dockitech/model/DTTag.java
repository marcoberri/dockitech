package it.marcoberri.dockitech.model;

import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.mongodb.morphia.annotations.Transient;

import it.marcoberri.dockitech.resources.FieldsName;

public class DTTag extends DTBase {

    public DTTag(DTClient client) {
	this.client = client;
    }

    public DTTag() {
	super();
    }

    @Reference(FieldsName.SECURITYGROUP_CLIENT)
    private DTClient client;

    @Property(FieldsName.TAG_VALUE)
    @Indexed
    private String value;

    @Property(FieldsName.TAG_NORM)
    private String norm;

    @Property(FieldsName.TAG_SIZE)
    private Integer size;

    public String getValue() {
	return value;
    }

    public String getNorm() {
	return norm;
    }

    public void setNorm(String norm) {
	this.norm = norm.toUpperCase();
    }

    public void setValue(String value) {
	this.value = value;
	setNorm(value);
    }

    public Integer getSize() {
	return size;
    }

    public void setSize(Integer size) {
	this.size = size;
    }

    @Override
    public String toString() {
	return "DTTag [client=" + client + ", value=" + value + ", norm=" + norm + ", size=" + size + "]";
    }

}
