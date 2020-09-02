package com.airdnd.back;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import service.AirdndSearchService;
import vo.AirdndHomePictureVO;
import vo.AirdndSearchVO;

@Controller
public class SearchController {

   @Autowired
   AirdndSearchService airdndsearchService;
   HttpServletRequest request;
   HttpServletResponse response;

   @RequestMapping(value="/search/location/{location}/checkIn/{checkIn}/checkOut/{checkOut}/adults/{adults}",
         method=RequestMethod.GET, produces = "application/json;charset=utf8", consumes = MediaType.ALL_VALUE)
   @ResponseBody         // 어디검색, 몇박며칠, 인원수...
   public String check(@PathVariable("location") String location, @PathVariable("checkIn") String checkIn,
         @PathVariable("checkOut") String checkOut, @PathVariable("adults") int adults) {

      HttpHeaders resHeaders = new HttpHeaders();
      resHeaders.add("Content-Type", "application/json;charset=UTF-8");
      try {
         location = URLEncoder.encode(location, "utf-8");
         checkIn = URLEncoder.encode(checkIn, "utf-8");
         checkOut = URLEncoder.encode(checkOut, "utf-8");
      } catch (UnsupportedEncodingException e1) {
         // TODO Auto-generated catch block
         e1.printStackTrace();
      }

      if(location.equals("guam")) {
         location = "괌";
      }else if(location.equals("jeju")) {
         location = "제주도";
      }else {
         location = "서울";
      }

      int page = 0;

      JSONObject res = new JSONObject();
      
      //search_list : 페이지별 숙소 리스트------------------
      List<AirdndSearchVO> search_list = airdndsearchService.searchselect(location, page);
      int size = search_list.size();

      JSONObject homes = new JSONObject();
      
      Double addLat = 0.0000000;
      Double addLng = 0.0000000;
      
      for(int i = 0; i < size; i++) {
         int home_idx = search_list.get(i).getHome_idx();

         List<AirdndHomePictureVO> picture = airdndsearchService.pictureselect(home_idx);
         List<String> picture_arr = new ArrayList<String>();

         JSONObject homes_info = new JSONObject();
         JSONObject latlng = new JSONObject();

         for(int j = 0; j < picture.size(); j++) {
            picture_arr.add(picture.get(j).getUrl());
         }

         search_list.get(i).setUrl(picture_arr);
         String lat = search_list.get(i).getLat();
         String lng = search_list.get(i).getLng();
         latlng.put("lat", lat);
         latlng.put("lng", lng);
         addLat +=  Double.parseDouble(lat);
         addLng +=  Double.parseDouble(lng);

         homes_info.put("homeId", search_list.get(i).getHome_idx());
         homes_info.put("isSuperhost", search_list.get(i).getIsSuperHost());
         homes_info.put("isBookmarked", "아직안받아옴");
         homes_info.put("imageArray", search_list.get(i).getUrl());
         homes_info.put("imageCount", search_list.get(i).getUrl().size());
         homes_info.put("subTitle", search_list.get(i).getSub_title());
         homes_info.put("title", search_list.get(i).getTitle());
         homes_info.put("feature", "최대 인원 " + search_list.get(i).getFilter_max_person() + "명 · 침실 " + search_list.get(i).getFilter_bedroom() + 
               "개 · 침대 " + search_list.get(i).getFilter_bed() + "개 · 욕실 " + search_list.get(i).getFilter_bathroom() + "개");
         homes_info.put("rating", search_list.get(i).getRating());
         homes_info.put("reviewCount", search_list.get(i).getReview_num());
         homes_info.put("price", search_list.get(i).getPrice());
         homes_info.put("location", latlng);

         homes.put(i, homes_info);

      }
      Double avgLat =(Math.round(addLat/size*10000000)/10000000.0); 
      Double avgLng =(Math.round(addLng/size*10000000)/10000000.0); 
      //Double avgLng = (double) (Math.round((addLng/size)*10000000)/10000000);
      
      System.out.println("avgLat"+avgLat);
      System.out.println("avgLng"+avgLng);
      
      res.put("homes", homes);

      //가격 분포도--------------------------------
      List<AirdndSearchVO> pricelist = airdndsearchService.unitpriceselect(location);
      List<Integer> price_array = new ArrayList();
      int start = 0;
      int end = 2;
      int save_num = 0;
      for(int i = 0; i < 50; i++) {
         for(AirdndSearchVO price : pricelist) {
            if(start <= (int)price.getPrice()/10000 && end > (int)price.getPrice()/10000) {
               save_num++;
            }
         }
         price_array.add(save_num);
         start += 2;
         end += 2;
         save_num = 0;
      }

      res.put("priceArray", price_array);

      //필터조건들 ---------------------------------
      List<AirdndSearchVO> facilities = airdndsearchService.facilityList(location);
      //List<AirdndUserVO> hostlanlists = airdndsearchService.hostLanlist(location);

      List<String> facilityList = new ArrayList<String>();
      List<String> convenienceList = new ArrayList<String>();
      List<String> hostLangList = new ArrayList<String>();

      for(AirdndSearchVO filter : facilities) {
         if( !filter.getFacilityList().contains("이용 불가:") ) {
            String element = filter.getFacilityList();
            if( element.contains("수영장") ||  element.contains("주차") || element.contains("자쿠지") || element.contains("헬스장") ) {
               facilityList.add(element);
            } else {
               convenienceList.add(element);
            }
         }
      }
      hostLangList.add("영어");
      hostLangList.add("프랑스어");
      hostLangList.add("한국어");
      hostLangList.add("스페인어");
      hostLangList.add("중국어");
      
      //for(AirdndUserVO filter : hostlanlists) {
      //  hostlanguagelist.add(filter.getHostLanlist());
      //}

      JSONObject filterCondition = new JSONObject();
      filterCondition.put("instantBooking", true);
      filterCondition.put("bedroom", true);
      filterCondition.put("convenience ", true);
      filterCondition.put("convenienceList", convenienceList);
      filterCondition.put("facilityList", facilityList);
      filterCondition.put("hostLangList", hostLangList);
      //filterCondition.put("hostLangList", hostlanguagelist);
      
      
      //--------최근 본 목록 코드 작성------------ 
      List<AirdndSearchVO> recentHomes = new ArrayList<AirdndSearchVO>();//이걸 쿠키로 받아와 검색하는 쿼리문
      JSONObject mapCenter = new JSONObject();
      mapCenter.put("lat", avgLat);
      mapCenter.put("lng", avgLng);
      
      res.put("filterCondition", filterCondition);
      //전체 숙소 데이터 개수, 1박 평균 가격 -----------------
      List<AirdndSearchVO> total = airdndsearchService.searchtotalselect(location);
      res.put("recentHomes", recentHomes);
      res.put("dataTotal", total.get(0).getData_total());
      res.put("averagePrice", total.get(0).getAverage_price());
      res.put("mapCenter",mapCenter);

      return res.toString();
   }
}