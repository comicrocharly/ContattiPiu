package dao;

import java.util.ArrayList;

public interface ContattoDAO{
	
	public ArrayList<String> getContID();
		
	public String getNome(int Cont_ID);
	
	public String getCognome(int Cont_ID);
	
	public String getIndFoto(int Cont_ID);
	
	public Integer getIndirizzoP(int Cont_ID);
	
}
