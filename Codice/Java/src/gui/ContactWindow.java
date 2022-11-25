package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.*;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;


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
		setBounds(100, 100, 350, 494);
		
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
				ModAttributes frameSocials = new ModSocials(c);
				frameSocials.setVisible(true);
			}
		});
		mnEdit.add(mntmSocials);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblFoto = new JLabel("New label");
		lblFoto.setBounds(57, 54, 114, 11);
		contentPane.add(lblFoto);

		JLabel lblNome = new JLabel(name);
		lblNome.setBounds(181, 21, 121, 18);
		contentPane.add(lblNome);
		
		JLabel lblIndirizzoP = new JLabel(indirizzoP);
		lblIndirizzoP.setBounds(181, 47, 121, 18);
		contentPane.add(lblIndirizzoP);
		
		JLabel lblRecapiti = new JLabel("Recapiti");
		lblRecapiti.setBounds(81, 110, 85, 11);
		contentPane.add(lblRecapiti);
		
		JLabel lblAlloggi = new JLabel("Alloggi");
		lblAlloggi.setBounds(219, 110, 57, 11);
		contentPane.add(lblAlloggi);
		
		JLabel lblGruppi = new JLabel("Gruppi");
		lblGruppi.setBounds(81, 226, 77, 11);
		contentPane.add(lblGruppi);
		
		JLabel lblEmails = new JLabel("Emails");
		lblEmails.setBounds(219, 226, 109, 11);
		contentPane.add(lblEmails);
		
		JLabel lblSocials = new JLabel("Socials");
		lblSocials.setBounds(57, 378, 64, 11);
		contentPane.add(lblSocials);
		
		//Liste con ScrollPane
		
		listRecapitoModel = new DefaultListModel<String>();
		loadRecapitoModel();
		
		JList<String> listRecapiti = new JList<String>(listRecapitoModel);
		JScrollPane scrollPaneRecapiti = new JScrollPane(listRecapiti);
		scrollPaneRecapiti.setBounds(33, 133, 133, 77);
		contentPane.add(scrollPaneRecapiti,listRecapiti);

		listAlloggiModel = new DefaultListModel<String>();
		loadAlloggiModel();
		
		JList<String> listAlloggi = new JList<String>(listAlloggiModel);
		JScrollPane scrollPaneAlloggi = new JScrollPane(listAlloggi);
		scrollPaneAlloggi.setBounds(169, 133, 133, 77);
		contentPane.add(scrollPaneAlloggi,listAlloggi);
		
		listGruppiModel = new DefaultListModel<String>();
		loadGruppiModel();
		
		JList<String> listGruppi = new JList<String>(listGruppiModel);
		JScrollPane scrollPaneGruppi = new JScrollPane(listGruppi);
		scrollPaneGruppi.setBounds(33, 245, 133, 77);
		contentPane.add(scrollPaneGruppi,listGruppi);
		
		listEmailModel = new DefaultListModel<String>();
		loadEmailModel();
		
		JList<String> listEmails = new JList<String>(listEmailModel);
		JScrollPane scrollPaneEmails = new JScrollPane(listEmails);
		scrollPaneEmails.setBounds(169, 245, 133, 77);
		contentPane.add(scrollPaneEmails,listEmails);

		listSocialsModel = new DefaultListModel<String>();
		loadSocialModel();
		
		JList<String> listSocials = new JList<String>(listSocialsModel);
		JScrollPane scrollPaneSocials = new JScrollPane(listSocials);
		scrollPaneSocials.setBounds(131, 344, 171, 77);
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
