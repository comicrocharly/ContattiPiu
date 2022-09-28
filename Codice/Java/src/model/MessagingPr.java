package model;

public class MessagingPr {
    private int prID;
    private String nickname;
    private String fraseBenvenuto;
    private String fornitore;
    
    public MessagingPr(String nickname,String fraseBenvenuto,String fornitore) {
    	this.nickname=nickname;
    	this.fraseBenvenuto=fraseBenvenuto;
    	this.fornitore=fornitore;
    }
    
    public MessagingPr(int prID,String nickname,String fraseBenvenuto,String fornitore) {
    	this.prID=prID;
    	this.nickname=nickname;
    	this.fraseBenvenuto=fraseBenvenuto;
    	this.fornitore=fornitore;
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
    
}
