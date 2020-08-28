package com.airdnd.back;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import service.AirdndBookmarkService;

@Controller
public class BookmarkController {
	@Autowired
	AirdndBookmarkService airdndBookmarkService;
	
	@Autowired
	HttpServletRequest request;
}
