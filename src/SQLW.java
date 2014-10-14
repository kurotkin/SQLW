
public class SQLW {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DBWriter MyW = new DBWriter("localhost", "SQLExpress", "BigSizeS", "sa", "123698745");
		String ans = MyW.CrTable("TestS2");
		System.out.println(ans);
		
		ans = MyW.Writer("TestS2", 10);
		
	}

	
	
	
	
	
}
