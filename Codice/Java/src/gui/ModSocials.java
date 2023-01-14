package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Contatto;
import model.Email;
import model.MessagingPr;
import javax.swing.JComboBox;
import javax.swing.JFrame;

import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ItemEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class ModSocials.
 */
public class ModSocials extends JFrame{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The combo box. */
	private static JComboBox<String> comboBox;
	
	/** The list socials model. */
	private static DefaultListModel<String> listSocialsModel;
	
	/** The c. */
	protected static Contatto c;
	
	/** The content pane. */
	protected JPanel contentPane;
	
	
	/**
	 * Instantiates a new mod socials.
	 *
	 * @param c the c
	 */
	public ModSocials(Contatto c) {
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
		setBounds(100, 100, 338, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		
		listSocialsModel = new DefaultListModel<>();
		
		setTitle("Modifica Socials");
		
		JList<String> listSocials = new JList<String>(listSocialsModel);
		listSocials.setBounds(9, 63, 310, 192);
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


		listSocials.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					if(listSocials.getSelectedIndex()>-1) 
					{
						
						ViewSocial frame= new ViewSocial(c.getEmail().get(comboBox.getSelectedIndex()).getMessagingPr().get(listSocials.getSelectedIndex()));
						frame.setVisible(true);
					}
				}
			}
		});

		JButton btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Email email = c.getEmail().get(comboBox.getSelectedIndex());
				MessagingPr mp = null;

				if(listSocials.getSelectedIndex()!=-1) {
				mp = email.getMessagingPr().get(listSocials.getSelectedIndex());
				
				try {
					Controller.delSocial(mp, email);;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				refreshTable();
				ContactWindow.refreshSocialModel();
				}
				else {
					JOptionPane.showMessageDialog(null, "Seleziona un profilo social!");
				}
				
			}
		});
		btnRimuovi.setBounds(234, 30, 85, 23);
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
		btnAggiungi.setBounds(145, 30, 85, 23);
		contentPane.add(btnAggiungi);
	}
	

	/**
	 * Sets the c.
	 *
	 * @param c the new c
	 */
	protected void setC(Contatto c) {
		ModSocials.c = c;
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
