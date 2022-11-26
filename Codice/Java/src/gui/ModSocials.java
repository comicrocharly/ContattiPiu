package gui;

import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import model.Contatto;
import model.Email;
import model.MessagingPr;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ModSocials extends ModAttributes{
	
	private static JComboBox<String> comboBox;
	private static DefaultListModel<String> listSocialsModel;
	
	
	public ModSocials(Contatto c) {
		super(c);
		
		
		listSocialsModel = new DefaultListModel<>();
		
		setTitle("Modifica Socials");
		
		JList listSocials = new JList(listSocialsModel);
		listSocials.setBounds(20, 66, 310, 192);
		super.contentPane.add(listSocials);
		
		
		JButton btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRimuovi.setBounds(255, 28, 85, 23);
		contentPane.add(btnRimuovi);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(10, 28, 120, 23);
		contentPane.add(comboBox);
		
		
		if(!c.getEmail().isEmpty()) {
			
			for(Email e:c.getEmail()) {
				comboBox.addItem(e.getIndirizzo());
			}
		}
		else {
			
			JOptionPane.showMessageDialog(this, "Non ci sono Emails assegnate!");
			setVisible(false);
			
			
		}
		
		comboBox.setSelectedIndex(0);
		loadTable();
		if (listSocialsModel.isEmpty())
			JOptionPane.showMessageDialog(rootPane, "Non ci sono Provider legati alla email selezionata");
		
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1) {
						clearTable();
						loadTable();
					if(listSocialsModel.isEmpty()) {
						JOptionPane.showMessageDialog(rootPane, "Non ci sono Provider legati alla email selezionata");
					}
				}	
			}
		});
		
		
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Email email : c.getEmail()) {
					if(email.getIndirizzo().equals(comboBox.getSelectedItem().toString())) {
						NewSocial frame = new NewSocial(c, email);
						frame.setVisible(true);
					}
				}
			}
		});
		btnAggiungi.setBounds(162, 28, 85, 23);
		contentPane.add(btnAggiungi);
	}
	
	public void clearTable() {
		listSocialsModel.removeAllElements();
	}

	public void loadTable() {

		String indirizzo = comboBox.getSelectedItem().toString();
		if(c.getEmail()!=null) {
			for(Email e:c.getEmail()) {
				if(e.getMessagingPr()!=null && e.getIndirizzo().equals(indirizzo))
					for(MessagingPr ms:e.getMessagingPr()) {
						listSocialsModel.addElement(ms.getFornitore()+" "+ms.getNickname());	
					}
			}
		}	
	}

	public static void updateTable() {
		MessagingPr mProvider;
		String indirizzo = comboBox.getSelectedItem().toString();

		//prende l'ultimo elemento della lista

		for(Email e:c.getEmail()) {
			if(e.getMessagingPr()!=null && e.getIndirizzo().equals(indirizzo)) {

				mProvider = e.getMessagingPr().get(e.getMessagingPr().size()-1);

				listSocialsModel.addElement(mProvider.getFornitore()+" "+mProvider.getNickname());
			}
		}
	}
}
