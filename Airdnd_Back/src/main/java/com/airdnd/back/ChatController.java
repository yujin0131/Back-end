package com.airdnd.back;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import service.AirdndChatService;
import vo.AirdndChatMsgsVO;
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
	
	@RequestMapping(value= {"/guest/inbox"}, produces="application/json;charset=utf8")
	@ResponseBody
	public String chat_list(Model model, @RequestParam(value="filter", defaultValue="")String filter) {
		//Login cookie
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		String sessionKey = "";
		int signInIdx = 1; //temp
		String signInName = "MR";
		String signInProfileImg = "https://a0.muscache.com/defaults/user_pic-225x225.png?im_w=720";
		
		if(cookies == null) {
			System.out.println("not cookies");
		}else {
			for(Cookie cookie : cookies) {
				if("AirdndSES".equals(cookie.getName())) {
					sessionKey = cookie.getValue();
					AirdndUserVO signInVO = (AirdndUserVO)session.getAttribute(sessionKey);
					signInIdx = signInVO.getUser_idx();
					signInName = signInVO.getFirst_name();
					signInProfileImg = signInVO.getProfileImg();
					
				} else {
					System.out.println("not login");
				}
			}
		}//if
		
		//Final data
		JSONObject res = new JSONObject();			//1
		JSONObject resFilter = new JSONObject();	//2
		JSONObject thirdUser = new JSONObject();	//3

		JSONArray fourthArr = new JSONArray();		//4
		JSONObject fifthlists = new JSONObject();	//5

		JSONArray info = new JSONArray();			//6
		JSONObject contents = new JSONObject();		//7-1
		JSONObject chatHistory = new JSONObject();	//7-2
		
		//select all chat list
		List<AirdndChatVO> listAll = airdndChatService.selectChatListAll(signInIdx);
		List<AirdndChatVO> listHidden = airdndChatService.selectChatListHidden(signInIdx);
		List<AirdndChatVO> listUnread = airdndChatService.selectChatListUnread(signInIdx);
		
		//Select latest msg in User's chat list - all
		AirdndChatMsgsVO latestMsgVO = new AirdndChatMsgsVO();
		
		List<AirdndHostVO> hostList = new ArrayList<AirdndHostVO>();
		AirdndUserResInfoVO userResInfoVO = new AirdndUserResInfoVO();
		List<AirdndChatMsgsVO> chattingListA = new ArrayList<AirdndChatMsgsVO>();
		List<AirdndChatMsgsVO> chattingListH = new ArrayList<AirdndChatMsgsVO>();
		List<AirdndChatMsgsVO> chattingListU = new ArrayList<AirdndChatMsgsVO>();
		
		if(filter.equalsIgnoreCase("all")) {
			for(int i = 0; i < listAll.size(); i++) {
				fifthlists = new JSONObject(); 	//5
				info = new JSONArray();			//6
				contents = new JSONObject();	//7-1
				
				int n = 0;
				
				//Host Info
				hostList = airdndChatService.selectHostList(signInIdx, listAll.get(0).getAll_hidden_unread());
				userResInfoVO = airdndChatService.selectUserResInfo(signInIdx, hostList.get(i).getIdx());
				latestMsgVO = airdndChatService.selectLatestMsg(signInIdx, hostList.get(i).getIdx(), listAll.get(0).getAll_hidden_unread());
				chattingListA = airdndChatService.selectMsgList(signInIdx, hostList.get(i).getIdx(), listAll.get(0).getAll_hidden_unread());
				
				fifthlists.put("id", hostList.get(i).getIdx());
				fifthlists.put("reservationId", userResInfoVO.getIdx());
				fifthlists.put("state", listAll.get(0).getAll_hidden_unread());
				fifthlists.put("hostname", hostList.get(i).getHost_name());
				
				//contents
				contents.put("hostProfileImg", hostList.get(i).getHost_profileImg());
				contents.put("lastMsg", latestMsgVO.getContent());
				contents.put("lastMsgDate", latestMsgVO.getSend_date_time());
				contents.put("isCanceled", userResInfoVO.getIs_canceled());
				contents.put("checkin", userResInfoVO.getCheckin());
				contents.put("checkout", userResInfoVO.getCheckout());
				
				//info.add(n, contents);
				fifthlists.put("contents", contents);
				
				//chatHistory
				for(int j = 0; j < chattingListA.size(); j++) {
					chatHistory = new JSONObject();
										
					chatHistory.put("id", chattingListA.get(j).getIdx());
					chatHistory.put("userId", chattingListA.get(j).getFrom_idx());
					
					if(hostList.get(i).getIdx() == chattingListA.get(j).getFrom_idx()) {
						chatHistory.put("name", hostList.get(i).getHost_name());
					} else if(signInIdx == chattingListA.get(j).getFrom_idx()) {
						chatHistory.put("name", signInName);
					}
					
					chatHistory.put("timeStamp", chattingListA.get(j).getSend_date_time());
					chatHistory.put("text", chattingListA.get(j).getContent());
					
					info.add(n, chatHistory);
					fifthlists.put("chatHistory", info);
				}

				fourthArr.add(i, fifthlists);
				
				thirdUser.put("messages", fourthArr);
				thirdUser.put("id", signInIdx);
				thirdUser.put("profileImg", signInProfileImg);
				
				resFilter.put("all", thirdUser);
			}
		} else if(filter.equalsIgnoreCase("hidden")) {
			for(int i = 0; i < listHidden.size(); i++) {
				fifthlists = new JSONObject(); 	//5
				info = new JSONArray();			//6
				contents = new JSONObject();	//7-1
				
				int n = 0;
				
				//Host Info
				hostList = airdndChatService.selectHostList(signInIdx, listHidden.get(0).getAll_hidden_unread());
				userResInfoVO = airdndChatService.selectUserResInfo(signInIdx, hostList.get(i).getIdx());
				latestMsgVO = airdndChatService.selectLatestMsg(signInIdx, hostList.get(i).getIdx(), listHidden.get(0).getAll_hidden_unread());
				chattingListH = airdndChatService.selectMsgList(signInIdx, hostList.get(i).getIdx(), listHidden.get(0).getAll_hidden_unread());
				
				fifthlists.put("id", hostList.get(i).getIdx());
				fifthlists.put("reservationId", userResInfoVO.getIdx());
				fifthlists.put("state", listHidden.get(0).getAll_hidden_unread());
				fifthlists.put("hostname", hostList.get(i).getHost_name());
				
				//contents
				contents.put("hostProfileImg", hostList.get(i).getHost_profileImg());
				contents.put("lastMsg", latestMsgVO.getContent());
				contents.put("lastMsgDate", latestMsgVO.getSend_date_time());
				contents.put("isCanceled", userResInfoVO.getIs_canceled());
				contents.put("checkin", userResInfoVO.getCheckin());
				contents.put("checkout", userResInfoVO.getCheckout());
				
				//info.add(n, contents);
				fifthlists.put("contents", contents);
				
				//chatHistory
				for(int j = 0; j < chattingListH.size(); j++) {
					chatHistory = new JSONObject();
										
					chatHistory.put("id", chattingListH.get(j).getIdx());
					chatHistory.put("userId", chattingListH.get(j).getFrom_idx());
					
					if(hostList.get(i).getIdx() == chattingListH.get(j).getFrom_idx()) {
						chatHistory.put("name", hostList.get(i).getHost_name());
					} else if(signInIdx == chattingListH.get(j).getFrom_idx()) {
						chatHistory.put("name", signInName);
					}
					
					chatHistory.put("timeStamp", chattingListH.get(j).getSend_date_time());
					chatHistory.put("text", chattingListH.get(j).getContent());
					
					info.add(n, chatHistory);
					fifthlists.put("chatHistory", info);
				}

				fourthArr.add(i, fifthlists);
				
				thirdUser.put("messages", fourthArr);
				thirdUser.put("id", signInIdx);
				thirdUser.put("profileImg", signInProfileImg);
				
				resFilter.put("hidden", thirdUser);
			}
		} else if(filter.equalsIgnoreCase("unread")) {
			for(int i = 0; i < listUnread.size(); i++) {
				fifthlists = new JSONObject(); 	//5
				info = new JSONArray();			//6
				contents = new JSONObject();	//7-1
				
				int n = 0;
				
				//Host Info
				hostList = airdndChatService.selectHostList(signInIdx, listUnread.get(0).getAll_hidden_unread());
				userResInfoVO = airdndChatService.selectUserResInfo(signInIdx, hostList.get(i).getIdx());
				latestMsgVO = airdndChatService.selectLatestMsg(signInIdx, hostList.get(i).getIdx(), listUnread.get(0).getAll_hidden_unread());
				chattingListU = airdndChatService.selectMsgList(signInIdx, hostList.get(i).getIdx(), listUnread.get(0).getAll_hidden_unread());
				
				fifthlists.put("id", hostList.get(i).getIdx());
				fifthlists.put("reservationId", userResInfoVO.getIdx());
				fifthlists.put("state", listUnread.get(0).getAll_hidden_unread());
				fifthlists.put("hostname", hostList.get(i).getHost_name());
				
				//contents
				contents.put("hostProfileImg", hostList.get(i).getHost_profileImg());
				contents.put("lastMsg", latestMsgVO.getContent());
				contents.put("lastMsgDate", latestMsgVO.getSend_date_time());
				contents.put("isCanceled", userResInfoVO.getIs_canceled());
				contents.put("checkin", userResInfoVO.getCheckin());
				contents.put("checkout", userResInfoVO.getCheckout());
				
				//info.add(n, contents);
				fifthlists.put("contents", contents);
				
				//chatHistory
				for(int j = 0; j < chattingListU.size(); j++) {
					chatHistory = new JSONObject();
										
					chatHistory.put("id", chattingListU.get(j).getIdx());
					chatHistory.put("userId", chattingListU.get(j).getFrom_idx());
					
					if(hostList.get(i).getIdx() == chattingListU.get(j).getFrom_idx()) {
						chatHistory.put("name", hostList.get(i).getHost_name());
					} else if(signInIdx == chattingListU.get(j).getFrom_idx()) {
						chatHistory.put("name", signInName);
					}
					
					chatHistory.put("timeStamp", chattingListU.get(j).getSend_date_time());
					chatHistory.put("text", chattingListU.get(j).getContent());
					
					info.add(n, chatHistory);
					fifthlists.put("chatHistory", info);
				}

				fourthArr.add(i, fifthlists);
				
				thirdUser.put("messages", fourthArr);
				thirdUser.put("id", signInIdx);
				thirdUser.put("profileImg", signInProfileImg);
				
				resFilter.put("unread", thirdUser);
			}
		}
		
		res.put("message", resFilter);
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
		//vo.setContent(request.getParameter("content"));
		
		//airdndChatService.insertChat(vo);
		model.addAttribute("vo", vo);
		
		return "redirect:chat";
	}
}
