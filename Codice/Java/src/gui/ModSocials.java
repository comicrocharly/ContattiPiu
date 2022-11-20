package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import model.Contatto;
import model.Email;
import model.MessagingPr;

public class ModSocials extends ModAttributes{
	
	private static DefaultListModel<String> listSocialsModel;
	private static Contatto c;
	
	public ModSocials(Contatto c) {
		super(c);
		this.c=c;
		
		listSocialsModel = new DefaultListModel<>();
		
		setTitle("Modifica Socials");


		JList listSocials = new JList(listSocialsModel);
		listSocials.setBounds(20, 66, 310, 192);
		super.contentPane.add(listSocials);
		
		if (listSocialsModel.isEmpty())
			JOptionPane.showMessageDialog(rootPane, "La lista è vuota");
		
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnAggiungi.setBounds(162, 28, 85, 23);
		contentPane.add(btnAggiungi);
		
		JButton btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRimuovi.setBounds(255, 28, 85, 23);
		contentPane.add(btnRimuovi);
	}
	
	public void loadTable() {
		
		if(c.getEmail()!=null)
			for(Email e:c.getEmail()) {
				if(e.getMessagingPr()!=null)
					for(MessagingPr ms:e.getMessagingPr()) {
						listSocialsModel.addElement(ms.getFornitore()+" "+ms.getNickname());
					}
			}
	}
	
	public static void updateTable() {
		MessagingPr mProvider;
		//prende l'ultimo elemento della lista
		mProvider = c.getEmail().get(c.getEmail().size()-1);
			
		listEmailModel.addElement(e.getIndirizzo());
			
	}

}
