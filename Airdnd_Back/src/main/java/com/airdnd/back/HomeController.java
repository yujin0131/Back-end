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

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import common.Common;
import service.AirdndHomeService;
import vo.AirdndHomeVO;

@Controller
public class HomeController {
   
   ObjectMapper mapper = new ObjectMapper();
   Gson gson = new Gson();
   
   
   @Autowired
   AirdndHomeService airdndhomeService;
   
   @RequestMapping("/home")
   public String check(Model model) {
      
      List<AirdndHomeVO> list = airdndhomeService.homeselect();
      model.addAttribute("list", list);

      return Common.VIEW_PATH + "home.jsp";
   }
   
   @RequestMapping("/home/autocomplete/{user_input}")
   @ResponseBody
   public List<String> check2(@PathVariable String user_input) {
      
      List<String> result = new ArrayList<String>();
      System.out.println(user_input);
      String user_input2 = "";
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
        	  result.add(display_name);
        	  //System.out.println(result.get(i));
          }
        
      } catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

      return result;
   }
   
   
   
}