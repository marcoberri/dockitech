package it.marcoberri.dockitech.model;

import java.util.Date;

import org.bson.types.ObjectId;

public class DTHistory {
    
    private ObjectId fileId;
    
    private Date lastOperation;
    
    private double version;

    public ObjectId getFileId() {
        return fileId;
    }

    public void setFileId(ObjectId fileId) {
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
