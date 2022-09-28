package model;

public class Recapito {
	private int recID;
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

}
