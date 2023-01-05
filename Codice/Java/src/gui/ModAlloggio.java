package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Contatto;
import model.Indirizzo;


// TODO: Auto-generated Javadoc
/**
 * The Class ModAlloggio.
 */
public class ModAlloggio extends JFrame{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The list alloggi model. */
	private static DefaultListModel<String> listAlloggiModel;
	
	/**
	 * Instantiates a new mod alloggio.
	 *
	 * @param c the c
	 */
	
	/** The c. */
	protected static Contatto c;
	
	/** The content pane. */
	protected JPanel contentPane;
	
	/**
	 * Instantiates a new mod Alloggio.
	 *
	 * @param c the c
	 */
	public ModAlloggio(Contatto c) {
		
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
					if(c.getIndirizzi().size()>1)
					Controller.delAlloggio(c, i);
					
					else {
						JOptionPane.showMessageDialog(null,"Il contatto deve avere almeno un indirizzo!");
					}
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
	
	/**
	 * Sets the c.
	 *
	 * @param c the new c
	 */

	
	protected void setC(Contatto c) {
		ModAlloggio.c = c;
	}
	
	/**
	 * Refresh table.
	 */
	public void refreshTable() {
		listAlloggiModel.removeAllElements();
		loadTable();
	}

	/**
	 * Load table.
	 */
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

	/**
	 * Update table.
	 */
	public static void updateTable() {
		Indirizzo i;
		//prende l'ultimo elemento della lista
		i = c.getIndirizzi().get(c.getIndirizzi().size()-1);
		listAlloggiModel.addElement(i.getCitta()+" "+i.getVia());
			
	}
}
