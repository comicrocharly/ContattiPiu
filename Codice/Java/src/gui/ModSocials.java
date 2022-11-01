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

	public ModSocials(Contatto c) {
		super(c);
		
		setTitle("Modifica Socials");

		DefaultListModel<String> listSocialsModel = new DefaultListModel<String>();
		if(c.getEmail()!=null)
			for(Email e:c.getEmail()) {
				if(e.getMessagingPr()!=null)
					for(MessagingPr ms:e.getMessagingPr()) {
						listSocialsModel.addElement(ms.getFornitore()+" "+ms.getNickname());
					}
			}

		JList listSocials = new JList(listSocialsModel);
		listSocials.setBounds(127, 344, 175, 77);
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

}
