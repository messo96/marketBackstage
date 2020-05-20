package baseClass;

import java.util.Date;

public class Cliente {
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private Integer punti;
	private Date dataDiNascita;
	
	public Cliente(String codiceFiscale, String nome, String cognome, Integer punti, Date dataDiNascita) {

		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.punti = punti;
		this.dataDiNascita = dataDiNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public Integer getPunti() {
		return punti;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", cognome=" + cognome + ", \nTotalepunti=" + punti + "]";
	}
	
	
}
