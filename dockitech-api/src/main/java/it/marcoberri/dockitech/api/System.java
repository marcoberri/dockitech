package it.marcoberri.dockitech.api;

import it.marcoberri.dockitech.adapter.MongoAdapter;
import it.marcoberri.dockitech.api.modelresponse.JSONResult;
import it.marcoberri.dockitech.resources.PathNames;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/" + PathNames.SYSTEM)
public class System {

    static Logger log = LogManager.getLogger(System.class);
    final MongoAdapter adapter = new MongoAdapter();

    @RequestMapping(value = "/" + PathNames.STATUS, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JSONResult status() {
	final JSONResult res = new JSONResult();
	res.setSuccess(true);
	return res;
    }

}
