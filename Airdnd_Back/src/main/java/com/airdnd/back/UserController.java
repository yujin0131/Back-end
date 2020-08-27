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

	@RequestMapping("/email_check")
	public String check(Model model) {

		List<AirdndUserVO> list = airdnduserService.userselect();
		model.addAttribute("list", list);

		//email_check에 프론트에서 받아오는 값 넣기 지금은 임시값
		String email_check = "yujin0131@naver.com";

		int email_res = airdnduserService.emailcheck(email_check);

		if(email_res == 0) {
			System.out.println("사용가능한 이메일");
		}else if(email_res == 1){
			System.out.println("이미 사용중인 이메일");
		}else {
			System.out.println("이상한 오류");
		}

		//이것도 임시값. 받아올거야
		AirdndUserVO vo = new AirdndUserVO();
		
		vo.setEmail("friendship@naver.com");
		vo.setPwd("3333");
		vo.setLast_name("김");
		vo.setFirst_name("우정");
		vo.setBirthday("1995-09-19");
		
		int res = airdnduserService.signup(vo);
		
		if( res == 1) {
			System.out.println("회원가입 성공");
		} else {
			System.out.println("회원가입 실패");
		}


		return Common.VIEW_PATH + "user.jsp";
	}



}
