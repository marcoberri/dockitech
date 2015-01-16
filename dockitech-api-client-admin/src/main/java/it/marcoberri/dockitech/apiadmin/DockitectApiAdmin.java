package it.marcoberri.dockitech.apiadmin;

import it.marcoberri.dockitech.api.modelresponse.JSONResult;
import it.marcoberri.dockitech.apiclient.DockitectApiClient;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DockitectApiAdmin extends DockitectApiClient {

    
    final DockitectApiAdminInterface service;
    
    static Logger log = LogManager.getLogger(DockitectApiAdmin.class);
    
    
    public DockitectApiAdmin(String url) {
	super(url);
	setRestAdapter(RestAdapterDispatcher.getClientRestAdapter(null, url));
	service = getRestAdapter().create(DockitectApiAdminInterface.class);
    }
    
    
   /* public DockitectApiAdmin(String url,String clinet) {
	setRestAdapter(new RestAdapter.Builder().setEndpoint(url).build());
	final DockitectApiAdminInterface service = getRestAdapter().create(DockitectApiAdminInterface.class);
	//final JSONResult result = service.autenticate(client, nickname, password);
	setAuth(true);
	/*if(result.isSuccess()){
	    final DTToken token = (DTToken) result.getData();
	    this.setToken(token.getId().toString());
	    setAuth(true);
	}else{
	    setAuth(false);
	}*/

    
    
    public boolean createWorld(String client){
	final JSONResult result = service.createword(client);
	return result.isSuccess();
    }
    
    public boolean dropUniverse(){
	final JSONResult result = service.dropUniverse();
	return result.isSuccess();
    }
    
    

}
