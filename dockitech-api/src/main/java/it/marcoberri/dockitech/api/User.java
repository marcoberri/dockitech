package it.marcoberri.dockitech.api;

import it.marcoberri.dockitech.model.DTSecurityUser;
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

    @RequestMapping(value = PathNames.AUTENTICATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DTSecurityUser autenticatebyUsernamePassword(@PathVariable(PathNames.CLIENT_TITLE) String clientTitle, String username, String password) {
	return null;
    }

    @RequestMapping(value = PathNames.AUTENTICATE_TOKEN, method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    DTSecurityUser autenticateToken(@PathVariable(PathNames.CLIENT_TITLE) String clientTitle, String username, String password) {
	return null;
    }

}
