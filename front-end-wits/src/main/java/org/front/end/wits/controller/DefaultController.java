package org.front.end.wits.controller;



import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/")
public class DefaultController {
	

	
	@Autowired
	public DefaultController(Validator validator) {
	}
	@RequestMapping( method=RequestMethod.GET)
	public String getDefaultForm(Model model) {
		model.addAttribute(new Object());
		return "index";
	}
	

}
