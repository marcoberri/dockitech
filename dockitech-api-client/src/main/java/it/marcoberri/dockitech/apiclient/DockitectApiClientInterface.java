package it.marcoberri.dockitech.apiclient;

import it.marcoberri.dockitech.api.modelresponse.JSONResult;
import it.marcoberri.dockitech.resources.PathNames;
import retrofit.http.POST;
import retrofit.http.Path;

public interface DockitectApiClientInterface {
    
	  @POST("/" + PathNames.USER + "/{" + PathNames.CLIENT_TITLE + "}/" + PathNames.AUTENTICATE)
	  public JSONResult autenticate(@Path(PathNames.CLIENT_TITLE) String client, String user, String password);
	
}
