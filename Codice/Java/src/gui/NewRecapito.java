package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewRecapito extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	
	public NewRecapito() {
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 270, 170);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Inserisci");
		btnNewButton.setBounds(72, 108, 86, 19);
		contentPane.add(btnNewButton);
		
		JLabel lblNumeroIn = new JLabel("NumeroIn");
		lblNumeroIn.setBounds(26, 37, 40, 11);
		contentPane.add(lblNumeroIn);
		
		JLabel lblNumeroOut = new JLabel("NumeroOut");
		lblNumeroOut.setBounds(26, 56, 40, 11);
		contentPane.add(lblNumeroOut);
		
		JTextField textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setBounds(92, 34, 29, 17);
		contentPane.add(textFieldIndirizzo);
		textFieldIndirizzo.setColumns(10);
		
		JTextField textFieldUso = new JTextField();
		textFieldUso.setBounds(92, 53, 29, 17);
		contentPane.add(textFieldUso);
		textFieldUso.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(129, 34, 86, 17);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(129, 53, 86, 17);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
	}
}
