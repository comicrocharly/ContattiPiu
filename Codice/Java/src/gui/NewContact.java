package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class NewContact extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private JTextField textFieldIndirizzo;
	private JLabel lblNewLabel_2;
	private JTextField textFieldPrefisso;
	private JTextField textFieldNumero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewContact frame = new NewContact();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public NewContact() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 275, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Inserisci");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(133, 177, 86, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(25, 37, 67, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCognome.setBounds(25, 64, 67, 19);
		contentPane.add(lblCognome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(112, 40, 127, 17);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldCognome = new JTextField();
		textFieldCognome.setBounds(112, 67, 127, 17);
		contentPane.add(textFieldCognome);
		textFieldCognome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Indirizzo");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(25, 91, 67, 19);
		contentPane.add(lblNewLabel_1);
		
		textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setBounds(112, 92, 127, 17);
		contentPane.add(textFieldIndirizzo);
		textFieldIndirizzo.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Telefono");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(25, 121, 67, 19);
		contentPane.add(lblNewLabel_2);
		
		textFieldPrefisso = new JTextField();
		textFieldPrefisso.setBounds(112, 123, 33, 19);
		contentPane.add(textFieldPrefisso);
		textFieldPrefisso.setColumns(10);
		
		textFieldNumero = new JTextField();
		textFieldNumero.setColumns(10);
		textFieldNumero.setBounds(153, 123, 86, 19);
		contentPane.add(textFieldNumero);
	}
}
