package baseClass;

public class Fornitore {

	private String P_IVA;
	private String nome;
	private String indirizzo;
	private String città;
	private String nazione;
	private String telefono;
	private String fax;
	private String email;
	private String sitoWeb;
	
	public Fornitore(String p_IVA, String nome, String indirizzo, String città, String nazione, String telefono, String fax,
			String email, String sitoWeb) {
		
		P_IVA = p_IVA;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.città = città;
		this.nazione = nazione;
		this.telefono = telefono;
		this.fax = fax;
		this.email = email;
		this.sitoWeb = sitoWeb;
	}
	
	public String getP_IVA() {
		return P_IVA;
	}
	public String getNome() {
		return nome;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public String getNazione() {
		return nazione;
	}
	public String getTelefono() {
		return telefono;
	}
	public String getFax() {
		return fax;
	}
	public String getEmail() {
		return email;
	}
	public String getSitoWeb() {
		return sitoWeb;
	}

	public String getCittà() {
		return città;
	}
	
	
	
	
}
