package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Contatto;
import model.Gruppo;

// TODO: Auto-generated Javadoc
/**
 * The Class ModGruppi.
 */
public class ModGruppi extends JFrame{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The list gruppi model. */
	private static DefaultListModel<String> listGruppiModel;
	
	/** The c. */
	protected static Contatto c;
	
	/** The content pane. */
	protected JPanel contentPane;
	
	JList<String> listGruppi;
	
	/**
	 * Instantiates a new mod gruppi.
	 *
	 * @param c the c
	 */
	public ModGruppi(Contatto c) {
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
		setTitle("Modifica Gruppi");

		listGruppiModel = new DefaultListModel<String>();

		if(c.getGruppi()!=null)
			for(Gruppo g:c.getGruppi()) {
				listGruppiModel.addElement(g.getNomeG());
			}
		
		listGruppi = new JList<String>(listGruppiModel);
		listGruppi.setBounds(20, 66, 310, 192);
		contentPane.add(listGruppi);
		
		listGruppi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					if(listGruppi.getSelectedIndex()>-1) 
					{
						ViewGruppo frame= new ViewGruppo(c.getGruppi().get(listGruppi.getSelectedIndex()));
						frame.setVisible(true);
					}
				}
			}
		});
		if (listGruppiModel.isEmpty())
			JOptionPane.showMessageDialog(rootPane, "La lista è vuota");
		
		JButton btnAggiungi = new JButton("Aggiungi");
		btnAggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewGruppo frame = new NewGruppo(c);
				frame.setVisible(true);
			}
		});
		btnAggiungi.setBounds(69, 28, 85, 23);
		contentPane.add(btnAggiungi);
		
		JButton btnRimuovi = new JButton("Rimuovi");
		btnRimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Gruppo g = null;
				try {
					g = c.getGruppi().get(listGruppi.getSelectedIndex());
				} catch (Exception IndexOutOfBoundsException) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Seleziona un Gruppo");
				}
				try {
					Controller.delAggregazione(g, c);;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, e1.getMessage());
				}
				refreshTable();
				ContactWindow.refreshGruppiModel();

			}
		});
		btnRimuovi.setBounds(255, 28, 85, 23);
		contentPane.add(btnRimuovi);
		
		JButton btnModifica = new JButton("Modifica");
		btnModifica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UpdateGruppo frame = new UpdateGruppo(c, c.getGruppi().get(listGruppi.getSelectedIndex()));
					frame.setVisible(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Seleziona un Gruppo");
				}
			}
		});
		btnModifica.setBounds(162, 28, 85, 23);
		contentPane.add(btnModifica);
	}
	

	/**
	 * Sets the c.
	 *
	 * @param c the new c
	 */
	protected void setC(Contatto c) {
		ModGruppi.c = c;
	}
	
	/**
	 * Refresh table.
	 */
	public static void refreshTable() {
		listGruppiModel.removeAllElements();
		loadTable();
	}
	
	/**
	 * Load table.
	 */
	public static void loadTable() {
		if(c.getGruppi()!=null)
			for(Gruppo g:c.getGruppi()) {
				listGruppiModel.addElement(g.getNomeG());
			}
	}
}
