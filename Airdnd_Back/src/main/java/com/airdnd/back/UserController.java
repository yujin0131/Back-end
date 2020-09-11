package com.airdnd.back;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import common.Common;
import exception.NoId;
import exception.NoIdService;
import exception.WrongPwdService;
import service.AirdndUserService;
import vo.AirdndUserVO;

@Controller
public class UserController {

	ObjectMapper mapper = new ObjectMapper();
	@Autowired
	AirdndUserService airdnduserService;

	//******회원가입
	@RequestMapping(value = "/signUp", method=RequestMethod.POST)
	@ResponseBody
	public String sign_up(@RequestBody String payload) {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		Map<String, Object> javaObject = null;
		try {
			javaObject = mapper.readValue(payload, Map.class);
		} catch (Exception e) {
			System.out.println("payload 오류");
		}
		System.out.println("javaObject: " + javaObject);

		String email_check = javaObject.get("email").toString();
		int email_res = airdnduserService.emailcheck(email_check);
		JSONObject result = new JSONObject();
		String result_msg = "";
		Gson gson = new Gson();

		//이메일 중복여부 체크
		switch (email_res) {
		case 0:
			System.out.println("사용가능한 이메일");
			String lastName = "";
			String firstName = "";
			String description = "";
			String profileImg = "";

			AirdndUserVO user_vo = new AirdndUserVO();
			try {
				lastName = URLDecoder.decode(javaObject.get("lastName").toString(),"utf-8");
				firstName = URLDecoder.decode(javaObject.get("firstName").toString(),"utf-8");
				description = URLDecoder.decode(javaObject.get("description").toString(),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			user_vo.setEmail(javaObject.get("email").toString());
			user_vo.setLast_name(lastName);
			user_vo.setFirst_name(firstName);
			user_vo.setPwd(javaObject.get("pwd").toString());
			user_vo.setBirthday(javaObject.get("birthday").toString());

			if(profileImg.equals("")) {
				profileImg = "https://a0.muscache.com/defaults/user_pic-225x225.png?im_w=720";
			}
			if(description.equals("")) {
				description = "자기를 소개해주세요!";
			}
			user_vo.setProfileImg(profileImg);
			user_vo.setPhone(javaObject.get("phone").toString());
			user_vo.setDescription(description);

			int res = airdnduserService.signup(user_vo);

			if( res == 1) {
				System.out.println("회원가입 성공");
				result_msg = "Success";
			} else {
				System.out.println("회원가입 실패");
				result_msg = "Fail";
			}
			break;
		case 1:
			System.out.println("회원가입 실패 : 이미 사용중인 이메일");
			result_msg = "AlreadyEmail";
			break;
		default:
			System.out.println("회원가입 실패 : 이상한 오류 ");
			result_msg = "Fail";
			break;
		}

		result.put("result", result_msg);

		System.out.println(result);
		//return값 json?
		//만약 중복된 이메일로 회원가입하면 자동 로그인되는거 만들기
		return result.toString();
	}

	//******로그인
	@RequestMapping(value = "/signIn", method=RequestMethod.POST,
			produces = "application/json;charset=utf8", consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public String sign_in(HttpServletRequest request, HttpServletResponse response, @RequestBody String payload) {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		Map<String, Object> javaObject = null;
		try {
			javaObject = mapper.readValue(payload, Map.class);
		} catch (Exception e) {
			System.out.println("payload 오류");
		}
		System.out.println("javaObject: " + javaObject);

		AirdndUserVO check_vo = new AirdndUserVO();
		check_vo.setEmail(javaObject.get("email").toString());
		check_vo.setPwd(javaObject.get("pwd").toString());

		AirdndUserVO login_vo = airdnduserService.signin(check_vo);
		String resultStr = "";
		if(login_vo == null) {
			
		} else {
			if ( !(check_vo.getPwd().equals(login_vo.getPwd())) ) {
				//로그인 실패
				resultStr = "Fail";
				WrongPwdService wps = new WrongPwdService();
				try {
					wps.WrongPwdMethod(resultStr);
				} catch (Exception e) {
					e.printStackTrace();
					resultStr = e.toString();
				}
			} else {
				//로그인 성공
				HttpSession session = request.getSession();
				Common com = new Common();

				//*로그인시 세션키 다르게 만들어주고 쿠키에 저장*
				String sessionKey = com.sessonKey();
				session.setAttribute(sessionKey, login_vo);
				String cookieValue = sessionKey;
				Cookie myCookie = new Cookie("AirdndSES", cookieValue);
				myCookie.setSecure(true);
				myCookie.setMaxAge(60*60);
				myCookie.setPath("/"); // 경로 설정
				response.addCookie(myCookie);

				AirdndUserVO userInfoVO = (AirdndUserVO) session.getAttribute(sessionKey);
				String userEmail = userInfoVO.getEmail();
				String username = userInfoVO.getLast_name() + userInfoVO.getFirst_name();
				//필요할때 이렇게 뽑아서 넘겨주기

				resultStr = "Success";
			}
		}
		return resultStr;
	}

	//******로그아웃
	@RequestMapping(value = "/signOut", method=RequestMethod.POST,
			produces = "application/json;charset=utf8", consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public String sign_out(HttpServletRequest request, HttpServletResponse response, @RequestBody String payload) {
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");

		HttpSession session = request.getSession();
		String sessionKey = "";
		String resultStr = "";
		//*쿠키에서 가져온 세션키로 세션 불러와 정보가져오기*
		Cookie[] cookies = request.getCookies();
		if(cookies == null) {
			resultStr = "Fail";
		}else {
			for (Cookie cookie : cookies) {
				if("AirdndSES".equals(cookie.getName())) {
					sessionKey = cookie.getValue();
				}
			}
			session.removeAttribute(sessionKey);
			Cookie myCookie = new Cookie("AirdndSES", null);
			myCookie.setMaxAge(0); // 쿠키의 expiration 타임을 0으로 하여 없앤다.
			myCookie.setPath("/"); // 모든 경로에서 삭제 됬음을 알린다.
			response.addCookie(myCookie);

			resultStr = "Success";
		}

		return resultStr;
	}

	//******자동완성 json
	@RequestMapping(value="/autocomplete/json/{user_input}", method= {RequestMethod.GET, RequestMethod.POST},
			produces = "application/json;charset=utf8", consumes = MediaType.ALL_VALUE)
	@ResponseBody
	public String autocomplete_json(@PathVariable String user_input) {

		Map<String,Object> resultjson = new JSONObject();
		System.out.println(user_input);
		String user_input2 = "";
		// 응답헤더 지정
		HttpHeaders resHeaders = new HttpHeaders();
		resHeaders.add("Content-Type", "application/json;charset=UTF-8");
		try {
			user_input2 = URLEncoder.encode(user_input, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String urlStr = "https://www.airbnb.co.kr/api/v2/autocompletes?country=KR&key=d306zoyjsyarp7ifhu67rjxn52tv0t20&language=ko&locale=ko&num_results=5&api_version=1.1.1&satori_config_token=EhIiJQIiEhUCEiIyEhIyEiEA&vertical_refinement=all&region=-1&options=should_filter_by_vertical_refinement%7Chide_nav_results%7Cshould_show_stays%7Csimple_search&user_input="+user_input2;
		URL url;

		try {
			if(urlStr.contains(" ")) {
				urlStr = urlStr.replace(" ", "%20");
			}
			url = new URL(urlStr);
			StringBuilder sb = new StringBuilder();  
			BufferedReader br = new BufferedReader(new InputStreamReader(((HttpURLConnection) (new URL(urlStr)).openConnection()).getInputStream(), Charset.forName("UTF-8")));
			String line = null;
			while ((line = br.readLine()) != null) {  
				sb.append(line + "\n");  
			}
			br.close(); 
			System.out.println(""+sb.toString());

			//Json 형식으로 변환
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(sb.toString());
			JSONObject jsonObj = (JSONObject) obj;

			JSONArray autocomplete_terms = (JSONArray)jsonObj.get("autocomplete_terms");
			int resultNum = autocomplete_terms.size();
			for( int i = 0; i < resultNum; i++) {

				JSONObject auto_boxes = (JSONObject)autocomplete_terms.get(i);
				String display_name = (String)auto_boxes.get("display_name");
				String keyy = ""+i;
				resultjson.put(keyy, display_name);
				//System.out.println(result.get(i));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultjson.toString();
	}

}