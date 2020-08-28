package com.airdnd.back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import service.AirdndSearchService;
import vo.AirdndHomePictureVO;
import vo.AirdndSearchVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SearchController {

	@Autowired
	AirdndSearchService airdndsearchService;

	@RequestMapping("/search")
	public String check(Model model) {
		List<AirdndSearchVO> list = airdndsearchService.searchselect();
		int size = list.size();
		List<String> home_idx_picture = new ArrayList<String>();
		
		for(int i = 0; i < size; i++) {
			int home_idx = list.get(i).getHome_idx();
			List<AirdndHomePictureVO> picture = airdndsearchService.pictureselect(home_idx);
			
			List<String> picture_arr = new ArrayList<String>();

			for(int j = 0; j < picture.size(); j++) {
				
				picture_arr.add(picture.get(j).getUrl());
			}

			home_idx_picture.add(i, picture_arr.toString());
			
		}
		model.addAttribute("list", list);
		model.addAttribute("arr", home_idx_picture);

		return Common.VIEW_PATH + "search.jsp";
	}
}
