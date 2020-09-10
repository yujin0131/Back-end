package vo;

public class AirdndChatVO {
	//DB column
	private int idx, host_idx, user_idx;
	private String host_profile_imgurl, all_hidden_unread, checkin, checkout;
	
	public AirdndChatVO() {
	}
	
	public AirdndChatVO(int idx, int host_idx, String host_profile_imgurl, int user_idx, 
						String all_hidden_unread, String checkin, String checkout) {
		this.idx = idx;
		this.host_idx = host_idx;
		this.host_profile_imgurl = host_profile_imgurl;
		this.user_idx = user_idx;
		this.all_hidden_unread = all_hidden_unread;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getHost_idx() {
		return host_idx;
	}
	public void setHost_idx(int host_idx) {
		this.host_idx = host_idx;
	}
	public int getUser_idx() {
		return user_idx;
	}
	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}
	public String getHost_profile_imgurl() {
		return host_profile_imgurl;
	}
	public void setHost_profile_imgurl(String host_profile_imgurl) {
		this.host_profile_imgurl = host_profile_imgurl;
	}
	public String getAll_hidden_unread() {
		return all_hidden_unread;
	}
	public void setAll_hidden_unread(String all_hidden_unread) {
		this.all_hidden_unread = all_hidden_unread;
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
}
