package com.airdnd.back;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

	@RequestMapping(value="/guest/inbox", produces="application/json;charset=utf8")
	@ResponseBody
	public String chat_list(Model model) {
		//Login cookie
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		String sessionKey = "";
		int signInIdx = 1; //temp
		String signInEmail = "";
		String signInName = "";
		
		if(cookies == null) {
			System.out.println("not cookies");
		}else {
			for(Cookie cookie : cookies) {
				if("AirdndSES".equals(cookie.getName())) {
					sessionKey = cookie.getValue();
					AirdndUserVO signInVO = (AirdndUserVO)session.getAttribute(sessionKey);
					signInIdx = signInVO.getUser_idx();
					signInEmail = signInVO.getEmail();
					signInName = signInVO.getLast_name() + signInVO.getFirst_name();
				} else {
					System.out.println("not login");
				}
			}
		}//if
		
		//Final data
		JSONObject res = new JSONObject();			//1

		JSONArray hostArr = new JSONArray();		//2
		JSONObject hostlists = new JSONObject();	//3

		JSONArray info = new JSONArray();			//4
		JSONObject contents = null;					//5-1
		JSONObject chatHistory = null;				//5-2
		
		//Host Info
		List<AirdndHostVO> hostList = airdndChatService.selectHostList(signInIdx);
		System.out.println("hostList size : " + hostList.size());
		
		//Chat Info
		Map<Integer, Object> chatList_host = new HashMap<Integer, Object>();
		List<AirdndChatVO> list = null;
		List<AirdndChatVO> list2 = null;
		
		for(int i = 0; i < hostList.size(); i++) {
			System.out.println("흠냐 : " + hostList.get(i).getIdx());
			list = airdndChatService.selectChatList(signInIdx, hostList.get(i).getIdx());
			
			chatList_host.put(i, list);
		}
		
		//User Info
		AirdndUserVO userVO = airdndChatService.selectUser(signInIdx).get(0);
		
		//UserResInfo
		List<AirdndUserResInfoVO> userResInfo = new ArrayList<AirdndUserResInfoVO>();
		AirdndUserResInfoVO vo = null;
		
		//Latest msg
		List<AirdndChatVO> chatInfo = null;
		AirdndChatVO chat_vo = null;
		
		for(int i = 0; i < hostList.size(); i++) {
			hostlists = new JSONObject(); 	//3
			info = new JSONArray();			//4
			contents = new JSONObject();	//5-1
			
			int n = 0;
			
			//list and vo setting
			list = airdndChatService.selectChatList(signInIdx, hostList.get(i).getIdx());
			list2 = (List<AirdndChatVO>)chatList_host.get(i);
			vo = airdndChatService.selectUserResInfo(signInIdx, hostList.get(i).getIdx()).get(0);
			chat_vo = airdndChatService.selectLatestMsg(signInIdx, hostList.get(i).getIdx()).get(0);
			
			hostlists.put("id", hostList.get(i).getIdx());
			hostlists.put("reservationId", vo.getIdx());
			
			int count = 0;
			
			for(int j = 0; j < list.size(); j++) {
				System.out.println("list 사이즈 : " + list.size());
				if(list.get(j).getMsg_hidden_or_not() == 0) {
					count++;
				}
			}
			
			if(count == list.size()) {
				hostlists.put("state", "all");
			} else if(count == 0) {
				hostlists.put("state", "hide");
			} else {
				hostlists.put("state", "error. 이게 보이면 미래에게 문의하세요.");
			}
			
			hostlists.put("hostname", hostList.get(i).getHost_name());
			
			
			//contents
			contents.put("hostProfileImg", hostList.get(i).getHost_profileImg());
			
			//latest message
			contents.put("lastMsg", chat_vo.getContent());
			contents.put("lastMsgDate", chat_vo.getSend_date_time());
			
			//userResInfo
			contents.put("isCanceled", vo.getIs_canceled());
			contents.put("checkin", vo.getCheckin());
			contents.put("checkout", vo.getCheckout());
			
			//info.add(n, contents);
			hostlists.put("contents", contents);
			
			
			//chatHistory
			for(int j = 0; j < list2.size(); j++) {
				chatHistory = new JSONObject();
				
				chatHistory.put("id", list2.get(j).getIdx());
				chatHistory.put("userId", list2.get(j).getUser_idx());
				chatHistory.put("name", userVO.getFirst_name());
				chatHistory.put("timeStamp", list2.get(j).getSend_date_time());
				chatHistory.put("text", list2.get(j).getContent());
				
				info.add(n, chatHistory);
				hostlists.put("chatHistory", info);
			}

			hostArr.add(i, hostlists);
		}//for
		
		res.put("message", hostArr);
		//model.addAttribute("res", res.toString());
		
		return res.toString();
		//return Common.VIEW_PATH + "chat.jsp";
	}
	
	@RequestMapping("/insert_chat")
	public String insert_chat(AirdndChatVO vo, Model model) {
		//Login cookie
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		String sessionKey = "";
		int signInIdx = 1; //temp
		String signInEmail = "";
		String signInName = "";
		
		if(cookies == null) {
			System.out.println("not cookies");
		}else {
			for(Cookie cookie : cookies) {
				if("AirdndSES".equals(cookie.getName())) {
					sessionKey = cookie.getValue();
					AirdndUserVO signInVO = (AirdndUserVO)session.getAttribute(sessionKey);
					signInIdx = signInVO.getUser_idx();
					signInEmail = signInVO.getEmail();
					signInName = signInVO.getLast_name() + signInVO.getFirst_name();
				} else {
					System.out.println("not login");
				}
			}
		}//if
		
		//Temp. 로그인 세션이나 쿠키에서 받아와야 함
		vo.setUser_idx(signInIdx);
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
