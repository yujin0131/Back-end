package vo;

public class AirdndDistanceVO {
	
	private String attractions_name, attractions_distance;
	
	public AirdndDistanceVO() {
		// TODO Auto-generated constructor stub
	}
	
	public AirdndDistanceVO(String attractions_name, String attractions_distance) {
		this.attractions_distance = attractions_distance;
		this.attractions_name = attractions_name;
	}

	public String getAttractions_name() {
		return attractions_name;
	}

	public void setAttractions_name(String attractions_name) {
		this.attractions_name = attractions_name;
	}

	public String getAttractions_distance() {
		return attractions_distance;
	}

	public void setAttractions_distance(String attractions_distance) {
		this.attractions_distance = attractions_distance;
	}
}
