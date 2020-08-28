package com.airdnd.back;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	
	@Autowired
	HttpServletRequest request;

	@RequestMapping("/chat")
	public String chat_list(Model model) {
		List<AirdndChatVO> list = airdndChatService.daoserviceconnect();
		model.addAttribute("list", list);
		
		return Common.VIEW_PATH + "chat.jsp";
	}
	
	@RequestMapping("/insert_chat")
	public String insert_chat(Model model, AirdndChatVO vo) {
		//Temp
		vo.setHost_idx(1);
		vo.setUser_idx(2);
		
		//Real
		vo.setContent(request.getParameter("content"));
		vo.setImage_url(request.getParameter("image_url"));
		
		//Image
		
		
		airdndChatService.daoserviceinsert(vo);
		model.addAttribute("vo", vo);
		
		return "redirect:chat";
	}
}
