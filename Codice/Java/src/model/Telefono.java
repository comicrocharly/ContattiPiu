package model;

public class Telefono {

	private String Numero;
	private String Prefisso;
	private String Tipo;
	
	public Telefono(String numero, String prefisso, String tipo) {
		this.Numero = numero;
		this.Prefisso = prefisso;
		this.Tipo = tipo;
	}

	public String getNumero() {
		return Numero;
	}
	public void setNumero(String numero) {
		Numero = numero;
	}
	public String getPrefisso() {
		return Prefisso;
	}
	public void setPrefisso(String prefisso) {
		Prefisso = prefisso;
	}
	public String getTipo() {
		return Tipo;
	}
	public void setTipo(String tipo) {
		Tipo = tipo;
	}
}