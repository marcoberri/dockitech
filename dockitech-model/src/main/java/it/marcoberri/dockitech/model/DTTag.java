package it.marcoberri.dockitech.model;

public class DTTag extends DTBase {

    public DTTag(DTEncryptionMethod encryptClass) {
	super(encryptClass);
    }

    private String value;
    private String norm;
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
    
}
