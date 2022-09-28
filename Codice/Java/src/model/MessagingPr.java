package model;

public class MessagingPr {
    private int prID;
    private String nickname;
    private String fraseBenvenuto;
    private String fornitore;
    private Email email;
    
    public MessagingPr(int prID,String nickname,String fraseBenvenuto,String fornitore,Email email) {
    	this.prID=prID;
    	this.nickname=nickname;
    	this.fraseBenvenuto=fraseBenvenuto;
    	this.fornitore=fornitore;
    	this.email=email;
    }
    
    public int getPrID() {
    	return this.prID;
    }
    
    public String getNickname() {
    	return this.nickname;
    }
    
    public String getFraseBenvenuto() {
    	return this.fraseBenvenuto;
    }
    
    public String getFornitore() {
    	return this.fornitore;
    }
    
    public Email getEmail() {
    	return this.email;
    }
}
