package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;

import javax.swing.JOptionPane;

import baseClass.Prodotto;

public class DBProdotto extends DBManager{
    private  ResultSet rs;
	
	public DBProdotto() {
		
	}
	
	
	public Prodotto getProductFromId(final Integer idProdotto) {
		
		try
		{
			String query = "select * from PRODOTTO where idProdotto= " + idProdotto;
			rs = open().executeQuery(query);
			if(rs.next()) {
				return new Prodotto(rs.getInt("idProdotto"),rs.getString("nome"), rs.getDouble("prezzo"));
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
			String query = "select * from PRODOTTO P, OFFERTA O where P.idProdotto = " + idProdotto + " AND P.id_offerta = O.id_offerta";
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
}