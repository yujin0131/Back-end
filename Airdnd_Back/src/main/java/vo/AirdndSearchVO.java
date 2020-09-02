package vo;

import java.util.List;

public class AirdndSearchVO {
	private String sub_title, title, lat, lng, place;
	private int home_idx, filter_max_person, filter_bedroom, filter_bed, filter_bathroom, price, review_num, average_price, data_total;
	private double rating;
	private boolean isSuperHost;
	private List<String> url;
	private String facilityList;
	
	public AirdndSearchVO() {

	}

	public AirdndSearchVO(int home_idx, boolean isSuperHost, String sub_title, String title, int filter_max_person,
			int filter_bedroom, int filter_bed, int filter_bathroom, int price, double rating, int review_num,
			String lat, String lng) {

		this.home_idx = home_idx;
		this.isSuperHost = isSuperHost;
		this.sub_title = sub_title;
		this.title = title;
		this.filter_max_person = filter_max_person;
		this.filter_bedroom = filter_bedroom;
		this.filter_bed = filter_bed;
		this.filter_bathroom = filter_bathroom;
		this.price = price;
		this.rating = rating;
		this.review_num = review_num;
		this.lat = lat;
		this.lng = lng;
		
	}

	public AirdndSearchVO(int price) {
		this.price = price;
	}
	
	public AirdndSearchVO(String facilityList) {
		this.facilityList = facilityList;
	}
		
	public AirdndSearchVO(int average_price, int data_total) {
		this.average_price = average_price;
		this.data_total = data_total;
	}
	
	public String getFacilityList() {
		return facilityList;
	}

	public void setFacilityList(String facilityList) {
		this.facilityList = facilityList;
	}
	
	public int getAverage_price() {
		return average_price;
	}

	public void setAverage_price(int average_price) {
		this.average_price = average_price;
	}

	public int getData_total() {
		return data_total;
	}

	public void setData_total(int data_total) {
		this.data_total = data_total;
	}

	public boolean getIsSuperHost() {
		return isSuperHost;
	}

	public void setSuperHost(boolean isSuperHost) {
		this.isSuperHost = isSuperHost;
	}

	public List<String> getUrl() {
		return url;
	}

	public void setUrl(List<String> url) {
		this.url = url;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getReview_num() {
		return review_num;
	}

	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getHome_idx() {
		return home_idx;
	}

	public void setHome_idx(int home_idx) {
		this.home_idx = home_idx;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

   public void setTitle(String title) {
      this.title = title;
   }

   public int getFilter_max_person() {
      return filter_max_person;
   }

   public void setFilter_max_person(int filter_max_person) {
      this.filter_max_person = filter_max_person;
   }

   public int getFilter_bedroom() {
      return filter_bedroom;
   }

   public void setFilter_bedroom(int filter_bedroom) {
      this.filter_bedroom = filter_bedroom;
   }

   public int getFilter_bed() {
      return filter_bed;
   }

   public void setFilter_bed(int filter_bed) {
      this.filter_bed = filter_bed;
   }

   public int getFilter_bathroom() {
      return filter_bathroom;
   }

   public void setFilter_bathroom(int filter_bathroom) {
      this.filter_bathroom = filter_bathroom;
   }

   public int getPrice() {
      return price;
   }

   public void setPrice(int price) {
      this.price = price;
   }


} 