package it.marcoberri.dockitech.apiclient;

import it.marcoberri.dockitech.api.modelresponse.JSONResult;
import it.marcoberri.dockitech.model.DTToken;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import retrofit.RestAdapter;

public class DockitectApiClient {

    private RestAdapter restAdapter = null;

    private String token = null;
    private String username;
    private String password;
    private String client;
    private boolean auth = false;
    private String url;

    
    final DockitectApiClientInterface service;
    
    static Logger log = LogManager.getLogger(DockitectApiClient.class);
    
    public RestAdapter getRestAdapter() {
        return restAdapter;
    }

    public void setRestAdapter(RestAdapter restAdapter) {
        this.restAdapter = restAdapter;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public DockitectApiClient(String url) {
	this.url = url;
	this.restAdapter = new RestAdapter.Builder().setEndpoint(url).build();
	service = restAdapter.create(DockitectApiClientInterface.class);
    }
    public DockitectApiClient(String url, String client, String nickname, String password) {
	
	this.url = url;
	this.username = nickname;
	this.password = password;

	this.restAdapter = RestAdapterDispatcher.getClientRestAdapter(null,url);
	
	service = restAdapter.create(DockitectApiClientInterface.class);
	final JSONResult result = service.autenticate(client, nickname, password);
	if(result.isSuccess()){
	    final DTToken token = (DTToken) result.getData();
	    this.setToken(token.getId().toString());
	    setAuth(true);
	}else{
	    setAuth(false);
	}
    }

    
    
    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public String getPassword() {
	return "*********************";
    }

    public boolean isAuth() {
	return auth;
    }

    public void setAuth(boolean auth) {
	this.auth = auth;
    }

    public String getToken() {
	return token;
    }

    public void setToken(String token) {
	this.token = token;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public void setPassword(String password) {
	this.password = password;
    }

}
