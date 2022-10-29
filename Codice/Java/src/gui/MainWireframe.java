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

public class MainWireframe {

	private JFrame frame;
	private static JTable table;
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
		searchBar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER)
					System.out.println(searchBar.getText());
			}
		});
		searchBar.setBounds(8, 10, 278, 17);
		frame.getContentPane().add(searchBar);
		//Studiando l'utilizzo di jTable


		Choice choice = new Choice();
		choice.setBounds(294, 10, 68, 17);
		frame.getContentPane().add(choice);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 35, 360, 383);
		frame.getContentPane().add(scrollPane);

		//jTable with not editable cells
		table = new JTable();
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
				ContactWindow frame = new ContactWindow(Controller.getcList().get(table.getSelectedRow()));
				frame.setVisible(true);
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane.setViewportView(table);

		choice.add("Telefono");
		choice.add("Nome");
		choice.add("Email");
		choice.add("Social");
		
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public static void addToTable(String data[]) {
		//data[] è formattato come: Nome, Cognome, Citta + Via, PrefissoFisso + NumeroFisso;
		
		String format[] = {data[0], data[1], data[2], data[3]};
		//
		DefaultTableModel tModel = (DefaultTableModel) table.getModel();
		tModel.addRow(format);

	}
	
	//Aggiorna il controller con tutti i dati obbligatori inseriti in NewContact, che li aggiunge ai modelli e al DB
	public static void updateController(String data[]) {
		try {
			Controller.insertDataRow(data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
						Recapito r = rList.get(0);
						String prefisso = r.getTelefonoIn().getPrefisso();
						String numero = r.getTelefonoIn().getNumero();

						String data[]= {c.getNome(),c.getCognome(),i.getCitta()+" "+i.getVia(), prefisso + " " + numero };

						addToTable(data);
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




