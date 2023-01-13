package gui;

import java.awt.FileDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.Controller;
import model.*;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.swing.JMenu;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Toolkit;

// TODO: Auto-generated Javadoc
/**
 * The Class ContactWindow.
 */
public class ContactWindow extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The c. */
	private static Contatto c;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The image. */
	private Image image;
	
	/** The default image. */
	private ImageIcon defaultImage;
	
	/** The lbl foto. */
	private JLabel lblFoto;
	
	private JTextField txtFieldNome;
	
	/** The list recapito model. */
	private static DefaultListModel<String> listRecapitoModel;
	
	/** The list alloggi model. */
	private static DefaultListModel<String> listAlloggiModel;
	
	/** The list gruppi model. */
	private static DefaultListModel<String> listGruppiModel;
	
	/** The list email model. */
	private static DefaultListModel<String> listEmailModel;
	
	/** The list socials model. */
	private static DefaultListModel<String> listSocialsModel;
	
	/**
	 * Instantiates a new contact window.
	 *
	 * @param c the c
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public ContactWindow(Contatto c,MainWireframe mainFrame) throws IOException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ContactWindow.class.getResource("/img/logo.png")));
		setTitle("Vista Contatto");
		ContactWindow.c = c;
		initialize(mainFrame);
	}

	/**
	 * Initialize.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void initialize(MainWireframe mainFrame) throws IOException {
		
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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 350, 525);
		
		setResizable(false);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
			}
		});
		
		mnFile.add(mntmExit);


		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);
		
		JMenuItem mntmRecapiti = new JMenuItem("Recapiti");
		mntmRecapiti.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ModRecapiti frameRecapiti = new ModRecapiti(c);
				frameRecapiti.setVisible(true);
			}
		});
		
		mnEdit.add(mntmRecapiti);
		
		JMenuItem mntmAlloggi = new JMenuItem("Alloggi");
		mntmAlloggi.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ModAlloggio frameAlloggi = new ModAlloggio(c);
				frameAlloggi.setVisible(true);
			}
		});
		mnEdit.add(mntmAlloggi);
		
		JMenuItem mntmGruppi = new JMenuItem("Gruppi");
		mntmGruppi.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ModGruppi frameGruppi = new ModGruppi(c);
				frameGruppi.setVisible(true);
			}
		});
		mnEdit.add(mntmGruppi);
		
		JMenuItem mntmEmails = new JMenuItem("Emails");
		mntmEmails.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				ModEmails frameEmails = new ModEmails(c);
				frameEmails.setVisible(true);
			}
		});
		mnEdit.add(mntmEmails);
		
		JMenuItem mntmSocials = new JMenuItem("Socials");
		mntmSocials.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				try {
					ModSocials frameSocials = new ModSocials(c);
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

		defaultImage = new ImageIcon(getClass().getResource("/user.png"));
	
		image = loadFoto();

		if(image!=null) {
			ImageIcon newIcon = new ImageIcon(image);
			lblFoto = new JLabel(newIcon);
		}
		
		else {
			lblFoto = new JLabel(defaultImage);
		}
		
		lblFoto.setSize(100, 100);
		lblFoto.setLocation(27, 8);
		contentPane.add(lblFoto);


		JMenuItem mntmFoto = new JMenuItem("Foto");
		mntmFoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//String indFoto = JOptionPane.showInputDialog("Inserisci nuovo percorso Foto (absolutepath\\photo.extension)");

				FileDialog filePicker = new FileDialog(new JFrame(), "Scegli un file", FileDialog.LOAD);
				filePicker.setDirectory("C:\\");
				filePicker.setFile("*.png");
				filePicker.setVisible(true);

				String path = filePicker.getDirectory()+filePicker.getFile();

				if(filePicker.getFile()!=null) {
					
					Controller.upContattoFoto(c, path);
					c.setIndFoto(path);

					contentPane.remove(lblFoto);
					contentPane.validate();
					contentPane.repaint();

					try {
						image = loadFoto();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(image!=null) {
						ImageIcon newIcon = new ImageIcon(image);
						lblFoto = new JLabel(newIcon);
					}
					else {
						lblFoto = new JLabel(defaultImage);
					}
					lblFoto.setSize(100, 100);
					lblFoto.setLocation(27, 8);
					contentPane.add(lblFoto);
					
				}

				
			}});
		
		mnEdit.add(mntmFoto);
		
		txtFieldNome= new JTextField();
		txtFieldNome.setText(name);
		txtFieldNome.setFont(new Font("Tahoma", Font.BOLD, 12));
		txtFieldNome.setBorder(null);
		txtFieldNome.setEditable(false);
		txtFieldNome.setBounds(169, 31, 123, 18);
		contentPane.add(txtFieldNome);
		
		JLabel lblRecapiti = new JLabel("Recapiti");
		lblRecapiti.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRecapiti.setBounds(27, 130, 64, 11);
		contentPane.add(lblRecapiti);
		
		JLabel lblAlloggi = new JLabel("Alloggi");
		lblAlloggi.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblAlloggi.setBounds(169, 126, 71, 18);
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
		
		JButton btnEditName = new JButton("");
		btnEditName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EditName editName = new EditName(mainFrame,ContactWindow.this,c);
				editName.setVisible(true);
				
			}
		});
		btnEditName.setToolTipText("Modifica Nome");
		btnEditName.setIcon(new ImageIcon(ContactWindow.class.getResource("/img/Pencil-icon (2).png")));
		btnEditName.setBounds(302, 29, 20, 20);
		contentPane.add(btnEditName);
		

	}
	
	/**
	 * Load foto.
	 *
	 * @return the image
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static Image loadFoto() throws IOException {
		BufferedImage buffer = null;
		Image image = null;
		if(c.getIndFoto()!=null) {
			try {
				buffer = ImageIO.read(new File(c.getIndFoto()));
				image = buffer.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Foto non trovata");;
			}

		}
		return image;
	}

	/**
	 * Refresh recapito model.
	 */
	public static void refreshRecapitoModel() {
		listRecapitoModel.removeAllElements();
		loadRecapitoModel();
	}
	
	/**
	 * Refresh alloggi model.
	 */
	public static void refreshAlloggiModel() {
		listAlloggiModel.removeAllElements();
		loadAlloggiModel();
	}
	
	/**
	 * Refresh gruppi model.
	 */
	public static void refreshGruppiModel() {
		listGruppiModel.removeAllElements();
		loadGruppiModel();
	}
	
	/**
	 * Refresh email model.
	 */
	public static void refreshEmailModel() {
		listEmailModel.removeAllElements();
		loadEmailModel();
	}
	
	/**
	 * Refresh social model.
	 */
	public static void refreshSocialModel() {
		listSocialsModel.removeAllElements();
		loadSocialModel();
	}

	/**
	 * Load recapito model.
	 */
	public static void loadRecapitoModel() {
		
		for(Recapito r:c.getRecapiti()) {
			String prefisso, numero;
			prefisso = r.getTelefonoIn().getPrefisso();
			numero = r.getTelefonoIn().getNumero();
			listRecapitoModel.addElement(prefisso+" "+numero);
		}
	}
	
	/**
	 * Load alloggi model.
	 */
	public static void loadAlloggiModel() {
		for(Indirizzo i:c.getIndirizzi()) {
			String citta, via;
			citta = i.getCitta();
			via = i.getVia();

			if(c.getIndirizzoP()==i.getAddrID()) {
				listAlloggiModel.addElement("★ "+citta+" "+via);
			}
			else
				listAlloggiModel.addElement(citta+" "+via);
		}
	}

	/**
	 * Load gruppi model.
	 */
	public static void loadGruppiModel() {
		if(c.getGruppi()!=null)
			for(Gruppo g:c.getGruppi()) {
				listGruppiModel.addElement(g.getNomeG());
			}

	}
	
	/**
	 * Load email model.
	 */
	public static void loadEmailModel() {
		if(c.getEmail()!=null)
			for(Email e:c.getEmail()) {
				listEmailModel.addElement(e.getIndirizzo());
			}
	}
	
	/**
	 * Load social model.
	 */
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


	/**
	 * Gets the c.
	 *
	 * @return the c
	 */
	public Contatto getC() {
		return c;
	}
	
	public void refreshName() {
		this.txtFieldNome.setText(c.getNome()+" "+c.getCognome());
	}
}
