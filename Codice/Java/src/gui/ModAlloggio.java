package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import controller.Controller;
import model.Contatto;
import model.Indirizzo;


public class ModAlloggio extends ModAttributes{
	
	private static final long serialVersionUID = 1L;
	private static DefaultListModel<String> listAlloggiModel;
	
	public ModAlloggio(Contatto c) {
		super(c);
		setResizable(false);
		
		setTitle("Modifica Alloggi");
		
		listAlloggiModel = new DefaultListModel<String>();

		JList<String> listAlloggi = new JList<String>(listAlloggiModel);
		listAlloggi.setBounds(10, 62, 330, 190);
		contentPane.add(listAlloggi);
		
		loadTable();
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
				Indirizzo i = null;
				try {
					i = c.getIndirizzi().get(listAlloggi.getSelectedIndex());
				} catch (Exception IndexOutOfBoundsException) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Seleziona un Indirizzo");
				}
				try {
					Controller.delAlloggio(c, i);
				} catch (Exception s) {
					// TODO Auto-generated catch block
					
				}
				refreshTable();
				ContactWindow.refreshAlloggiModel();
			}
		});
		btnRimuovi.setBounds(255, 28, 85, 23);
		contentPane.add(btnRimuovi);
		
		JButton btnNewButton = new JButton("Primario");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Indirizzo i = null;
				try {
					i = c.getIndirizzi().get(listAlloggi.getSelectedIndex());
				} catch (Exception IndexOutOfBoundsException) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Seleziona un Indirizzo");
				}
				
				try {
					Controller.upIndirizzoP(c,i);
				} catch (Exception s) {
					// TODO Auto-generated catch block
					
				}
				refreshTable();
				ContactWindow.refreshAlloggiModel();
			}
		});
		btnNewButton.setBounds(10, 28, 85, 23);
		getContentPane().add(btnNewButton);
	}
	
	public void refreshTable() {
		listAlloggiModel.removeAllElements();
		loadTable();
	}

	public void loadTable() {
		for(Indirizzo i:c.getIndirizzi()) {
			String citta, via;
			citta = i.getCitta();
			via = i.getVia();

			if(c.getIndirizzoP()==i.getAddrID()) {
				listAlloggiModel.addElement("★ "+citta+" "+via);
				}
			else {
				listAlloggiModel.addElement(citta+" "+via);
			}
		}

	}

	public static void updateTable() {
		Indirizzo i;
		//prende l'ultimo elemento della lista
		i = c.getIndirizzi().get(c.getIndirizzi().size()-1);
		listAlloggiModel.addElement(i.getCitta()+" "+i.getVia());
			
	}
}
