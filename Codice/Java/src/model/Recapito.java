package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Recapito.
 */
public class Recapito {
	
	/** The rec ID. */
	private int recID;
	
	/** The cont ID. */
	private int contID;

	/** The telefono in. */
	private Telefono telefonoIn;
	
	/** The telefono out. */
	private Telefono telefonoOut;

	/**
	 * Instantiates a new recapito.
	 *
	 * @param telefonoIn the telefono in
	 * @param telefonoOut the telefono out
	 */
	public Recapito(Telefono telefonoIn, Telefono telefonoOut) {
		this.telefonoIn = telefonoIn;
		this.telefonoOut = telefonoOut;
	}
	

	/**
	 * Instantiates a new recapito.
	 *
	 * @param recID the rec ID
	 * @param telefonoIn the telefono in
	 * @param telefonoOut the telefono out
	 */
	public Recapito(int recID, Telefono telefonoIn, Telefono telefonoOut) {
		this.recID = recID;
		this.telefonoIn = telefonoIn;
		this.telefonoOut = telefonoOut;
	}

	/**
	 * Gets the telefono in.
	 *
	 * @return the telefono in
	 */
	public Telefono getTelefonoIn() {
		return this.telefonoIn;
	}

	/**
	 * Gets the telefono out.
	 *
	 * @return the telefono out
	 */
	public Telefono getTelefonoOut() {
		return this.telefonoOut;
	}

	/**
	 * Gets the rec ID.
	 *
	 * @return the rec ID
	 */
	public int getRecID() {
		return this.recID;
	}

	/**
	 * Gets the cont ID.
	 *
	 * @return the cont ID
	 */
	public int getContID() {
		return contID;
	}

	/**
	 * Sets the cont ID.
	 *
	 * @param contID the new cont ID
	 */
	public void setContID(int contID) {
		this.contID = contID;
	}


}
