package it.marcoberri.dockitech.api;

import it.marcoberri.dockitech.resources.PathNames;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/" + PathNames.USER )
public class User {
    

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
