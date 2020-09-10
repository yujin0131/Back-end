package com.airdnd.back;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
					List arr = new ArrayList();
					bookmark.put("bookmarks", arr);
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
	
	@RequestMapping(value="/wishlist_insert", method=RequestMethod.POST,
					produces="application/json;charset=utf8", consumes=MediaType.ALL_VALUE)
	//, @RequestParam(value="checkIn")String checkIn, @RequestParam(value="checkOut")String checkOut
	@ResponseBody
	public String insert_bookmark(@RequestBody String payload) {
		System.out.println(payload);
		
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
		
		//initialization
		JSONObject result = new JSONObject();
		Map<String, Object> javaObject = null;
		int result_idx = 0;
		int res = 0;
		int lastIdx = 0;
		
		AirdndBookmarkVO vo = new AirdndBookmarkVO();
		AirdndBookmarkedHomesVO vo2 = new AirdndBookmarkedHomesVO();
		
		int home_idx = 0;
		String bookmark_list_title = null;
		String url = null;
		
		//from Login cookie
		vo.setUser_idx(signInIdx);
		vo2.setUser_idx(signInIdx);
		
		//from Parameter
		/*
		checkIn = null;
		checkOut = null;
		System.out.println("하하 잘 나오느냐 : " + checkIn + "/" + checkOut);
		
		try {
			checkIn = URLDecoder.decode(checkIn, "utf-8");
			checkOut = URLDecoder.decode(checkOut, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
		//from Payload
		try {
			javaObject = mapper.readValue(payload, Map.class);
			System.out.println("payload가 잘 돼용!");
		} catch (Exception e) {
			System.out.println("payload 오류");
		}
		System.out.println("javaObject: " + javaObject);
		
		if(javaObject.get("bookmarkListTitle").toString() != null) {
			bookmark_list_title = javaObject.get("bookmarkListTitle").toString();
		}
		if(javaObject.get("bookmarkHomeId").toString() != null) {
			home_idx = Integer.parseInt(javaObject.get("bookmarkHomeId").toString());
		}
		
		if(javaObject.get("bookmarkImage").toString() != null) {
			url = javaObject.get("bookmarkImage").toString();
		}
		/*
		if(checkIn != null) {
			vo.setCheckin(checkIn);
		}
		if(checkOut != null) {
			vo.setCheckout(checkOut);
		}
		*/
		
		//create bookmark without home
		if(home_idx == 0 && bookmark_list_title != null && url == null) {
			vo.setBookmark_list_title(bookmark_list_title);
			
			res = airdndBookmarkService.insert_bookmark(vo);
			lastIdx = airdndBookmarkService.selectNewBookmarkInfo();
			
			if(res != 0 && lastIdx != 0) {
				System.out.println("새 북마크 추가(북마크 idx : " + lastIdx + ")");
				result_idx = lastIdx;
			} else {
				System.out.println("북마크를 추가하지 못함");
			}
		  //add home in bookmark
		} else if(home_idx != 0 && bookmark_list_title == null && url != null) {
			vo2.setHome_idx(home_idx);
			vo2.setUrl(url);

			res = airdndBookmarkService.insert_bookmarkHome(vo2);
			
			if(res == 1) {
				res = airdndBookmarkService.update_updateTime(vo.getIdx());
				
				if(res == 1) {
					System.out.println("숙소 추가 완료(북마크 idx : " + lastIdx + ")");
					result_idx = vo.getIdx();
				}
			} else {
				System.out.println("북마크를 추가하지 못함");
			}
		  //create bookmark with home
		} else if(home_idx != 0 && bookmark_list_title != null && url != null) {
			vo2.setHome_idx(home_idx);
			vo.setBookmark_list_title(bookmark_list_title);
			vo2.setUrl(url);
			
			res = airdndBookmarkService.insert_bookmark(vo);
			
			if(res == 1) {
				vo2.setBookmark_idx(res);
				res = airdndBookmarkService.insert_bookmarkHome(vo2);
				
				if(res == 1) {
					res = airdndBookmarkService.update_updateTime(vo.getIdx());
					
					if(res == 1) {
						System.out.println("새 북마크 및 숙소 추가 완료(북마크 idx : " + vo.getIdx() + ")");
						result_idx = vo.getIdx();
					}
				}
			} else {
				System.out.println("북마크를 추가하지 못함");
			}
		}
		
		result.put("result", result_idx);

		//model.addAttribute("vo", vo);
		//model.addAttribute("vo2", vo2);

		//return "redirect:bookmark";
		return result.toString();
	}
	
	@RequestMapping("/wishlist_delete")
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
	
	/*
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
	*/
}