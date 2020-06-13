package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import baseClass.Dipendente;

public class DBScontrino extends DBManager{
    private  ResultSet rs;
    private java.util.Date d;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdf_normal = new SimpleDateFormat("dd-MM-yyyy");

	private Time time;
	private DateFormat timeFormat = new SimpleDateFormat("HH:mm");
	private static DecimalFormat df = new DecimalFormat("#.##");
	private DBCliente dbcliente = new DBCliente();
	
	
	public void stampaScontrino(final Integer idCassa, Double totale, final String codiceFiscale) {
		Integer punti = dbcliente.getPuntiCliente(codiceFiscale);
		System.out.println(punti);
		punti += (int) (totale/10);
		dbcliente.setPuntiCliente(codiceFiscale,punti);
		if(punti >= 100) {
			int dir = JOptionPane.showConfirmDialog(null, "Ha dei punti validi per " + punti/100 + " € di sconto, vuole usarli?","Punti",JOptionPane.YES_NO_OPTION);
			if(dir == JOptionPane.YES_NO_OPTION){
				totale -= punti/100;
				dbcliente.setPuntiCliente(codiceFiscale,-1);
			}
			
		}
		
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
				JOptionPane.showMessageDialog(null, "Scontrino stampato correttamente\n Totale: " + df.format(totale) + " €");
				
				

		}
		catch(Exception e) {

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			JOptionPane.showMessageDialog(null, "Errore mentre si cercava di stampare lo scontrino\n "+e);
		}
		finally {
			close();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void stampaScontrino(final Integer idCassa, final Double totale) {
		
		
		try {
			 open();
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("INSERT INTO SCONTRINO (idCassa, dataEmissione, orarioEmissione, totale) values (?,?,?,?)");
		        prepared.setInt(1, idCassa);
		        d = new java.util.Date();
		        prepared.setDate(2, java.sql.Date.valueOf(sdf.format(d)));
		        time = new Time(timeFormat.parse(timeFormat.format(d)).getTime());
		        prepared.setTime(3, time);
		        prepared.setDouble(4, totale);
		     	prepared.executeUpdate();
				JOptionPane.showMessageDialog(null, "Scontrino stampato correttamente"
						+ "\nData Emissione: "+sdf_normal.format(d)+ "\nOra Emissione: " +time.getHours()+":"+time.getMinutes() + "\nNumero Cassa: "+idCassa+"\nTotale: " + df.format(totale) + " €");
		}
		catch(Exception e) {

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("\nError while print scontrino " + e);
		}
		finally {
			close();
		}
	}
	
	public Dipendente ricercaDipDaScontrino(final Date data, final String oraEmissione, final Integer idCassa) {
		try
		{
			Date date = java.sql.Date.valueOf(sdf.format(data)) ;
			Time time = new java.sql.Time(timeFormat.parse(oraEmissione).getTime());
			String query = "select D.* from LAVORA L, DIPENDENTE D where L.idCassa = "
					+ idCassa + " AND \"" + date +"\" = L.data AND \"" + time + "\" BETWEEN L.oraInizio AND L.oraFine " + 
					" AND L.idDipendente = D.idDipendente";
			rs = open().executeQuery(query);
			if(rs.next()) {
				return new Dipendente(rs.getInt("idDipendente"), rs.getString("nome"), rs.getString("cognome"),
														rs.getString("tipo"), rs.getString("codiceFiscale"), rs.getString("telefono"));
				}
			
		}
		catch(Exception e)
		{

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("download products error! "+e);
		}
		finally {
			close();	
		}
		return null;
	}
}
