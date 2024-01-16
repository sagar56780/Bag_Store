package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.mysql.cj.protocol.x.SyncFlushDeflaterOutputStream;

public class UpdateSeats {
	public static void main(String[] args) throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		System.out.println("driver loaded successfully....");
	
		String url = "jdbc:mysql://localhost:3306/entertainment";//product is database name
		String user = "root";
		String password = "Sagar@1122";
		Connection con = null;
		try {
			 con = DriverManager.getConnection(url, user, password);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		for(int i=1;i<=10;i++)
		{
			PreparedStatement ps=con.prepareStatement("insert into seats values(?,?)");
			ps.setInt(1,i);
			ps.setString(2,"available");
			ps.executeUpdate();
			
		}
		
		System.out.println("all values entered succussfully");
		
	}
	
	
}