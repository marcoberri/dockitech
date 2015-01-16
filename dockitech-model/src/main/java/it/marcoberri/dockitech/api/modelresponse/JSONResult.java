package it.marcoberri.dockitech.api.modelresponse;

import it.marcoberri.dockitech.model.DTBase;
import it.marcoberri.dockitech.resources.Configuration;

import java.beans.Transient;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JSONResult implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    static Logger log = LogManager.getLogger(JSONResult.class);

    private boolean success = true;
    private String api_version = "UNDEFINED";
    private Date ts = new Date();
    private List<String> errors;
    private List<DTBase> data;

    private Pagination pages; 


    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Object getData() {
        return data;
    }

    public void setData(List data) {
        this.data = data;
        this.pages = new Pagination();
        pages.setTotEle(data.size());
        //TODO
    }
    
    public void addData(DTBase data) {
	if(this.data == null)
	    this.data = new ArrayList<DTBase>();
        this.data.add(data);
    }
    

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void addError(String error){
	
	if(this.errors == null)
	    errors = new ArrayList<String>();
	this.errors.add(error);
    }
    
    public String getApi_version() {
	return api_version;
    }

    public void setApi_version(String api_version) {
	this.api_version = api_version;
    }

    public Date getTs() {
	return ts;
    }

    public void setTs(Date ts) {
	this.ts = ts;
    }

    public JSONResult() {

	try {
	    final File baseDir = FileUtils.toFile(Configuration.class.getResource("/"));
	    final Properties p = new Properties();
	    p.load(FileUtils.openInputStream(new File(baseDir + "/version.properties")));
	    if (p != null)
		this.api_version = p.getProperty("version", "UNDEFINED");
	} catch (final IOException e) {
	    log.error("file version.propertie not found ", e);
	}

    }

    public boolean isSuccess() {
	return success;
    }

    public void setSuccess(boolean success) {
	this.success = success;
    }

}
