package pl.spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import pl.spring.demo.constants.ViewNames;

@Controller
@RequestMapping(value = "/find", method = RequestMethod.GET)
public class FindController {
	
	@RequestMapping
	public ModelAndView defaultFindController() {
		return requestParameters();
	}
	
	@RequestMapping(value = "/searchingParameters", method = RequestMethod.GET)
	public ModelAndView requestParameters() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(ViewNames.FIND_BOOKS);
		return modelAndView;
	}


}
