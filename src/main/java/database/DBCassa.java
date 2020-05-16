package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBCassa extends DBManager{

    private  ResultSet rs;
    private String sql;
    
	public boolean getisOccupata(Integer idCassa) {
		open();
		try {
			sql = "select isOccupata from CASSA where idCassa= "+ idCassa;
			rs = getConn().createStatement().executeQuery(sql);
			return rs.getBoolean("isOccupata");
		}
		catch(SQLException e ) {
			System.out.println(e);
		}
		finally {
			close();
		}
		return false;
	}
	
	public void liberaCassa(Integer idCassa) {
		try
		{
			 open();
	        PreparedStatement prepared = getConn()
	        		.prepareStatement("update CASSA set isOccupata = false where idCassa = ?");
	        prepared.setInt(1, idCassa);
	     	
	     	prepared.executeUpdate();		
		}
		catch(Exception e)
		{
			System.out.println("Free cassa error! "+e);
		}
		finally {
			close();	
		}
	}
	
	public void occupaCassa(Integer idCassa) {
		try
		{
			 open();
	        PreparedStatement prepared = getConn()
	        		.prepareStatement("update CASSA set isOccupata = true where idCassa = ?");
	        prepared.setInt(1, idCassa);
	     	
	     	prepared.executeUpdate();		
		}
		catch(Exception e)
		{
			System.out.println("Occupa cassa error! "+e);
		}
		finally {
			close();	
		}
	}
	
	
}
