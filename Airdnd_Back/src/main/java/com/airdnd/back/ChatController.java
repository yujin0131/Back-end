package com.airdnd.back;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import service.AirdndChatService;
import vo.AirdndChatVO;
import vo.AirdndHostVO;
import vo.AirdndUserResInfoVO;
import vo.AirdndUserVO;

@Controller
public class ChatController {
	@Autowired
	AirdndChatService airdndChatService;
	
	@Autowired
	HttpServletRequest request;

	@RequestMapping("/chat")
	public String chat_list(Model model) {
		JSONObject resChat = new JSONObject();
		JSONArray chatList = new JSONArray();
		JSONObject chatInfo = null;
		
		List<AirdndChatVO> list = airdndChatService.selectChatList();
		model.addAttribute("list", list);
		
		//Host Info
		List<AirdndHostVO> hostList = airdndChatService.selectHostList();
		
		//User Info
		int user_idx = 1; //세션에서 받아오기
		AirdndUserVO userVO = airdndChatService.selectUser(user_idx).get(0);
		
		//UserResInfo
		
		
		int size = list.size();
		
		for(int i = 0; i < size; i++) {
			chatInfo = new JSONObject();
			
			chatInfo.put("idx", list.get(i).getIdx());
			
			//Host
			chatInfo.put("hostIdx", list.get(i).getHost_idx());
			
			for(int j = 0; j < hostList.size(); j++) {
				if(list.get(i).getHost_idx() == hostList.get(j).getIdx()) {
					chatInfo.put("hostName", hostList.get(j).getHost_name());
					chatInfo.put("hostProfileImg", hostList.get(j).getHost_profileImg());
				}
				
				//UserResInfo
				//AirdndUserResInfoVO vo = airdndChatService.selectUserResInfo(list.get(i).getUser_idx(), hostList.get(j).getHome_idx());
				
			}
			
			//User
			chatInfo.put("userIdx", list.get(i).getUser_idx());
			chatInfo.put("userName", userVO.getFirst_name());
			chatInfo.put("userProfileImg", userVO.getProfileImg());
			
			chatInfo.put("content", list.get(i).getContent());
			chatInfo.put("image_url", list.get(i).getImage_url());
			chatInfo.put("image", list.get(i).getImage());
			chatInfo.put("send_date_time", list.get(i).getSend_date_time());
			chatInfo.put("msg_hidden_or_not", list.get(i).getMsg_hidden_or_not());
			
			chatList.add(i, chatInfo);
		}//for
		
		resChat.put("chatList", chatList);
		
		model.addAttribute("res", resChat.toString());
		//System.out.println(resChat.toString());
		
		//return resChat.toString();
		return Common.VIEW_PATH + "chat.jsp";
	}
	
	@RequestMapping("/insert_chat")
	public String insert_chat(AirdndChatVO vo, Model model) {
		//Temp. 로그인 세션이나 쿠키에서 받아와야 함
		vo.setUser_idx(2);
		//이건 어디서 받아오지.. detail 페이지인가..?
		vo.setHost_idx(2);
		
		//Real
		vo.setContent(request.getParameter("content"));
		vo.setImage_url(request.getParameter("image_url"));
		
		//Image
		
		airdndChatService.insertChat(vo);
		model.addAttribute("vo", vo);
		
		return "redirect:chat";
	}
}
