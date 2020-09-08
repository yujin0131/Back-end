package vo;

public class AirdndReviewVO {
	private int idx;
	private String user_name, review_date, review_content;
	private double room_cleanliness, room_accuracy, room_communication, room_position, room_checkin, room_cost_effectiveness;
	
	public AirdndReviewVO() {
		// TODO Auto-generated constructor stub
	}
	
	public AirdndReviewVO(int idx, String user_name, String review_date, String review_content, double room_cleanliness, double room_accuracy, double room_communication, double room_position, double room_checkin, double room_cost_effectiveness) {
		// TODO Auto-generated constructor stub
		this.idx = idx;
		this.user_name = user_name;
		this.review_date = review_date;
		this.review_content = review_content;
		this.room_cleanliness=room_cleanliness;
		this.room_accuracy=room_accuracy;
		this.room_communication=room_communication;
		this.room_position=room_position;
		this.room_checkin=room_checkin;
		this.room_cost_effectiveness=room_cost_effectiveness;
		
	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getReview_date() {
		return review_date;
	}

	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}

	public String getReview_content() {
		return review_content;
	}

	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}

	public double getRoom_cleanliness() {
		return room_cleanliness;
	}

	public void setRoom_cleanliness(double room_cleanliness) {
		this.room_cleanliness = room_cleanliness;
	}

	public double getRoom_accuracy() {
		return room_accuracy;
	}

	public void setRoom_accuracy(double room_accuracy) {
		this.room_accuracy = room_accuracy;
	}

	public double getRoom_communication() {
		return room_communication;
	}

	public void setRoom_communication(double room_communication) {
		this.room_communication = room_communication;
	}

	public double getRoom_position() {
		return room_position;
	}

	public void setRoom_position(double room_position) {
		this.room_position = room_position;
	}

	public double getRoom_checkin() {
		return room_checkin;
	}

	public void setRoom_checkin(double room_checkin) {
		this.room_checkin = room_checkin;
	}

	public double getRoom_cost_effectiveness() {
		return room_cost_effectiveness;
	}

	public void setRoom_cost_effectiveness(double room_cost_effectiveness) {
		this.room_cost_effectiveness = room_cost_effectiveness;
	}
	
}
