package com.airdnd.back;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Common;
import service.AirdndUserResInfoService;
import vo.AirdndHomePictureVO;
import vo.AirdndUserResInfoVO;

@Controller
public class UserResInfoController {
	@Autowired
	AirdndUserResInfoService airdndUserResInfoService;
	
	@RequestMapping(value = "/userResInfo_upcoming", produces = "application/json;charset=utf8")
	@ResponseBody
	public String user_res_info_list1(Model model) {
		JSONObject resUpcoming = new JSONObject();
		JSONArray upcomingList = new JSONArray();
		
		List<AirdndUserResInfoVO> list = airdndUserResInfoService.selectUserResInfo();
		//1. Upcoming reservation list
		List<AirdndUserResInfoVO> list1 = new ArrayList<AirdndUserResInfoVO>();
		int size = list.size();
		
		for(int i = 0; i < size; i++) {
			int home_idx = list.get(i).getHome_idx();
			
			List<AirdndHomePictureVO> mainPictures = airdndUserResInfoService.selectHomeMainPicture(home_idx);
			String url = mainPictures.get(0).getUrl(); //맨 처음 메인 사진만 가져옴
			list.get(i).setUrl(url);
		}//for
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		//Today's date
		int ymd = Integer.parseInt(format.format(date));
		
		String[] checkout_temp = null;
		String checkout = "";
		int checkoutDate = 0;
		
		System.out.println("사이즈 : " + size);
		
		for(int i = 0; i < size; i++) {
			if(list.get(i).getIs_canceled() == 0) {
				checkout_temp = list.get(i).getCheckout().split("-");
				checkout = "";
				
				for(int j = 0; j < 3; j++) {
					checkout += checkout_temp[j];	
				}
				
				checkoutDate = Integer.parseInt(checkout);
				
				if(ymd - checkoutDate <= 0) {
					//1. Upcoming reservation
					list1.add(list.get(i));
					model.addAttribute("list1", list1);
				}
			}
		}//for
		
		for(int i = 0; i < list1.size(); i++) {
			JSONObject javaObject = new JSONObject();
			
			javaObject.put("idx", list1.get(i).getIdx());
			javaObject.put("user_idx", list1.get(i).getUser_idx());
			javaObject.put("home_idx", list1.get(i).getHome_idx());
			javaObject.put("checkin", list1.get(i).getCheckin());
			javaObject.put("checkout", list1.get(i).getCheckout());
			javaObject.put("guest_idx", list1.get(i).getGuest_idx());
			javaObject.put("is_canceled", list1.get(i).getIs_canceled());
			javaObject.put("url", list1.get(i).getUrl());
			
			upcomingList.add(i, javaObject);
		}
		
		resUpcoming.put("upcomingList", upcomingList);
		
		model.addAttribute("res", resUpcoming.toString());
		//System.out.println(resUpcoming.toString());
		
		return resUpcoming.toString();
		//return Common.VIEW_PATH + "userResInfo_1upcoming.jsp";
	}
	
