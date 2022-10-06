package gui;

import java.awt.Desktop;
import gui.NewContact;
import model.Contatto;

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

		String[][] rowData = {{"Ciao","Ciao","Ciao","Ciao","0"},
				{"Carlo","Carlo","Carlo","Ciao","1"}};


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

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		//Selezione della riga cliccata e prima colonna
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(table.getValueAt(table.getSelectedRow(),3));
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
		String dataView[] = {data[0],data[1],data[5]+" "+data[6],data[2]+" "+data[3]};
		

		DefaultTableModel tModel = (DefaultTableModel) table.getModel();
		tModel.addRow(dataView);
		Controller.insertDataRow(data);

	}
	//Inserisce i dati inseriti in tabella nel DB
	

	//restituisce un arrayList di matrice a oggetti
	/*public ArrayList<String []> printTable(ArrayList<Contatto> lista) {
		ArrayList<Object[][]> m;
		Contatto c;
		Iterator i = lista.iterator();
		while(i.hasNext()) {
			c = (Contatto) i.next();
			m.add();
		}


		return null;

	}*/

}


