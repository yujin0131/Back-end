package vo;

public class AirdndChatMsgsVO {
	//DB column
	private int idx, message_idx, from_idx, to_idx;
	private String content, send_date_time, from_profile_imgurl;
	
	public AirdndChatMsgsVO() {
	}
	
	public AirdndChatMsgsVO(int idx, int message_idx, int from_idx, int to_idx, String content, String send_date_time, String from_profile_imgurl) {
		this.idx = idx;
		this.message_idx = message_idx;
		this.from_idx = from_idx;
		this.to_idx = to_idx;
		this.content = content;
		this.send_date_time = send_date_time;
		this.from_profile_imgurl = from_profile_imgurl;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public void setMessage_idx(int message_idx) {
		this.message_idx = message_idx;
	}
	public int getMessage_idx() {
		return message_idx;
	}
	public int getFrom_idx() {
		return from_idx;
	}
	public void setFrom_idx(int from_idx) {
		this.from_idx = from_idx;
	}
	public int getTo_idx() {
		return to_idx;
	}
	public void setTo_idx(int to_idx) {
		this.to_idx = to_idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSend_date_time() {
		return send_date_time;
	}
	public void setSend_date_time(String send_date_time) {
		this.send_date_time = send_date_time;
	}
	public String getFrom_profile_imgurl() {
		return from_profile_imgurl;
	}
	public void setFrom_profile_imgurl(String from_profile_imgurl) {
		this.from_profile_imgurl = from_profile_imgurl;
	}
}