	@RequestMapping(value = "/userResInfo_past", produces = "application/json;charset=utf8")
	@ResponseBody
	public String user_res_info_list2(Model model) {
		JSONObject resPast = new JSONObject();
		JSONArray pastList = new JSONArray();
		
		List<AirdndUserResInfoVO> list = airdndUserResInfoService.selectUserResInfo();
		//2. Past reservation list
		List<AirdndUserResInfoVO> list2 = new ArrayList<AirdndUserResInfoVO>();
		int size = list.size();
		
		for(int i = 0; i < size; i++) {
			int home_idx = list.get(i).getHome_idx();
			
			List<AirdndHomePictureVO> mainPictures = airdndUserResInfoService.selectHomeMainPicture(home_idx);
			String url = mainPictures.get(0).getUrl(); //맨 처음 메인 사진만 가져옴
			list.get(i).setUrl(url);
		}//for
		
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		//Today's date
		int ymd = Integer.parseInt(format.format(date));
		
		String[] checkout_temp = null;
		String checkout = "";
		int checkoutDate = 0;
		
		for(int i = 0; i < size; i++) {
			if(list.get(i).getIs_canceled() == 0) {
				checkout_temp = list.get(i).getCheckout().split("-");
				checkout = "";
				
				for(int j = 0; j < 3; j++) {
					checkout += checkout_temp[j];	
				}
				
				checkoutDate = Integer.parseInt(checkout);
				
				if(ymd - checkoutDate > 0) {
					//2. Past reservation
					list2.add(list.get(i));
					model.addAttribute("list2", list2);
				}
			}
		}//for
		
		for(int i = 0; i < list2.size(); i++) {
			JSONObject javaObject = new JSONObject();
			
			javaObject.put("idx", list2.get(i).getIdx());
			javaObject.put("user_idx", list2.get(i).getUser_idx());
			javaObject.put("home_idx", list2.get(i).getHome_idx());
			javaObject.put("checkin", list2.get(i).getCheckin());
			javaObject.put("checkout", list2.get(i).getCheckout());
			javaObject.put("guest_idx", list2.get(i).getGuest_idx());
			javaObject.put("is_canceled", list2.get(i).getIs_canceled());
			javaObject.put("url", list2.get(i).getUrl());
			
			pastList.add(i, javaObject);
		}
		
		resPast.put("pastList", pastList);
		
		model.addAttribute("res", resPast.toString());
		System.out.println(resPast.toString());
		
		return resPast.toString();
		//return Common.VIEW_PATH + "userResInfo_2past.jsp";
	}
	
	@RequestMapping(value = "/userResInfo_canceled", produces = "application/json;charset=utf8")
	@ResponseBody
	public String user_res_info_list3(Model model) {
		JSONObject resCanceled = new JSONObject();
		JSONArray canceledList = new JSONArray();
		
		JSONObject jsonObject = new JSONObject();
		
		List<AirdndUserResInfoVO> list = airdndUserResInfoService.selectUserResInfo();
		//3. Canceled reservation list
		List<AirdndUserResInfoVO> list3 = new ArrayList<AirdndUserResInfoVO>();
		int size = list.size();
		
		for(int i = 0; i < size; i++) {
			int home_idx = list.get(i).getHome_idx();
			
			List<AirdndHomePictureVO> mainPictures = airdndUserResInfoService.selectHomeMainPicture(home_idx);
			String url = mainPictures.get(0).getUrl(); //맨 처음 메인 사진만 가져옴
			list.get(i).setUrl(url);
		}//for
		
		//3. Canceled reservation
		for(int i = 0; i < size; i++) {
			if(list.get(i).getIs_canceled() != 0) {
				list3.add(list.get(i));
				model.addAttribute("list3", list3);
			}
		}//for
		
		for(int i = 0; i < list3.size(); i++) {
			JSONObject javaObject = new JSONObject();
			
			javaObject.put("idx", list3.get(i).getIdx());
			javaObject.put("user_idx", list3.get(i).getUser_idx());
			javaObject.put("home_idx", list3.get(i).getHome_idx());
			javaObject.put("checkin", list3.get(i).getCheckin());
			javaObject.put("checkout", list3.get(i).getCheckout());
			javaObject.put("guest_idx", list3.get(i).getGuest_idx());
			javaObject.put("is_canceled", list3.get(i).getIs_canceled());
			javaObject.put("url", list3.get(i).getUrl());
			
			jsonObject.put(i, javaObject);
			
			canceledList.add(i, javaObject);
		}
		
		resCanceled.put("canceledList", canceledList);
		
		model.addAttribute("res", resCanceled.toString());
		System.out.println(resCanceled.toString());
		
		return resCanceled.toString();
		//return Common.VIEW_PATH + "userResInfo_3canceled.jsp";
	}
}
