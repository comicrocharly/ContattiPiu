package model;

public class Recapito {
	private int recID;
	private int contID;

	private Telefono telefonoIn;
	private Telefono telefonoOut;

	public Recapito(Telefono telefonoIn, Telefono telefonoOut) {
		this.telefonoIn = telefonoIn;
		this.telefonoOut = telefonoOut;
	}
	

	public Recapito(int recID, Telefono telefonoIn, Telefono telefonoOut) {
		this.recID = recID;
		this.telefonoIn = telefonoIn;
		this.telefonoOut = telefonoOut;
	}

	public Telefono getTelefonoIn() {
		return this.telefonoIn;
	}

	public Telefono getTelefonoOut() {
		return this.telefonoOut;
	}

	public int getRecID() {
		return this.recID;
	}

	public int getContID() {
		return contID;
	}

	public void setContID(int contID) {
		this.contID = contID;
	}


}
