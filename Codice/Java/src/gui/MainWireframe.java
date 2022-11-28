package gui;
import java.awt.Desktop;
import gui.NewContact;
import model.Contatto;
import model.Indirizzo;
import model.Recapito;
import model.Telefono;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import javax.swing.JEditorPane;

import java.awt.GridBagLayout;
import java.awt.PopupMenu;

import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.event.HyperlinkEvent;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import controller.Controller;

import javax.swing.JScrollBar;
import javax.swing.JSlider;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.Choice;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class MainWireframe {

	private JFrame frame;
	private static JTable table;
	private static Choice choice;
	//private static Controller controller;


	/**
	 * Launch the application.
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
	 * Create the application.
	 */
	public MainWireframe() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("ContattiPiù");
		frame.setBounds(100, 100, 382, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu file = new JMenu("File");
		menuBar.add(file);

		JMenuItem exit = new JMenuItem("Exit");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenuItem refresh = new JMenuItem("Refresh");
		refresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					updateTable();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		file.add(refresh);
		file.add(exit);

		JMenu edit = new JMenu("Edit");
		menuBar.add(edit);

		JMenuItem aggiungi = new JMenuItem("Aggiungi");
		aggiungi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				NewContact f = new NewContact();

				f.setVisible(true);
			}
		});


		edit.add(aggiungi);

		JMenuItem rimuovi = new JMenuItem("Rimuovi");
		rimuovi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Controller.deleteContact(table.getSelectedRow()+1);
				clearTable();
				try {
					updateTable();
				} catch (Throwable e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
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
		searchBar.setBounds(8, 10, 180, 17);
		frame.getContentPane().add(searchBar);
		//Studiando l'utilizzo di jTable


		choice = new Choice();
		choice.setBounds(281, 10, 81, 15);
		frame.getContentPane().add(choice);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 35, 360, 383);
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
			/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
					false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
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
				System.out.println(table.getSelectedRow());
				if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
				ContactWindow frame = new ContactWindow(Controller.getcList().get(table.getSelectedRow()));
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
		
		btnSearch.setBounds(196, 10, 79, 19);
		frame.getContentPane().add(btnSearch);

		choice.add("Nome");
		choice.add("Telefono");
		choice.add("Email");
		choice.add("Social");
		
	}
	
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
	//This method clear all table row
	private void clearTable() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public static void addToTable(String data[]) {
		//data[] è formattato come: Nome, Cognome, Citta + Via, Prefisso + Numero;
		
		String format[] = {data[0], data[1], data[2], data[3]};
		//
		DefaultTableModel tModel = (DefaultTableModel) table.getModel();
		tModel.addRow(format);

	}
	
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
	

	
	public void updateTable() throws Throwable {
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




