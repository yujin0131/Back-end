package vo;

public class AirdndUseRuleVO {
	private int idx, home_idx;
	private String use_rule;
	
	public AirdndUseRuleVO() {
	}
	
	public AirdndUseRuleVO(int idx, int home_idx, String use_rule) {
		this.idx = idx;
		this.home_idx = home_idx;
		this.use_rule = use_rule;
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
	public String getUse_rule() {
		return use_rule;
	}
	public void setUse_rule(String use_rule) {
		this.use_rule = use_rule;
	}
}
