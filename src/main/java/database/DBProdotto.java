package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
				return new Prodotto(rs.getInt("idProdotto"),rs.getString("nome"), rs.getDouble("prezzo"), rs.getString("reparto"), rs.getInt("quantità"));
				}
			else
				JOptionPane.showMessageDialog(null, "Il prodotto non esiste");
		}
		catch(Exception e)
		{
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
			String query = "select * from PRODOTTO P, OFFERTA O where P.idProdotto = " + idProdotto 
																		+ " AND P.id_offerta = O.id_offerta AND dataFine <= " + sdf.format(d);
			rs = open().executeQuery(query);
			if(rs.next()) {
				return Optional.of(rs.getInt("sconto"));
				}
			else
				Optional.empty();
		}
		catch(Exception e)
		{
			System.out.println("Product offer error! "+e);
		}
		finally {
			close();	
		}
		return Optional.empty();
	}

	public void aggiornaMagazzino(final Integer idProdotto) {
		try
		{
			 open();
	        PreparedStatement prepared = getConn()
	        		.prepareStatement("update PRODOTTO set quantità = quantità - 1 where idProdotto = ?");
	        prepared.setInt(1, idProdotto);
	     	
	     	prepared.executeUpdate();		
		}
		catch(Exception e)
		{
			System.out.println("Product offer error! "+e);
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
				list.add(new Prodotto(rs.getInt("idProdotto"), rs.getString("nome"), rs.getDouble("prezzo"), rs.getString("reparto"), rs.getInt("quantità")));
				}
			
		}
		catch(Exception e)
		{
			System.out.println("download products error! "+e);
		}
		finally {
			close();	
		}
		return list;
	}
}