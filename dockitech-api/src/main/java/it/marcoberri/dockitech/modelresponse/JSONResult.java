package it.marcoberri.dockitech.modelresponse;

import it.marcoberri.dockitech.resources.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class JSONResult  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	static Logger log = LogManager.getLogger(JSONResult.class);
	
	private boolean success = true;
    private String api_version = "UNDEFINED";
    private Date ts = new Date();

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

	public JSONResult(){
    	

        	try {
            	final File baseDir = FileUtils.toFile(Configuration.class.getResource("/"));
            	final Properties p = new Properties();
				p.load(FileUtils.openInputStream(new File(baseDir + "/version.properties" )));
			 	if(p != null)
					this.api_version = p.getProperty("version","UNDEFINED");
	    	} catch (final IOException e) {
	    		log.error("file version.propertie not found ",e);
			}
       
    }
		
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
	
}
