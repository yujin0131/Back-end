package com.airdnd.back;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import service.AirdndHomeService;
import service.AirdndSearchService;
import vo.AirdndHomeVO;
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

		model.addAttribute("list", list);

		return Common.VIEW_PATH + "search.jsp";
	}
}
