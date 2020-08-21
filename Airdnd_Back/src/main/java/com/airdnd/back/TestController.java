package com.airdnd.back;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import dao.AirdndRoomDAO;
import service.AirdndRoomService;
import vo.AirdndRoomVO;


@Controller
public class TestController {
	
	/*
	 * @Autowired AirdndRoomService airdndroomService;
	 */
	
	@Autowired
	AirdndRoomService airdndroomService;

	@RequestMapping("/test")
	public String test(Model model) {

		List<AirdndRoomVO> list = airdndroomService.daoserviceconnect();
		model.addAttribute("list", list);

		
		return Common.VIEW_PATH + "test.jsp";
	}

}
