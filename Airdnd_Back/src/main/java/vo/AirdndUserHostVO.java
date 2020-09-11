package vo;

public class AirdndUserHostVO {
	private int idx, user_idx, host_idx;
	
	public AirdndUserHostVO() {
	}
	
	public AirdndUserHostVO(int idx, int user_idx, int host_idx) {
		this.idx = idx;
		this.user_idx = user_idx;
		this.host_idx = host_idx;
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

	public int getHost_idx() {
		return host_idx;
	}

	public void setHost_idx(int host_idx) {
		this.host_idx = host_idx;
	}


}
