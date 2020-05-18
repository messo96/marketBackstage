package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DBCliente extends DBManager{
    private  ResultSet rs;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    

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
	
	public void addCliente(final String codiceFiscale, final String nome, final String cognome, final Date dataDiNascita) {
		try {
			 open();
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("INSERT INTO CLIENTE (codiceFiscale, nome, cognome, totalePunti, dataDiNascita) values (?,?,?,?,?)");
		        prepared.setString(1, codiceFiscale);
		        prepared.setString(2, nome);
		        prepared.setString(3, cognome);
		        prepared.setInt(4, 0);
		        prepared.setDate(5, java.sql.Date.valueOf(sdf.format(dataDiNascita)));

		     	prepared.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("\nError while insert new Cliente, maybe duplicate codiceFiscale " + e);
		}
		finally {
			close();
		}
	}
}
