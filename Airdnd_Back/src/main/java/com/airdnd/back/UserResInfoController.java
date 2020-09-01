package com.airdnd.back;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import service.AirdndUserResInfoService;
import vo.AirdndHomePictureVO;
import vo.AirdndUserResInfoVO;

@Controller
public class UserResInfoController {
	@Autowired
	AirdndUserResInfoService airdndUserResInfoService;
	
	@RequestMapping("/userResInfo_upcoming")
	public String user_res_info_list1(Model model) {
		JSONObject jsonObject1 = new JSONObject();
		
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
		
		for(int i = 0; i < size; i++) {
			Map<Object, Object> javaObject = new HashMap<Object, Object>();
			
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
			javaObject.put("idx", list1.get(i).getIdx());
			javaObject.put("user_idx", list1.get(i).getUser_idx());
			javaObject.put("home_idx", list1.get(i).getHome_idx());
			javaObject.put("checkin", list1.get(i).getCheckin());
			javaObject.put("checkout", list1.get(i).getCheckout());
			javaObject.put("guest_idx", list1.get(i).getGuest_idx());
			javaObject.put("is_canceled", list1.get(i).getIs_canceled());
			
			jsonObject1.put(i, javaObject);
		}//for
		
		//return jsonObject1.toString();
		return Common.VIEW_PATH + "userResInfo_1upcoming.jsp";
	}
	
	@RequestMapping("/userResInfo_past")
	public String user_res_info_list2(Model model) {
		JSONObject jsonObject2 = new JSONObject();
		
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
			Map<Object, Object> javaObject = new HashMap<Object, Object>();
			
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
			javaObject.put("idx", list2.get(i).getIdx());
			javaObject.put("user_idx", list2.get(i).getUser_idx());
			javaObject.put("home_idx", list2.get(i).getHome_idx());
			javaObject.put("checkin", list2.get(i).getCheckin());
			javaObject.put("checkout", list2.get(i).getCheckout());
			javaObject.put("guest_idx", list2.get(i).getGuest_idx());
			javaObject.put("is_canceled", list2.get(i).getIs_canceled());
			
			jsonObject2.put(i, javaObject);
		}//for
		
		//return jsonObject2.toString();
		return Common.VIEW_PATH + "userResInfo_2past.jsp";
	}
	
	@RequestMapping("/userResInfo_canceled")
	public String user_res_info_list3(Model model) {
		JSONObject jsonObject3 = new JSONObject();
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
			Map<Object, Object> javaObject = new HashMap<Object, Object>();
			
			if(list.get(i).getIs_canceled() != 0) {
				list3.add(list.get(i));
				model.addAttribute("list3", list3);
			}
			javaObject.put("idx", list3.get(i).getIdx());
			javaObject.put("user_idx", list3.get(i).getUser_idx());
			javaObject.put("home_idx", list3.get(i).getHome_idx());
			javaObject.put("checkin", list3.get(i).getCheckin());
			javaObject.put("checkout", list3.get(i).getCheckout());
			javaObject.put("guest_idx", list3.get(i).getGuest_idx());
			javaObject.put("is_canceled", list3.get(i).getIs_canceled());
			
			jsonObject3.put(i, javaObject);
		}//for
		
		//return jsonObject3.toString();
		return Common.VIEW_PATH + "userResInfo_3canceled.jsp";
	}
}
