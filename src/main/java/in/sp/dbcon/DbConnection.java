package in.sp.dbcon;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
private static Connection con;
public static Connection getConnection() {
	try {
		if(con==null) {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/expenses_db","YOUR_USERNAME","YOUR_PASSWORD");
	}
	}
	catch(Exception e) {
	e.printStackTrace();	
	}
	return con;
}
}
