package it.marcoberri.dockitech.apiadmin;

import it.marcoberri.dockitech.api.modelresponse.JSONResult;
import it.marcoberri.dockitech.apiclient.DockitectApiClient;
import it.marcoberri.dockitech.model.DTToken;
import retrofit.RestAdapter;

public class DockitectApiAdmin extends DockitectApiClient {

    public DockitectApiAdmin(String url, String client, String nickname, String password) {
	super(url, client, nickname, password);
	setRestAdapter(new RestAdapter.Builder().setEndpoint(url).build());
	final DockitectApiAdminInterface service = getRestAdapter().create(DockitectApiAdminInterface.class);
	final JSONResult result = service.autenticate(client, nickname, password);
	if(result.isSuccess()){
	    final DTToken token = (DTToken) result.getData();
	    this.setToken(token.getId().toString());
	    setAuth(true);
	}else{
	    setAuth(false);
	}

    }

}
