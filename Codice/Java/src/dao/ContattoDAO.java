package dao;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Interface ContattoDAO.
 */
public interface ContattoDAO{
	
	/**
	 * Gets the cont ID.
	 *
	 * @return the cont ID
	 */
	public ArrayList<String> getContID();
		
	/**
	 * Gets the nome.
	 *
	 * @param Cont_ID the cont ID
	 * @return the nome
	 */
	public String getNome(int Cont_ID);
	
	/**
	 * Gets the cognome.
	 *
	 * @param Cont_ID the cont ID
	 * @return the cognome
	 */
	public String getCognome(int Cont_ID);
	
	/**
	 * Gets the ind foto.
	 *
	 * @param Cont_ID the cont ID
	 * @return the ind foto
	 */
	public String getIndFoto(int Cont_ID);
	
	/**
	 * Gets the indirizzo P.
	 *
	 * @param Cont_ID the cont ID
	 * @return the indirizzo P
	 */
	public Integer getIndirizzoP(int Cont_ID);
	
}
