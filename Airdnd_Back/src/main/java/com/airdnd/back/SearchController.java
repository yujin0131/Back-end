package com.airdnd.back;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.AirdndSearchService;
import vo.AirdndHomePictureVO;
import vo.AirdndSearchVO;

@Controller
public class SearchController {

	@Autowired
	AirdndSearchService airdndsearchService;
	
	@RequestMapping("/search" )
	public String check() {
		String place = "서울";
		String user_idx = "1";
		String page = "1";
		List<AirdndSearchVO> search_list = airdndsearchService.searchselect(place);
		//List<AirdndBookmarkVO> user_bookmark = (user_idx);
		int size = search_list.size();
		System.out.println("size : " + size);
		JSONObject jsonObject = new JSONObject();
		
		for(int i = 0; i < size; i++) {
			int home_idx = search_list.get(i).getHome_idx();
			System.out.println("home_idx : " + home_idx);
			List<AirdndHomePictureVO> picture = airdndsearchService.pictureselect(home_idx);
			
			List<String> picture_arr = new ArrayList<String>();
			Map<Object, Object> javaObject = new HashMap<Object, Object>();
			Map<String, Object> latlng = new HashMap<String, Object>();

			for(int j = 0; j < picture.size(); j++) {
				
				picture_arr.add(picture.get(j).getUrl());
			}
		
			search_list.get(i).setUrl(picture_arr);
			latlng.put("lat", search_list.get(i).getLat());
			latlng.put("lng", search_list.get(i).getLng());
			
			System.out.println("picture size : " + search_list.get(i).getUrl().size());
			
			javaObject.put("homeId", search_list.get(i).getHome_idx());
			javaObject.put("isSuperhost", search_list.get(i).getIsSuperHost());
			javaObject.put("isBookmarked", "아직안받아옴");
			javaObject.put("imageArray", search_list.get(i).getUrl());
			javaObject.put("imageCount", search_list.get(i).getUrl().size());
			javaObject.put("subTitle", search_list.get(i).getSub_title());
			javaObject.put("title", search_list.get(i).getTitle());
			javaObject.put("feature", "최대 인원 " + search_list.get(i).getFilter_max_person() + "명 . 침실 " + search_list.get(i).getFilter_bedroom() + 
					"개 . 침대 " + search_list.get(i).getFilter_bed() + "개 . 욕실 " + search_list.get(i).getFilter_bathroom() + "개");
			javaObject.put("rating", search_list.get(i).getRating());
			javaObject.put("reviewCount", search_list.get(i).getReview_num());
			javaObject.put("price", search_list.get(i).getPrice());
			javaObject.put("location", latlng.toString());

			jsonObject.put(i, javaObject);
			System.out.println("json : " + jsonObject.toString());
		}
		System.out.println("outer : " + jsonObject.toString());
		String result = "jsonObject.toString()";
		try {
			result = URLEncoder.encode(result, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value="/initialState/location/{location}/checkIn/{checkIn}/checkOut/{checkOut}/adults/{adults}", method=RequestMethod.GET )
	@ResponseBody			// 어디검색, 몇박며칠, 인원수...
	public String check(@PathVariable String location, @PathVariable String checkIn, @PathVariable String checkOut, @PathVariable int adult) {

		try {
			location = URLEncoder.encode(location, "utf-8");
			checkIn = URLEncoder.encode(checkIn, "utf-8");
			checkOut = URLEncoder.encode(checkOut, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		if(location.equals("guam")) {
			location = "괌";
		}else if(location.equals("jeju")) {
			location = "제주도";
		}else {
			location = "서울";
		}
		
		List<AirdndSearchVO> search_list = airdndsearchService.searchselect(location);
		int size = search_list.size();
		JSONObject jsonObject = new JSONObject();
		
		for(int i = 0; i < size; i++) {
			int home_idx = search_list.get(i).getHome_idx();
			
			List<AirdndHomePictureVO> picture = airdndsearchService.pictureselect(home_idx);
			List<String> picture_arr = new ArrayList<String>();
			Map<Object, Object> javaObject = new HashMap<Object, Object>();
			Map<String, Object> latlng = new HashMap<String, Object>();
			
			for(int j = 0; j < picture.size(); j++) {
				picture_arr.add(picture.get(j).getUrl());
			}
		
			search_list.get(i).setUrl(picture_arr);
			
			latlng.put("lat", search_list.get(i).getLat());
			latlng.put("lng", search_list.get(i).getLng());
			
			javaObject.put("homeId", search_list.get(i).getHome_idx());
			javaObject.put("isSuperhost", search_list.get(i).getIsSuperHost());
			javaObject.put("isBookmarked", "아직안받아옴");
			javaObject.put("imageArray", search_list.get(i).getUrl());
			javaObject.put("imageCount", search_list.get(i).getUrl().size());
			javaObject.put("subTitle", search_list.get(i).getSub_title());
			javaObject.put("title", search_list.get(i).getTitle());
			javaObject.put("feature", "최대 인원 " + search_list.get(i).getFilter_max_person() + "명 . 침실 " + search_list.get(i).getFilter_bedroom() + 
					"개 . 침대 " + search_list.get(i).getFilter_bed() + "개 . 욕실 " + search_list.get(i).getFilter_bathroom() + "개");
			javaObject.put("rating", search_list.get(i).getRating());
			javaObject.put("reviewCount", search_list.get(i).getReview_num());
			javaObject.put("price", search_list.get(i).getPrice());
			javaObject.put("location", latlng.toString());
			
			jsonObject.put(i, javaObject);
			System.out.println("json : " + jsonObject.toString());
		}

		String result = jsonObject.toString();
		try {
			result = URLEncoder.encode(result, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}
