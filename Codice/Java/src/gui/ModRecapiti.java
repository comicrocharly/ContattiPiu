package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import controller.Controller;
import model.Contatto;
import model.Recapito;

public class ModRecapiti extends ModAttributes {
	
	private static final long serialVersionUID = 1L;
	private static DefaultListModel<String> listRecapitiModel;
	
	
	public ModRecapiti(Contatto c) {
		super(c);
		setResizable(false);
		
		listRecapitiModel = new DefaultListModel<String>();
		setTitle("Modifica Recapiti");
		
		loadTable();
		
		
		JList<String> list = new JList<String>(listRecapitiModel);
		list.setBounds(20, 66, 310, 192);
		super.contentPane.add(list);
		
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewRecapito frame = new NewRecapito(c);
				frame.setVisible(true);
			}
		});
		btnAggiungi.setBounds(162, 28, 85, 23);
		contentPane.add(btnAggiungi);
		
		JButton btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Recapito re = null;
				try {
					re = c.getRecapiti().get(list.getSelectedIndex());
				} catch (Exception IndexOutOfBoundsException) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Seleziona un Recapito");
				}
				try {
					Controller.delRecapito(c, re);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				refreshTable();
				ContactWindow.refreshRecapitoModel();
			}
		});
		btnRimuovi.setBounds(255, 28, 85, 23);
		contentPane.add(btnRimuovi);
		
		
		
	}
	
	public void refreshTable() {
		listRecapitiModel.removeAllElements();
		loadTable();
	}
	
	public void loadTable() {
		for(Recapito r:c.getRecapiti()) {
			String prefisso, numero;
			prefisso = r.getTelefonoIn().getPrefisso();
			numero = r.getTelefonoIn().getNumero();
			listRecapitiModel.addElement(prefisso+" "+numero);
		}
	}
	
	public static void updateTable() {
		Recapito r;
		//prende l'ultimo elemento della lista
		r = c.getRecapiti().get(c.getRecapiti().size()-1);
			
		listRecapitiModel.addElement(r.getTelefonoIn().getPrefisso()+" "+r.getTelefonoIn().getNumero());
			
	}

}
