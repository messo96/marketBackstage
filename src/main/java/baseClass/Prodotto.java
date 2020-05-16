package baseClass;

public class Prodotto {
	private Integer idProdotto;
	private String nome;
	private Double prezzo;
	
	public Prodotto(Integer idProdotto, String nome, Double prezzo) {
		this.idProdotto = idProdotto;
		this.nome = nome;
		this.prezzo = prezzo;
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
	
	

	
}
