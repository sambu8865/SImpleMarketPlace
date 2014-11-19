package Connection;

import java.sql.ResultSet;
import java.util.LinkedList;

import javax.jws.WebService;

@WebService
public class Service {	
	DatabaseConnection db=new DatabaseConnection();
	ConnectionManager con1=new ConnectionManager(); 
	public String signUp(String fullname,String email,String phonenumber,String pwd)
	{
		System.out.println("Inside Signup");
		String result;
		
		/**This space is left for validation and manipulation of 
		input values entered by the user as a part of the server side validation*/
		
		result = db.signUp(fullname,email,phonenumber,pwd);
		System.out.println(result);
		return result;
		//this value is returned to the calling servlet
	}
	
	public String getusername(String userid)
	{
		System.out.println("Inside getusername with userid "+userid);
		String result;
		
		/**This space is left for validation and manipulation of 
		input values entered by the user as a part of the server side validation*/
		
		result = db.getusername(userid);
		
		System.out.println(result);
		return result;//this value is returned to the calling servlet
	}
	
	public Place [] places(String search,String placename){
		Place [] placeslist=db.getplaces(search,placename);
		return placeslist;
	}
	
	
	public Place [] userplaces(int userid){
		Place [] placeslist=db.userplaces(userid);
		return placeslist;
	}
	
	
	public int signIn(String username,String password)
	{
		System.out.println("Inside SignIn");
		int result=0;
		
		/**This space is left for validation and manipulation of 
		input values entered by the user as a part of the server side validation*/
		
		result = db.signIn(username,password);
		System.out.println(result);
		return result;
	}
	
	public String lastLogin(int userid)
	{
		System.out.println("Inside lastlogin");
		String result=null;
		
		/**This space is left for validation and manipulation of 
		input values entered by the user as a part of the server side validation*/
		
		result = db.lastLogin(userid);
		System.out.println(result);
		return result;
	}
		
	public int addPlace(Add place)
	{
		System.out.println("Inside addplace");
		int result=0;
		
		result=db.addPlace(place);
		System.out.println(result);
		return result;
	}
	
	public Review[] reviews(int userid)
	{
		System.out.println("inside reviews");
		Review[] review;
		review=db.reviews(userid);
		System.out.println("After fetching reviews");
		return review;
	}
	
	public Review[] reviewgen(int placeid)
	{
		System.out.println("inside reviewgen");
		Review[] review;
		review=db.reviewgen(placeid);
		System.out.println("After fetching reviewgen");
		return review;
	}
	
	public int reviewPlace(Review review1)
	{
		System.out.println("inside reviewPlace");
		int result;
		result=db.reviewPlace(review1);
		System.out.println("After posting review");
		return result;
	}
	
	//public search
	
	public int deletePlace(int placeid)
	{
		System.out.println("Inside deletePlace");
		int result;
		result=db.deletePlace(placeid);
		System.out.println("After deleting place");
		return result;
	}
	
	public int deleteReview(int reviewid)
	{
		System.out.println("Inside deletereview");
		int result;
		result=db.deleteReview(reviewid);
		System.out.println("After deleting review");
		return result;
	}
	
	
	
}
