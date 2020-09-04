package com.airdnd.back;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.AirdndBookmarkService;
import vo.AirdndBookmarkVO;
import vo.AirdndBookmarkedHomesVO;
import vo.AirdndHomePictureVO;

@Controller
public class BookmarkController {
	@Autowired
	AirdndBookmarkService airdndBookmarkService;

	@Autowired
	HttpServletRequest request;

	@RequestMapping(value = "/bookmark", produces = "application/json;charset=utf8")
	@ResponseBody
	public String bookmark_list(Model model) {
		//파싱할 최종 데이터
		JSONObject res = new JSONObject();

		JSONArray bookmarkList = new JSONArray();
		JSONObject bookmark = new JSONObject();

		JSONArray bookmarkHomeList = new JSONArray();
		JSONObject bookmarkHome = null;

		Map<Integer, Integer> map = new HashMap<Integer, Integer>();

		List<AirdndBookmarkVO> list = airdndBookmarkService.selectBookmark();
		int list_size = list.size();
		List<AirdndBookmarkedHomesVO> homes = airdndBookmarkService.selectBookmarkHomes();
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

			bookmark.put("idx", list.get(i).getIdx());
			//bookmark.put("user_idx", list.get(i).getUser_idx());
			bookmark.put("bookmark_list_title", list.get(i).getBookmark_list_title());
			bookmark.put("checkin", list.get(i).getCheckin());
			bookmark.put("checkout", list.get(i).getCheckout());
			
			for(int j = 0; j < homes_size; j++) {
				
				int k  = 0;
				
				if(list.get(i).getHome_count() == 0) {
					//bookmark.put("homes", null);
					break;
				} else {
					if(list.get(i).getIdx() == homes.get(j).getBookmark_idx()) {
						bookmarkHome = new JSONObject();
						
						bookmarkHome.put("idx", homes.get(j).getIdx());
						bookmarkHome.put("bookmarkIdx", homes.get(j).getBookmark_idx());
						//bookmarkHome.put("userIdx", homes.get(j).getUser_idx());
						//bookmarkHome.put("homeIdx", homes.get(j).getHome_idx());
						bookmarkHome.put("url", homes.get(j).getUrl());
						
						System.out.print(homes.get(j).getIdx() + "/");
						System.out.print(homes.get(j).getBookmark_idx() + "/");
						System.out.println(homes.get(j).getUrl());
						
						bookmarkHomeList.add(k, bookmarkHome);
						bookmark.put("homes", bookmarkHomeList);
					}
				}
			}//for

			bookmarkList.add(i, bookmark);
		}//for

		res.put("bookmark", bookmarkList);
		//model.addAttribute("res", res.toString());

		return res.toString();
		//return Common.VIEW_PATH + "bookmark.jsp";
	}

	@RequestMapping(value = "/bookmark_insert", method=RequestMethod.POST,
			produces = "application/json;charset=utf8", consumes = MediaType.ALL_VALUE)
	//@ResponseBody
	public String insert_bookmark(AirdndBookmarkVO vo, AirdndBookmarkedHomesVO vo2, Model model) {
		//Temp. 로그인 세션이나 쿠키에서 받아와야 함
		vo.setUser_idx(1);

		//search나 detail 페이지에서 받아와야 함
		int home_idx = 0;

		if(request.getParameter("home_idx") == null) {
			home_idx = 0;
		} else if(request.getParameter("home_idx") != null || Integer.parseInt(request.getParameter("home_idx")) != 0) {
			home_idx = Integer.parseInt(request.getParameter("home_idx"));
		}

		vo.setBookmark_list_title(request.getParameter("bookmark_list_title"));

		//Temp. 검색에서 받아와야 함
		vo.setCheckin(null);
		vo.setCheckout(null);

		airdndBookmarkService.insert_bookmark(vo);

		//bookmark_list_title로 idx 얻어오기
		int idx = airdndBookmarkService.selectIdx(vo.getBookmark_list_title());

		vo2.setBookmark_idx(idx);
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

	@RequestMapping("/bookmark_insertHome")
	public String insert_bookmarkHome(AirdndBookmarkVO vo, AirdndBookmarkedHomesVO vo2, Model model) {
		//Temp. 로그인 세션이나 쿠키에서 받아와야 함
		vo.setUser_idx(1);

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

	@RequestMapping("/bookmark_deleteHome")
	public String delete_bookmarkHome(AirdndBookmarkVO vo, AirdndBookmarkedHomesVO vo2, Model model) {
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

	@RequestMapping("/bookmark_delete")
	public String delete_bookmark(AirdndBookmarkVO vo, Model model) {
		int idx = Integer.parseInt(request.getParameter("idx"));
		vo.setIdx(idx);

		airdndBookmarkService.delete_bookmark(vo.getIdx());

		model.addAttribute("vo", vo);

		return "redirect:bookmark";
	}


	/*
   @RequestMapping("/bookmark_insert")
   public String insert_bookmark(AirdndBookmarkVO vo, Model model) {
      //Temp. 로그인 세션이나 쿠키에서 받아와야 함
      vo.setUser_idx(1);

      //search나 detail 페이지에서 받아와야 함
      int home_idx = 0;

      //vo.setHome_idx(home_idx);
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
      //vo.setHome_idx(home_idx);

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
	 */
}