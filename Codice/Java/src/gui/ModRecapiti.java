package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Contatto;
import model.Recapito;

// TODO: Auto-generated Javadoc
/**
 * The Class ModRecapiti.
 */
public class ModRecapiti extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The list recapiti model. */
	private static DefaultListModel<String> listRecapitiModel;
	/** The c. */
	protected static Contatto c;
	
	/** The content pane. */
	protected JPanel contentPane;
	
	
	/**
	 * Instantiates a new mod recapiti.
	 *
	 * @param c the c
	 */
	public ModRecapiti(Contatto c) {
		try {
			setC(c);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(contentPane, "Contatto nullo");
			this.setVisible(false);
		}
		
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 364, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setResizable(false);
		
		listRecapitiModel = new DefaultListModel<String>();
		setTitle("Modifica Recapiti");
		
		loadTable();
		
		
		JList<String> list = new JList<String>(listRecapitiModel);
		list.setBounds(20, 66, 310, 192);
		contentPane.add(list);
		
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
				} catch (SQLException sqlE) {
					if(sqlE.getSQLState().compareTo("23000")==0)
					JOptionPane.showMessageDialog(null, "Deve esserci almeno un mobile e un fisso!");
					else sqlE.printStackTrace();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				refreshTable();
				ContactWindow.refreshRecapitoModel();
			}
		});
		btnRimuovi.setBounds(255, 28, 85, 23);
		contentPane.add(btnRimuovi);
		
		
		
	}
	
	/**
	 * Sets the c.
	 *
	 * @param c the new c
	 */
	protected void setC(Contatto c) {
		ModRecapiti.c = c;
	}
	
	
	/**
	 * Refresh table.
	 */
	public void refreshTable() {
		listRecapitiModel.removeAllElements();
		loadTable();
	}
	
	/**
	 * Load table.
	 */
	public void loadTable() {
		for(Recapito r:c.getRecapiti()) {
			String prefisso, numero;
			prefisso = r.getTelefonoIn().getPrefisso();
			numero = r.getTelefonoIn().getNumero();
			listRecapitiModel.addElement(prefisso+" "+numero);
		}
	}
	
	/**
	 * Update table.
	 */
	public static void updateTable() {
		Recapito r;
		//prende l'ultimo elemento della lista
		r = c.getRecapiti().get(c.getRecapiti().size()-1);
			
		listRecapitiModel.addElement(r.getTelefonoIn().getPrefisso()+" "+r.getTelefonoIn().getNumero());
			
	}

}
