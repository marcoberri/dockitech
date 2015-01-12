package it.marcoberri.dockitech.apiadmin;

import it.marcoberri.dockitech.api.modelresponse.JSONResult;
import it.marcoberri.dockitech.apiclient.DockitectApiClientInterface;
import it.marcoberri.dockitech.resources.PathNames;
import retrofit.http.POST;
import retrofit.http.Path;

public interface DockitectApiAdminInterface  {
    
    @POST("/" + PathNames.ADMIN + "/" + PathNames.CREATE_WORLD + "/{" + PathNames.CLIENT_TITLE + "}")
    public JSONResult createword(@Path(PathNames.CLIENT_TITLE) String client);
    
}
