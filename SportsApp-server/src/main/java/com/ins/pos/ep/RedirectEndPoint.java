package com.ins.pos.ep;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping
public class RedirectEndPoint {

	 @RequestMapping(value = "/**/{path:[^.]*}")       
	    public RedirectView redirect() {
		 return new RedirectView("/", true);
	    }
	 
	 
	 }
