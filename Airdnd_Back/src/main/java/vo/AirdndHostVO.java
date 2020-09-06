package vo;

public class AirdndHostVO {
	//DB column
	private int idx, home_idx, host_review_num;
	private String host_name, host_sign_in_date, host_status_message, interaction_with_guests,
			host_language, response_rate, response_time, host_profileImg;
	private boolean check_superhost, check_certification;
	
	public AirdndHostVO() {
	}
	
	public AirdndHostVO(int idx, int home_idx, boolean check_superhost, boolean check_certification, int host_review_num,
			String host_name, String host_sign_in_date, String host_status_message, String interaction_with_guests,
			String host_language, String response_rate, String response_time, String host_profileImg) {
		this.idx = idx;
		this.home_idx = home_idx;
		this.check_superhost = check_superhost;
		this.check_certification = check_certification;
		this.host_review_num = host_review_num;
		this.host_name = host_name;
		this.host_sign_in_date = host_sign_in_date;
		this.host_status_message = host_status_message;
		this.interaction_with_guests = interaction_with_guests;
		this.host_language = host_language;
		this.response_rate = response_rate;
		this.response_time = response_time;
		this.host_profileImg = host_profileImg;
	}

	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getHome_idx() {
		return home_idx;
	}
	public void setHome_idx(int home_idx) {
		this.home_idx = home_idx;
	}
	public boolean isCheck_superhost() {
		return check_superhost;
	}
	public void setCheck_superhost(boolean check_superhost) {
		this.check_superhost = check_superhost;
	}
	public boolean isCheck_certification() {
		return check_certification;
	}
	public void setCheck_certification(boolean check_certification) {
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
		return interaction_with_guests;
	}
	public void setInteraction_with_guests(String interaction_with_guests) {
		this.interaction_with_guests = interaction_with_guests;
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
}
