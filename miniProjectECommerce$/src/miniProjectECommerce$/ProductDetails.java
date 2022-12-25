package miniProjectECommerce$;


	
	
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.HashMap;
	import java.util.Iterator;
	import java.util.Map;
	import java.util.Scanner;
	import java.util.Set;

	public class ProductDetails {
		int totalammount;
		HashMap<Integer,Integer> addtocart=new HashMap<Integer,Integer>();
		ProductDetails()
		{
			System.out.println("-----------------------================--------------------------");
			System.out.println("-----------------------|Product Catlog|--------------------------");
			System.out.println("-----------------------================--------------------------");
			
			try {
				String sql="select * from products order by price;";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","mydatabase");
				Statement stmt=con.createStatement();  
				ResultSet rs=stmt.executeQuery(sql);
				while(rs.next())
				{
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t\t"+rs.getString(3)+"\t\t\t"+rs.getInt(4)+"\t\t\t"+rs.getInt(5));
				}
				con.close();
				stmt.close();
			}	catch(Exception e){ System.out.println(e);} 
		}
		
		void buy()
		{
			int choice=0;
			int exit = 0;
			int price=0;

				System.out.println("Enter 1:   To Select the product id's to add the product to cart: ");
				System.out.println("Enter 100: To exit ");
				Scanner sc=new Scanner(System.in);
				choice=sc.nextInt();
				while(choice!=100)
				{	
					System.out.println("Enter product id to buy:");
					int product_id=sc.nextInt();
					if(product_id<10)
					{
						System.out.println("Enter Quantity: ");
						int quantity=sc.nextInt();	
						addtocart.put(product_id,quantity);
				try {
					String sql="select price from products where product_id=?";
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Velocity17september2022");
					PreparedStatement stmt=con.prepareStatement(sql); 
					stmt.setInt(1,product_id);
					ResultSet rs=stmt.executeQuery();
					if(rs.next())
					{
						price=rs.getInt(1);
					}
					
					con.close();
					stmt.close();
				}	catch(Exception e){ System.out.println(e);} 
				int intermid=price*quantity;
				totalammount+=intermid;
				
				
				System.out.println("Total Ammount to be Paid: "+totalammount);
				
				System.out.println("1. Checkout");
				System.out.println("2. Continue");
				
				exit = sc.nextInt();
				
				if(exit==1)
				{
					System.out.println("Total Amount: "+totalammount);
					System.out.println("||Product_id||"+" "+"Quantity||");
					for(Map.Entry m : addtocart.entrySet()){    
					    System.out.println("    "+m.getKey()+"  	       "+m.getValue());    
					   }
					
					break;
				}
				}
				else 
				{
					System.out.println("Incorrect product id");
				}
					
			}
		}
		
		
		void inventoryAdjustment(ProductDetails p, String email)
		{
				Set productid=p.addtocart.keySet();
				Iterator pid=productid.iterator();
			while(pid.hasNext())
			{
				int id=(int) pid.next();
				int quant=p.addtocart.get(id);
				int dbquant=getQuantityfromDB(id);
				dbquant-=quant;
				try {
					String sql="update products set quantity=? where product_id=?;";
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Velocity17september2022");
					PreparedStatement stmt=con.prepareStatement(sql);  
					stmt.setInt(1, dbquant);
					stmt.setInt(2, id);
					stmt.executeUpdate();
					con.close();
					stmt.close();
				}	catch(Exception e){ System.out.println(e);}
				try {
					String sql="insert into purchase_history values(?,?,?);";
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Velocity17september2022");
					PreparedStatement stmt=con.prepareStatement(sql);  
					stmt.setString(1, email);
					stmt.setInt(2, id);
					stmt.setInt(3, quant);
					stmt.executeUpdate();
					con.close();
					stmt.close();
				}	catch(Exception e){ System.out.println(e);}
			}
		}
		
		int getQuantityfromDB(int productid)
		{
			int quantity=0;
			try {
				String sql="select quantity from products where product_id=?";
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/miniproject","root","Velocity17september2022");
				PreparedStatement stmt=con.prepareStatement(sql); 
				stmt.setInt(1, productid);
				ResultSet rs=stmt.executeQuery();
				if(rs.next())
				{
					quantity=rs.getInt(1);
				}
				con.close();
				stmt.close();
			}	catch(Exception e){ System.out.println(e);} 
			return quantity;
		}

	}



