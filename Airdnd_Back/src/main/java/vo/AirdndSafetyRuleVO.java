package vo;

public class AirdndSafetyRuleVO {
	private int idx, home_idx;
	private String safety_rule;
	
	public AirdndSafetyRuleVO() {
		// TODO Auto-generated constructor stub
	}
	
	public AirdndSafetyRuleVO(int idx, int home_idx, String safety_rule) {
		this.idx = idx;
		this.home_idx = home_idx;
		this.safety_rule = safety_rule;
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
	public String getSafety_rule() {
		return safety_rule;
	}
	public void setSafety_rule(String safety_rule) {
		this.safety_rule = safety_rule;
	}
}
