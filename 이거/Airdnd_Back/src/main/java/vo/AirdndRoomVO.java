package vo;

public class AirdndRoomVO {
	private int room_idx;
	private String room_name, room_price, room_score, room_review_num, room_type, room_option;
	
	public AirdndRoomVO() {
		
	}
	
	public AirdndRoomVO(int room_idx, String room_name, String room_price, String room_score, String room_review_num, String room_type, String room_option) {
		this.room_idx = room_idx;
		this.room_name = room_name;
		this.room_price = room_price;
		this.room_score = room_score;
		this.room_review_num = room_review_num;
		this.room_type = room_type;
		this.room_option = room_option;
	}
	
	public int getRoom_idx() {
		return room_idx;
	}
	public void setRoom_idx(int room_idx) {
		this.room_idx = room_idx;
	}
	public String getRoom_name() {
		return room_name;
	}
	public void setRoom_name(String room_name) {
		this.room_name = room_name;
	}
	public String getRoom_price() {
		return room_price;
	}
	public void setRoom_price(String room_price) {
		this.room_price = room_price;
	}
	public String getRoom_score() {
		return room_score;
	}
	public void setRoom_score(String room_score) {
		this.room_score = room_score;
	}
	public String getRoom_review_num() {
		return room_review_num;
	}
	public void setRoom_review_num(String room_review_num) {
		this.room_review_num = room_review_num;
	}
	public String getRoom_type() {
		return room_type;
	}
	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}
	public String getRoom_option() {
		return room_option;
	}
	public void setRoom_option(String room_option) {
		this.room_option = room_option;
	}
}
