package com.airdnd.back;

import java.io.Console;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.http.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import common.Common;
import dao.AirdndRoomDAO;
import service.AirdndRoomService;
import vo.AirdndRoomVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	AirdndRoomService airdndroomService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	ObjectMapper mapper = new ObjectMapper();
	Gson gson = new Gson();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@ResponseBody
	public String home() {
		System.out.println("get");
		
		List<AirdndRoomVO> list = null;
		list = airdndroomService.daoserviceconnect();
		
		System.out.println("list : " + list.get(0).getRoom_name());
		
		JSONObject jsonObject = new JSONObject();
		for(int i = 0; i < list.size(); i++) {
			Map<Object, Object> javaObject = new HashMap<Object, Object>();
			javaObject.put("name", list.get(i).getRoom_name());
			javaObject.put("price", list.get(i).getRoom_price());
			
			jsonObject.put(i, javaObject);
			System.out.println("inner : " + jsonObject.get(i).toString());
			
		}
		return jsonObject.toString();
	}
	
	

	@RequestMapping(value="/post", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
	public String postHome(@RequestBody String payload) throws Exception{
		System.out.println("===== POST =====");

		// 프론트에서 보내는 payload는 JSON형태(String)
		System.out.println("payload: " + payload);


		// 백에서 작업할때(프론트에서 보낸 JSON을 자바형태의 객체(Map)으로 바꿔서 작업	
		Map<String,Object> javaObject = mapper.readValue(payload, Map.class);
		System.out.println("javaObject: " + javaObject);

		AirdndRoomVO vo = new AirdndRoomVO();
		vo.setRoom_idx(Integer.parseInt(javaObject.get("idx").toString()));
		vo.setRoom_name(javaObject.get("name").toString());
		vo.setRoom_price(javaObject.get("price").toString());
		vo.setRoom_score(javaObject.get("score").toString());
		vo.setRoom_review_num(javaObject.get("review_num").toString());
		vo.setRoom_type(javaObject.get("type").toString());
		vo.setRoom_option(javaObject.get("option").toString());
		System.out.println("list : " + vo.getRoom_idx() + " / " + vo.getRoom_name() );

		airdndroomService.daoserviceinsert(vo);
		


		// 프론트로 보내실때 java object(Map<String, Object>) => JSON 형태		
		String jsonObject = gson.toJson(javaObject);
		System.out.println("jsonObject: " + jsonObject);

		return jsonObject;
	}
}
