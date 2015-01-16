package it.marcoberri.dockitech.admin.controller;
import java.util.ArrayList;

import it.marcoberri.dockitech.admin.model.ClientModel;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
 
@Controller
public class HomeController {

    	
    
	@RequestMapping("/")
	public ModelAndView index(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
		
		ArrayList<ClientModel> clients = new ArrayList<ClientModel>();
		
		ClientModel client = new ClientModel();
		
		client.setTitle("test");
		client.setDesc("test client");
		
		
		clients.add(client);
		
		ClientModel client2= new ClientModel();
		
		client2.setTitle("test 2");
		client2.setDesc("test client 2");
		clients.add(client2);
		
		ModelAndView modelAndView = new ModelAndView("clients");
		modelAndView.addObject("clients", clients);
		
		
		return modelAndView;
	}
}
