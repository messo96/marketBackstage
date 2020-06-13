package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import baseClass.Cliente;


public class DBCliente extends DBManager{
    private  ResultSet rs;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    

	public boolean isClienteEsiste(final String codiceFiscale) {
		try
		{
			String query = "select * from CLIENTE where codiceFiscale= \"" + codiceFiscale + "\"";
			rs = open().executeQuery(query);
			if(rs.next()) {
				return true;
				}
			else
				return false;
				
		}
		catch(Exception e)
		{

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("Cliente doesn't exist!"+e);
		}
		finally {
			close();	
		}
		return false;

	}
	
	public Cliente getClienteFromCodiceFiscale(final String codiceFiscale) {
		try
		{
			String query = "select * from CLIENTE where codiceFiscale= \"" + codiceFiscale + "\"";
			rs = open().executeQuery(query);
			if(rs.next()) 
				return new Cliente(rs.getString("codiceFiscale"), rs.getString("nome"), rs.getString("cognome"),rs.getInt("totalePunti"), rs.getDate("dataDiNascita"));
				
				
		}
		catch(Exception e)
		{

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("Cliente doesn't exist!"+e);
		}
		finally {
			close();	
		}
		return null;

	}
	
	public void addCliente(Cliente c) {
		try {
			 open();
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("INSERT INTO CLIENTE (codiceFiscale, nome, cognome, totalePunti, dataDiNascita) values (?,?,?,?,?)");
		        prepared.setString(1, c.getCodiceFiscale());
		        prepared.setString(2, c.getNome());
		        prepared.setString(3, c.getCognome());
		        prepared.setInt(4, 0);
		        prepared.setDate(5, java.sql.Date.valueOf(sdf.format(c.getDataDiNascita())));

		     	prepared.executeUpdate();
				JOptionPane.showMessageDialog(null, "Cliente " + c.getNome() + " "+ c.getCognome() + " creato correttamente");

		}
		catch(Exception e) {

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("\nError while insert new Cliente, maybe duplicate codiceFiscale " + e);
		}
		finally {
			close();
		}
	}
	
	
	public List<Cliente> getAllClienti(){
		List<Cliente> list = new ArrayList<>();
		
		try
		{
			String query = "select * from CLIENTE";
			rs = open().executeQuery(query);
			while(rs.next()) {
				list.add(new Cliente(rs.getString("codiceFiscale"), rs.getString("nome"), rs.getString("cognome"), rs.getInt("totalePunti"), rs.getDate("dataDiNascita")));
				}
			
		}
		catch(Exception e)
		{

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("download clienti error! "+e);
		}
		finally {
			close();	
		}
		return list;
	}
	
	public Integer getPuntiCliente(String codiceFiscale) {
		if(!isClienteEsiste(codiceFiscale)) {
			JOptionPane.showMessageDialog(null, "Il codice del cliente è inesistente");
			return 0;
		}
		
		try
		{
			String query = "select * from CLIENTE where codiceFiscale= \"" +codiceFiscale + "\"";
			rs = open().executeQuery(query);
			if(rs.next()) {
				return rs.getInt("totalePunti");
			}
			
		}
		catch(Exception e)
		{

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("download punti clienti error! "+e);
		}
		finally {
			close();	
		}
		return 0;
	}
	
	public void setPuntiCliente(String codiceFiscale, Integer punti) {
		try {
			 open();
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("update CLIENTE set totalePunti = ? where codiceFiscale = ? ");
		        if(punti == -1)
		        	prepared.setInt(1, 0);
		        else
				    prepared.setInt(1, this.getPuntiCliente(codiceFiscale) + punti);

		        prepared.setString(2, codiceFiscale);
		    
		     	prepared.executeUpdate();

		}
		catch(Exception e) {

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("\nError while update totalePunti " + e);
		}
		finally {
			close();
		}
	}

}
