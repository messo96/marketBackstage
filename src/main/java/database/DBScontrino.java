package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javax.swing.JOptionPane;

import baseClass.Dipendente;
import baseClass.Prodotto;

public class DBScontrino extends DBManager{
    private  ResultSet rs;
    private java.util.Date d;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Time time;
	private DateFormat timeFormat = new SimpleDateFormat("HH:mm");

	
	
	public void stampaScontrino(final Integer idCassa, final Double totale, final String codiceFiscale) {
		
		try {
			 open();
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("INSERT INTO SCONTRINO (idCassa, dataEmissione, orarioEmissione, totale, codiceFiscale) values (?,?,?,?,?)");
		        prepared.setInt(1, idCassa);
		        d = new java.util.Date();
		        prepared.setDate(2, java.sql.Date.valueOf(sdf.format(d)));
		        time = new Time(d.getTime());
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
	
	public Dipendente ricercaDipDaScontrino(final String data, final String oraEmissione, final Integer idCassa) {
		try
		{
			String query = "select D.* from SCONTRINO S, LAVORA L, DIPENDENTE D where L.idCassa = "
					+ idCassa + " AND " + java.sql.Date.valueOf(data) +" = L.data AND " +new java.sql.Time(timeFormat.parse(oraEmissione).getTime()) + "BETWEEN L.oraInizio AND L.oraFine " + 
					"AND L.idDipendente = D.idDipendente";
			rs = open().executeQuery(query);
			if(rs.next()) {
				return new Dipendente(rs.getInt("idDipendente"), rs.getString("nome"), rs.getString("cognome"),
														rs.getString("tipo"), rs.getString("codiceFiscale"), rs.getString("telefono"));
				}
			
		}
		catch(Exception e)
		{
			System.out.println("download products error! "+e);
		}
		finally {
			close();	
		}
		return null;
	}
}
