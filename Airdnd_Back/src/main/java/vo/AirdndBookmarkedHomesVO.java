package vo;

public class AirdndBookmarkedHomesVO {
	//DB column
	private int idx, bookmark_idx, user_idx, home_idx;
	
	//not DB column
	private String url;
	
	public AirdndBookmarkedHomesVO() {
	}
	
	public AirdndBookmarkedHomesVO(int idx, int bookmark_idx, int user_idx, int home_idx) {
		this.idx = idx;
		this.bookmark_idx = bookmark_idx;
		this.user_idx = user_idx;
		this.home_idx = home_idx;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getBookmark_idx() {
		return bookmark_idx;
	}
	public void setBookmark_idx(int bookmark_idx) {
		this.bookmark_idx = bookmark_idx;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
