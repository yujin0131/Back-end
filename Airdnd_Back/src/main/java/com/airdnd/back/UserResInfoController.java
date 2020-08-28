package com.airdnd.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import service.AirdndUserResInfoService;

@Controller
public class UserResInfoController {
	@Autowired
	AirdndUserResInfoService airdndUserResInfoService;
	
	@Autowired
	HttpServletRequest request;
}
