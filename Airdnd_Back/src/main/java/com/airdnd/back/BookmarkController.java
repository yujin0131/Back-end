package com.airdnd.back;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import service.AirdndBookmarkService;
import vo.AirdndBookmarkVO;
import vo.AirdndBookmarkedHomesVO;
import vo.AirdndHomePictureVO;
import vo.AirdndUserVO;

@Controller
public class BookmarkController {
	ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	AirdndBookmarkService airdndBookmarkService;

	@Autowired
	HttpServletRequest request;

	@RequestMapping(value = "/wishlists", produces = "application/json;charset=utf8")
	@ResponseBody
	public String bookmark_list(Model model) {
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
		JSONObject res = new JSONObject();			  //1

		JSONArray bookmarkList = new JSONArray();	  //2
		JSONObject bookmark = new JSONObject();		  //3

		JSONArray bookmarkHomeList = new JSONArray(); //4
		JSONObject bookmarkHome = null;				  //5

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		List<AirdndBookmarkVO> list = airdndBookmarkService.selectBookmark(signInIdx);
		int list_size = list.size();
		List<AirdndBookmarkedHomesVO> homes = airdndBookmarkService.selectBookmarkHomes(signInIdx);
		int homes_size = homes.size();
		int[] array = new int[homes_size];

		int temp = homes.get(0).getBookmark_idx();
		int count = 0;

		//Saved homes count
		for(int i = 0; i < list_size; i++) {
			count = airdndBookmarkService.selectBookmarkHomesCount(list.get(i).getIdx());
			list.get(i).setHome_count(count);
		}

		for(int i = 0; i < homes_size; i++) {
			int home_idx = homes.get(i).getHome_idx();

			if(home_idx == 0) {
				homes.get(i).setUrl(null);
			} else {
				List<AirdndHomePictureVO> mainPictures = airdndBookmarkService.selectHomeMainPicture(home_idx);
				String url = mainPictures.get(0).getUrl(); //맨 처음 메인 사진만 가져옴
				homes.get(i).setUrl(url);
			}
		}//for

		model.addAttribute("list", list);
		model.addAttribute("homes", homes);

		//JSON
		for(int i = 0; i < list_size; i++) {
			bookmark = new JSONObject();
			bookmarkHomeList = new JSONArray();

			bookmark.put("bookmarkListId", list.get(i).getIdx());
			//bookmark.put("user_idx", list.get(i).getUser_idx());
			bookmark.put("bookmarkListTitle", list.get(i).getBookmark_list_title());
			bookmark.put("checkin", list.get(i).getCheckin());
			bookmark.put("checkout", list.get(i).getCheckout());
			
			for(int j = 0; j < homes_size; j++) {
				int n  = 0;
				
				if(list.get(i).getHome_count() == 0) {
					//bookmark.put("homes", null);
					break;
				} else {
					if(list.get(i).getIdx() == homes.get(j).getBookmark_idx()) {
						bookmarkHome = new JSONObject();
						
						bookmarkHome.put("homeId", homes.get(j).getIdx());
						//bookmarkHome.put("bookmarkIdx", homes.get(j).getBookmark_idx());
						//bookmarkHome.put("userIdx", homes.get(j).getUser_idx());
						//bookmarkHome.put("homeIdx", homes.get(j).getHome_idx());
						bookmarkHome.put("images", homes.get(j).getUrl());
						
						bookmarkHomeList.add(n, bookmarkHome);
						bookmark.put("bookmarks", bookmarkHomeList);
					}
				}
			}//for

			bookmarkList.add(i, bookmark);
		}//for

		res.put("bookmarkLists", bookmarkList);
		//model.addAttribute("res", res.toString());

		return res.toString();
		//return Common.VIEW_PATH + "bookmark.jsp";
	}
	
	
												//얘 나중에 POST로 바꿔 주셈
	@RequestMapping(value = "/wishlist_insert", method=RequestMethod.GET,
					produces = "application/json;charset=utf8", consumes = MediaType.ALL_VALUE)
	@ResponseBody
	//public String insert_bookmark(AirdndBookmarkVO vo, AirdndBookmarkedHomesVO vo2, Model model) {
	public String insert_bookmark(@RequestBody String payload,
								  @RequestParam(value="checkIn")String checkin,
								  @RequestParam(value="checkOut")String checkout) {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		
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
		

		//북마크 추가
		JSONObject result = new JSONObject();
		String result_msg = "";
		int res = 0;
		
		AirdndBookmarkVO vo = new AirdndBookmarkVO();
		AirdndBookmarkedHomesVO vo2 = new AirdndBookmarkedHomesVO();
		
		//from Login cookie
		vo.setUser_idx(signInIdx);

		//search나 detail 페이지에서 받아와야 함
		int home_idx = 0;
		/*
		if(javaObject.get("home_idx").toString() == null || javaObject.get("home_idx").toString() == "") {
			home_idx = 0;
		} else {
			home_idx = Integer.parseInt(javaObject.get("home_idx").toString());
		}
		vo2.setHome_idx(home_idx);
		*/
		//from Parameter
		checkin = null;
		checkout = null;
		
		try {
			checkin = URLDecoder.decode(checkin, "utf-8");
			checkout = URLDecoder.decode(checkout, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		vo.setBookmark_list_title(payload);
		//북마크 추가
		res = airdndBookmarkService.insert_bookmark(vo);
		
		if(home_idx == 0) {
			//숙소 없이 북마크만 추가할 때
			if(res == 1) {
				System.out.println("새 북마크 추가(북마크 idx : " + vo.getIdx() + ")");
				result_msg = "Success";
			} else {
				System.out.println("북마크를 추가하지 못함");
				result_msg = "Fail";
			}
		} else {
			//숙소 + 북마크 추가할 때
			if(res == 1) {
				res = airdndBookmarkService.insert_bookmarkHome(vo2);
				
				if(res == 1) {
					res = airdndBookmarkService.update_updateTime(vo.getIdx());
					
					if(res == 1) {
						List<AirdndHomePictureVO> mainPictures = airdndBookmarkService.selectHomeMainPicture(home_idx);
						String mainUrl = mainPictures.get(0).getUrl(); //메인 사진만 가져옴
						vo2.setUrl(mainUrl);
						
						System.out.println("새 북마크 및 숙소 추가 완료(북마크 idx : " + vo.getIdx() + ")");
						result_msg = "Success";
					} else {
						System.out.println("시간을 DB에 저장하지 못함");
						result_msg = "Fail_time";
					}
				} else {
					System.out.println("숙소를 추가하지 못함");
					result_msg = "Fail_home";
				}
			} else {
				System.out.println("북마크를 추가하지 못함");
				result_msg = "Fail_bookmark";
			}
		}

		//bookmark_list_title로 idx 얻어오기
		int idx = airdndBookmarkService.selectIdx(vo.getBookmark_list_title());

		vo2.setBookmark_idx(idx);
		vo2.setUser_idx(vo.getUser_idx());
		
		if(vo2.getHome_idx() != 0) {
			airdndBookmarkService.insert_bookmarkHome(vo2);
			airdndBookmarkService.update_updateTime(vo.getIdx());

			List<AirdndHomePictureVO> mainPictures = airdndBookmarkService.selectHomeMainPicture(home_idx);
			String mainUrl = mainPictures.get(0).getUrl(); //메인 사진만 가져옴
			vo2.setUrl(mainUrl);
		}
		
		//model.addAttribute("vo", vo);
		//model.addAttribute("vo2", vo2);

		return "redirect:bookmark";
		//return result_msg;
	}

	@RequestMapping("/wishlist_insertHome")
	public String insert_bookmarkHome(AirdndBookmarkVO vo, AirdndBookmarkedHomesVO vo2, Model model) {
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

		//vo의 idx이자 vo2의 bookmark_idx
		int idx = 0;

		if(request.getParameter("idx") == null) {
			idx = 0;
		} else if(request.getParameter("idx") != null || Integer.parseInt(request.getParameter("idx")) != 0) {
			idx = Integer.parseInt(request.getParameter("idx"));
			vo.setIdx(idx);
		}

		//search나 detail 페이지에서 받아와야 함
		int home_idx = 0;

		if(request.getParameter("home_idx") == null) {
			home_idx = 0;
		} else if(request.getParameter("home_idx") != null || Integer.parseInt(request.getParameter("home_idx")) != 0) {
			home_idx = Integer.parseInt(request.getParameter("home_idx"));
		}

		vo2.setBookmark_idx(vo.getIdx());
		vo2.setUser_idx(vo.getUser_idx());
		vo2.setHome_idx(home_idx);

		if(vo2.getHome_idx() != 0) {
			airdndBookmarkService.insert_bookmarkHome(vo2);
			airdndBookmarkService.update_updateTime(vo.getIdx());

			List<AirdndHomePictureVO> mainPictures = airdndBookmarkService.selectHomeMainPicture(home_idx);
			String url = mainPictures.get(0).getUrl(); //맨 처음 메인 사진만 가져옴
			vo2.setUrl(url);
		}

		model.addAttribute("vo", vo);
		model.addAttribute("vo2", vo2);

		return "redirect:bookmark";
	}

	@RequestMapping("/wishlist_deleteHome")
	public String delete_bookmarkHome(AirdndBookmarkVO vo, AirdndBookmarkedHomesVO vo2, Model model) {
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
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		int bookmark_idx = Integer.parseInt(request.getParameter("bookmark_idx"));

		vo2.setIdx(idx);
		vo.setIdx(bookmark_idx);

		airdndBookmarkService.delete_bookmarkHome(vo2.getIdx());
		airdndBookmarkService.update_updateTime(vo.getIdx());

		model.addAttribute("vo", vo);
		model.addAttribute("vo2", vo2);

		return "redirect:bookmark";
	}

	@RequestMapping("/wishlist_delete")
	public String delete_bookmark(AirdndBookmarkVO vo, Model model) {
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
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		vo.setIdx(idx);

		airdndBookmarkService.delete_bookmark(vo.getIdx());

		model.addAttribute("vo", vo);

		return "redirect:bookmark";
	}
}