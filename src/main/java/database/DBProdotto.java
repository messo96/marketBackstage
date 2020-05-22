package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import baseClass.Prodotto;

public class DBProdotto extends DBManager{
    private  ResultSet rs;
	private java.util.Date d = new java.util.Date();
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
	
	public DBProdotto() {
		
	}
	
	
	public Prodotto getProductFromId(final Integer idProdotto) {
			
		try
		{
			String query = "select * from PRODOTTO where idProdotto= " + idProdotto;
			rs = open().executeQuery(query);
			if(rs.next()) {
				return new Prodotto(rs.getInt("idProdotto"), rs.getString("nome"), rs.getString("descrizione"), rs.getDouble("prezzo"), rs.getString("reparto"), rs.getInt("quantità"), rs.getString("PIVA"));
				}
			else
				JOptionPane.showMessageDialog(null, "Il prodotto non esiste");
		}
		catch(Exception e)
		{

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("Product doesn't exist!"+e);
		}
		finally {
			close();	
		}
		return null;
	}
	
	public Optional<Integer> getOfferforProduct(final Integer idProdotto) {
		
		try
		{
			String query = "select * from PRODOTTO P, OFFERTA O where P.id_offerta = O.id_offerta AND P.idProdotto=  " 
																+ idProdotto + " AND \" "+ sdf.format(d) + "\" BETWEEN O.dataInizio AND O.dataFine";
			rs = open().executeQuery(query);
			if(rs.next()) {
				return Optional.of(rs.getInt("sconto"));
				}
			else
				Optional.empty();
		}
		catch(Exception e)
		{

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("Product offer error! "+e);
		}
		finally {
			close();	
		}
		return Optional.empty();
	}

	public void aggiornaMagazzino(final Integer idProdotto, final Integer q) {
		try
		{
			 open();
	        PreparedStatement prepared = getConn()
	        		.prepareStatement("update PRODOTTO set quantità = quantità + ? where idProdotto = ?");
	        prepared.setInt(1, q);
	        prepared.setInt(2, idProdotto);
	     	
	     	prepared.executeUpdate();		
		}
		catch(Exception e)
		{

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("Product refresh error! "+e);
		}
		finally {
			close();	
		}
	}
	
	public List<Prodotto> getAllProducts() {
		List<Prodotto> list = new ArrayList<>();
		
		try
		{
			String query = "select * from PRODOTTO";
			rs = open().executeQuery(query);
			while(rs.next()) {
				list.add(new Prodotto(rs.getInt("idProdotto"), rs.getString("nome"), rs.getString("descrizione"), rs.getDouble("prezzo"), rs.getString("reparto"), rs.getInt("quantità"), rs.getString("PIVA")));
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
		return list;
	}
	
	public void addProdotto(String nome, String descrizione, String reparto, Double prezzo, Integer quantity, String PIVA) {
		if(nome.isEmpty() || descrizione.isEmpty() || reparto.isEmpty() || prezzo.isNaN() || quantity <= 0 || PIVA.isEmpty())
			JOptionPane.showMessageDialog(null, "Inserisci tutti i campi");
		else {
		
		try {
			 open();
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("INSERT INTO PRODOTTO (nome, descrizione, reparto, prezzo, quantità, PIVA) values (?,?,?,?,?,?)");
		        prepared.setString(1, nome);
		        prepared.setString(2, descrizione);
		        prepared.setString(3, reparto);
		        prepared.setDouble(4, prezzo);
		        prepared.setInt(5, quantity);
		        prepared.setString(6, PIVA);

		     	prepared.executeUpdate();
		     	JOptionPane.showMessageDialog(null, "Il prodotto "+nome+" è stato inserito correttamente");
		}
		catch(Exception e) {

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("\nError while insert new prodotto or PIVA fornitore is not valid" + e);
		}
		finally {
			close();
		}
		
		}
	}
	
		
		public List<String> getAllReparti(){
			List<String> list = new ArrayList<>();
			
			try
			{
				String query = "select distinct reparto from PRODOTTO";
				rs = open().executeQuery(query);
				while(rs.next()) {
					list.add(rs.getString("reparto"));
				}
			}
			catch(Exception e)
			{

				JOptionPane.showMessageDialog(null, "Errore\n" + e);
				System.out.println("download reparti error! "+e);
			}
			finally {
				close();	
			}
			return list;
		}


		public List<Prodotto> getAllProductsLess(final Integer less) {
			List<Prodotto> list = new ArrayList<>();
			
			try
			{
				String query = "select * from PRODOTTO where quantità <= "+less;
				rs = open().executeQuery(query);
				while(rs.next()) {
					list.add(new Prodotto(rs.getInt("idProdotto"), rs.getString("nome"), rs.getString("descrizione"), rs.getDouble("prezzo"), rs.getString("reparto"), rs.getInt("quantità"), rs.getString("PIVA")));
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
			return list;
		}
}