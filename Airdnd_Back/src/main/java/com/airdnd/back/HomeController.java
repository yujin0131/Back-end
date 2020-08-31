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
   
   @Autowired
   AirdndHomeService airdndhomeService;
   
   @RequestMapping("/home")
   public String check(Model model) {
      
      List<AirdndHomeVO> list = airdndhomeService.homeselect();
      model.addAttribute("list", list);

      return Common.VIEW_PATH + "home.jsp";
   }
   
}