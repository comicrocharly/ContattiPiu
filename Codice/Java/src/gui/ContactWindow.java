package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.*;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JMenu;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;


public class ContactWindow extends JFrame {

	private static Contatto c;
	private JPanel contentPane;
	
	private static DefaultListModel<String> listRecapitoModel;
	private static DefaultListModel<String> listAlloggiModel;
	private static DefaultListModel<String> listGruppiModel;
	private static DefaultListModel<String> listEmailModel;
	private static DefaultListModel<String> listSocialsModel;
	
	public ContactWindow(Contatto c) {
		setTitle("Vista Contatto");
		ContactWindow.c = c;
		initialize();
	}

	public void initialize() {
		
		//Estrazione dei dati dal contatto ai fini della presentazione
		String name = (c.getNome()+" "+c.getCognome());

		String indirizzoP = null;
		for(Indirizzo i: c.getIndirizzi()) {
			
			if(i.getAddrID()==c.getIndirizzoP()){
				indirizzoP = (i.getCitta()+" "+i.getVia());
				
				break;
			}
		}
		
		//Rappresentazione
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 350, 525);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmRefresh = new JMenuItem("Refresh");
		mntmRefresh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refreshModels();
			}
		});
		mnFile.add(mntmRefresh);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		mnFile.add(mntmExit);


		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmRecapiti = new JMenuItem("Recapiti");
		mntmRecapiti.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ModAttributes frameRecapiti = new ModRecapiti(c);
				frameRecapiti.setVisible(true);
			}
		});
		
		mnEdit.add(mntmRecapiti);
		
		JMenuItem mntmAlloggi = new JMenuItem("Alloggi");
		mntmAlloggi.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ModAttributes frameAlloggi = new ModAlloggi(c);
				frameAlloggi.setVisible(true);
			}
		});
		mnEdit.add(mntmAlloggi);
		
		JMenuItem mntmGruppi = new JMenuItem("Gruppi");
		mntmGruppi.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ModAttributes frameGruppi = new ModGruppi(c);
				frameGruppi.setVisible(true);
			}
		});
		mnEdit.add(mntmGruppi);
		
		JMenuItem mntmEmails = new JMenuItem("Emails");
		mntmEmails.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ModAttributes frameEmails = new ModEmails(c);
				frameEmails.setVisible(true);
			}
		});
		mnEdit.add(mntmEmails);
		
		JMenuItem mntmSocials = new JMenuItem("Socials");
		mntmSocials.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					ModAttributes frameSocials = new ModSocials(c);
					frameSocials.setVisible(true);
				} catch (Exception s) {
					// TODO Auto-generated catch block
				
				}
			}
		});
		mnEdit.add(mntmSocials);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("C:\\Users\\Carlo\\git\\ContattiPiu\\Codice\\Java\\src\\photos\\user.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		
		JLabel lblFoto = new JLabel(new ImageIcon(img.getScaledInstance(100, 100, DO_NOTHING_ON_CLOSE)));
		
		lblFoto.setBounds(27, 8, 125, 120);
		contentPane.add(lblFoto);

		JTextField txtFieldNome = new JTextField(name);
		txtFieldNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtFieldNome.setBorder(null);
		txtFieldNome.setEditable(false);
		txtFieldNome.setBounds(169, 31, 159, 18);
		contentPane.add(txtFieldNome);
		
		JTextField txtFieldIndirizzoP = new JTextField(indirizzoP);
		txtFieldIndirizzoP.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtFieldIndirizzoP.setBorder(null);
		txtFieldIndirizzoP.setEditable(false);
		txtFieldIndirizzoP.setBounds(169, 51, 159, 18);
		contentPane.add(txtFieldIndirizzoP);
		
		JLabel lblRecapiti = new JLabel("Recapiti");
		lblRecapiti.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRecapiti.setBounds(27, 130, 64, 11);
		contentPane.add(lblRecapiti);
		
		JLabel lblAlloggi = new JLabel("Alloggi");
		lblAlloggi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAlloggi.setBounds(171, 130, 71, 11);
		contentPane.add(lblAlloggi);
		
		JLabel lblGruppi = new JLabel("Gruppi");
		lblGruppi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblGruppi.setBounds(27, 246, 44, 11);
		contentPane.add(lblGruppi);
		
		JLabel lblEmails = new JLabel("Emails");
		lblEmails.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmails.setBounds(171, 246, 71, 11);
		contentPane.add(lblEmails);
		
		JLabel lblSocials = new JLabel("Socials");
		lblSocials.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSocials.setBounds(27, 354, 53, 11);
		contentPane.add(lblSocials);
		
		//Liste con ScrollPane
		
		listRecapitoModel = new DefaultListModel<String>();
		loadRecapitoModel();
		
		JList<String> listRecapiti = new JList<String>(listRecapitoModel);
		JScrollPane scrollPaneRecapiti = new JScrollPane(listRecapiti);
		scrollPaneRecapiti.setBounds(25, 153, 140, 77);
		contentPane.add(scrollPaneRecapiti,listRecapiti);

		listAlloggiModel = new DefaultListModel<String>();
		loadAlloggiModel();
		
		JList<String> listAlloggi = new JList<String>(listAlloggiModel);
		JScrollPane scrollPaneAlloggi = new JScrollPane(listAlloggi);
		scrollPaneAlloggi.setBounds(169, 153, 140, 77);
		contentPane.add(scrollPaneAlloggi,listAlloggi);
		
		listGruppiModel = new DefaultListModel<String>();
		loadGruppiModel();
		
		JList<String> listGruppi = new JList<String>(listGruppiModel);
		JScrollPane scrollPaneGruppi = new JScrollPane(listGruppi);
		scrollPaneGruppi.setBounds(25, 265, 140, 77);
		contentPane.add(scrollPaneGruppi,listGruppi);
		
		listEmailModel = new DefaultListModel<String>();
		loadEmailModel();
		
		JList<String> listEmails = new JList<String>(listEmailModel);
		JScrollPane scrollPaneEmails = new JScrollPane(listEmails);
		scrollPaneEmails.setBounds(169, 265, 140, 77);
		contentPane.add(scrollPaneEmails,listEmails);

		listSocialsModel = new DefaultListModel<String>();
		loadSocialModel();
		
		JList<String> listSocials = new JList<String>(listSocialsModel);
		JScrollPane scrollPaneSocials = new JScrollPane(listSocials);
		scrollPaneSocials.setBounds(25, 373, 140, 77);
		contentPane.add(scrollPaneSocials,listSocials);
		

	}
	
	public static void refreshRecapitoModel() {
		listRecapitoModel.removeAllElements();
		loadRecapitoModel();
	}
	
	public static void refreshAlloggiModel() {
		listAlloggiModel.removeAllElements();
		loadAlloggiModel();
	}
	
	public static void refreshGruppiModel() {
		listGruppiModel.removeAllElements();
		loadGruppiModel();
	}
	
	public static void refreshEmailModel() {
		listRecapitoModel.removeAllElements();
		loadRecapitoModel();
	}
	
	public static void refreshSocialModel() {
		listSocialsModel.removeAllElements();
		loadSocialModel();
	}
	
	public void refreshModels() {
		listAlloggiModel.removeAllElements();
		listEmailModel.removeAllElements();
		listGruppiModel.removeAllElements();
		listRecapitoModel.removeAllElements();
		listSocialsModel.removeAllElements();
		
		loadAlloggiModel();
		loadEmailModel();
		loadGruppiModel();
		loadRecapitoModel();
		loadSocialModel();
	}

	public static void loadRecapitoModel() {
		
		for(Recapito r:c.getRecapiti()) {
			String prefisso, numero;
			prefisso = r.getTelefonoIn().getPrefisso();
			numero = r.getTelefonoIn().getNumero();
			listRecapitoModel.addElement(prefisso+" "+numero);
		}
	}
	
	public static void loadAlloggiModel() {
		for(Indirizzo i:c.getIndirizzi()) {
			String citta, via;
			citta = i.getCitta();
			via = i.getVia();
			listAlloggiModel.addElement(citta+" "+via);
		}

	}
	
	public static void loadGruppiModel() {
		if(c.getGruppi()!=null)
			for(Gruppo g:c.getGruppi()) {
				listGruppiModel.addElement(g.getNomeG());
			}

	}
	
	public static void loadEmailModel() {
		if(c.getEmail()!=null)
			for(Email e:c.getEmail()) {
				listEmailModel.addElement(e.getIndirizzo());
			}

	}
	
	public static void loadSocialModel() {
		
	if(c.getEmail()!=null)
		for(Email e:c.getEmail()) {
			
			if(e.getMessagingPr()!=null) {
				for(MessagingPr ms:e.getMessagingPr()) {
					listSocialsModel.addElement(ms.getFornitore()+" "+ms.getNickname());
				}
			}
		}
	}


	public Contatto getC() {
		return c;
	}
}
