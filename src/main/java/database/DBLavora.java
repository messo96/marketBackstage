package database;

import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.JOptionPane;

import baseClass.Dipendente;

public class DBLavora extends DBManager{
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
	private Calendar now = Calendar.getInstance(Locale.ITALY);
	private String startTime;
	private Dipendente dip = new Dipendente();

	private Integer idCassa;
	public DBLavora(final Dipendente dip, final Integer idCassa) {
		this.dip = dip;
		this.idCassa = idCassa;
	}
	
	public void iniziaLavoro() {
		
		try {
			 startTime = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);
			 System.out.println(startTime);
			 System.out.println(sdf.format(sdf.parse(String.valueOf(now.get(Calendar.YEAR) +"-"+ 
						Integer.valueOf(now.get(Calendar.MONTH)+1) +"-"+now.get(Calendar.DAY_OF_MONTH)))));
			 open();
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("insert into LAVORA (data, oraInizio, idCassa, idDipendente) values (?,?,?,?)");
		        prepared.setDate(1, java.sql.Date.valueOf(sdf.format(sdf.parse(String.valueOf(now.get(Calendar.YEAR) +"-"+ 
		        		Integer.valueOf(now.get(Calendar.MONTH)+1)  +"-"+now.get(Calendar.DAY_OF_MONTH) ) ))));
		     	prepared.setTime(2,  new java.sql.Time(timeFormat.parse(startTime).getTime()));
		     	prepared.setInt(3,idCassa);
		     	prepared.setInt(4, dip.getId());
		     	
		     	prepared.executeUpdate();
		}
		catch(Exception e) {

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("\nError while adding new Work " + e);
		}
		finally {
			close();
		}
	}
	
	public void fineLavoro() {
		try {
			 open();
			now = Calendar.getInstance();
			String time = now.get(Calendar.HOUR_OF_DAY) + ":" + now.get(Calendar.MINUTE);
			
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("update LAVORA set oraFine = ? where data = ? AND oraInizio = ? AND idCassa = ?");

		        prepared.setTime(1,  new java.sql.Time(timeFormat.parse(time).getTime()));
		        prepared.setDate(2, java.sql.Date.valueOf(sdf.format(sdf.parse(String.valueOf(now.get(Calendar.YEAR) +"-"+ 
		        		Integer.valueOf(now.get(Calendar.MONTH)+1)  +"-"+now.get(Calendar.DAY_OF_MONTH) ) ))));
		        prepared.setTime(3, new java.sql.Time(timeFormat.parse(startTime).getTime()));
		        prepared.setInt(4, idCassa);

		     	prepared.executeUpdate();
		}
		catch(Exception e) {

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("\nError while finish work " + e);
		}
		finally {
			close();
		}
	}
	
	
}
