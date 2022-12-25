package miniProjectECommerce$;

	
	import java.util.Map;

	public class Billing {
		void generateInvoice(ProductDetails p,String email)
		{
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("********************************************************");
			System.out.println("************************INVOICE*************************");
			System.out.println("********************************************************");
			System.out.println("Email:  "+email);
			System.out.println("Total Amount:   "+p.totalammount);
			System.out.println("**************************Cart**************************");
			System.out.println("||Product_id||"+" "+"Quantity||");
			for(Map.Entry m : p.addtocart.entrySet()){    
			    System.out.println("    "+m.getKey()+"  	       "+m.getValue());    
			   }
			System.out.println("********************************************************");
		}

	}



