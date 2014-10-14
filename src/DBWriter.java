import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class DBWriter {
	// ��������� �����������
	private String host;
	private String db;
	private String dbName;
	private String user;
	private String pass;
	private String conUrl;
	
	//���������� �����������
	Connection con = null;
    Statement stmt = null;
    ResultSet rs = null;
	
	public DBWriter(String host, String db, String dbName, String user, String pass){
		this.host = host;
		this.db = db;
		this.dbName = dbName;
		this.user = user;
		this.pass = pass;
		//"jdbc:sqlserver://localhost\\SQLExpress;databaseName=BigSizeS;user=sa;password=123698745;";
		conUrl = "jdbc:sqlserver://" + host + "\\"+ db + ";databaseName=" + dbName + ";user=" + user + ";password=" + pass + ";";
	}	
	

	
	public String CrTable(String nameTable){
		String ans = "������!"; 
		try {
             Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
             con = DriverManager.getConnection(conUrl);
             
             if (con == null) {
                 ans = "��� ���������� � ��!";
                 System.exit(0);
             }             

             stmt = con.createStatement();
             boolean r1 = stmt.execute("create table " + nameTable + "(A float, B float)");
             ans = "OK!";
        }
		catch (Exception e) {
             e.printStackTrace();
             ans = "������ ������ � ��!";
             
		}
		finally {
             if (rs != null)   try { rs.close(); }   catch(Exception e) {}
             if (stmt != null) try { stmt.close(); } catch(Exception e) {}
             if (con != null)  try { con.close(); }  catch(Exception e) {}
		}
				
		return ans;
	}
	
	
	public String Writer(String nameTable, int n)
	{
		String ans = "������!";    
        long start = System.currentTimeMillis();
 
        try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                con = DriverManager.getConnection(conUrl);
                if (con == null) {
                	ans = "��� ���������� � ��!";
                    System.exit(0);
                }             

                stmt = con.createStatement();
                
                int count=1;
                while(count <= n){
                	boolean rr = stmt.execute("INSERT INTO " + nameTable +
                					  " ( A, B ) VALUES (" + rnd() + ", " + rnd() + ");" );
                	int ps = count*100/n;
                    System.out.println("OK = " + ps + "%  ������. = " + QWork(start, count) + "  ����� = " + TmWork(start));
                	count++;
                }

        }
 
        catch (Exception e) {
                e.printStackTrace();
        }
 
        finally {
                if (rs != null)   try { rs.close(); }   catch(Exception e) {}
                if (stmt != null) try { stmt.close(); } catch(Exception e) {}
                if (con != null)  try { con.close(); }  catch(Exception e) {}
        }
        return ans;
	}

	public static String TmWork(long start){
    	long tm = (System.currentTimeMillis() - start)/1000;
    	int h = (int)(tm/3600);
    	int m = (int)((tm-h*3600)/60);
    	int s = (int)(tm-h*3600-m*60);
    	String S = Integer.toString(h) + ":" + Integer.toString(m) + ":" + Integer.toString(s);
    	return S;
	}
	public static String QWork(long start, int n){
    	long tm = (System.currentTimeMillis() - start)/1000;
    	int Qq=0;
    	if(tm!=0){
    		Qq=(int)(n/tm);
    	}
    	String S = Integer.toString(Qq) + " ���/���";
    	return S;
	}
	public static double rnd()
	{
		return Math.random();
	}
}
