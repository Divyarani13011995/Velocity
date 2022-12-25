package miniProjectECommerce$;


	
	
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.util.Scanner;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	public class Registration {
		void register_user()
		{
			String name="";
			String email="";
			String phoneNumber="";
			long temp=0;
			boolean flag1=false;
			boolean flag2=false;
			Pattern p1= Pattern.compile("^[1-9]{10}$");
			Pattern p2= Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE );
			
			System.out.println("                                                                          ");
			System.out.println("                      =============================                 ");
			System.out.println("                     ||"+"PLEASE REGISTER TO CONTINUE"+"||                      ");
			System.out.println("                      =============================                 ");
			System.out.println("                                                                          ");
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter your Name:");
			name=sc.nextLine();
			
			while(flag1==false)
			{
			System.out.println("Enter your Email:");
			email=sc.nextLine();
			if(p2.matcher(email).matches())
			{
				flag1=true;
			}
			else {
				flag1=false;
				System.out.println("Invalid Email Address");
			}
			}
			
			
			while(flag2==false) {
			System.out.println("Enter your 10 Digit Phone Number:");
			phoneNumber=sc.nextLine();
			if(p1.matcher(phoneNumber).matches())
			{
				flag2=true;
			}
			else {
				flag2=false;
				System.out.println("Invalid Phone Number");
			}
			}
			if(flag1==true&&flag2==true)
			{
			try {
				String sql="insert into users(Name,Email,PhoneNumber) values(?,?,?)";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Velocity17september2022");
				PreparedStatement stmt=con.prepareStatement(sql);
				stmt.setString(1, name);
				stmt.setString(2, email);
				stmt.setString(3, phoneNumber);
				stmt.executeUpdate();
				System.out.println("Registration Successfull.");
				
				con.close();
				stmt.close();
			}	catch(Exception e){ System.out.println(e);} 
			}
			
			System.out.println("");
			
			Login l = new Login();
			l.login();
		}
		
	}



