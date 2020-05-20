package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import baseClass.Dipendente;
import baseClass.Turno;

public class DBTurno extends DBManager{
    private  ResultSet rs;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
    
	public List<Turno> allTurnoFromIdDipendente(final int idDipendente){
		List<Turno> list = new ArrayList<>();
		
		try
		{
			String query = "select * from TURNO where idDipendente= " + idDipendente + " order by data desc, oraInizio asc";
			rs = open().executeQuery(query);
			while(rs.next()) {
				list.add(new Turno(rs.getDate("data"), rs.getTime("oraInizio"), rs.getTime("oraFine"), rs.getInt("idDipendente")));
			}
			
		}
		catch(Exception e)
		{

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("download Turni from idDipendente " + idDipendente + " failed! "+e);
		}
		finally {
			close();	
		}
		return list;
	}

	
	public List<Turno> allTurni(){
		List<Turno> list = new ArrayList<>();
	
		try
		{
			String query = "select * from TURNO order by data desc, oraInizio asc";
			rs = open().executeQuery(query);
			while(rs.next()) {
				list.add(new Turno(rs.getDate("data"), rs.getTime("oraInizio"), rs.getTime("oraFine"), rs.getInt("idDipendente")));
			}
		
		}
		catch(Exception e)
		{

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("download Turni failed! "+e);
		}
		finally {
			close();	
		}
		return list;
	}
	
	public void InserisciTurno(final String data, final String oraInizio, final String oraFine,final Integer idDip) {
		try {
			 open();
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("INSERT INTO TURNO (data, oraInizio, oraFine, idDipendente) values (?,?,?,?)");
		        prepared.setDate(1, java.sql.Date.valueOf(data));
		        prepared.setTime(2, new java.sql.Time(timeFormat.parse(oraInizio).getTime()));
		        prepared.setTime(3, new java.sql.Time(timeFormat.parse(oraFine).getTime()));
		        prepared.setInt(4, idDip);
		     	prepared.executeUpdate();
				JOptionPane.showMessageDialog(null, "Turno inserito correttamente");

		}
		catch(Exception e) {

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("\nError while insert Turno " + e);
		}
		finally {
			close();
		}
	}
	
	public List<Dipendente> allDipendenti(){
		List<Dipendente> list = new ArrayList<>();
	
		try
		{
			String query = "select * from DIPENDENTE";
			rs = open().executeQuery(query);
			while(rs.next()) {
				list.add(new Dipendente(rs.getInt("idDipendente"), rs.getString("nome"),
												rs.getString("cognome"), rs.getString("tipo"),rs.getString("codiceFiscale"), rs.getString("telefono")));
			}
		
		}
		catch(Exception e)
		{

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("download Turni failed! "+e);
		}
		finally {
			close();	
		}
		return list;
	}
	
	public List<Turno> ricercaTurno(final Date data, final Integer idDipendente ){
		List<Turno> list = new ArrayList<>();
		
		try
		{
			String query = "select * from TURNO where data= " + sdf.format(data) + " AND idDipendente= "+ idDipendente;
			rs = open().executeQuery(query);
			while(rs.next()) {
				list.add(new Turno(rs.getDate("data"), rs.getTime("oraInizio"), rs.getTime("oraFine"), rs.getInt("idDipendente")));
			}
		
		}
		catch(Exception e)
		{

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("download ricerca Turni failed! "+e);
		}
		finally {
			close();	
		}
		return list;
	}
	
	public List<Turno> allTurni(final java.util.Date data){
		List<Turno> list = new ArrayList<>();
	
		try
		{
			String query = "select * from TURNO where data= \"" + java.sql.Date.valueOf(sdf.format(data)) + "\" order by oraInizio";
			rs = open().executeQuery(query);
			while(rs.next()) {
				list.add(new Turno(rs.getDate("data"), rs.getTime("oraInizio"), rs.getTime("oraFine"), rs.getInt("idDipendente")));
			}
		
		}
		catch(Exception e)
		{

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("download Turni with data "+ data + " failed! "+e);
		}
		finally {
			close();	
		}
		return list;
	}
}
