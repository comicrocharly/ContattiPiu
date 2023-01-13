package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 364, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		setResizable(false);
		
		listRecapitiModel = new DefaultListModel<String>();
		setTitle("Modifica Recapiti");
		
		loadTable();
		
		
		JList<String> listRecapiti = new JList<String>(listRecapitiModel);
		listRecapiti.setBounds(20, 66, 310, 192);
		contentPane.add(listRecapiti);
		
		listRecapiti.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					if(listRecapiti.getSelectedIndex()>-1) 
					{
						ViewRecapito frame= new ViewRecapito(c.getRecapiti().get(listRecapiti.getSelectedIndex()));
						frame.setVisible(true);
					}
				}
			}
		});
		
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewRecapito frame = new NewRecapito(c);
				frame.setVisible(true);
			}
		});
		btnAggiungi.setBounds(70, 28, 85, 23);
		contentPane.add(btnAggiungi);
		
		JButton btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				Recapito re = null;
				
				if(listRecapiti.getSelectedIndex()!=-1) {
				
				re = c.getRecapiti().get(listRecapiti.getSelectedIndex());
				
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
				
				else {
					JOptionPane.showMessageDialog(null, "Seleziona un recapito!");
				}
			}
			
		});
		btnRimuovi.setBounds(255, 28, 85, 23);
		contentPane.add(btnRimuovi);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateRecapito frame = new UpdateRecapito(c, c.getRecapiti().get(listRecapiti.getSelectedIndex()));
					frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Seleziona un Recapito");
				}
			}
		});
		btnModifica.setBounds(163, 28, 85, 23);
		contentPane.add(btnModifica);
		
		
		
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
	public static void refreshTable() {
		listRecapitiModel.removeAllElements();
		loadTable();
	}
	
	/**
	 * Load table.
	 */
	public static void loadTable() {
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
