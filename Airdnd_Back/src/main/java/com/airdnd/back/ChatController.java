package com.airdnd.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import service.AirdndChatService;
import vo.AirdndChatVO;

@Controller
public class ChatController {
	@Autowired
	AirdndChatService airdndChatService;

	@RequestMapping("/chat")
	public String chat_list(Model model) {
		List<AirdndChatVO> list = airdndChatService.daoserviceconnect();
		model.addAttribute("list", list);
		
		return Common.VIEW_PATH + "chat.jsp";
	}
}
