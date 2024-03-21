package com.sprng.main.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sprng.main.service.HomeService;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {		
		return "index";
	}
	
	@RequestMapping(value = "/connect")
	public String connect(Model model) {
		
		HomeService ser = new HomeService();
		
		String msg = ser.result();
		model.addAttribute("msg", msg);
		
		return "index";
	}
	
	@RequestMapping(value = "/pool" , method = RequestMethod.GET)
	public String pool(Model model) {
		
		HomeService ser = new HomeService();
		String msg = ser.dbPool();
		
		model.addAttribute("msg", msg);
		
		return "index";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
