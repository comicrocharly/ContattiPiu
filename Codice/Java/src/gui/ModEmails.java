package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import model.Contatto;
import model.Email;

public class ModEmails extends ModAttributes{

	public ModEmails(Contatto c) {
		super(c);
		
		setTitle("Modifica Emails");

		DefaultListModel<String> listEmailModel = new DefaultListModel<String>();
		
		if(c.getEmail()!=null)
			for(Email e:c.getEmail()) {
				listEmailModel.addElement(e.getIndirizzo());
			}

		JList listEmails = new JList(listEmailModel);
		listEmails.setBounds(169, 245, 133, 77);
		contentPane.add(listEmails);
		
		if (listEmailModel.isEmpty())
			JOptionPane.showMessageDialog(rootPane, "La lista è vuota");

		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewEmail frame = new NewEmail(c);
				frame.setVisible(true);
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
