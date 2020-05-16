package baseClass;

import java.sql.Time;
import java.util.Date;

public class Turno {

	private java.util.Date data = new java.util.Date();
	private Time oraInizio;
	private Time oraFine;
	private int idDipendente;
	
	
	public Turno(Date data, Time oraInizio, Time oraFine, int idDipendente) {
		this.data = data;
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
		this.idDipendente = idDipendente;
	}

	public java.util.Date getData() {
		return data;
	}

	public Time getOraInizio() {
		return oraInizio;
	}

	public Time getOraFine() {
		return oraFine;
	}

	public int getIdDipendente() {
		return idDipendente;
	}

	


}
