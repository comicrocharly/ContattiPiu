package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import model.Contatto;
import model.Email;
import model.Gruppo;

public class ModGruppi extends ModAttributes{
	
	private static DefaultListModel<String> listGruppiModel;
	
	public ModGruppi(Contatto c) {
		super(c);
		
		setTitle("Modifica Gruppi");

		listGruppiModel = new DefaultListModel<String>();

		if(c.getGruppi()!=null)
			for(Gruppo g:c.getGruppi()) {
				listGruppiModel.addElement(g.getNomeG());
			}
		
		JList listGruppi = new JList(listGruppiModel);
		listGruppi.setBounds(20, 66, 310, 192);
		super.contentPane.add(listGruppi);
		
		if (listGruppiModel.isEmpty())
			JOptionPane.showMessageDialog(rootPane, "La lista è vuota");
		
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGruppo frame = new NewGruppo(c);
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
	
	public void loadTable() {
		if(c.getEmail()!=null)
			for(Gruppo g:c.getGruppi()) {
				listGruppiModel.addElement(g.getNomeG());
			}
	}
	
	public static void updateTable() {
		Gruppo g = c.getGruppi().get(c.getGruppi().size()-1);
		
		listGruppiModel.addElement(g.getNomeG());
		
	}

}
