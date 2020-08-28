package com.airdnd.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import service.AirdndHomeService;
import vo.AirdndHomeVO;

//재은언니,,,여기는 다시 하셔야 해용 일단 틀만 해뒀습니당..조금만 바꾸면 되긴 하는데 vo랑 dao가 많이 바뀌어요!! 
//지금은 그냥 airdnd_home 테이블 했었는데 테이블 여러개로 나눈것들 합쳐서 하는 view하나 생성해서 그걸로 다시 vo,dao생성해서 바꾸면 됩니당!!
@Controller
public class HomeController {
	
	@Autowired
	AirdndHomeService airdndhomeService;
	
	@RequestMapping("/home")
	public String check(Model model) {
		
		List<AirdndHomeVO> list = airdndhomeService.homeselect();
		model.addAttribute("list", list);

		return Common.VIEW_PATH + "home.jsp";
	}
}
