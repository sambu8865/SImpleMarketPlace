package Connection;


public class Place {
	
	/**
	 * 
	 */
	private int placeid;
	private String placename;
	private String address;
	private String phone;
	private int review;
	
	public Place(){
		placeid=0;
		placename=null;
		address=null;
		phone=null;
		review=0;
	}
	
	public int getPlaceid() {
		return placeid;
	}
	public void setPlaceid(int placeid) {
		this.placeid = placeid;
	}
	public String getPlacename() {
		return placename;
	}
	public void setPlacename(String placename) {
		this.placename = placename;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getReview() {
		return review;
	}
	public void setReview(int review) {
		this.review = review;
	}
		
}
