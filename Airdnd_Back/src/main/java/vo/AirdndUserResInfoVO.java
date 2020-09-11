package vo;

public class AirdndUserResInfoVO {
	private int idx, user_idx, home_idx, guest_idx, is_canceled, adult, child, infant;
	private String checkin, checkout;
	
	//not DB column
	private String url; //home_picture table's column

	public AirdndUserResInfoVO() {
	}
	
	public AirdndUserResInfoVO(int idx, int user_idx, int home_idx, String checkin, String checkout, int guest_idx, int is_canceled, int adult, int child, int infant) {
		this.idx = idx;
		this.user_idx = user_idx;
		this.home_idx = home_idx;
		this.guest_idx = guest_idx;
		this.is_canceled = is_canceled;
		this.checkin = checkin;
		this.checkout = checkout;
		this.adult = adult;
		this.child = child;
		this.infant = infant;
	}

	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public int getHome_idx() {
		return home_idx;
	}
	public void setHome_idx(int home_idx) {
		this.home_idx = home_idx;
	}
	public int getGuest_idx() {
		return guest_idx;
	}
	public void setGuest_idx(int guest_idx) {
		this.guest_idx = guest_idx;
	}
	public int getIs_canceled() {
		return is_canceled;
	}
	public void setIs_canceled(int is_canceled) {
		this.is_canceled = is_canceled;
	}
	public String getCheckin() {
		return checkin;
	}
	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}
	public String getCheckout() {
		return checkout;
	}
	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getAdult() {
		return adult;
	}
	public void setAdult(int adult) {
		this.adult = adult;
	}
	public int getChild() {
		return child;
	}
	public void setChild(int child) {
		this.child = child;
	}
	public int getInfant() {
		return infant;
	}
	public void setInfant(int infant) {
		this.infant = infant;
	}
}
