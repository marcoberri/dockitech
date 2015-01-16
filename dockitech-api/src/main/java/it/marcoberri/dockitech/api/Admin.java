package it.marcoberri.dockitech.api;

import it.marcoberri.dockitech.adapter.MongoAdapter;
import it.marcoberri.dockitech.api.modelresponse.JSONResult;
import it.marcoberri.dockitech.model.DTClient;
import it.marcoberri.dockitech.resources.PathNames;

import java.util.List;

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
    final JSONResult res = new JSONResult();
    
    @RequestMapping(value = "/" + PathNames.CREATE_WORLD + "/{" + PathNames.CLIENT_TITLE + "}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JSONResult createWorld(@PathVariable(PathNames.CLIENT_TITLE) String clientTitle) {

	log.debug("Admin.createWorld() --> start");

	adapter.getSession();
	
	final DTClient clientObj = adapter.createWorld(clientTitle);
	if (clientObj != null) {
	    res.setSuccess(true);
	    res.addData(clientObj);
	    return res;
	}

	res.setSuccess(false);
	res.addError("Error creating Client [" + clientTitle + "]");
	return res;

    }

    
    @RequestMapping(value = "/" + PathNames.CLIENT_LIST , method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JSONResult clientList(@PathVariable(PathNames.CLIENT_TITLE) String clientTitle) {

	log.debug("Admin.clientList() --> start");

	adapter.getSession();
	
	
	
	
	final List<DTClient> clients = adapter.getClientList();

	if (clients != null) {
	    res.setSuccess(true);
	    res.setData(clients);
	    return res;
	}

	res.setSuccess(false);
	res.addError("Error creating Client [" + clientTitle + "]");
	return res;

    }
    
    
    @RequestMapping(value = "/" + PathNames.DROP_UNIVERSE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JSONResult dropUniverse() {

	log.debug("Admin.createWorld() --> start");

	adapter.getSession();
	adapter.dropUniverse();
	
	res.setSuccess(true);
	return res;

    }    
    
    @RequestMapping(value = "/" + PathNames.CREATE_UNIVERSE, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JSONResult createUniverse() {

	log.debug("Admin.createUniverse() --> start");

	adapter.getSession();
	adapter.createUniverse();
	
	res.setSuccess(true);
	return res;

    }    
    

}
