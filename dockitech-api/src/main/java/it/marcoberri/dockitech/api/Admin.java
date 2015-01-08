package it.marcoberri.dockitech.api;

import it.marcoberri.dockitech.resources.PathNames;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/" + PathNames.ADMIN)
public class Admin {

    static Logger log = LogManager.getLogger(Admin.class);


}
