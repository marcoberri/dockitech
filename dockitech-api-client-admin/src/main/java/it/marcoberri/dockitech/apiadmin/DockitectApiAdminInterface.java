package it.marcoberri.dockitech.apiadmin;

import it.marcoberri.dockitech.api.modelresponse.JSONResult;
import it.marcoberri.dockitech.resources.PathNames;
import retrofit.http.GET;
import retrofit.http.Path;

public interface DockitectApiAdminInterface  {
    
    @GET(value = "/" + PathNames.ADMIN + "/" + PathNames.CREATE_WORLD + "/{" + PathNames.CLIENT_TITLE + "}")
    public JSONResult createword(@Path(PathNames.CLIENT_TITLE) String client);
    
    @GET(value = "/" + PathNames.ADMIN + "/" + PathNames.DROP_UNIVERSE)
    public JSONResult dropUniverse();
    
    
    
}
