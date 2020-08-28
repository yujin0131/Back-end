package com.airdnd.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import service.AirdndHomeService;
import vo.AirdndHomeVO;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	AirdndHomeService airdndhomeService;
	
	@RequestMapping("/home")
	public String check(Model model) {
		
		List<AirdndHomeVO> list = airdndhomeService.homeselect();
		model.addAttribute("list", list);

		return Common.VIEW_PATH + "home.jsp";
	}
}
