package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

import model.Contatto;
import model.Indirizzo;
import model.Recapito;

public class ModAlloggi extends ModAttributes{
	
	private static DefaultListModel<String> listAlloggiModel;
	
	public ModAlloggi(Contatto c) {
		super(c);
		
		setTitle("Modifica Alloggi");

		listAlloggiModel = new DefaultListModel<String>();

		for(Indirizzo i:c.getIndirizzi()) {
			String citta, via;
			citta = i.getCitta();
			via = i.getVia();
			listAlloggiModel.addElement(citta+" "+via);
		}
		
		JList list = new JList<String>(listAlloggiModel);
		list.setBounds(10, 62, 330, 190);
		super.contentPane.add(list);
		
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewAlloggi frame = new NewAlloggi(c);
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

	public static void updateTable() {
		Indirizzo i;
		//prende l'ultimo elemento della lista
		i = c.getIndirizzi().get(c.getIndirizzi().size()-1);
		listAlloggiModel.addElement(i.getCitta()+" "+i.getVia());
			
	}
}
