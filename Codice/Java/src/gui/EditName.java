package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;

import controller.Controller;
import model.Contatto;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class EditName.
 */
public class EditName extends JFrame {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The nome text field. */
	private JTextField nomeTextField;
	
	/** The cognome text field. */
	private JTextField cognomeTextField;


	/**
	 * Create the frame.
	 *
	 * @param mainFrame the main frame
	 * @param contactWindow the contact window
	 * @param c the c
	 */
	public EditName(MainWireframe mainFrame,ContactWindow contactWindow,Contatto c) {
		setTitle("Modifica Nome");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 280, 155);
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		nomeTextField = new JTextField();
		nomeTextField.setBounds(89, 25, 165, 20);
		panel.add(nomeTextField);
		nomeTextField.setColumns(10);
		nomeTextField.setText(c.getNome());
		
		cognomeTextField = new JTextField();
		cognomeTextField.setBounds(89, 53, 165, 20);
		panel.add(cognomeTextField);
		cognomeTextField.setColumns(10);
		cognomeTextField.setText(c.getCognome());
		
		JLabel lblNewLabel = new JLabel("Nome");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 26, 46, 17);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cognome");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 54, 69, 17);
		panel.add(lblNewLabel_1);
		
		JButton btnConferma = new JButton("Conferma");
		btnConferma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(nomeTextField.getText().isBlank() || cognomeTextField.getText().isBlank()) {
					JOptionPane.showMessageDialog(null, "Inserisci dei valori validi!");
				}
				else {
					Controller.updateNome(c,nomeTextField.getText(), cognomeTextField.getText());
					contactWindow.refreshName();
					mainFrame.clearTable();
					try {
						mainFrame.updateTable();
					} catch (Throwable e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					dispose();
				}
			}
		});
		
		btnConferma.setBounds(89, 82, 101, 23);
		panel.add(btnConferma);
		
		
	}
}
