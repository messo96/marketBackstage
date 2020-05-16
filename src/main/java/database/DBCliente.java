package database;

import java.sql.ResultSet;


public class DBCliente extends DBManager{
    private  ResultSet rs;

	public boolean isClienteEsiste(final String codiceFiscale) {
		try
		{
			String query = "select * from CLIENTE where codiceFiscale= " + codiceFiscale;
			rs = open().executeQuery(query);
			if(rs.next()) {
				return true;
				}
			else
				return false;
				
		}
		catch(Exception e)
		{
			System.out.println("Cliente doesn't exist!"+e);
		}
		finally {
			close();	
		}
		return false;

	}
	
	public void addCliente(final String codiceFiscale) {
		
	}
}
