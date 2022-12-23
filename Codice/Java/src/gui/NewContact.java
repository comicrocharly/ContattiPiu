package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.Controller;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class NewContact.
 */
public class NewContact extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The content pane. */
	private JPanel contentPane;
	
	/** The text field nome. */
	private JTextField textFieldNome;
	
	/** The text field cognome. */
	private JTextField textFieldCognome;
	
	/** The lbl new label T fisso. */
	private JLabel lblNewLabelTFisso;
	
	/** The text field numero fisso. */
	private JTextField textFieldNumeroFisso;
	
	/** The text field nazione. */
	private JTextField textFieldNazione;
	
	/** The text field citta. */
	private JTextField textFieldCitta;
	
	/** The text field via. */
	private JTextField textFieldVia;
	
	/** The text field cap. */
	private JTextField textFieldCap;
	
	/** The text field prefisso fisso. */
	private JTextField textFieldPrefissoFisso;
	
	/** The lbl new label T mobile. */
	private JLabel lblNewLabelTMobile;
	
	/** The text field prefisso mobile. */
	private JTextField textFieldPrefissoMobile;
	
	/** The text field numero mobile. */
	private JTextField textFieldNumeroMobile;

	
	/**
	 * Instantiates a new new contact.
	 */
	public NewContact() {
		
		setResizable(false);
		setTitle("Nuovo Contatto");
		setAlwaysOnTop(true);
		//Nasconde il jframe alla chiusura
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 307, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Inserisci");
	
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(106, 268, 86, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabelNome = new JLabel("Nome");
		lblNewLabelNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelNome.setBounds(25, 34, 67, 19);
		contentPane.add(lblNewLabelNome);
		
		JLabel lblNewLabelCognome = new JLabel("Cognome");
		lblNewLabelCognome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelCognome.setBounds(25, 62, 67, 19);
		contentPane.add(lblNewLabelCognome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(133, 37, 148, 17);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldCognome = new JTextField();
		textFieldCognome.setBounds(133, 64, 148, 17);
		contentPane.add(textFieldCognome);
		textFieldCognome.setColumns(10);
		
		lblNewLabelTFisso = new JLabel("T.Fisso");
		lblNewLabelTFisso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelTFisso.setBounds(25, 88, 67, 19);
		contentPane.add(lblNewLabelTFisso);
		
		textFieldNumeroFisso = new JTextField();
		textFieldNumeroFisso.setColumns(10);
		textFieldNumeroFisso.setBounds(174, 89, 107, 19);
		contentPane.add(textFieldNumeroFisso);
		
		JLabel lblNewLabelNazione = new JLabel("Nazione");
		lblNewLabelNazione.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelNazione.setBounds(25, 158, 67, 11);
		contentPane.add(lblNewLabelNazione);
		
		JLabel lblNewLabelCittaCap = new JLabel("Citta e CAP");
		lblNewLabelCittaCap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelCittaCap.setBounds(25, 185, 98, 11);
		contentPane.add(lblNewLabelCittaCap);
		
		JLabel lblNewLabelVia = new JLabel("Via e N.Civico");
		lblNewLabelVia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelVia.setBounds(25, 212, 98, 11);
		contentPane.add(lblNewLabelVia);
		
		textFieldNazione = new JTextField();
		textFieldNazione.setBounds(133, 156, 148, 17);
		contentPane.add(textFieldNazione);
		textFieldNazione.setColumns(10);
		
		textFieldCitta = new JTextField();
		textFieldCitta.setBounds(133, 183, 98, 17);
		contentPane.add(textFieldCitta);
		textFieldCitta.setColumns(10);
		
		textFieldVia = new JTextField();
		textFieldVia.setBounds(133, 210, 148, 17);
		contentPane.add(textFieldVia);
		textFieldVia.setColumns(10);
		
		textFieldCap = new JTextField();
		textFieldCap.setBounds(236, 183, 45, 17);
		contentPane.add(textFieldCap);
		textFieldCap.setColumns(10);
		
		textFieldPrefissoFisso = new JTextField();
		textFieldPrefissoFisso.setBounds(133, 89, 33, 19);
		contentPane.add(textFieldPrefissoFisso);
		textFieldPrefissoFisso.setColumns(10);
		
		lblNewLabelTMobile = new JLabel("T.Mobile");
		lblNewLabelTMobile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelTMobile.setBounds(25, 115, 67, 19);
		contentPane.add(lblNewLabelTMobile);

		textFieldPrefissoMobile = new JTextField();
		textFieldPrefissoMobile.setColumns(10);
		textFieldPrefissoMobile.setBounds(133, 116, 33, 19);
		contentPane.add(textFieldPrefissoMobile);

		textFieldNumeroMobile = new JTextField();
		textFieldNumeroMobile.setColumns(10);
		textFieldNumeroMobile.setBounds(174, 116, 107, 19);
		contentPane.add(textFieldNumeroMobile);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Controllo presenza campi
				if(textFieldNome.getText().equals("")||textFieldCognome.getText().equals("")
						||textFieldPrefissoFisso.getText().equals("")||textFieldNumeroFisso.getText().equals("")
						||textFieldPrefissoMobile.getText().equals("")||textFieldNumeroMobile.getText().equals("")
						||textFieldNazione.getText().equals("")||textFieldCitta.getText().equals("")
						||textFieldVia.getText().equals("")||textFieldCap.getText().equals("")) 
				{
					JOptionPane.showMessageDialog(NewContact.this,"Errore: Compila i campi richisti.");
				}
				else if(!(textFieldPrefissoFisso.getText().matches("[0-9]+") && textFieldNumeroFisso.getText().matches("[0-9]+")
						&& textFieldPrefissoMobile.getText().matches("[0-9]+") && textFieldNumeroMobile.getText().matches("[0-9]+")) ) 
				{
					JOptionPane.showMessageDialog(NewContact.this,"Errore: I telefoni devono contenere solo numeri.");
				}

				else {

					String data[]= {textFieldNome.getText(), textFieldCognome.getText(), 
							textFieldPrefissoFisso.getText(),textFieldNumeroFisso.getText(),
							textFieldPrefissoMobile.getText(),textFieldNumeroMobile.getText(),
							textFieldNazione.getText(), textFieldCitta.getText(), textFieldVia.getText(), textFieldCap.getText()};

					String dataView[] = {data[0],data[1],data[7]+" "+data[8],data[2]+" "+data[3]};

					try {
						Controller.insertContatto(data);
						MainWireframe.addToTable(dataView);
						JOptionPane.showMessageDialog(NewContact.this,"Inserimento Riuscito.");
						setVisible(false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(NewContact.this,"Inserimento Fallito." + "\n" + e1.getMessage());
					}


					
				}
			}


		});
	}

}
