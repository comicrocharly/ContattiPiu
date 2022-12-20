package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Telefono.
 */
public class Telefono {

	/** The numero. */
	private String numero;
	
	/** The prefisso. */
	private String prefisso;
	
	/** The tipo. */
	private String tipo;
	
	/**
	 * Instantiates a new telefono.
	 *
	 * @param numero the numero
	 * @param prefisso the prefisso
	 * @param tipo the tipo
	 */
	public Telefono(String numero, String prefisso, String tipo) {
		this.numero = numero;
		this.prefisso = prefisso;
		this.tipo = tipo;
	}

	/**
	 * Gets the numero.
	 *
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}
	
	/**
	 * Sets the numero.
	 *
	 * @param numero the new numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}
	
	/**
	 * Gets the prefisso.
	 *
	 * @return the prefisso
	 */
	public String getPrefisso() {
		return prefisso;
	}
	
	/**
	 * Sets the prefisso.
	 *
	 * @param prefisso the new prefisso
	 */
	public void setPrefisso(String prefisso) {
		this.prefisso = prefisso;
	}
	
	/**
	 * Gets the tipo.
	 *
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Sets the tipo.
	 *
	 * @param tipo the new tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}