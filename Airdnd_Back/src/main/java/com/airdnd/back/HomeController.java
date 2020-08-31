package com.airdnd.back;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import common.Common;
import service.AirdndHomeService;
import vo.AirdndHomeVO;

//재은언니,,,여기는 다시 하셔야 해용 일단 틀만 해뒀습니당..조금만 바꾸면 되긴 하는데 vo랑 dao가 많이 바뀌어요!! 
//지금은 그냥 airdnd_home 테이블 했었는데 테이블 여러개로 나눈것들 합쳐서 하는 view하나 생성해서 그걸로 다시 vo,dao생성해서 바꾸면 됩니당!!
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

	@RequestMapping("/")
	@ResponseBody
	public List<String> test(Model model) {

		String str, receiveMsg;
		List<String> result = new ArrayList<String>();
		JsonObject requestJson = new JsonObject();   
		String urlStr = "https://www.airbnb.co.kr/api/v2/autocompletes?country=KR&key=d306zoyjsyarp7ifhu67rjxn52tv0t20&language=ko&locale=ko&num_results=5&user_input=서울&api_version=1.1.1&satori_config_token=EhIiJQIiEhUCEiIyEhIyEiEA&vertical_refinement=all&region=-1&options=should_filter_by_vertical_refinement%7Chide_nav_results%7Cshould_show_stays%7Csimple_search";
		urlStr = urlStr.replace(" ", "%20");
		URL url;
		try {
			url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();

			//         conn.setDoOutput(true);
			//         conn.setDoInput(true);

			//         conn.setRequestProperty("Content-Type", "application/json;charset=utf8");
			//         conn.setRequestProperty("Accept", "application/json");
			//         conn.setRequestProperty("Method", "POST");
			//          OutputStream os = conn.getOutputStream();
			//          os.write(requestJson.toString().getBytes("UTF-8"));
			//          os.close();

			StringBuilder sb = new StringBuilder();  
			int HttpResult =conn.getResponseCode();

			BufferedReader br;
			System.out.println(HttpResult);
			if(HttpResult == 200) {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8")); 
				System.out.println("잘옴");
			}else {
				br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));  
				System.out.println("잘오지못함");
			}
			String line = null;
			while ((line = br.readLine()) != null) {  
				sb.append(line + "\n");  
			}
			br.close(); 
			conn.disconnect();
			System.out.println(""+sb.toString());  



		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return result;
	}


	@RequestMapping("/home/autocomplete")
	@ResponseBody
	public List<String> check(Model model, String user_input) {

		String str, receiveMsg;
		List<String> result = new ArrayList<String>();
		JsonObject requestJson = new JsonObject();   
		String urlStr = "https://www.airbnb.co.kr/api/v2/autocompletes?country=KR&key=d306zoyjsyarp7ifhu67rjxn52tv0t20&language=ko&locale=ko&num_results=5&user_input="+user_input+"&api_version=1.1.1&satori_config_token=EhIiJQIiEhUCEiIyEhIyEiEA&vertical_refinement=all&region=-1&options=should_filter_by_vertical_refinement%7Chide_nav_results%7Cshould_show_stays%7Csimple_search";

		URL url;
		try {
			url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();

			conn.setDoOutput(true);
			conn.setDoInput(true);

			conn.setRequestProperty("Content-Type", "application/json;charset=utf8");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Method", "POST");
			OutputStream os = conn.getOutputStream();
			os.write(requestJson.toString().getBytes("UTF-8"));
			os.close();

			StringBuilder sb = new StringBuilder();  
			int HttpResult =conn.getResponseCode();
			if(HttpResult ==HttpURLConnection.HTTP_OK){
				BufferedReader br = new BufferedReader(new   InputStreamReader(conn.getInputStream(),"utf-8"));  

				String line = null;
				while ((line = br.readLine()) != null) {  
					sb.append(line + "\n");  
				}
				br.close(); 
				System.out.println(""+sb.toString());  

			}else{
				System.out.println(conn.getResponseCode());
				System.out.println(conn.getResponseMessage());  
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return result;
	}





	@RequestMapping("/2")
	@ResponseBody
	public List<String> check2(Model model) {

		String str, receiveMsg;
		List<String> result = new ArrayList<String>();
		//  JsonObject requestJson = new JsonObject();   
		String query = "서울";

		String user_input = "";
		try {
			user_input = URLEncoder.encode(query, "utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		String urlStr = "https://www.airbnb.co.kr/api/v2/autocompletes?country=KR&key=d306zoyjsyarp7ifhu67rjxn52tv0t20&language=ko&locale=ko&num_results=5&user_input="+user_input+"&api_version=1.1.1&satori_config_token=EhIiJQIiEhUCEiIyEhIyEiEA&vertical_refinement=all&region=-1&options=should_filter_by_vertical_refinement%7Chide_nav_results%7Cshould_show_stays%7Csimple_search";

		URL url;
		try {
			url = new URL(urlStr);


			StringBuilder sb = new StringBuilder();  
			//     int HttpResult =conn.getResponseCode();
			//if(HttpResult == HttpURLConnection.HTTP_OK){
			BufferedReader br = new BufferedReader(new   InputStreamReader(url.openStream()));  

			System.out.println(br);
			String line = null;
			while ((line = br.readLine()) != null) {  
				sb.append(line + "\n");  
			}
			br.close(); 
			System.out.println(""+sb.toString());  

			// }else{
			//System.out.println(conn.getResponseCode());
			// System.out.println(conn.getResponseMessage());  
			// }

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return result;
	}

}