package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

import model.Contatto;
import model.Indirizzo;

public class ModAlloggi extends ModAttributes{

	public ModAlloggi(Contatto c) {
		super(c);
		
		setTitle("Modifica Alloggi");

		DefaultListModel<String> listAlloggiModel = new DefaultListModel<String>();

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
