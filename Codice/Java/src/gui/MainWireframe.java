/*
 * 
 */
package gui;

import model.Contatto;
import model.Indirizzo;
import model.Recapito;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import controller.Controller;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Choice;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

// TODO: Auto-generated Javadoc
/**
 *  ContattiPiù is a Java and PostgreSQL application designed to store and manage personal contacts.
 *
 * @author Carlo Onofrio, Lino Strina.
 * @version 1.0
 */

public class MainWireframe {

	/** The frame. */
	private JFrame frame;
	
	/** The table. */
	private static JTable table;
	
	/** The choice. */
	private static Choice choice;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWireframe window = new MainWireframe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	/**
	 * Instantiates a new main wireframe.
	 */
	public MainWireframe() {
		initialize();
	}

	/**
	 * Initialize.
	 */
	private void initialize() {
		frame = new JFrame("ContattiPiù");
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWireframe.class.getResource("/img/logo.png")));
		frame.setBounds(100, 100, 480, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);
		
		System.out.println(System.getProperty("user.dir"));

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		file.add(exit);

		JMenu edit = new JMenu("Gestisci");
		menuBar.add(edit);

		JMenuItem aggiungi = new JMenuItem("Nuovo Contatto");
		aggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewContact f = new NewContact();

				f.setVisible(true);
			}
		});


		edit.add(aggiungi);

		JMenuItem rimuovi = new JMenuItem("Rimuovi Contatto");
		rimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRowCount()>0) {
				Controller.deleteContact(table.getSelectedRow());
				clearTable();
				try {
					updateTable();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Seleziona la riga del contatto da rimuovere");
				}
			}
		});
		edit.add(rimuovi);

		JMenu help = new JMenu("Help");
		menuBar.add(help);

		JMenuItem credits = new JMenuItem("Credits");
		credits.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame,("https://github.com/comicrocharly/ContattiPiu.git"));
			}
		});
		help.add(credits);
		frame.getContentPane().setLayout(null);

		JTextArea searchBar = new JTextArea();
		searchBar.setBounds(10, 10, 237, 17);
		frame.getContentPane().add(searchBar);
		
		choice = new Choice();
		choice.setBounds(339, 7, 81, 15);
		frame.getContentPane().add(choice);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 35, 444, 383);
		frame.getContentPane().add(scrollPane);

		//jTable with not editable cells
		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Nome", "Cognome", "Indirizzo", "Telefono"
				}
				) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
			@SuppressWarnings("unused")
			public boolean editCellAt(int row, int column, java.util.EventObject e) {
				return false;
			}
		});

		Controller.caricaRubrica();
		try {
			updateTable();
		} catch (Throwable e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		
		//Avvia la finestra ContactWindow con le caratteristiche del contatto selezionato dalla tabella
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
				ContactWindow frame = null;
				try {
					frame = new ContactWindow(Controller.getcList().get(table.getSelectedRow()),MainWireframe.this);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				frame.setVisible(true);
				}
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(table);
		
		JButton btnSearch = new JButton("Cerca");
		btnSearch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				search(searchBar.getText());
			}
		});
		
		btnSearch.setBounds(257, 11, 79, 19);
		frame.getContentPane().add(btnSearch);
		
		JButton btnRefreshTable = new JButton("");
		btnRefreshTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				clearTable();
				try {
					updateTable();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRefreshTable.setToolTipText("Aggiorna Lista Contatti");
		btnRefreshTable.setIcon(new ImageIcon(MainWireframe.class.getResource("/img/Button-Refresh-icon.png")));
		btnRefreshTable.setBounds(426, 4, 31, 26);
		frame.getContentPane().add(btnRefreshTable);

		choice.add("Nome");
		choice.add("Telefono");
		choice.add("Email");
		choice.add("Social");
		
	}
	
	/**
	 * Search.
	 *
	 * @param text the text
	 */
	private void search(String text) {
		
		clearTable();
		
		if(choice.getSelectedItem().equals("Telefono")) {
			ArrayList<Contatto> cList = Controller.searchContactByTelephone(text);
			for(Contatto c:cList) {
				addToTable(c);
			}		
		}
		else if(choice.getSelectedItem().equals("Nome")) {
			ArrayList<Contatto> cList = Controller.searchContactByName(text);
			for(Contatto c:cList) {
				addToTable(c);
			}
		}
		else if(choice.getSelectedItem().equals("Email")) {
			Contatto c = Controller.searchContactByEmail(text);
			if(c!=null)
				addToTable(c);	
		}
		else if(choice.getSelectedItem().equals("Social")) {
			ArrayList<Contatto> cList = Controller.searchContactByNickname(text);
			for(Contatto c:cList) {
				addToTable(c);
			}
		}
	}
	
	/**
	 * Clear table.
	 */
	//This method clear all table row
	public void clearTable() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
	}

	/**
	 * Gets the table.
	 *
	 * @return the table
	 */
	public JTable getTable() {
		return table;
	}

	/**
	 * Sets the table.
	 *
	 * @param table the new table
	 */
	public void setTable(JTable table) {
		MainWireframe.table = table;
	}

	/**
	 * Adds the to table.
	 *
	 * @param data the data
	 */
	public static void addToTable(String data[]) {
		//data[] è formattato come: Nome, Cognome, Citta + Via, Prefisso + Numero;
		
		String format[] = {data[0], data[1], data[2], data[3]};
		//
		DefaultTableModel tModel = (DefaultTableModel) table.getModel();
		tModel.addRow(format);

	}
	
	/**
	 * Adds the to table.
	 *
	 * @param c the c
	 */
	public static void addToTable(Contatto c) {
		String nome = c.getNome();
		String cognome = c.getCognome();
		String citta = c.getIndirizzi().get(0).getCitta();
		String via = c.getIndirizzi().get(0).getVia();
		String prefisso = c.getRecapiti().get(0).getTelefonoIn().getPrefisso();
		String numero = c.getRecapiti().get(0).getTelefonoIn().getNumero();
		
		String format[]= { nome, cognome, citta+" "+via, prefisso+" "+numero };
		
		DefaultTableModel tModel = (DefaultTableModel) table.getModel();
		tModel.addRow(format);
		
	}
	

	
	/**
	 * Update table.
	 *
	 * @throws Throwable the throwable
	 */
	public  void updateTable() throws Throwable {
		//data[] è formattato come: Nome, Cognome, Citta, Via, PrefissoFisso, NumeroFisso, 

		ArrayList<Contatto> cList = new ArrayList<>();
		Indirizzo i = null;
		
		cList = Controller.getcList();
		
		if(cList!=null) {
			if(!cList.isEmpty()) {
				for(Contatto c:cList) {
					
					
					for(Indirizzo tmp:c.getIndirizzi()) {
						
						if(c.getIndirizzoP() == tmp.getAddrID()) {
							i = tmp;
							break;
						}
						
						
					}
					
					if(i==null)
						throw new Exception("L'indirizzo principale non é presente tra gli alloggi");
					//data[] è formattato come: Nome, Cognome, Citta + Via, PrefissoFisso + NumeroFisso;

					try {
						
						ArrayList<Recapito> rList = new ArrayList<>();
						rList = c.getRecapiti();
						if(!rList.isEmpty()) {
							Recapito r = rList.get(0);
							String prefisso = r.getTelefonoIn().getPrefisso();
							String numero = r.getTelefonoIn().getNumero();

							String data[]= {c.getNome(),c.getCognome(),i.getCitta()+" "+i.getVia(), prefisso + " " + numero };

							addToTable(data);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();

					}
				}
			}
		}
		else
			JOptionPane.showMessageDialog(frame,("La lista dei contatti è vuota"));

	}
}




