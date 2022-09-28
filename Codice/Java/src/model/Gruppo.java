package model;

public class Gruppo {
	 private int groupID;
	 private String nomeG;
	 private String descrizione;
	 
	 public Gruppo(String nomeG,String descrizione) {
		 this.nomeG=nomeG;
		 this.descrizione=descrizione;
	 }
	 
	 public Gruppo(int groupID,String nomeG,String descrizione) {
		 this.groupID=groupID;
		 this.nomeG=nomeG;
		 this.descrizione=descrizione;
	 }
	 
	 public int getGroupID(){
		 return this.groupID;
	 }
	 
	 public String getNomeG() {
		 return this.nomeG;
	 }
	 
	 public String getDescrizione() {
		 return this.descrizione;
	 }
}
