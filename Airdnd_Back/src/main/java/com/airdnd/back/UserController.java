package com.airdnd.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import service.AirdndUserService;
import vo.AirdndHomeVO;
import vo.AirdndUserVO;

@Controller
public class UserController {
	
	@Autowired
	AirdndUserService airdnduserService;
	
	@RequestMapping("/sign_up")
	public String test(Model model) {

		List<AirdndUserVO> list = airdnduserService.daoserviceconnect();
		model.addAttribute("list", list);
		
		//email_check에 프론트에서 받아오는 값 넣기 지금은 임시값
		String email_check = "wooseong@naver.com";
		
		int res = airdnduserService.daoservicesearch(email_check);
		
		if(res == 0) {
			System.out.println("사용가능한 이메일");
		}else if(res == 1){
			System.out.println("이미 사용중인 이메일");
		}else {
			System.out.println("이상한 오류");
		}
		

		return Common.VIEW_PATH + "user.jsp";
	}

}
