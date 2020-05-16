package database;

import java.sql.PreparedStatement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import baseClass.Dipendente;

public class DBLavora extends DBManager{
	private java.util.Date d = new java.util.Date();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private Dipendente dip = new Dipendente();
	private Time startTime;
	private Time time = new Time(d.getTime());

	private Integer idCassa;
	public DBLavora(final Dipendente dip, final Integer idCassa) {
		this.dip = dip;
		this.idCassa = idCassa;
	}
	
	public void iniziaLavoro() {
		
		try {
			 open();
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("insert into LAVORA (data, oraInizio, idCassa, idDipendente) values (?,?,?,?)");
		        prepared.setDate(1, java.sql.Date.valueOf(sdf.format(d)));
		     	prepared.setTime(2, time);
		     	startTime = time;
		     	prepared.setInt(3,idCassa);
		     	prepared.setInt(4, dip.getId());
		     	
		     	prepared.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("\nError while adding new Work " + e);
		}
		finally {
			close();
		}
	}
	
	public void fineLavoro() {
		try {
			 open();
			 time = new Time(d.getTime());
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("update LAVORA set oraFine = ? where data = ? AND oraInizio = ? AND idCassa = ?");

		        prepared.setTime(1, time);
		        prepared.setDate(2, java.sql.Date.valueOf(sdf.format(d)));
		        prepared.setTime(3, startTime);
		        prepared.setInt(4, idCassa);

		     	prepared.executeUpdate();
		}
		catch(Exception e) {
			System.out.println("\nError while finish work " + e);
		}
		finally {
			close();
		}
	}
	
	
}
