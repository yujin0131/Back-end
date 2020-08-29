package com.airdnd.back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import common.Common;
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
		List<AirdndSearchVO> search_list = airdndsearchService.searchselect(place);
		int size = search_list.size();
		System.out.println("size : " + size);
		JSONObject jsonObject = new JSONObject();
		
		for(int i = 0; i < size; i++) {
			int home_idx = search_list.get(i).getHome_idx();
			System.out.println("home_idx : " + home_idx);
			List<AirdndHomePictureVO> picture = airdndsearchService.pictureselect(home_idx);
			
			List<String> picture_arr = new ArrayList<String>();
			Map<Object, Object> javaObject = new HashMap<Object, Object>();

			for(int j = 0; j < picture.size(); j++) {
				
				picture_arr.add(picture.get(j).getUrl());
			}
		
			search_list.get(i).setUrl(picture_arr);
			
			javaObject.put("place", search_list.get(i).getPlace());
			javaObject.put("home_idx", search_list.get(i).getHome_idx());
			javaObject.put("sub_title", search_list.get(i).getSub_title());
			javaObject.put("title", search_list.get(i).getTitle());
			javaObject.put("f_per", search_list.get(i).getFilter_max_person());
			javaObject.put("f_broom", search_list.get(i).getFilter_bedroom());
			javaObject.put("f_bed", search_list.get(i).getFilter_bed());
			javaObject.put("f_bath", search_list.get(i).getFilter_bathroom());
			javaObject.put("price", search_list.get(i).getPrice());
			javaObject.put("rating", search_list.get(i).getRating());
			javaObject.put("review_num", search_list.get(i).getReview_num());
			javaObject.put("lat", search_list.get(i).getLat());
			javaObject.put("lng", search_list.get(i).getLng());
			javaObject.put("picture", search_list.get(i).getUrl());
			System.out.println("json_idx : " + javaObject.get("home_idx").toString());
			System.out.println(" ");
			System.out.println("inner : " + javaObject.toString());
			jsonObject.put(i, javaObject);
			System.out.println("json : " + jsonObject.toString());
		}
		System.out.println("outer : " + jsonObject.toString());
		return jsonObject.toString();
	}

	@RequestMapping(value="/initialState", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody			// 어디검색, 몇박며칠, 인원수...
	public String check(@PathVariable String place, @PathVariable String checkIn, @PathVariable String checkOut, @PathVariable int adults) {
		
		List<AirdndSearchVO> search_list = airdndsearchService.searchselect(place);
		int size = search_list.size();
		JSONObject jsonObject = new JSONObject();
		
		for(int i = 0; i < size; i++) {
			int home_idx = search_list.get(i).getHome_idx();
			
			List<AirdndHomePictureVO> picture = airdndsearchService.pictureselect(home_idx);
			List<String> picture_arr = new ArrayList<String>();
			Map<Object, Object> javaObject = new HashMap<Object, Object>();
			
			for(int j = 0; j < picture.size(); j++) {
				picture_arr.add(picture.get(j).getUrl());
			}
		
			search_list.get(i).setUrl(picture_arr);
			
			javaObject.put("place", search_list.get(i).getPlace());
			javaObject.put("home_idx", search_list.get(i).getHome_idx());
			javaObject.put("sub_title", search_list.get(i).getSub_title());
			javaObject.put("title", search_list.get(i).getTitle());
			javaObject.put("f_per", search_list.get(i).getFilter_max_person());
			javaObject.put("f_broom", search_list.get(i).getFilter_bedroom());
			javaObject.put("f_bed", search_list.get(i).getFilter_bed());
			javaObject.put("f_bath", search_list.get(i).getFilter_bathroom());
			javaObject.put("price", search_list.get(i).getPrice());
			javaObject.put("rating", search_list.get(i).getRating());
			javaObject.put("review_num", search_list.get(i).getReview_num());
			javaObject.put("lat", search_list.get(i).getLat());
			javaObject.put("lng", search_list.get(i).getLng());
			javaObject.put("picture", search_list.get(i).getUrl());
			
			jsonObject.put(i, javaObject);
		}

		return jsonObject.toString();
	}
}
