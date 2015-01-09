package it.marcoberri.dockitech.api;

import it.marcoberri.dockitech.adapter.MongoAdapter;
import it.marcoberri.dockitech.api.modelresponse.JSONResult;
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
@RequestMapping(value = "/" + PathNames.USER + "/{" + PathNames.CLIENT_TITLE + "}")
public class User {

    static Logger log = LogManager.getLogger(User.class);

    final JSONResult res = new JSONResult();

    @RequestMapping(value = PathNames.AUTENTICATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JSONResult autenticatebyUsernamePassword(@PathVariable(PathNames.CLIENT_TITLE) String clientTitle, String username, String password) {

	MongoAdapter adapter = new MongoAdapter();
	adapter = (MongoAdapter) adapter.openSession();
	DTToken token = adapter.autenticate(clientTitle, username, password);

	if (token == null) {
	    res.setSuccess(false);
	    res.addError("username/passoword/client not found - token not generated");
	    return res;
	}
	res.setData(token);
	res.setSuccess(true);
	return res;

    }

    @RequestMapping(value = PathNames.AUTENTICATE_TOKEN, method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    JSONResult autenticateToken(@PathVariable(PathNames.CLIENT_TITLE) String clientTitle, String username, String password) {

	res.setSuccess(true);
	return res;

    }

}
