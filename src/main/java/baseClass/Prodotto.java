package baseClass;

public class Prodotto {
	private Integer idProdotto;
	private String nome;
	private Double prezzo;
	private String reparto;
	private Integer quantity;
	
	public Prodotto(Integer idProdotto, String nome, Double prezzo, String reparto, Integer quantity) {
		this.idProdotto = idProdotto;
		this.nome = nome;
		this.prezzo = prezzo;
		this.reparto = reparto;
		this.quantity = quantity;
	}


	public Integer getIdProdotto() {
		return idProdotto;
	}

	public String getNome() {
		return nome;
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
	
	
}
