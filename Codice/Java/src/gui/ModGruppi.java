package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import controller.Controller;
import model.Contatto;
import model.Gruppo;

public class ModGruppi extends ModAttributes{

	private static final long serialVersionUID = 1L;
	private static DefaultListModel<String> listGruppiModel;
	
	public ModGruppi(Contatto c) {
		super(c);
		
		setTitle("Modifica Gruppi");

		listGruppiModel = new DefaultListModel<String>();

		if(c.getGruppi()!=null)
			for(Gruppo g:c.getGruppi()) {
				listGruppiModel.addElement(g.getNomeG());
			}
		
		JList<String> listGruppi = new JList<String>(listGruppiModel);
		listGruppi.setBounds(20, 66, 310, 192);
		contentPane.add(listGruppi);
		
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
				Gruppo g = null;
				try {
					g = c.getGruppi().get(listGruppi.getSelectedIndex());
				} catch (Exception IndexOutOfBoundsException) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Seleziona un Gruppo");
				}
				try {
					Controller.delAggregazione(g, c);;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				refreshTable();
				ContactWindow.refreshGruppiModel();

			}
		});
		btnRimuovi.setBounds(255, 28, 85, 23);
		contentPane.add(btnRimuovi);
	}
	
	public void refreshTable() {
		listGruppiModel.removeAllElements();
		loadTable();
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
