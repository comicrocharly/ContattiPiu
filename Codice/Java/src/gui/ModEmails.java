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

// TODO: Auto-generated Javadoc
/**
 * The Class ModEmails.
 */
public class ModEmails extends ModAttributes{ 
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The list email model. */
	private static DefaultListModel<String> listEmailModel;
	
	
	/**
	 * Instantiates a new mod emails.
	 *
	 * @param c the c
	 */
	public ModEmails(Contatto c) {
		super(c);
		setResizable(false);
		
		listEmailModel = new DefaultListModel<String>();
		
		setTitle("Modifica Emails");

		loadTable();

		JList<String> listEmails = new JList<String>(listEmailModel);
		listEmails.setBounds(20, 66, 310, 192);
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
				Email email = null;
				try {
					email = c.getEmail().get(listEmails.getSelectedIndex());
				} catch (Exception IndexOutOfBoundsException) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Seleziona una Email");
				}
				try {
					Controller.delEmail(email,c);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				refreshTable();
				ContactWindow.refreshEmailModel();
				
			}
		});
		btnRimuovi.setBounds(255, 28, 85, 23);
		contentPane.add(btnRimuovi);
	}
	
	/**
	 * Refresh table.
	 */
	public static void refreshTable() {
		listEmailModel.removeAllElements();
		loadTable();
	}
	
	/**
	 * Load table.
	 */
	public static void loadTable() {
		
		if(c.getEmail()!=null)
			for(Email e:c.getEmail()) {
				listEmailModel.addElement(e.getIndirizzo());
			}
	}
	
	/**
	 * Update table.
	 */
	public static void updateTable() {
		Email e;
		//prende l'ultimo elemento della lista
		e = c.getEmail().get(c.getEmail().size()-1);
			
		listEmailModel.addElement(e.getIndirizzo());
			
	}
	

}

