package vo;

public class AirdndHomeVO {
	private int home_idx, filter_max_person, filter_bedroom, filter_bed, filter_bathroom, price;
	private String place, title, addr, lat, lng, sub_title, host_notice, loc_info;
	private boolean isSuperHost;
	
	public AirdndHomeVO() {
		
	}
	
	public AirdndHomeVO(int home_idx, String place, String title, boolean isSuperHost, String addr, String lat,
						String lng, String sub_title, int filter_max_person, int filter_bedroom, int filter_bed, int filter_bathroom,
						int price, String host_notice, String loc_info) {
		
		this.home_idx = home_idx;
		this.place = place;
		this.title = title;
		this.isSuperHost = isSuperHost;
		this.addr = addr;
		this.lat = lat;
		this.lng = lng;
		this.sub_title = sub_title;
		this.filter_max_person = filter_max_person;
		this.filter_bedroom = filter_bedroom;
		this.filter_bed = filter_bed;
		this.filter_bathroom = filter_bathroom;
		this.price = price;
		this.host_notice = host_notice;
		this.loc_info = loc_info;
		
	}
	
	public int getHome_idx() {
		return home_idx;
	}
	public void setHome_idx(int home_idx) {
		this.home_idx = home_idx;
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
	public boolean isSuperHost() {
		return isSuperHost;
	}
	public void setSuperHost(boolean isSuperHost) {
		this.isSuperHost = isSuperHost;
	}
	
	
} 
