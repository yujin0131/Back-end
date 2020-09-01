package com.airdnd.back;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import service.AirdndBookmarkService;
import vo.AirdndBookmarkVO;
import vo.AirdndHomePictureVO;

@Controller
public class BookmarkController {
	@Autowired
	AirdndBookmarkService airdndBookmarkService;
	
	@Autowired
	HttpServletRequest request;
	
	@RequestMapping("/bookmark")
	public String bookmark_list(Model model) {
		JSONObject jsonObject = new JSONObject();
		
		List<AirdndBookmarkVO> list = airdndBookmarkService.selectBookmark();
		int size = list.size();
		
		for(int i = 0; i < size; i++) {
			Map<Object, Object> javaObject = new HashMap<Object, Object>();
			int home_idx = list.get(i).getHome_idx();
			
			if(home_idx == 0) {
				list.get(i).setUrl(null);
			} else {
				List<AirdndHomePictureVO> mainPictures = airdndBookmarkService.selectHomeMainPicture(home_idx);
				String url = mainPictures.get(0).getUrl(); //맨 처음 메인 사진만 가져옴
				list.get(i).setUrl(url);
			}
			javaObject.put("idx", list.get(i).getIdx());
			javaObject.put("user_idx", list.get(i).getUser_idx());
			javaObject.put("home_idx", list.get(i).getHome_idx());
			javaObject.put("review_idx", list.get(i).getReview_idx());
			javaObject.put("bookmark_list_title", list.get(i).getBookmark_list_title());
			javaObject.put("checkin", list.get(i).getCheckin());
			javaObject.put("checkout", list.get(i).getCheckout());
			
			jsonObject.put(i, javaObject);
		}//for
		
		model.addAttribute("list", list);
		
		//return jsonObject.toString();
		return Common.VIEW_PATH + "bookmark.jsp";
	}
	
	@RequestMapping("/bookmark_insert")
	public String insert_bookmark(AirdndBookmarkVO vo, Model model) {
		//Temp. 로그인 세션이나 쿠키에서 받아와야 함
		vo.setUser_idx(1);
		
		//search나 detail 페이지에서 받아와야 함
		int home_idx = 0;
		
		/*
		if(request.getParameter("home_idx") != null || Integer.parseInt(request.getParameter("home_idx")) != 0) {
			home_idx = Integer.parseInt(request.getParameter("home_idx"));
		}
		*/
		
		vo.setHome_idx(home_idx);
		vo.setBookmark_list_title(request.getParameter("bookmark_list_title"));
		
		//Temp. 검색에서 받아와야 함
		vo.setCheckin(null);
		vo.setCheckout(null);
		
		airdndBookmarkService.insert_bookmark(vo);
		model.addAttribute("vo", vo);
		
		return "redirect:bookmark";
	}
	
	@RequestMapping("/bookmark_updateHome")
	public String update_bookmarkHome(AirdndBookmarkVO vo, Model model) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		int home_idx = Integer.parseInt(request.getParameter("home_idx"));
		
		vo.setIdx(idx);
		vo.setHome_idx(home_idx);
		
		airdndBookmarkService.update_bookmarkHome(vo);
		model.addAttribute("vo", vo);
		
		return "redirect:bookmark";
	}
	
	@RequestMapping("/bookmark_deleteHome")
	public String delete_bookmarkHome(AirdndBookmarkVO vo, Model model) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		vo.setIdx(idx);
		
		airdndBookmarkService.delete_bookmarkHome(vo);
		
		return "redirect:bookmark";
	}
}
