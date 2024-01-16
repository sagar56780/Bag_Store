package product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.Scanner;


public class Bag {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// driver load and register
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Driver loaded");
		String url = "jdbc:mysql://localhost:3306/product";//product is database name
		String user = "root";
		String password = "Sagar@1122";
		Connection con = DriverManager.getConnection(url, user, password);
		
		//for creating table
//		String qurry="create table bag(brand varchar(20),"
//				+ "color varchar(20),"
//				+ "price integer(6),"
//				+ "type varchar(20),"
//				+ "discount integer(6),"
//				+ "rating decimal(20))";
		Scanner sc = new Scanner(System.in);
		System.out.println("do want to add more bags");
		System.out.println("if Yes press 'Y' else anything");
		String check = sc.nextLine();
		while (check.equals("y")) {
			System.out.println("enter Brand name");
			String brand = sc.nextLine();
			System.out.println("enter color ");
			String color = sc.nextLine();
			System.out.println("enter price ");
			int price = sc.nextInt();
			sc.nextLine();
			System.out.println("enter type ");
			String type = sc.nextLine();
			System.out.println("enter discount ");
			int discount = sc.nextInt();
			System.out.println("enter rating ");
			double rating;
			while (true) {
				rating = sc.nextDouble();
				if (rating <= 5 && rating >= 0) {
					break;
				}
				else {
					System.out.println("Rating is not in range !");
				}
			}
			String qurry = "insert into bag values(?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(qurry);
			ps.setString(1, brand);
			ps.setString(2, color);
			ps.setInt(3, price);
			ps.setString(4, type);
			ps.setInt(5, discount);
			ps.setDouble(6, rating);
			ps.executeUpdate();
			System.out.println("product added successfully ");
			System.out.println("do you want to add more data \n If yes press 'Y' else press anything");
			check = sc.next();
			sc.nextLine();
			ps.close();

		}

