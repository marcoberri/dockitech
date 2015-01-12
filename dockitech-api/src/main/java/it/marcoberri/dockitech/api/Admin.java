package it.marcoberri.dockitech.api;

import it.marcoberri.dockitech.adapter.MongoAdapter;
import it.marcoberri.dockitech.api.modelresponse.JSONResult;
import it.marcoberri.dockitech.model.DTClient;
import it.marcoberri.dockitech.model.DTToken;
import it.marcoberri.dockitech.resources.PathNames;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/" + PathNames.ADMIN)
public class Admin {

    static Logger log = LogManager.getLogger(Admin.class);
    final MongoAdapter adapter = new MongoAdapter();
    
    @RequestMapping(value = PathNames.CREATE_WORLD  + "/{" + PathNames.CLIENT_TITLE + "}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JSONResult createWorld(@PathVariable(PathNames.CLIENT_TITLE) String clientTitle) {
	adapter.getSession();
	JSONResult res = new JSONResult();
	DTClient clientObj = adapter.createWorld(clientTitle);
	if(clientObj != null){
	    res.setSuccess(true);
	    res.setData(clientObj);
	    return res;
	}
	
	    res.setSuccess(false);
	    res.addError("Error creating Client [" + clientTitle + "]");
	    return res;


    }


}
