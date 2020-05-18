package baseClass;

public class Prodotto {
	private Integer idProdotto;
	private String nome;
	private String descrizione;
	private Double prezzo;
	private String reparto;
	private Integer quantity;
	private String PIVA;
	
	public Prodotto(Integer idProdotto, String nome, String descrizione, Double prezzo, String reparto,
			Integer quantity, String pIVA) {
		super();
		this.idProdotto = idProdotto;
		this.nome = nome;
		this.descrizione = descrizione;
		this.prezzo = prezzo;
		this.reparto = reparto;
		this.quantity = quantity;
		PIVA = pIVA;
	}

	public Integer getIdProdotto() {
		return idProdotto;
	}

	public String getNome() {
		return nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public Double getPrezzo() {
		return prezzo;
	}

	public String getReparto() {
		return reparto;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public String getPIVA() {
		return PIVA;
	}
	
	
}
	