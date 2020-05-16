package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import baseClass.Dipendente;

public class DBDipendente extends DBManager{

    private  ResultSet rs;

	
	//cambiare ed ottimizzare query con Relax
	public Dipendente getDipendenteFromId(final Integer id) {
		Dipendente dip = new Dipendente();
		
		try
		{
			String query = "select * from DIPENDENTE where idDipendente= " + id;
			rs = open().executeQuery(query);
			if(rs.next()) {
				dip.setId(id);
				dip.setNome(rs.getString("nome"));
				dip.setCognome(rs.getString("cognome"));
				dip.setCodiceFiscale(rs.getString("codiceFiscale"));
				dip.setTipo(rs.getString("tipo"));
				dip.setTelefono(rs.getString("telefono"));
				}
			else
				return null;
		}
		catch(Exception e)
		{
			System.out.println("User doesn't exist!"+e);
		}
		finally {
			close();	
		}
		
		return dip;	

	}

	public boolean addNuovoDipendente(final String nome, final String cognome, final String codiceFiscale, final String tipo, final String dataDiNascita) {
	    
	     try {
			 open();
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("insert into DIPENDENTE (nome, cognome, codiceFiscale, tipo, dataDiNascita) values (?,?,?,?,?)");
		        prepared.setString(1, nome);
		     	prepared.setString(2, cognome);
		     	prepared.setString(3,codiceFiscale);
		     	prepared.setString(4, dataDiNascita);
		     	
		     	prepared.executeUpdate();
		     	return true;
		}
		catch(Exception e) {
			System.out.println("\nError while adding new Employee " + e);
			return false;
		}
		finally {
			close();
		}
	}
	
	
}
