package model;

// TODO: Auto-generated Javadoc
/**
 * The Class MessagingPr.
 */
public class MessagingPr {
    
    /** The pr ID. */
    private int prID;
    
    /** The nickname. */
    private String nickname;
    
    /** The frase benvenuto. */
    private String fraseBenvenuto;
    
    /** The fornitore. */
    private String fornitore;
    
    /**
     * Instantiates a new messaging pr.
     *
     * @param nickname the nickname
     * @param fraseBenvenuto the frase benvenuto
     * @param fornitore the fornitore
     */
    public MessagingPr(String nickname,String fraseBenvenuto,String fornitore) {
    	this.nickname=nickname;
    	this.fraseBenvenuto=fraseBenvenuto;
    	this.fornitore=fornitore;
    }
    
    /**
     * Instantiates a new messaging pr.
     *
     * @param prID the pr ID
     * @param nickname the nickname
     * @param fraseBenvenuto the frase benvenuto
     * @param fornitore the fornitore
     */
    public MessagingPr(int prID,String nickname,String fraseBenvenuto,String fornitore) {
    	this.prID=prID;
    	this.nickname=nickname;
    	this.fraseBenvenuto=fraseBenvenuto;
    	this.fornitore=fornitore;
    }
    
    /**
     * Gets the pr ID.
     *
     * @return the pr ID
     */
    public int getPrID() {
    	return this.prID;
    }
    
    /**
     * Gets the nickname.
     *
     * @return the nickname
     */
    public String getNickname() {
    	return this.nickname;
    }
    
    /**
     * Gets the frase benvenuto.
     *
     * @return the frase benvenuto
     */
    public String getFraseBenvenuto() {
    	return this.fraseBenvenuto;
    }
    
    /**
     * Gets the fornitore.
     *
     * @return the fornitore
     */
    public String getFornitore() {
    	return this.fornitore;
    }
    
}
