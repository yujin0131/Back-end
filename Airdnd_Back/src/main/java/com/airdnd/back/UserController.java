package com.airdnd.back;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.Common;
import service.AirdndUserService;
import vo.AirdndUserVO;

@Controller
public class UserController {
	
	@Autowired
	AirdndUserService airdnduserService;
	
	@RequestMapping("/sign_up")
	public String test(Model model) {

		List<AirdndUserVO> list = airdnduserService.daoserviceconnect();
		
		AirdndUserVO vo = new AirdndUserVO();
		vo.setEmail("dntjd851@naver.com");
		vo.setPwd("2222");
		vo.setLast_name("윤");
		vo.setFirst_name("우성");
		vo.setBirthday("1995-09-19");
	    int res = airdnduserService.daoserviceinsert(vo);
	    if( res == 1) {
	    	System.out.println("회원가입에 성공");
	    } else {
	    	System.out.println("회원가입에 실패");
	    }
		model.addAttribute("list", list);

		
		return Common.VIEW_PATH + "user.jsp";
	}

}
