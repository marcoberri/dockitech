package it.marcoberri.dockitech.model;

import java.util.Date;

public class DTHistory {

    private String fileId;

    private Date lastOperation;

    private double version;



    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public Date getLastOperation() {
	return lastOperation;
    }

    public void setLastOperation(Date lastOperation) {
	this.lastOperation = lastOperation;
    }

    public double getVersion() {
	return version;
    }

    public void setVersion(double version) {
	this.version = version;
    }

}
