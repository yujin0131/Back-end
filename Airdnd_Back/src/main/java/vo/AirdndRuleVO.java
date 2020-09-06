package vo;

public class AirdndRuleVO {
	private String safety_rule, use_rule;
	
	public AirdndRuleVO() {
	}
	
	public AirdndRuleVO(String safety_rule, String use_rule) {
		this.safety_rule = safety_rule;
		this.use_rule = use_rule;
	}

	public String getSafety_rule() {
		return safety_rule;
	}

	public void setSafety_rule(String safety_rule) {
		this.safety_rule = safety_rule;
	}

	public String getUse_rule() {
		return use_rule;
	}

	public void setUse_rule(String use_rule) {
		this.use_rule = use_rule;
	}
	
}
