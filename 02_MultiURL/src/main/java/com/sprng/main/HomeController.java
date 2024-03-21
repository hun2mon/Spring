package com.sprng.main;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, HttpServletRequest req) {		
		
		String param = req.getParameter("param");
//		System.out.println("param : " +param);
//		logger.info("param : {}",param);
		
		String val = "Invelid Data";
		
		if (param != null) {
			if (param.equals("greeting")) {
				val = "안녕하세요";
			} else if (param.equals("date")) {
				val = new Date().toString();
			}
		}
		model.addAttribute("msg", val);
		return "index";
	}
	
	// /index 로 요청이 오면 index() 메서드를 실행해라
	// get 방식으로만 받겠다.	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {// Model 이라는 녀석으로 jsp 에 데이터를 보낼 수 있다.
		//name (jsp 에서 el 태그로 부를때 사용하는 이름), value
		model.addAttribute("msg", "index 페이지에 오셨습니다.");
		return "index"; //가야할 jsp 이
	}
	
	
	// /main 으로 요청이 오면 main() 메서드를 실행 하도록
	// jsp 로 전달될 문구는 "main 페이지에 오셨습니다.
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Model model) {
		logger.info("/main 요청");
		model.addAttribute("msg", "main 페이지에 오셨습니다.");
		return "index";
	}
	
}




































