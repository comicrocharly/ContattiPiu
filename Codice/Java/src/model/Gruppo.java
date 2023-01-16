package model;

// TODO: Auto-generated Javadoc
/**
 * The Class Gruppo.
 */
public class Gruppo {
	 
 	/** The group ID. */
 	private int groupID;
	 
 	/** The nome G. */
 	private String nomeG;
	 
 	/** The descrizione. */
 	private String descrizione;


	/**
 	 * Instantiates a new gruppo.
 	 *
 	 * @param nomeG the nome G
 	 * @param descrizione the descrizione
 	 */
 	public Gruppo(String nomeG,String descrizione) {
		 this.nomeG=nomeG;
		 this.descrizione=descrizione;
	 }
	 
	 /**
 	 * Instantiates a new gruppo.
 	 *
 	 * @param groupID the group ID
 	 * @param nomeG the nome G
 	 * @param descrizione the descrizione
 	 */
 	public Gruppo(int groupID,String nomeG,String descrizione) {
		 this.groupID=groupID;
		 this.nomeG=nomeG;
		 this.descrizione=descrizione;
	 }
	 
	 /**
 	 * Gets the group ID.
 	 *
 	 * @return the group ID
 	 */
 	public int getGroupID(){
		 return this.groupID;
	 }
	 
	 /**
 	 * Gets the nome G.
 	 *
 	 * @return the nome G
 	 */
 	public String getNomeG() {
		 return this.nomeG;
	 }
	 
	 /**
 	 * Gets the descrizione.
 	 *
 	 * @return the descrizione
 	 */
 	public String getDescrizione() {
		 return this.descrizione;
	 }
 	
 	 /**
 	  * Sets the group ID.
 	  *
 	  * @param groupID the new group ID
 	  */
 	 public void setGroupID(int groupID) {
 		this.groupID = groupID;
 	}

 	/**
	  * Sets the nome G.
	  *
	  * @param nomeG the new nome G
	  */
	 public void setNomeG(String nomeG) {
 		this.nomeG = nomeG;
 	}

 	/**
	  * Sets the descrizione.
	  *
	  * @param descrizione the new descrizione
	  */
	 public void setDescrizione(String descrizione) {
 		this.descrizione = descrizione;
 	}
}
