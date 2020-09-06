package vo;

public class AirdndFacilityVO {
	private String facility, facility_icon;
	
	public AirdndFacilityVO() {
		// TODO Auto-generated constructor stub
	}
	
	public AirdndFacilityVO(String facility, String facility_icon) {
		this.facility = facility;
		this.facility_icon = facility_icon;
	}

	public String getFacility() {
		return facility;
	}

	public void setFacility(String facility) {
		this.facility = facility;
	}

	public String getFacility_icon() {
		return facility_icon;
	}

	public void setFacility_icon(String facility_icon) {
		this.facility_icon = facility_icon;
	}
}
