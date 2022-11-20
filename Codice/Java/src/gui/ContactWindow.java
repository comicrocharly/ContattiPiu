package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.*;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollBar;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.SpringLayout;
import java.awt.FlowLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenu;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuKeyEvent;

public class ContactWindow extends JFrame {

	private Contatto c;

	private JPanel contentPane;

	public ContactWindow(Contatto c) {
		setTitle("Vista Contatto");
		setC(c);
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
		
		JMenu mnExit = new JMenu("Exit");
		mnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		menuBar.add(mnExit);
		
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
		lblSocials.setBounds(57, 378, 245, 11);
		contentPane.add(lblSocials);
		
		DefaultListModel<String> listRecapitoModel = new DefaultListModel<String>();
		
		for(Recapito r:c.getRecapiti()) {
			String prefisso, numero;
			prefisso = r.getTelefonoIn().getPrefisso();
			numero = r.getTelefonoIn().getNumero();
			listRecapitoModel.addElement(prefisso+" "+numero);
		}
		
		JList listRecapiti = new JList(listRecapitoModel);
		listRecapiti.setBounds(33, 133, 133, 77);
		contentPane.add(listRecapiti);

		DefaultListModel<String> listAlloggiModel = new DefaultListModel<String>();

		for(Indirizzo i:c.getIndirizzi()) {
			String citta, via;
			citta = i.getCitta();
			via = i.getVia();
			listAlloggiModel.addElement(citta+" "+via);
		}

		JList listAlloggi = new JList(listAlloggiModel);
		listAlloggi.setBounds(169, 133, 133, 77);
		contentPane.add(listAlloggi);
		
		DefaultListModel<String> listGruppiModel = new DefaultListModel<String>();

		if(c.getGruppi()!=null)
			for(Gruppo g:c.getGruppi()) {
				listGruppiModel.addElement(g.getNomeG());
			}

		JList listGruppi = new JList(listGruppiModel);
		listGruppi.setBounds(33, 245, 133, 77);
		contentPane.add(listGruppi);
		
		DefaultListModel<String> listEmailModel = new DefaultListModel<String>();

		if(c.getEmail()!=null)
			for(Email e:c.getEmail()) {
				listEmailModel.addElement(e.getIndirizzo());
			}

		JList listEmails = new JList(listEmailModel);
		listEmails.setBounds(169, 245, 133, 77);
		contentPane.add(listEmails);

		DefaultListModel<String> listSocialsModel = new DefaultListModel<String>();
		if(c.getEmail()!=null)
			for(Email e:c.getEmail()) {
				if(e.getMessagingPr()!=null)
					for(MessagingPr ms:e.getMessagingPr()) {
						listAlloggiModel.addElement(ms.getFornitore()+" "+ms.getNickname());
					}
			}

		JList listSocials = new JList(listSocialsModel);
		listSocials.setBounds(127, 344, 175, 77);
		contentPane.add(listSocials);
		
		
		
	}
	
	public Contatto getC() {
		return c;
	}

	public void setC(Contatto c) {
		this.c = c;
	}
}
