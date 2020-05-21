package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import baseClass.Dipendente;

public class DBDipendente extends DBManager{

    private  ResultSet rs;

	
	public Dipendente getDipendenteFromId(final Integer id) {
		if(!this.existDipendente(id))
			return null;
		
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

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("User doesn't exist!"+e);
		}
		finally {
			close();	
		}
		
		return dip;	

	}

	private boolean existDipendente(Integer id) {
		try
		{
			String query = "select * from DIPENDENTE where idDipendente= " + id;
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
			System.out.println("User doesn't exist!"+e);
		}
		finally {
			close();	
		}
		return false;
	}

	public boolean addNuovoDipendente(final Dipendente dip) {
	    
	     try {
			 open();
		        PreparedStatement prepared = getConn()
		        		.prepareStatement("insert into DIPENDENTE (nome, cognome, codiceFiscale, tipo, telefono) values (?,?,?,?,?)");
		        prepared.setString(1, dip.getNome());
		     	prepared.setString(2, dip.getCognome());
		     	prepared.setString(3,dip.getCodiceFiscale());
		     	prepared.setString(4, dip.getTipo());
		     	prepared.setString(5, dip.getTelefono());
		     	
		     	prepared.executeUpdate();
				JOptionPane.showMessageDialog(null, "Dipendente "+dip.getNome() + " " + dip.getCognome()+ "\n Tipo: " + dip.getTipo()+ " \n\tcreato correttamente");
		     	return true;
		}
		catch(Exception e) {

			JOptionPane.showMessageDialog(null, "Errore\n" + e);
			System.out.println("\nError while adding new Employee " + e);
			return false;
		}
		finally {
			close();
		}
	}
	
	
}
