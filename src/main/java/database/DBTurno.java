package database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import baseClass.Turno;

public class DBTurno extends DBManager{
    private  ResultSet rs;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    
	public List<Turno> allTurnoFromIdDipendente(final int idDipendente){
		List<Turno> list = new ArrayList<>();
		
		try
		{
			String query = "select * from TURNO where idDipendente= " + idDipendente;
			rs = open().executeQuery(query);
			while(rs.next()) {
				list.add(new Turno(rs.getDate("data"), rs.getTime("oraInizio"), rs.getTime("oraFine"), rs.getInt("idDipendente")));
			}
			
		}
		catch(Exception e)
		{
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
			String query = "select * from TURNO";
			rs = open().executeQuery(query);
			while(rs.next()) {
				list.add(new Turno(rs.getDate("data"), rs.getTime("oraInizio"), rs.getTime("oraFine"), rs.getInt("idDipendente")));
			}
		
		}
		catch(Exception e)
		{
			System.out.println("download Turni failed! "+e);
		}
		finally {
			close();	
		}
		return list;
	}
	
	public void InserisciTurno(final Date data, final String oraInizio, final String oraFine,final Integer idDip) {
		try {
			 open();
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("INSERT INTO TURNO (data, oraInizio, oraFine, idDipendente) values (?,?,?,?)");
		        prepared.setDate(1, java.sql.Date.valueOf(sdf.format(data)));
		        prepared.setTime(2, new java.sql.Time(timeFormat.parse(oraInizio).getTime()));
		        prepared.setTime(3, new java.sql.Time(timeFormat.parse(oraFine).getTime()));
		        prepared.setInt(4, idDip);
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
