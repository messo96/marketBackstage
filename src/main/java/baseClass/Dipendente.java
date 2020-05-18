package baseClass;

public class Dipendente {

	private int id;
	private String nome;
	private String cognome;
	private String tipo;
	private String codiceFiscale;
	private String telefono;
	
	public Dipendente() {
	}
	
	public Dipendente(final Integer id) {
		
	}
	
	public Dipendente(Integer id, String nome, String cognome, String tipo, String codiceFiscale, String telefono) {
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.tipo = tipo;
		this.codiceFiscale = codiceFiscale;
		this.telefono = telefono;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getTipo() {
		return tipo;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getTelefono() {
		return telefono;
	}
	
	
	
}
