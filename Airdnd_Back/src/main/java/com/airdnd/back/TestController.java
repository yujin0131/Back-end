package com.airdnd.back;

import java.io.Console;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import dao.AirdndRoomDAO;
import vo.AirdndRoomVO;

@Controller
public class TestController {
	
	AirdndRoomDAO airdnd_room_dao;
	
	public void setAirdnd_room_dao(AirdndRoomDAO airdnd_room_dao) {
		this.airdnd_room_dao = airdnd_room_dao;
	}
	
	@RequestMapping("/test")
	public String test(Model model) {
		
		AirdndRoomVO vo = airdnd_room_dao.select();
		model.addAttribute("vo", vo);
		return Common.VIEW_PATH + "test.jsp";
	}

}
