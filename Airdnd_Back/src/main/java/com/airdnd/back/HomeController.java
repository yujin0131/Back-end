package com.airdnd.back;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import common.Common;
import exception.WrongPwdService;
import service.AirdndHomeService;
import service.AirdndHomeServiceI;
import vo.AirdndBedroomVO;
import vo.AirdndBookmarkedHomesVO;
import vo.AirdndDistanceVO;
import vo.AirdndFacilityVO;
import vo.AirdndHomePictureVO;
import vo.AirdndHomeVO;
import vo.AirdndHostVO;
import vo.AirdndNoticeVO;
import vo.AirdndReviewVO;
import vo.AirdndSafetyRuleVO;
import vo.AirdndUseRuleVO;
import vo.AirdndUserResInfoVO;
import vo.AirdndUserVO;

@Controller
public class HomeController {

	@Autowired
	AirdndHomeService airdndhomeService;

	@Autowired
	HttpServletRequest request;

	@RequestMapping(value="/home",
			method=RequestMethod.GET, produces = "application/json;charset=utf8", consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public String check( HttpServletResponse response, int home_idx) {

		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		String sessionKey = "";
		int signInIdx = 0;
		String signInEmail;
		String signInName;
		if(cookies == null) {
			System.out.println("not cookies");
		}else {
			for (Cookie cookie : cookies) {
				if("AirdndSES".equals(cookie.getName())) {
					sessionKey = cookie.getValue();
					AirdndUserVO signInVO = (AirdndUserVO) session.getAttribute(sessionKey);
					signInIdx = signInVO.getUser_idx();
					signInEmail = signInVO.getEmail();
					signInName = signInVO.getLast_name() + signInVO.getFirst_name();
				} else {
					System.out.println("not login");
				}
			}
		}

		JSONObject res = new JSONObject();

		AirdndHostVO hostvo = airdndhomeService.hostselect(home_idx);
		res.put("id", home_idx);

		String cookie_name = "AirdndRH"+home_idx;
		Cookie recentCookie = new Cookie(cookie_name, Integer.toString(home_idx));
		recentCookie.setMaxAge(60*60);
		recentCookie.setPath("/"); // 경로 설정
		response.addCookie(recentCookie);

		JSONObject hostres = new JSONObject();
		hostres.put("hostId", hostvo.getIdx());
		String name = hostvo.getHost_name();
		name.substring(1, name.length() - 2);
		hostres.put("hostFirstName", name);
		hostres.put("profileImg", hostvo.getHost_profileImg());
		hostres.put("signupDate", hostvo.getHost_sign_in_date());
		hostres.put("reviewCount", hostvo.getHost_review_num());
		hostres.put("identityVerified", hostvo.isCheck_certification());
		hostres.put("isSupperhost", hostvo.isCheck_superhost());
		hostres.put("responseRate", hostvo.getResponse_rate());
		hostres.put("responseTime", hostvo.getResponse_time());
		String communi = hostvo.getHost_status_message();
		if(communi.equals("None")) communi = "";
		String interac = hostvo.getInteraction_with_guests();
		if(interac.equals("None")) interac = "";
		hostres.put("selfExplanation", communi);
		hostres.put("communication", interac);

		res.put("host", hostres);

		AirdndHomeVO homevo = airdndhomeService.homeselect(home_idx);
		res.put("title", homevo.getTitle());
		res.put("subTitle", homevo.getSub_title());
		res.put("feature", "최대 인원 " + homevo.getFilter_max_person() + "명 · 침실 " + homevo.getFilter_bedroom() + "개 · 침대"+ homevo.getFilter_bed() + "개 · 욕실 " + homevo.getFilter_bathroom() + "개");

		JSONObject locationres = new JSONObject();
		locationres.put("lat", homevo.getLat());
		locationres.put("lng", homevo.getLng());
		res.put("location", locationres);

		res.put("address", homevo.getAddr());
		res.put("addressDescription", homevo.getLoc_info());
		res.put("homeType", "개인실");
		res.put("capacity", homevo.getFilter_max_person());

		List<String> reservedDate = new ArrayList<String>();
		List<AirdndUserResInfoVO> reservedDatelist = airdndhomeService.userresinfoselect(home_idx);
		for( int i = 0; i< reservedDatelist.size(); i++) {
			String str = reservedDatelist.get(i).getCheckin();
			str = str.replace("-", ".");
			reservedDate.add(str);
		}
		res.put("reservedDates", reservedDate);

		String checkin = ""; String checkout = "";
		JSONObject notice_for = new JSONObject();
		List<JSONObject> userule_info = new ArrayList<JSONObject>();
		List<AirdndUseRuleVO> userule = airdndhomeService.useruleselect(home_idx);
		for( int i = 0; i< userule.size(); i++) {
			JSONObject rule_name = new JSONObject();
			rule_name.put("name", userule.get(i).getUse_rule());
			userule_info.add(rule_name);
			if(userule.get(i).getUse_rule().indexOf("체크아웃 시간:") != -1) {
				checkout = userule.get(i).getUse_rule();
				checkout = checkout.replaceAll("체크아웃 시간: ", "");
			}

			if(userule.get(i).getUse_rule().indexOf("체크인 시간:") != -1) {
				checkin = userule.get(i).getUse_rule();
				checkin = checkin.replaceAll("체크인 시간: ", "");
			}
		}
		notice_for.put("rules", userule_info);

		List<JSONObject> safetyrule_info = new ArrayList<JSONObject>();
		List<AirdndSafetyRuleVO> safetyrule = airdndhomeService.safetyruleselect(home_idx);
		for( int i = 0; i< safetyrule.size(); i++) {
			JSONObject rule_name = new JSONObject();
			rule_name.put("name", safetyrule.get(i).getSafety_rule());
			safetyrule_info.add(rule_name);
		}
		notice_for.put("safeties", safetyrule_info);

		res.put("checkin", checkin);
		res.put("checkout", checkout);
		res.put("price", String.format("%,d", homevo.getPrice()));

		if(signInIdx == 0) res.put("isBookmarked", "false");
		else {
			AirdndBookmarkedHomesVO bookmarkedhomesvo = airdndhomeService.bookmarkedhomes(signInIdx, home_idx);
			if(bookmarkedhomesvo.getIdx() == 0 && bookmarkedhomesvo.getBookmark_idx() == 0) res.put("isBookmarked", "false");
			else{
				res.put("isBookmarked", "true");
			}
		}

		List<String> picture = new ArrayList<String>();
		List<AirdndHomePictureVO> picturelist = airdndhomeService.pictureselect(home_idx);
		for(int i = 0; i < picturelist.size(); i++) {
			picture.add( picturelist.get(i).getUrl() );
		}
		res.put("images", picture);

		List<JSONObject> notice_info = new ArrayList<JSONObject>();
		List<AirdndNoticeVO> notice = airdndhomeService.noticeselect(home_idx);
		for( int i = 0; i< notice.size(); i++) {
			JSONObject noticeinfo = new JSONObject();
			noticeinfo.put("text", notice.get(i).getHome_notice_sort());
			noticeinfo.put("subText", notice.get(i).getHome_notice_content());
			noticeinfo.put("icon", notice.get(i).getHome_notice_icon());

			notice_info.add(noticeinfo);
		}
		res.put("explains", notice_info);
		res.put("description", homevo.getHost_notice());

		List<JSONObject> bedroom_info = new ArrayList<JSONObject>();
		List<AirdndBedroomVO> bedroom = airdndhomeService.bedroomeselect(home_idx);
		for( int i = 0; i< bedroom.size(); i++) {
			JSONObject bedroominfo = new JSONObject();
			List<String> bedroom_icon_arr = new ArrayList<String>();
			bedroom_icon_arr.add(bedroom.get(i).getBed_icons());
			bedroominfo.put("icons", bedroom_icon_arr);
			bedroominfo.put("room", bedroom.get(i).getBed_room_name());
			bedroominfo.put("size", bedroom.get(i).getBed_room_option());

			bedroom_info.add(bedroominfo);
		}
		res.put("bedrooms", bedroom_info);

		List<JSONObject> facility_info = new ArrayList<JSONObject>();
		List<AirdndFacilityVO> facility = airdndhomeService.facilityselect(home_idx);
		for( int i = 0; i< facility.size(); i++) {
			JSONObject facilityinfo = new JSONObject();
			facilityinfo.put("name", facility.get(i).getFacility());
			facilityinfo.put("icon",facility.get(i).getFacility_icon());

			facility_info.add(facilityinfo);
		}
		res.put("amenities", facility_info);

		res.put("notice", notice_for);

		JSONObject review_res = new JSONObject();
		List<JSONObject> review_info = new ArrayList<JSONObject>();
		List<AirdndReviewVO> review = airdndhomeService.reviewselect(home_idx);
		double totalReview = 0.0;
		for( int i = 0; i< review.size(); i++) {
			JSONObject reviewinfo = new JSONObject();		  
			reviewinfo.put("userId", review.get(i).getIdx());
			reviewinfo.put("userProfileImg", "https://a0.muscache.com/defaults/user_pic-225x225.png?im_w=720");
			reviewinfo.put("userFirstName", review.get(i).getUser_name());
			reviewinfo.put("date", review.get(i).getReview_date());
			reviewinfo.put("contents", review.get(i).getReview_content());

			totalReview += (review.get(i).getRoom_cleanliness() + review.get(i).getRoom_accuracy() + review.get(i).getRoom_communication() + review.get(i).getRoom_position() + review.get(i).getRoom_checkin() + review.get(i).getRoom_cost_effectiveness());

			review_info.add(reviewinfo);

		}
		if(review.size() == 0 ) {
			review_res.put("cleanliness", 0);
			review_res.put("accuracy", 0);
			review_res.put("communication", 0);
			review_res.put("location", 0);
			review_res.put("checkin", 0);
			review_res.put("value", 0);
			review_res.put("rating", 0);
			review_res.put("comments", "");
		} else {
			double avgReview = (totalReview/(review.size()*6));
			review_res.put("cleanliness", review.get(0).getRoom_cleanliness());
			review_res.put("accuracy", review.get(0).getRoom_accuracy());
			review_res.put("communication", review.get(0).getRoom_communication());
			review_res.put("location", review.get(0).getRoom_position());
			review_res.put("checkin", review.get(0).getRoom_checkin());
			review_res.put("value", review.get(0).getRoom_cost_effectiveness());
			review_res.put("rating", Math.round(avgReview*100)/100.0);
			review_res.put("comments", review_info);
		}
		review_res.put("count", hostvo.getHost_review_num());
		res.put("reviews", review_res);

		int ran = new Random().nextInt(3) + 1;
		res.put("minimumStay", ran);

		return res.toString();

		/*List<JSONObject> attractions_info = new ArrayList<JSONObject>();
		  List<AirdndDistanceVO> distancelist = airdndhomeService.distanceselect(home_idx);
		  for( int i = 0; i< distancelist.size(); i++) {
			  JSONObject distance_info = new JSONObject();
			  distance_info.put("attractions_name", distancelist.get(i).getAttractions_name());
			  distance_info.put("attractions_distance", distancelist.get(i).getAttractions_distance());

			  attractions_info.add(distance_info);
		  }
		  res.put("attractions_info", attractions_info);*/
	}//home

	//예약
	@RequestMapping(value = "/book", method=RequestMethod.POST,
			produces = "application/json;charset=utf8", consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public String sign_in(HttpServletRequest request, HttpServletResponse response, @RequestBody String payload) {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		//Login cookie
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		String sessionKey = "";
		int signInIdx = 0; //temp
		String signInEmail = "";
		String signInName = "";

		if(cookies == null) {
			System.out.println("not cookies");
		}else {
			for(Cookie cookie : cookies) {
				if("AirdndSES".equals(cookie.getName())) {
					sessionKey = cookie.getValue();
					AirdndUserVO signInVO = (AirdndUserVO)session.getAttribute(sessionKey);
					signInIdx = signInVO.getUser_idx();
					signInEmail = signInVO.getEmail();
					signInName = signInVO.getLast_name() + signInVO.getFirst_name();
				} else {
					System.out.println("not login");
				}
			}
		}//if

		ObjectMapper mapper = new ObjectMapper();
		JSONObject result = new JSONObject();
		String result_msg = "";

		Map<String, Object> javaObject = null;
		try {
			javaObject = mapper.readValue(payload, Map.class);
		} catch (Exception e) {
			System.out.println("payload 오류");
		}
		System.out.println("javaObject: " + javaObject);

		AirdndUserResInfoVO bookvo = new AirdndUserResInfoVO();
		bookvo.setUser_idx(signInIdx);
		bookvo.setHome_idx(Integer.parseInt(javaObject.get("homeId").toString()));
		bookvo.setCheckin(javaObject.get("checkin").toString());
		bookvo.setCheckout(javaObject.get("checkout").toString());
		bookvo.setAdult(Integer.parseInt(javaObject.get("adult").toString()));
		bookvo.setChild(Integer.parseInt(javaObject.get("child").toString()));
		bookvo.setInfant(Integer.parseInt(javaObject.get("infant").toString()));
		
		String toHostMessage = javaObject.get("toHostMessage").toString();

		int res = airdndhomeService.book(bookvo);

		if( res == 1 ) {
			System.out.println("부킹잘됨");
			result_msg = "Success";
		} else {
			System.out.println("부킹실패");
			result_msg = "Fail";
		}

		result.put("result", result_msg);

		return result.toString();

	}//book

	//예약가능여부
	/*@RequestMapping(value="/home_pos", produces="application/json;charset=utf8")
	@ResponseBody
	public String home_pos(Model model, @RequestParam(value="home_idx", required=false)int home_idx, @RequestParam(value="checkin", required=false)String checkin, 
			@RequestParam(value="checkout", required=false)String checkout) {

		//Login cookie
		HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		String sessionKey = "";
		int signInIdx = 1; //temp
		String signInEmail = "";
		String signInName = "";

		if(cookies == null) {
			System.out.println("not cookies");
		}else {
			for(Cookie cookie : cookies) {
				if("AirdndSES".equals(cookie.getName())) {
					sessionKey = cookie.getValue();
					AirdndUserVO signInVO = (AirdndUserVO)session.getAttribute(sessionKey);
					signInIdx = signInVO.getUser_idx();
					signInEmail = signInVO.getEmail();
					signInName = signInVO.getLast_name() + signInVO.getFirst_name();
				} else {
					System.out.println("not login");
				}
			}
		}//if

		JSONObject res = new JSONObject();
		List<AirdndUserResInfoVO> list = airdndhomeService.userresinfoselect(home_idx, checkin, checkout);
		try {
			if(list.size() == 0	) res.put("result", "ok");
			else res.put("result", "no");
		} catch (Exception e) {
		}

		return res.toString();
	}*/

}