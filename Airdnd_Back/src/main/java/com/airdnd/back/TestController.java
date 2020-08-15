package com.airdnd.back;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;

@Controller
public class TestController {
	
	@RequestMapping("/test")
	public String gotest() {
		return Common.VIEW_PATH + "test.jsp";
	}

}
