package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import controller.Controller;
import model.Contatto;
import model.Email;
import model.MessagingPr;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class ModSocials.
 */
public class ModSocials extends ModAttributes{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The combo box. */
	private static JComboBox<String> comboBox;
	
	/** The list socials model. */
	private static DefaultListModel<String> listSocialsModel;
	
	
	/**
	 * Instantiates a new mod socials.
	 *
	 * @param c the c
	 */
	public ModSocials(Contatto c) {
		super(c);
		setResizable(false);
		
		
		listSocialsModel = new DefaultListModel<>();
		
		setTitle("Modifica Socials");
		
		JList<String> listSocials = new JList<String>(listSocialsModel);
		listSocials.setBounds(20, 66, 310, 192);
		contentPane.add(listSocials);
		
	
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


		JButton btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Email email = c.getEmail().get(comboBox.getSelectedIndex());
				MessagingPr mp = null;

				try {
					mp = email.getMessagingPr().get(listSocials.getSelectedIndex());
				} catch (Exception IndexOutOfBoundsException) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Seleziona un MessagingPr");
				}
				try {
					Controller.delSocial(mp, email);;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				refreshTable();
				ContactWindow.refreshSocialModel();

				
			}
		});
		btnRimuovi.setBounds(255, 28, 85, 23);
		contentPane.add(btnRimuovi);

		
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

	/**
	 * Refresh table.
	 */
	public void refreshTable() {
		clearTable();
		loadTable();
	}
	
	/**
	 * Clear table.
	 */
	public void clearTable() {
		listSocialsModel.removeAllElements();
	}

	/**
	 * Load table.
	 */
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

	/**
	 * Update table.
	 */
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
