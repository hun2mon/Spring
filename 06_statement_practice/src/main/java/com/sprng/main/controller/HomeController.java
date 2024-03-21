package com.sprng.main.controller;

import java.io.UnsupportedEncodingException;

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
	
	@RequestMapping(value = "/stmt", method = RequestMethod.GET)
	public String stmt(Model model) {
		
		logger.info("controller 요청");
		
		HomeService service = new HomeService();
		String msg = service.stmt();
		model.addAttribute("msg", msg);
		
		
		return "index";
	}
	
	
	@RequestMapping(value = "/insert")
	public String insert(Model model, HttpServletRequest req) throws Exception {
		req.setCharacterEncoding("UTF-8");
		
		logger.info("controller 요청");
		String id = req.getParameter("ID");
		String pw = req.getParameter("PW");
		String name = req.getParameter("NAME");
		String age = req.getParameter("AGE");
		String gender = req.getParameter("GENDER");
		String email = req.getParameter("EMAIL");
		
		logger.info(id + "/" + pw + "/" + name + "/" + age + "/" + gender + "/" + email);
		
		HomeService service = new HomeService();
		String msg = service.insert(id,pw,name,age,gender,email);
		model.addAttribute("msg", msg);
		
		return "index";
	}
}
