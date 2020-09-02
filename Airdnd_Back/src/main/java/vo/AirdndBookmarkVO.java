package vo;

public class AirdndBookmarkVO {
	//DB column
	private int idx, user_idx;
	private String bookmark_list_title, checkin, checkout, update_date_time;
	
	//not DB column
	private int home_count;
	
	public AirdndBookmarkVO() {
	}
	
	public AirdndBookmarkVO(int idx, int user_idx, String bookmark_list_title, String checkin, String checkout, String update_date_time) {
		this.idx = idx;
		this.user_idx = user_idx;
		this.bookmark_list_title = bookmark_list_title;
		this.checkin = checkin;
		this.checkout = checkout;
		this.update_date_time = update_date_time;
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
	public String getBookmark_list_title() {
		return bookmark_list_title;
	}
	public void setBookmark_list_title(String bookmark_list_title) {
		this.bookmark_list_title = bookmark_list_title;
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
	public String getUpdate_date_time() {
		return update_date_time;
	}
	public void setUpdate_date_time(String update_date_time) {
		this.update_date_time = update_date_time;
	}
	public int getHome_count() {
		return home_count;
	}
	public void setHome_count(int home_count) {
		this.home_count = home_count;
	}
}
