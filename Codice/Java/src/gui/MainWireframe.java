package gui;

import java.awt.Desktop;
import gui.NewContact;
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
import javax.swing.JComboBox;
import java.awt.Choice;

public class MainWireframe {

	private JFrame frame;
	private JTable table;

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

		table = new JTable();
		table.setBounds(8, 33, 327, 383);
		frame.getContentPane().add(table);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(345, 33, 17, 383);
		frame.getContentPane().add(scrollBar);
		
		Choice choice = new Choice();
		choice.setBounds(294, 10, 68, 17);
		frame.getContentPane().add(choice);
		
		choice.add("Telefono");
		choice.add("Nome");
		choice.add("Email");
		choice.add("Social");
	}
}
