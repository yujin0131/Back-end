package vo;

public class AirdndHomePictureVO {
	private int idx, home_idx;
	private String url;

	public AirdndHomePictureVO() {

	}
	
	public AirdndHomePictureVO(int idx, int home_idx, String url) {
		this.idx=idx;
		this.home_idx=home_idx;
		this.url=url;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
} 
