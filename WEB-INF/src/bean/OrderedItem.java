package bean;

public class OrderedItem {
	private String userid;
	private String title;
	private String date;

	// Constructor
	public OrderedItem() {
		this.userid = null;
		this.title = null;
		this.date = null;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
