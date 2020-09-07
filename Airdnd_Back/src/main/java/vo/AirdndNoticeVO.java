package vo;

public class AirdndNoticeVO {
	private String home_notice_sort, home_notice_content, home_notice_icon;
	
	public AirdndNoticeVO() {
	}
	
	public AirdndNoticeVO(String home_notice_sort,String home_notice_content,String home_notice_icon) {
		this.home_notice_sort =home_notice_sort;
		this.home_notice_content = home_notice_content;
		this.home_notice_icon = home_notice_icon;
	}

	public String getHome_notice_sort() {
		return home_notice_sort;
	}

	public void setHome_notice_sort(String home_notice_sort) {
		this.home_notice_sort = home_notice_sort;
	}

	public String getHome_notice_content() {
		return home_notice_content;
	}

	public void setHome_notice_content(String home_notice_content) {
		this.home_notice_content = home_notice_content;
	}

	public String getHome_notice_icon() {
		return home_notice_icon;
	}

	public void setHome_notice_icon(String home_notice_icon) {
		this.home_notice_icon = home_notice_icon;
	}
	
}
