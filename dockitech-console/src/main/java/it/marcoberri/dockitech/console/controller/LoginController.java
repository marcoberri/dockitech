package it.marcoberri.dockitech.console.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController  {
    
    static Logger logger = LogManager.getLogger(LoginController.class);
    
    @RequestMapping(value= "/", method=RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("message", "Hello World!");
        return "helloWorld";
    }
}
