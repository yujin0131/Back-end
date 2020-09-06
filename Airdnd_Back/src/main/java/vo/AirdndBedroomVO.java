package vo;

public class AirdndBedroomVO {
	private String bed_room_name, bed_room_option, bed_icons;
	
	public AirdndBedroomVO() {
	}
	
	public AirdndBedroomVO(String bed_room_name, String bed_room_option, String bed_icons) {
		this.bed_room_name = bed_room_name;
		this.bed_room_option = bed_room_option;
		this.bed_icons = bed_icons;
	}

	public String getBed_room_name() {
		return bed_room_name;
	}

	public void setBed_room_name(String bed_room_name) {
		this.bed_room_name = bed_room_name;
	}

	public String getBed_room_option() {
		return bed_room_option;
	}

	public void setBed_room_option(String bed_room_option) {
		this.bed_room_option = bed_room_option;
	}

	public String getBed_icons() {
		return bed_icons;
	}

	public void setBed_icons(String bed_icons) {
		this.bed_icons = bed_icons;
	}

}
