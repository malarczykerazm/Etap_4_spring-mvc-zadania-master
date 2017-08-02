package pl.spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.spring.demo.constants.ModelConstants;
import pl.spring.demo.constants.ViewNames;

@Controller
public class HomeController {

	private static final String INFO_TEXT = "Here You shall display information containing informations about newly created TO";
	private static final String COMPANY_NAME = "e-library&trade;";

	@RequestMapping("/")
	public String welcome(Model model) {
		model.addAttribute(ModelConstants.COMPANY, COMPANY_NAME);
		model.addAttribute(ModelConstants.INFO, INFO_TEXT);
		return ViewNames.WELCOME;
	}
}
