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
import model.Email;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class ModEmails.
 */
public class ModEmails extends JFrame{ 
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The list email model. */
	private static DefaultListModel<String> listEmailModel;
	
	/** The c. */
	protected static Contatto c;
	
	/** The content pane. */
	protected JPanel contentPane;
	
	/**
	 * Instantiates a new mod emails.
	 *
	 * @param c the c
	 */
	
	public ModEmails(Contatto c) {
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
		
		listEmailModel = new DefaultListModel<String>();
		
		setTitle("Modifica E-mails");

		loadTable();

		JList<String> listEmails = new JList<String>(listEmailModel);
		listEmails.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					if(listEmails.getSelectedIndex()>-1) 
					{
						ViewEmail frame= new ViewEmail(c.getEmail().get(listEmails.getSelectedIndex()));
						frame.setVisible(true);
					}
				}
			}
		});
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
		btnAggiungi.setBounds(64, 28, 85, 23);
		contentPane.add(btnAggiungi);
		
		JButton btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Email email = null;
				
				if(listEmails.getSelectedIndex()!=-1) {
				
				email = c.getEmail().get(listEmails.getSelectedIndex());
				
				try {
					Controller.delEmail(email,c);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				refreshTable();
				ContactWindow.refreshEmailModel();
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Seleziona una Email!");
				}
				
			}
		});
		btnRimuovi.setBounds(255, 28, 85, 23);
		contentPane.add(btnRimuovi);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Email email = c.getEmail().get(listEmails.getSelectedIndex());
					UpdateEmail frame = new UpdateEmail(c,email);
					frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Seleziona una email");
				}
			}
		});
		btnModifica.setBounds(159, 28, 89, 23);
		contentPane.add(btnModifica);
	}

	/**
	 * Sets the c.
	 *
	 * @param c the new c
	 */
	protected void setC(Contatto c) {
		ModEmails.c = c;
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

