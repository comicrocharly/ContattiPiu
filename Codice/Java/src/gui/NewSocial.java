package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Contatto;
import model.Email;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class NewSocial.
 */
public class NewSocial extends JFrame{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The text field nickname. */
	protected JTextField textFieldNickname;
	
	/** The text field provider. */
	protected JTextField textFieldProvider;
	
	/** The text field W frase. */
	protected JTextField textFieldWFrase;
	
	/** The btn run. */
	protected JButton btnRun;
	
	/** The c. */
	protected Contatto c;
	
	/** The email. */
	protected Email email;
	/**
	 * Instantiates a new new social.
	 *
	 * @param c the c
	 * @param email the email
	 */
	public NewSocial(Contatto c, Email email) {
		
		this.c=c;
		this.email=email;
		
		
		setTitle("Nuovo Profilo Social");
		setResizable(false);
		
		setAlwaysOnTop(true);		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 314, 193);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(20, 40, 105, 11);
		contentPane.add(lblNickname);
		
		textFieldNickname = new JTextField();
		textFieldNickname.setBounds(135, 34, 153, 17);
		contentPane.add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		JLabel lblProvider = new JLabel("Provider");
		lblProvider.setBounds(20, 59, 105, 11);
		contentPane.add(lblProvider);
		
		textFieldProvider = new JTextField();
		textFieldProvider.setBounds(135, 53, 153, 17);
		contentPane.add(textFieldProvider);
		textFieldProvider.setColumns(10);
		
		JLabel lblWFrase = new JLabel("Frase di Benvenuto");
		lblWFrase.setBounds(20, 78, 106, 11);
		contentPane.add(lblWFrase);
		
		textFieldWFrase = new JTextField();
		textFieldWFrase.setBounds(135, 72, 153, 17);
		contentPane.add(textFieldWFrase);
		textFieldWFrase.setColumns(10);

		btnRun = new JButton("Inserisci");
		btnRun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				run();

			}
		});
		btnRun.setBounds(84, 124, 86, 19);
		contentPane.add(btnRun);
	}

	/**
	 * Run.
	 */
	protected void run() {
		if(textFieldNickname.getText().isBlank()==false && textFieldWFrase.getText().isBlank() == false && textFieldProvider.getText().isBlank() == false) {
			String data[]= {textFieldNickname.getText().trim(),textFieldProvider.getText().trim(),textFieldWFrase.getText().trim()};
			Controller.insertSocial(data, c, email);
			ModSocials.updateTable();
			ContactWindow.refreshSocialModel();
			setVisible(false);
			dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "Compila i campi richiesti!");
		}
	}


}
