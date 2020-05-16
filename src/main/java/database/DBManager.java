package database;

import java.sql.*;

public class DBManager {

		private Connection conn;
	    private Statement stmt;
	    
	    public DBManager() {
	    	try {
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		open();
			} 
			catch (ClassNotFoundException e) {
				e.getStackTrace();
			}
	    	
	    }
		
		public Statement open() {
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/MarketBackstage","root", "password");
				stmt = conn.createStatement();
				System.out.print("Database is connected !");
				return stmt;
			} catch (SQLException e) {
				System.out.println("Error while connect with database"+e);
			}
			return null;
		}
		
		public void close() {
			try {
				conn.close();
				System.out.println("Connection closed safely");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		public Connection getConn() {
			return this.conn;
		}
		

}
