package Connection;

import java.sql.*;
import java.util.LinkedList;


public class DatabaseConnection {
	
    Statement stmt = null;
	
	
	public String signUp(String fullname,String email,String phonenumber,String pwd){
		String result = "";
		int rowcount;
		ResultSet results = null;
		int avail = 10;
		Connection con=null;
		try{
		avail=ConnectionManager.availConnection();
		while(avail==10)
		{
			avail=ConnectionManager.availConnection();
		}
		con=ConnectionManager.getConnection(avail);
		stmt = con.createStatement();
		}catch(Exception e){
			System.out.println("Connection failure");
			e.printStackTrace();
		}
		try {
			String query= "Select * from users where username='"+email+"'";	
			results=stmt.executeQuery(query);
			if(results.next()){
				System.out.println("Insert failed!!!");
				return ("Error: The email is already used");
			}			
			query = "Insert into users (fullname,username,phone,password,lastlogin) values ('" + fullname + "', '" + email + "', '" + phonenumber + "', '" + pwd + "',now())";
			rowcount=stmt.executeUpdate(query);
			if(rowcount > 0){
				result="true";
				System.out.println("Insert Successful");
				query="Select userid from users where username='"+email+"' limit 1";
				results=stmt.executeQuery(query);
				while(results.next()){
					System.out.println("userid: "+String.valueOf(results.getInt(1)));
					result=result+String.valueOf(results.getInt(1));
				}
			}
			else{
				result="Error: The data could not be inserted in the database.";
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result="ERROR: Wrong Input!!!";
			e.printStackTrace();
		}
		
		ConnectionManager.close(avail);
		return result;
	}
	
	public String getusername(String userid){
		ResultSet result=null;
		String username=null;
		int avail=10;
		Connection con=null;
		try{
		avail=ConnectionManager.availConnection();
		while(avail==10)
		{
			avail=ConnectionManager.availConnection();
		}
		con=ConnectionManager.getConnection(avail);
		stmt = con.createStatement();
		}catch(Exception e){
			System.out.println("Connection failure");
			e.printStackTrace();
		}
		try{
			String query="Select fullname from users where userid='"+Integer.parseInt(userid)+"'";
			result=stmt.executeQuery(query);
			if(result.next())
				username=result.getString(1) ;
			System.out.println(username);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		ConnectionManager.close(avail);
		return username;
		
	}
	
	public Place[] getplaces(String category,String cityname){
		ResultSet result=null;
		Place place1=new Place();
		int i=0;
		Place[] placelist =null;
		int avail=10;
		Connection con=null;
		try{
		avail=ConnectionManager.availConnection();
		while(avail==10)
		{
			avail=ConnectionManager.availConnection();
		}
		con=ConnectionManager.getConnection(avail);
		stmt = con.createStatement();
		}catch(Exception e){
			System.out.println("Connection failure");
			e.printStackTrace();
		}
		try{
			String query="select placeid,pname,address1,address2,phone,(select max(review) from reviewplace where placeid=places.placeid group by placeid )review from places where categoryid=(select categoryid from category where categoryname='"+ category +"') and cityid=(select cityid from city where cityname like '%"+ cityname +"%')";
			System.out.println(query);
			result=stmt.executeQuery(query);
			result.last();
			System.out.println(result.getRow());
			Place[] placelist1 =new Place[result.getRow()];
			System.out.println(placelist1.length);
			result.beforeFirst();
			System.out.println(result.getRow());
			while(result.next()){
				System.out.println("starting iteration");
				System.out.println(result.getInt(1));
				placelist1[i]=new Place();
				placelist1[i].setPlaceid(result.getInt(1));
				placelist1[i].setPlacename(result.getString(2));
				placelist1[i].setAddress(result.getString(3)+" "+result.getString(4));
				placelist1[i].setPhone(result.getString(5));
				placelist1[i].setReview(result.getInt(6));
				System.out.println("iteration "+i);
				i++;
			}
			return placelist1;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		ConnectionManager.close(avail);
		return placelist;
	}
	
	public Place[] userplaces(int userid){
		ResultSet result=null;
		Place place1=new Place();
		int i=0;
		Place[] placelist =null;
		int avail=10;
		Connection con=null;
		try{
		avail=ConnectionManager.availConnection();
		while(avail==10)
		{
			avail=ConnectionManager.availConnection();
		}
		con=ConnectionManager.getConnection(avail);
		stmt = con.createStatement();
		}catch(Exception e){
			System.out.println("Connection failure");
			e.printStackTrace();
		}
		try{
			String query="select placeid,pname,address1,address2,phone,(select max(review) from reviewplace where placeid=places.placeid group by placeid )review from places where userid="+userid;
			System.out.println(query);
			result=stmt.executeQuery(query);
			result.last();
			System.out.println(result.getRow());
			Place[] placelist1 =new Place[result.getRow()];
			System.out.println(placelist1.length);
			result.beforeFirst();
			System.out.println(result.getRow());
			while(result.next()){
				System.out.println("starting iteration");
				System.out.println(result.getInt(1));
				placelist1[i]=new Place();
				placelist1[i].setPlaceid(result.getInt(1));
				placelist1[i].setPlacename(result.getString(2));
				placelist1[i].setAddress(result.getString(3)+" "+result.getString(4));
				placelist1[i].setPhone(result.getString(5));
				placelist1[i].setReview(result.getInt(6));
				System.out.println("iteration "+i);
				i++;
			}
			return placelist1;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		ConnectionManager.close(avail);
		return placelist;
	}
	
	public int signIn(String username,String password){
		ResultSet result=null;
		Place place1=new Place();
		int i=0;
		int avail=10;
		Connection con=null;
		try{
		avail=ConnectionManager.availConnection();
		while(avail==10)
		{
			avail=ConnectionManager.availConnection();
		}
		con=ConnectionManager.getConnection(avail);
		stmt = con.createStatement();
		}catch(Exception e){
			System.out.println("Connection failure");
			e.printStackTrace();
		}
		try{
			String query="select userid from users where username='"+username+"' and password='"+password+"'";
			System.out.println(query);
			result=stmt.executeQuery(query);
			result.beforeFirst();
			if(result.next())
				{
				query="Update users set lastlogin=now() where userid="+result.getInt(1);
				int result1=(int)result.getInt("userid");
				int status=stmt.executeUpdate(query);
				ConnectionManager.close(avail);
				return result1;}
			else{
				ConnectionManager.close(avail);
				return 0;}
		}		
		catch(Exception e){
			ConnectionManager.close(avail);
			e.printStackTrace();
			return 0;
		}
		//ConnectionManager.close(avail);
	}
	
	public String lastLogin(int userid){
		ResultSet result=null;
		int avail=10;
		Connection con=null;
		try{
		avail=ConnectionManager.availConnection();
		while(avail==10)
		{
			avail=ConnectionManager.availConnection();
		}
		con=ConnectionManager.getConnection(avail);
		stmt = con.createStatement();
		}catch(Exception e){
			System.out.println("Connection failure");
			e.printStackTrace();
		}
		try{
			String query="select lastlogin from users where userid="+userid;
			System.out.println(query);
			result=stmt.executeQuery(query);
			result.beforeFirst();
			if(result.next())
				{ConnectionManager.close(avail);
				return result.getString(1);}
			else
				{ConnectionManager.close(avail);
				return null;}
		}		
		catch(Exception e){
			e.printStackTrace();
			ConnectionManager.close(avail);
			return null;
		}
	}
	
	public int addPlace(Add place){
		ResultSet result=null;
		int i=0;
		int resultinsert=0;
		int categoryid;
		int cityid;
		int avail=10;
		Connection con=null;
		try{
		avail=ConnectionManager.availConnection();
		while(avail==10)
		{
			avail=ConnectionManager.availConnection();
		}
		con=ConnectionManager.getConnection(avail);
		stmt = con.createStatement();
		}catch(Exception e){
			System.out.println("Connection failure");
			e.printStackTrace();
		}
		try{
			String query="select IFNULL(cityid,99999) from city where cityname like '%" + place.getCityname()+"%'";
			System.out.println(query);
			result=stmt.executeQuery(query);
			result.beforeFirst();
			if(result.next())
				cityid=result.getInt(1);
			else
				cityid=99999;
			query="select IFNULL(categoryid,99999) from category where categoryname like '%" + place.getCategory()+"%'";
			System.out.println(query);
			result=stmt.executeQuery(query);
			result.beforeFirst();
			if(result.next())
				categoryid=result.getInt(1);
			else
				categoryid=99999;
			query="Insert into places (pname,cityid,address1,address2,phone,categoryid,userid) values('"+place.getPlacename()+"','"+cityid+"','"+place.getAddress1()+"','"+place.getAddress2()+"','"+place.getPhone()+"','"+categoryid+"',"+place.getUserid()+")";
			System.out.println(query);
			resultinsert=stmt.executeUpdate(query);
		}		
		catch(Exception e){
			ConnectionManager.close(avail);
			e.printStackTrace();
			return 0;
		}
		ConnectionManager.close(avail);
		return resultinsert;
	}
	
	public Review[] reviews(int userid){
		ResultSet result=null;
		Review[] review=new Review[1];
		int i=0;
		int avail=10;
		Connection con=null;
		try{
		avail=ConnectionManager.availConnection();
		while(avail==10)
		{
			avail=ConnectionManager.availConnection();
		}
		con=ConnectionManager.getConnection(avail);
		stmt = con.createStatement();
		}catch(Exception e){
			System.out.println("Connection failure");
			e.printStackTrace();
		}
		try{
			String query= "select reviewid,pname,address1,address2,score,comments from places a,review b where a.placeid=b.placeid and b.userid='"+ userid +"'";
			System.out.println(query);
			result=stmt.executeQuery(query);
			result.last();
			System.out.println(result.getRow());
			Review[] review1 =new Review[result.getRow()];
			System.out.println(review1.length);
			result.beforeFirst();
			System.out.println(result.getRow());
			while(result.next()){
				System.out.println("starting iteration");
				System.out.println(result.getInt(1));
				review1[i]=new Review();
				review1[i].setReviewid(result.getInt(1));
				review1[i].setPlacename(result.getString(2));
				review1[i].setAddress(result.getString(3)+" "+result.getString(4));
				review1[i].setReview(result.getInt(5));
				review1[i].setComment(result.getString(6));
				System.out.println("iteration "+i);
				i++;
			}
			ConnectionManager.close(avail);
			return review1;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		ConnectionManager.close(avail);
		return review;
	}


	public int reviewPlace(Review review){
		int result=0;
		int avail=10;
		Connection con=null;
		try{
		avail=ConnectionManager.availConnection();
		while(avail==10)
		{
			avail=ConnectionManager.availConnection();
		}
		con=ConnectionManager.getConnection(avail);
		stmt = con.createStatement();
		}catch(Exception e){
			System.out.println("Connection failure");
			e.printStackTrace();
		}
		try{
			String query= "insert into review (placeid,score,userid,COMMENTS) values("+review.getPlaceid()+ ","+review.getReview() +",'"+review.getUserid() +"',\""+review.getComment()+"\")";
			System.out.println(query);
			result=stmt.executeUpdate(query);
			System.out.println("Review inserted successful");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		ConnectionManager.close(avail);
		return result;
	}

	public int deletePlace(int placeid){
		int result=0;
		int avail=10;
		Connection con=null;
		try{
		avail=ConnectionManager.availConnection();
		while(avail==10)
		{
			avail=ConnectionManager.availConnection();
		}
		con=ConnectionManager.getConnection(avail);
		stmt = con.createStatement();
		}catch(Exception e){
			System.out.println("Connection failure");
			e.printStackTrace();
		}
		try{
			String query= "delete from places where placeid='"+placeid+"'";
			System.out.println(query);
			result=stmt.executeUpdate(query);
			System.out.println("after deleting place successful");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		ConnectionManager.close(avail);
		return result;
	}

	public int deleteReview(int reviewid){
		int result=0;
		int avail=10;
		Connection con=null;
		try{
		avail=ConnectionManager.availConnection();
		while(avail==10)
		{
			avail=ConnectionManager.availConnection();
		}
		con=ConnectionManager.getConnection(avail);
		stmt = con.createStatement();
		}catch(Exception e){
			System.out.println("Connection failure");
			e.printStackTrace();
		}
		try{
			String query="delete from review where reviewid='"+reviewid+"'";
			System.out.println(query);
			result=stmt.executeUpdate(query);
			System.out.println("after deleting review successful");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		ConnectionManager.close(avail);
		return result;
	}
	
	
	public Review[] reviewgen(int placeid){
		ResultSet result=null;
		Review[] review=new Review[1];
		int i=0;
		int avail=10;
		Connection con=null;
		try{
		avail=ConnectionManager.availConnection();
		while(avail==10)
		{
			avail=ConnectionManager.availConnection();
		}
		con=ConnectionManager.getConnection(avail);
		stmt = con.createStatement();
		}catch(Exception e){
			System.out.println("Connection failure");
			e.printStackTrace();
		}
		try{
			String query= "select reviewid,pname,address1,address2,score,comments from places a,review b where a.placeid=b.placeid and b.placeid='"+ placeid +"'";
			System.out.println(query);
			result=stmt.executeQuery(query);
			result.last();
			System.out.println(result.getRow());
			Review[] review1 =new Review[result.getRow()];
			System.out.println(review1.length);
			result.beforeFirst();
			System.out.println(result.getRow());
			while(result.next()){
				System.out.println("starting iteration");
				System.out.println(result.getInt(1));
				review1[i]=new Review();
				review1[i].setReviewid(result.getInt(1));
				review1[i].setPlacename(result.getString(2));
				review1[i].setAddress(result.getString(3)+" "+result.getString(4));
				review1[i].setReview(result.getInt(5));
				review1[i].setComment(result.getString(6));
				System.out.println("iteration "+i);
				i++;
			}
			ConnectionManager.close(avail);
			return review1;
		}
		catch(Exception e){
			e.printStackTrace();
		}
		ConnectionManager.close(avail);
		return review;
	}
	
}
