package model;

public class Telefono {

	private String numero;
	private String prefisso;
	private String tipo;
	
	public Telefono(String numero, String prefisso, String tipo) {
		this.numero = numero;
		this.prefisso = prefisso;
		this.tipo = tipo;
	}

	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getPrefisso() {
		return prefisso;
	}
	public void setPrefisso(String prefisso) {
		this.prefisso = prefisso;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}