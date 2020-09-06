package vo;

import java.util.List;

public class AirdndHomeVO {

	private int check_superhost, check_certification, host_review_num;
	private String host_name, host_sign_in_date, host_status_message, Interaction_with_guests, host_language, response_rate, response_time, host_profileImg;

	private int filter_max_person, filter_bedroom, filter_bed, filter_bathroom, price; 
	private double lat, lng;
	private String place, title, addr, sub_title, host_notice, loc_info;
	
	public AirdndHomeVO() {
	}

	public AirdndHomeVO(int check_superhost, int check_certification, int host_review_num, String host_name, String host_sign_in_date,
			String host_status_message, String Interaction_with_guests, String host_language, String response_rate, String response_time, String host_profileImg) {
		this.check_superhost = check_superhost;
		this.check_certification = check_certification;
		this.host_review_num = host_review_num;
		this.host_name = host_name;
		this.host_sign_in_date = host_sign_in_date;
		this.host_status_message = host_status_message;
		this.Interaction_with_guests = Interaction_with_guests;
		this.host_language = host_language;
		this.response_rate = response_rate;
		this.response_time = response_time;
		this.host_profileImg = host_profileImg;
	}

	public int getCheck_superhost() {
		return check_superhost;
	}

	public void setCheck_superhost(int check_superhost) {
		this.check_superhost = check_superhost;
	}

	public int getCheck_certification() {
		return check_certification;
	}

	public void setCheck_certification(int check_certification) {
		this.check_certification = check_certification;
	}

	public int getHost_review_num() {
		return host_review_num;
	}

	public void setHost_review_num(int host_review_num) {
		this.host_review_num = host_review_num;
	}

	public String getHost_name() {
		return host_name;
	}

	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}

	public String getHost_sign_in_date() {
		return host_sign_in_date;
	}

	public void setHost_sign_in_date(String host_sign_in_date) {
		this.host_sign_in_date = host_sign_in_date;
	}

	public String getHost_status_message() {
		return host_status_message;
	}

	public void setHost_status_message(String host_status_message) {
		this.host_status_message = host_status_message;
	}

	public String getInteraction_with_guests() {
		return Interaction_with_guests;
	}

	public void setInteraction_with_guests(String interaction_with_guests) {
		Interaction_with_guests = interaction_with_guests;
	}

	public String getHost_language() {
		return host_language;
	}

	public void setHost_language(String host_language) {
		this.host_language = host_language;
	}

	public String getResponse_rate() {
		return response_rate;
	}

	public void setResponse_rate(String response_rate) {
		this.response_rate = response_rate;
	}

	public String getResponse_time() {
		return response_time;
	}

	public void setResponse_time(String response_time) {
		this.response_time = response_time;
	}

	public String getHost_profileImg() {
		return host_profileImg;
	}

	public void setHost_profileImg(String host_profileImg) {
		this.host_profileImg = host_profileImg;
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

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getSub_title() {
		return sub_title;
	}

	public void setSub_title(String sub_title) {
		this.sub_title = sub_title;
	}

	public String getHost_notice() {
		return host_notice;
	}

	public void setHost_notice(String host_notice) {
		this.host_notice = host_notice;
	}

	public String getLoc_info() {
		return loc_info;
	}

	public void setLoc_info(String loc_info) {
		this.loc_info = loc_info;
	}
	
}
