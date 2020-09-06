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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.AirdndHomeService;
import vo.AirdndBedroomVO;
import vo.AirdndDistanceVO;
import vo.AirdndFacilityVO;
import vo.AirdndHomePictureVO;
import vo.AirdndHomeVO;
import vo.AirdndNoticeVO;
import vo.AirdndReviewVO;
import vo.AirdndRuleVO;

@Controller
public class HomeController {

	@Autowired
	AirdndHomeService airdndhomeService;

	@RequestMapping(value="/home",
			method=RequestMethod.GET, produces = "application/json;charset=utf8", consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public String check(HttpServletRequest request, HttpServletResponse response, int home_idx) {

		JSONObject res = new JSONObject();

		AirdndHomeVO hostvo = airdndhomeService.hostselect(home_idx);
		boolean isSupperhost = false;
		boolean isCertification = false;//보증된 호스트인지
		if(hostvo.getCheck_superhost() == 1) isSupperhost = true;
		if(hostvo.getCheck_certification() == 1) isCertification = true;
		res.put("hostName", hostvo.getHost_name());
		res.put("hostSigninDate", hostvo.getHost_sign_in_date());
		res.put("isSupperhost", isSupperhost);
		res.put("isCertification", isCertification);
		res.put("hostStatusMessage", hostvo.getHost_status_message());
		res.put("interaction", hostvo.getInteraction_with_guests());
		res.put("language", hostvo.getHost_language());
		res.put("resRate", hostvo.getResponse_rate());
		res.put("resTime", hostvo.getResponse_time());
		res.put("hostProfile", hostvo.getHost_profileImg());

		AirdndHomeVO homevo = airdndhomeService.homeselect(home_idx);
		res.put("place", homevo.getPlace());
		res.put("title", homevo.getTitle());
		res.put("addr", homevo.getAddr());
		res.put("lat", homevo.getLat());
		res.put("lng", homevo.getLng());
		res.put("sub_title", homevo.getSub_title());
		res.put("filter_max_person", homevo.getFilter_max_person());
		res.put("filter_bedroom", homevo.getFilter_bedroom());
		res.put("filter_bed", homevo.getFilter_bed());
		res.put("filter_bathroom", homevo.getFilter_bathroom());
		res.put("price", homevo.getPrice());
		res.put("host_notice", homevo.getHost_notice());
		res.put("loc_info", homevo.getLoc_info());

		List<JSONObject> attractions_info = new ArrayList<JSONObject>();
		List<AirdndDistanceVO> distancelist = airdndhomeService.distanceselect(home_idx);
		for( int i = 0; i< distancelist.size(); i++) {
			JSONObject distance_info = new JSONObject();
			distance_info.put("attractions_name", distancelist.get(i).getAttractions_name());
			distance_info.put("attractions_distance", distancelist.get(i).getAttractions_distance());

			attractions_info.add(distance_info);
		}
		res.put("attractions_info", attractions_info);

		List<String> picture = new ArrayList<String>();
		List<AirdndHomePictureVO> picturelist = airdndhomeService.pictureselect(home_idx);
		for(int i = 0; i < picturelist.size(); i++) {
			picture.add( picturelist.get(i).getUrl() );
		}
		res.put("picture_url", picture);

		List<JSONObject> bedroom_info = new ArrayList<JSONObject>();
		List<AirdndBedroomVO> bedroom = airdndhomeService.bedroomeselect(home_idx);
		for( int i = 0; i< bedroom.size(); i++) {
			JSONObject bedroominfo = new JSONObject();
			bedroominfo.put("bedroomName", bedroom.get(i).getBed_room_name());
			bedroominfo.put("bedroomOption", bedroom.get(i).getBed_room_option());
			bedroominfo.put("bedIcons", bedroom.get(i).getBed_icons());

			bedroom_info.add(bedroominfo);
		}
		res.put("bedroomInfo", bedroom_info);

		List<JSONObject> facility_info = new ArrayList<JSONObject>();
		List<AirdndFacilityVO> facility = airdndhomeService.facilityselect(home_idx);
		for( int i = 0; i< facility.size(); i++) {
			JSONObject facilityinfo = new JSONObject();
			facilityinfo.put("facility", facility.get(i).getFacility());
			facilityinfo.put("facilityIcon", facility.get(i).getFacility_icon());

			facility_info.add(facilityinfo);
		}
		res.put("facilityInfo", facility_info);

		List<JSONObject> notice_info = new ArrayList<JSONObject>();
		List<AirdndNoticeVO> notice = airdndhomeService.noticeselect(home_idx);
		for( int i = 0; i< notice.size(); i++) {
			JSONObject noticeinfo = new JSONObject();
			noticeinfo.put("noticeSort", notice.get(i).getHome_notice_sort());
			noticeinfo.put("noticeContent", notice.get(i).getHome_notice_content());
			noticeinfo.put("noticeIcon", notice.get(i).getHome_notice_icon());

			notice_info.add(noticeinfo);
		}
		res.put("noticeInfo", notice_info);

		JSONObject review_res = new JSONObject();
		List<JSONObject> review_info = new ArrayList<JSONObject>();
		List<AirdndReviewVO> review = airdndhomeService.reviewselect(home_idx);
		double totalReview = 0.0;
		for( int i = 0; i< review.size(); i++) {
			JSONObject reviewinfo = new JSONObject();
			reviewinfo.put("userName", review.get(i).getUser_name());
			reviewinfo.put("reviewDate", review.get(i).getReview_date());
			reviewinfo.put("reviewContent", review.get(i).getReview_content());
			reviewinfo.put("cleanliness", review.get(i).getRoom_cleanliness());
			reviewinfo.put("accuracy", review.get(i).getRoom_accuracy());
			reviewinfo.put("communication", review.get(i).getRoom_communication());
			reviewinfo.put("roomPosition", review.get(i).getRoom_position());
			reviewinfo.put("checkin", review.get(i).getRoom_checkin());
			reviewinfo.put("costEffectiveness", review.get(i).getRoom_cost_effectiveness());

			totalReview += (review.get(i).getRoom_cleanliness() + review.get(i).getRoom_accuracy() + review.get(i).getRoom_communication() + review.get(i).getRoom_position() + review.get(i).getRoom_checkin() + review.get(i).getRoom_cost_effectiveness());

			review_info.add(reviewinfo);

		}
		double avgReview = totalReview/review.size();
		review_res.put("avgReview", avgReview);
		review_res.put("reviewCount", review.size());
		review_res.put("noticeInfo", review_info);
		res.put("review", review_res);

		List<String> safetyrule_info = new ArrayList<String>();
		List<AirdndRuleVO> safetyrule = airdndhomeService.safetyruleselect(home_idx);
		for( int i = 0; i< safetyrule.size(); i++) {
			safetyrule_info.add(safetyrule.get(i).getSafety_rule());
		}
		res.put("safetyruleInfo", safetyrule_info);

		List<String> userule_info = new ArrayList<String>();
		List<AirdndRuleVO> userule = airdndhomeService.useruleselect(home_idx);
		for( int i = 0; i< userule.size(); i++) {
			userule_info.add(userule.get(i).getUse_rule());
		}
		res.put("useruleInfo", userule_info);

		return res.toString();
	}

}