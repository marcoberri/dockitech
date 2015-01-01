package it.marcoberri.dockitech.api;

import it.marcoberri.dockitech.resources.PathNames;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/" + PathNames.ADMIN )
public class Admin {
    
	static Logger log = LogManager.getLogger(Admin.class);
	

    /*
    @RequestMapping( method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    void doLog(@PathVariable(ClarinetApiContext.CODCLIENTE) String codCliente, @RequestBody it.skylab.clarinet.model.api.logger.ClarinetLogger model) {

	try {
	    final AsyncLogger asyncLogger = new AsyncLogger(codCliente, model);
	    asyncLogger.run();
	} catch (final Exception e) {
	    logger.error("ClarinetLogger.doLog -->" + e);
	}
	res.setSuccess(true);
	return res;
    }
*/
    
    
    
}
