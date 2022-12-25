package miniProjectECommerce$;



	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.Statement;
	import java.util.Iterator;
	import java.util.Map;
	import java.util.Map.Entry;
	import java.util.Scanner;
	import java.util.Set;

	public class Login {
		Scanner sc=new Scanner(System.in);
		String email="";
		void login()
		{
			System.out.println("please enter your registered email: ");
			email=sc.nextLine();
			try {
				String sql="select Email from users where Email=?";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Velocity17september2022");
				PreparedStatement stmt=con.prepareStatement(sql); 
				stmt.setString(1, email);
				ResultSet rs=stmt.executeQuery();
				if(rs.next())
				{
					if(rs.getString(1).equals(email))
					{
						System.out.println("Authentication Successfull.");
						ProductDetails pd = new ProductDetails();
						pd.buy();
						Billing bill=new Billing();
						bill.generateInvoice(pd,email);
						pd.inventoryAdjustment(pd,email);
					}
				}
				else {
				System.out.println("Authentication Unsuccessfull.");
				}
				con.close();
				stmt.close();
			}	catch(Exception e){ System.out.println(e);} 
		}
		
		
	}
	
	
	
	
	
	

