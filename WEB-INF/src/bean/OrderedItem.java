package bean;

public class OrderedItem {
	private String userid;
	private String title;
	private int quantity;
	private String date;

	// Constructor
	public OrderedItem() {
		this.userid = null;
		this.title = null;
		this.quantity = 0;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
