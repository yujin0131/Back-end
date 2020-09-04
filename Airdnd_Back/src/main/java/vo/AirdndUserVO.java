package vo;

public class AirdndUserVO {
	private int user_idx;
	private String email, pwd, last_name, first_name, birthday, profileImg, phone, signupDate, description;
	private String hostLanlist;
	
	public AirdndUserVO() {
		
	}
	public AirdndUserVO(String hostLanlist) {
		this.hostLanlist = hostLanlist;
	}

	public AirdndUserVO(int user_idx, String email, String pwd, String last_name, String first_name, String birthday, String profileImg, String phone, String signupDate, String description) {
		this.user_idx = user_idx;
		this.email = email;
		this.pwd = pwd;
		this.last_name = last_name;
		this.first_name = first_name;
		this.birthday = birthday;
		this.profileImg = profileImg;
		this.phone = phone;
		this.signupDate = signupDate;
		this.description = description;
	}
	
	public String getHostLanlist() {
		return hostLanlist;
	}
	
	public void setHostLanlist(String hostLanlist) {
		this.hostLanlist = hostLanlist;
	}
	
	public int getUser_idx() {
		return user_idx;
	}

	public void setUser_idx(int user_idx) {
		this.user_idx = user_idx;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSignupDate() {
		return signupDate;
	}

	public void setSignupDate(String signupDate) {
		this.signupDate = signupDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
