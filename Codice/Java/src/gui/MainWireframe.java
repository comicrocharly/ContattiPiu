package gui;

import java.awt.Desktop;
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

public class MainWireframe {

	private JFrame frame;

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
		frame.setBounds(100, 100, 382, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		
		JMenuItem exit = new JMenuItem("Exit");
		file.add(exit);
		
		JMenu edit = new JMenu("Edit");
		menuBar.add(edit);
		
		JMenuItem modContatti = new JMenuItem("Modifica");
		edit.add(modContatti);
		
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
			}
		});
		searchBar.setText("Cerca");
		searchBar.setBounds(8, 8, 352, 22);
		frame.getContentPane().add(searchBar);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(343, 38, 17, 375);
		frame.getContentPane().add(scrollBar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(8, 38, 327, 375);
		frame.getContentPane().add(scrollPane);
	}
	
}
