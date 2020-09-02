package com.airdnd.back;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.Common;
import service.AirdndUserService;
import vo.AirdndUserVO;

@Controller
public class UserController {

   ObjectMapper mapper = new ObjectMapper();
   @Autowired
   AirdndUserService airdnduserService;
   HttpServletRequest request;
   HttpServletResponse response;

   //******회원가입
   @RequestMapping(value = "/signUp", method=RequestMethod.POST,
         produces = "application/json;charset=utf8", consumes = MediaType.ALL_VALUE)
   @ResponseBody
   public String sign_up(Model model, @RequestBody String payload) {
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

      //이메일 중복여부 체크
      switch (email_res) {
      case 0:
         System.out.println("사용가능한 이메일");
         break;
      case 1:
         System.out.println("이미 사용중인 이메일");
         break;
      default:
         System.out.println("이상한 오류");
         break;
      }

      AirdndUserVO user_vo = new AirdndUserVO();
      user_vo.setEmail(javaObject.get("email").toString());
      user_vo.setLast_name(javaObject.get("lastName").toString());
      user_vo.setFirst_name(javaObject.get("firstName").toString());
      user_vo.setPwd(javaObject.get("pwd").toString());
      user_vo.setBirthday(javaObject.get("birthday").toString());

      user_vo.setProfileImg(javaObject.get("profileImg").toString());
      user_vo.setPhone(javaObject.get("phone").toString());
      user_vo.setSignupDate(javaObject.get("signupDate").toString());
      user_vo.setDescription(javaObject.get("description").toString());
      //List<AirdndUserVO> list = airdnduserService.userselect();
      //model.addAttribute("list", list);

      int res = airdnduserService.signup(user_vo);
      String result = "";
      if( res == 1) {
         System.out.println("회원가입 성공");
         result = "Success";
      } else {
         System.out.println("회원가입 실패");
         result = "Fail";
      }
      //return값 json?
      //만약 중복된 이메일로 회원가입하면 자동 로그인됨
      return result;
   }

   //******로그인
   @RequestMapping(value = "/signIn", method=RequestMethod.POST,
         produces = "application/json;charset=utf8", consumes = MediaType.ALL_VALUE)
   @ResponseBody
   public String sign_in(Model model, @RequestBody String payload) {
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
      if (login_vo == null) {
         //로그인 실패
         resultStr = "Fail";
      } else {
         //로그인 성공
         HttpSession session = request.getSession();
         Common com = new Common();
         
         //*로그인시 세션키 다르게 만들어주고 쿠키에 저장*
         String sessionKey = com.sessonKey();
         session.setAttribute(sessionKey, login_vo);
         String cookieValue = sessionKey;
         Cookie myCookie = new Cookie("AirdndSES", cookieValue);
         myCookie.setMaxAge(60*60);
         myCookie.setPath("/"); // 경로 설정
         response.addCookie(myCookie);
         
         //*쿠키에서 가져온 세션키로 세션 불러와 정보가져오기*
         Cookie[] cookies = request.getCookies();
         for (Cookie cookie : cookies) {
            if("AirdndSES".equals(cookie.getName())) {
               sessionKey = cookie.getValue();
            }
         }
         AirdndUserVO userInfoVO = (AirdndUserVO) session.getAttribute(sessionKey);
         String userEmail = userInfoVO.getEmail();
         String username = userInfoVO.getLast_name() + userInfoVO.getFirst_name();
         //필요할때 이렇게 뽑아서 넘겨주기
         
         resultStr = "Success";
      }

      return resultStr;
   }

   //******로그아웃
   @RequestMapping(value = "/signOut", method=RequestMethod.POST,
         produces = "application/json;charset=utf8", consumes = MediaType.ALL_VALUE)
   @ResponseBody
   public String sign_out(Model model, @RequestBody String payload) {
      HttpHeaders resHeaders = new HttpHeaders();
      resHeaders.add("Content-Type", "application/json;charset=UTF-8");

      HttpSession session = request.getSession();
      String sessionKey = "";
      //*쿠키에서 가져온 세션키로 세션 불러와 정보가져오기*
      Cookie[] cookies = request.getCookies();
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
       
      String resultStr = "Success";

      return resultStr;
   }
   //******자동완성 list
   @RequestMapping(value="/autocomplete/{user_input}", method= {RequestMethod.GET, RequestMethod.POST},
         produces = "application/json;charset=utf8", consumes = MediaType.ALL_VALUE)
   @ResponseBody
   public String autocomplete(@PathVariable String user_input) {

      List<String> result = new ArrayList<String>();
      System.out.println(user_input);
      String user_input2 = "";
      // 응답헤더 지정
      HttpHeaders resHeaders = new HttpHeaders();
      resHeaders.add("Content-Type", "application/json;charset=UTF-8");

      try {
         user_input2 = URLDecoder.decode(user_input, "utf-8");
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
            result.add(display_name);
            //System.out.println(result.get(i));
         }

      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return result.toString();
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
         user_input2 = URLDecoder.decode(user_input, "utf-8");
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