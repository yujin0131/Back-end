package vo;

import org.springframework.web.multipart.MultipartFile;

public class AirdndChatVO {
	//DB column
	private int idx, host_idx, user_idx, msg_hidden_or_not;
	private String content, image_url, send_date_time;
	
	//not DB column
	private String host_name, user_name;
	private MultipartFile host_profile, user_profile, image;
	
	public AirdndChatVO() {
	}
	
	public AirdndChatVO(int idx, int host_idx, int user_idx, int msg_hidden_or_not, String content, String image_url, String send_date_time) {
		this.idx = idx;
		this.host_idx = host_idx;
		this.user_idx = user_idx;
		this.msg_hidden_or_not = msg_hidden_or_not;
		this.content = content;
		this.image_url = image_url;
		this.send_date_time = send_date_time;
		/*
		this.host_name = host_name;
		this.user_name = user_name;
		this.host_profile = host_profile;
		this.user_profile = user_profile;
		this.image = image;
		*/
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
	public int getMsg_hidden_or_not() {
		return msg_hidden_or_not;
	}
	public void setMsg_hidden_or_not(int msg_hidden_or_not) {
		this.msg_hidden_or_not = msg_hidden_or_not;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getSend_date_time() {
		return send_date_time;
	}
	public void setSend_date_time(String send_date_time) {
		this.send_date_time = send_date_time;
	}
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public MultipartFile getHost_profile() {
		return host_profile;
	}
	public void setHost_profile(MultipartFile host_profile) {
		this.host_profile = host_profile;
	}
	public MultipartFile getUser_profile() {
		return user_profile;
	}
	public void setUser_profile(MultipartFile user_profile) {
		this.user_profile = user_profile;
	}
	public MultipartFile getImage() {
		return image;
	}
	public void setImage(MultipartFile image) {
		this.image = image;
	}
}
