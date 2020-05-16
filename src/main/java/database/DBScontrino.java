package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import baseClass.Prodotto;

public class DBScontrino extends DBManager{
    private  ResultSet rs;
    private java.util.Date d = new java.util.Date();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Time time = new Time(d.getTime());

	
	
	public void stampaScontrino(final Integer idCassa, final Double totale, final String codiceFiscale) {
		
		try {
			 open();
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("INSERT INTO SCONTRINO (idCassa, dataEmissione, orarioEmissione, totale, codiceFiscale) values (?,?,?,?,?)");
		        prepared.setInt(1, idCassa);
		        prepared.setDate(2, java.sql.Date.valueOf(sdf.format(d)));
		        prepared.setTime(3, time);
		        prepared.setDouble(4, totale);
		        prepared.setString(5, codiceFiscale);
		     	prepared.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("\nError while print scontrino " + e);
		}
		finally {
			close();
		}
	}
	
	public void stampaScontrino(final Integer idCassa, final Double totale) {
		
		
		try {
			 open();
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("INSERT INTO SCONTRINO (idCassa, dataEmissione, orarioEmissione, totale) values (?,?,?,?)");
		        prepared.setInt(1, idCassa);
		        prepared.setDate(2, java.sql.Date.valueOf(sdf.format(d)));
		        prepared.setTime(3, time);
		        prepared.setDouble(4, totale);
		     	prepared.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("\nError while print scontrino " + e);
		}
		finally {
			close();
		}
	}
}
