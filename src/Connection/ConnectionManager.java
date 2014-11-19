package Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionManager {

static ArrayList availconn=new ArrayList();

private static Connection[] con = new Connection[10];
private static int Max=10;


public ConnectionManager(){
	try{
		System.out.println("Inside ConnectionManager constructor");
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		for(int i=0;i<Max;i++){
			con[i] = DriverManager.getConnection("jdbc:mysql://localhost:3306/cmpe273","root","password");
			if(!con[i].isClosed())
			{
				System.out.println("Successfully Connected "+i);
				availconn.add(i,true);
			}
			
		}
			} catch (SQLException e) {
		// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
		// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}

	public synchronized static  int availConnection() 
	{   int avail=10; 
		for(int i=0;i<Max;i++)
		{
			if((boolean)availconn.get(i))
				{
				avail=i;
				availconn.set(i,false);
				break;
				}
		}
		return avail;
	}
	
	public synchronized static Connection getConnection(int avail)
	{
		return con[avail];
	}
	
	public synchronized static void close(int avail)
	{
		availconn.set(avail,true);
	}
	
}
