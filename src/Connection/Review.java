package Connection;

public class Review {

	private int reviewid;
	private String comment;
	private String Placename;
	private String address;
	private int review;
	private int placeid;
	private int userid;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getPlaceid() {
		return placeid;
	}

	public void setPlaceid(int placeid) {
		this.placeid = placeid;
	}

	public Review(){
		reviewid=0;
		comment=null;
		Placename=null;
		review=0;
		placeid=0;
		userid=0;
	}
	
	public int getReviewid() {
		return reviewid;
	}
	public void setReviewid(int reviewid) {
		this.reviewid = reviewid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getPlacename() {
		return Placename;
	}
	public void setPlacename(String placename) {
		Placename = placename;
	}
	public int getReview() {
		return review;
	}
	public void setReview(int review) {
		this.review = review;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
