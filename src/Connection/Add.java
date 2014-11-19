package Connection;


public class Add {
	
	/**
	 * 
	 */
	private String Placename;
	private String Address1;
	private String Address2;
	private String Cityname;
	private String Phone;
	private String Category;
	private int userid; 
	
	
	public Add(){
		Placename=null;
		Address1=null;
		Address2=null;
		Cityname=null;
		Category=null;
		Phone=null;
	}
	
	public String getPlacename() {
		return Placename;
	}
	public void setPlacename(String placename) {
		Placename = placename;
	}
	public String getAddress1() {
		return Address1;
	}
	public void setAddress1(String address1) {
		Address1 = address1;
	}
	public String getAddress2() {
		return Address2;
	}
	public void setAddress2(String address2) {
		Address2 = address2;
	}
	public String getCityname() {
		return Cityname;
	}
	public void setCityname(String cityname) {
		Cityname = cityname;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	
	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}