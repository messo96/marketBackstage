package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class DBCassa extends DBManager{

    private  ResultSet rs;
    private String sql;
    private boolean valore;
    
	public boolean getisOccupata(Integer idCassa) {
		open();
		try {
			System.out.println(idCassa);
			sql = "select * from CASSA where idCassa= "+ idCassa;
			rs = getConn().createStatement().executeQuery(sql);
			if(rs.next())
			valore = rs.getBoolean("isOccupata");
			System.out.println(valore);
		}
		catch(SQLException e ) {
			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println(e);
		}
		finally {
			close();
		}
		return valore;
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

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
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

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("Occupa cassa error! "+e);
		}
		finally {
			close();	
		}
	}
	
	
}
