package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import baseClass.Fornitore;
import baseClass.Prodotto;

public class DBFornitore extends DBManager{
    private  ResultSet rs;

	
	public List<Fornitore> getAllFornitori(){
		List<Fornitore> list = new ArrayList<>();
		
		try
		{
			String query = "select * from FORNITORE";
			rs = open().executeQuery(query);
			while(rs.next()) {
				list.add(new Fornitore(rs.getString("PIVA"), rs.getString("nomeAzienda"), rs.getString("indirizzo"),rs.getString("città"),
						rs.getString("nazione"), rs.getString("telefono"), rs.getString("fax"), rs.getString("email")
									,rs.getString("sitoWeb")));
				}
			
		}
		catch(Exception e)
		{

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("download fornitore error! "+e);
		}
		finally {
			close();	
		}
		return list;
	}
	
	public void addFornitore(Fornitore f) {
		
		try {
			 open();
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("INSERT INTO FORNITORE (PIVA, nomeAzienda, indirizzo, città, nazione, telefono, fax, sitoWeb, email) values (?,?,?,?,?,?,?,?,?)");
		        prepared.setString(1,f.getP_IVA());
		        prepared.setString(2,f.getNome());
		        prepared.setString(3,f.getIndirizzo());
		        prepared.setString(4,f.getCittà());
		        prepared.setString(5,f.getNazione());
		        prepared.setString(6,f.getTelefono());
		        prepared.setString(7,f.getFax());
		        prepared.setString(8,f.getSitoWeb());
		        prepared.setString(9,f.getEmail());
		        
		     	prepared.executeUpdate();
				JOptionPane.showMessageDialog(null, "Fornitore "+f.getNome()+" inserito correttamente");

		}
		catch(Exception e) {

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("\nError while add Fornitore " + e);
		}
		finally {
			close();
		}
	}
	
	public Fornitore getFornitore(final String PIVA) {

		try
		{
			String query = "select * from FORNITORE";
			rs = open().executeQuery(query);
			if(rs.next()) {
				return new Fornitore(rs.getString("PIVA"), rs.getString("nomeAzienda"), rs.getString("indirizzo"),rs.getString("città"),
						rs.getString("nazione"), rs.getString("telefono"), rs.getString("fax"), rs.getString("email")
									,rs.getString("sitoWeb"));
				}
			
		}
		catch(Exception e)
		{

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("download fornitore error! "+e);
		}
		finally {
			close();	
		}
		return null;
	}
//	
//public void addFornitore(final String PIVA, final String nomeAzienda, final String indirizzo, final String citta, final String nazione, final String  telefono, final String email, final String sitoWeb) {
//		
//		try {
//			 open();
//		        PreparedStatement prepared = getConn()
//		        		.prepareStatement("INSERT INTO FORNITORE (PIVA, nomeAzienda, indirizzo, città, nazione, telefono, email, sitoWeb) values (?,?,?,?,?,?,?,?)");
//		        prepared.setString(1,PIVA);
//		        prepared.setString(2,nomeAzienda);
//		        prepared.setString(3,indirizzo);
//		        prepared.setString(4,citta);
//		        prepared.setString(5,nazione);
//		        prepared.setString(6,telefono);
//		        prepared.setString(7,email);
//		        prepared.setString(8,sitoWeb);
//		        
//		        
//		     	prepared.executeUpdate();
//		}
//		catch(Exception e) {
//
//			JOptionPane.showMessageDialog(null, "Errore\n" + e);
//			System.out.println("\nError while add Fornitore " + e);
//		}
//		finally {
//			close();
//		}
//	}
//
//public void addFornitore(final String PIVA, final String nomeAzienda, final String indirizzo, final String citta, final String nazione, final String email, final String  telefono) {
//	
//	try {
//		 open();
//	        PreparedStatement prepared = getConn()
//	        		.prepareStatement("INSERT INTO FORNITORE (PIVA, nomeAzienda, indirizzo, città, nazione, , email, telefono) values (?,?,?,?,?,?,?)");
//	        prepared.setString(1,PIVA);
//	        prepared.setString(2,nomeAzienda);
//	        prepared.setString(3,indirizzo);
//	        prepared.setString(4,citta);
//	        prepared.setString(5,nazione);
//	        prepared.setString(6,email);
//	        prepared.setString(7,telefono);
//	        
//	     	prepared.executeUpdate();
//	}
//	catch(Exception e) {
//
//		JOptionPane.showMessageDialog(null, "Errore\n" + e);
//		System.out.println("\nError while add Fornitore " + e);
//	}
//	finally {
//		close();
//	}
//}
}