		System.out.println("values added successfully");
		boolean flag = true;
		while (flag) {
			System.out.println("choose Option option on which you want to filter bags ");
			System.out.println("1 all data :-");
			System.out.println("2 brand   :-");
			System.out.println("3 color   :-");
			System.out.println("4 price   :-");
			System.out.println("5 discount:-");
			System.out.println("6 rating  :-");
			System.out.println("7 for exit():-");
			int choice = sc.nextInt();
			sc.nextLine();
			switch (choice) {

			case 1:
				Statement st = con.createStatement();
				ResultSet rs = st.executeQuery("select * from bag ");

				while(rs.next()) {
					System.out.println("brand name  =>   " + rs.getString(1) + " ");
					System.out.println("color       =>   " + rs.getString(2) + " ");
					System.out.println("price       =>   " + rs.getInt(3) + " ");
					System.out.println("type        =>   " + rs.getString(4) + " ");
					System.out.println("discount    =>   " + rs.getInt(5) + " ");
					System.out.println("rating      =>   " + rs.getDouble(6) + " ");
					System.out.println();
					System.out.println();

				}
				break;

			case 2:
				System.out.println("choice brand :- ");
				st = con.createStatement();
				rs = st.executeQuery("select distinct brand from bag");
				while (rs.next()) {
					System.out.println(rs.getString(1));

				}
				System.out.println("Enter which brand bags you want");
				System.out.println();
				String brandchoice = sc.next();
//				String qurry="Select * from bag where brand='"+brandchoice+"'";
//				
//				rs=st.executeQuery(qurry);

				String query = "select * from bag where brand = ?";
				PreparedStatement pst = con.prepareStatement(query);
				pst.setString(1, brandchoice);
				rs = pst.executeQuery();
				while (rs.next()) {
					System.out.println("brand name  =>   " + rs.getString(1) + " ");
					System.out.println("color       =>   " + rs.getString(2) + " ");
					System.out.println("price       =>   " + rs.getInt(3) + " ");
					System.out.println("type        =>   " + rs.getString(4) + " ");
					System.out.println("discount    =>   " + rs.getInt(5) + " ");
					System.out.println("rating      =>   " + rs.getDouble(6) + " ");
					System.out.println();
					System.out.println();

				}

				break;
			case 3:
				st = con.createStatement();
				rs = st.executeQuery("select distinct color from bag ");
				while (rs.next()) {
					System.out.println(rs.getString(1));

				}
				System.out.println("These are the color present  Enter your color");
				String colorchoice = sc.next();
				String qurry = "select * from bag where color='"+ colorchoice +"'";

				rs = st.executeQuery(qurry);
				while (rs.next()) {
					System.out.println("brand name  =>   " + rs.getString(1) + " ");
					System.out.println("color       =>   " + rs.getString(2) + " ");
					System.out.println("price       =>   " + rs.getInt(3) + " ");
					System.out.println("type        =>   " + rs.getString(4) + " ");
					System.out.println("discount    =>   " + rs.getInt(5) + " ");
					System.out.println("rating      =>   " + rs.getDouble(6) + " ");
					System.out.println();
					System.out.println();

				}

				break;
			case 4:
				System.out.println("choice in which order you want to display Bags");
				System.out.println("1 for low to high");
				System.out.println("2 for high to low");
				st = con.createStatement();

				int pricechoice = sc.nextInt();
				if (pricechoice == 1) {
					rs = st.executeQuery("select * from bag order by price");

					while (rs.next()) {
						System.out.println("brand name  =>   " + rs.getString(1) + " ");
						System.out.println("color       =>   " + rs.getString(2) + " ");
						System.out.println("price       =>   " + rs.getInt(3) + " ");
						System.out.println("type        =>   " + rs.getString(4) + " ");
						System.out.println("discount    =>   " + rs.getInt(5) + " ");
						System.out.println("rating      =>   " + rs.getDouble(6) + " ");
						System.out.println();
						System.out.println();

					}

				} else if (pricechoice == 2) {
					rs = st.executeQuery("select * from bag order by price desc");

					while (rs.next()) {
						System.out.println("brand name  =>   " + rs.getString(1) + " ");
						System.out.println("color       =>   " + rs.getString(2) + " ");
						System.out.println("price       =>   " + rs.getInt(3) + " ");
						System.out.println("type        =>   " + rs.getString(4) + " ");
						System.out.println("discount    =>   " + rs.getInt(5) + " ");
						System.out.println("rating      =>   " + rs.getDouble(6) + " ");
						System.out.println();
						System.out.println();

					}

				} else {
					System.out.println("Invalid input !");
					System.out.println("please retry");

				}
				break;
			case 5:
				System.out.println("choice Discount range");
				System.out.println("1 for 0-20% discount:-");
				System.out.println("2 for 20-50% discount:-");
				System.out.println("3 for 50-70% discount:-");
				System.out.println("4 for 70-100% discount:-");

				int dischoice = sc.nextInt();
				switch (dischoice) {
				case 1:
					st = con.createStatement();
					rs = st.executeQuery("select * from bag where discount<=20");
					while (rs.next()) {
						System.out.println("brand name  =>   " + rs.getString(1) + " ");
						System.out.println("color       =>   " + rs.getString(2) + " ");
						System.out.println("price       =>   " + rs.getInt(3) + " ");
						System.out.println("type        =>   " + rs.getString(4) + " ");
						System.out.println("discount    =>   " + rs.getInt(5) + " ");
						System.out.println("rating      =>   " + rs.getDouble(6) + " ");
						System.out.println();
						System.out.println();

					}

					break;
				case 2:
					st = con.createStatement();
					rs = st.executeQuery("select * from bag where discount>=20||discount<=50");
					while (rs.next()) {
						System.out.println("brand name  =>   " + rs.getString(1) + " ");
						System.out.println("color       =>   " + rs.getString(2) + " ");
						System.out.println("price       =>   " + rs.getInt(3) + " ");
						System.out.println("type        =>   " + rs.getString(4) + " ");
						System.out.println("discount    =>   " + rs.getInt(5) + " ");
						System.out.println("rating      =>   " + rs.getDouble(6) + " ");
						System.out.println();
						System.out.println();

					}

					break;
				case 3:
					st = con.createStatement();
					rs = st.executeQuery("select * from bag where discount>=50||discount<=70");
					while (rs.next()) {
						System.out.println("brand name  =>   " + rs.getString(1) + " ");
						System.out.println("color       =>   " + rs.getString(2) + " ");
						System.out.println("price       =>   " + rs.getInt(3) + " ");
						System.out.println("type        =>   " + rs.getString(4) + " ");
						System.out.println("discount    =>   " + rs.getInt(5) + " ");
						System.out.println("rating      =>   " + rs.getDouble(6) + " ");
						System.out.println();
						System.out.println();

					}

					break;

				default:
					System.out.println("Invalid Input !");
					System.out.println("please retry ");

					break;

				}
				break;
			case 6:
				System.out.println("filter by rating ");
				System.out.println("1  for Low to high");
				System.out.println("2 for  high to low ");
				int ratingchoice = sc.nextInt();
				if (ratingchoice == 1) {
					st = con.createStatement();
					rs = st.executeQuery("select * from bag order by rating");
					while (rs.next()) {
						System.out.println("brand name  =>   " + rs.getString(1) + " ");
						System.out.println("color       =>   " + rs.getString(2) + " ");
						System.out.println("price       =>   " + rs.getInt(3) + " ");
						System.out.println("type        =>   " + rs.getString(4) + " ");
						System.out.println("discount    =>   " + rs.getInt(5) + " ");
						System.out.println("rating      =>   " + rs.getDouble(6) + " ");
						System.out.println();
						System.out.println();

					}

				} else if (ratingchoice == 2) {
					st = con.createStatement();
					rs = st.executeQuery("select * from bag order by rating desc");
					while (rs.next()) {
						System.out.println("brand name  =>   " + rs.getString(1) + " ");
						System.out.println("color       =>   " + rs.getString(2) + " ");
						System.out.println("price       =>   " + rs.getInt(3) + " ");
						System.out.println("type        =>   " + rs.getString(4) + " ");
						System.out.println("discount    =>   " + rs.getInt(5) + " ");
						System.out.println("rating      =>   " + rs.getDouble(6) + " ");
						System.out.println();
						System.out.println();

					}
				} else {
					System.out.println("Invalid input !");
					System.out.println("please retry");

				}
				break;
			case 7:
				flag = false;

			default:
				
			}
			
		}
		con.close();
		sc.close();

	}

}
