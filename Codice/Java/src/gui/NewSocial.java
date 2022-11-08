package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import java.awt.Font;

public class NewSocial extends JFrame{
	private JTextField textFieldName;
	private JTextField textFieldProvider;
	private JTextField textField;
	
	public NewSocial() {
		setAlwaysOnTop(true);		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 265, 251);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Inserisci");
		btnNewButton.setBounds(85, 187, 86, 19);
		contentPane.add(btnNewButton);
		
		JList list = new JList();
		list.setBounds(104, 8, 124, 50);
		contentPane.add(list);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEmail.setBounds(20, 30, 40, 11);
		contentPane.add(lblEmail);
		
		JLabel lblNewLabel = new JLabel("Nickname");
		lblNewLabel.setBounds(20, 88, 40, 11);
		contentPane.add(lblNewLabel);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(104, 85, 124, 17);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblProvider = new JLabel("Provider");
		lblProvider.setBounds(20, 107, 40, 11);
		contentPane.add(lblProvider);
		
		textFieldProvider = new JTextField();
		textFieldProvider.setBounds(104, 104, 124, 17);
		contentPane.add(textFieldProvider);
		textFieldProvider.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Frase di Benvenuto");
		lblNewLabel_1.setBounds(20, 126, 86, 11);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(104, 123, 124, 17);
		contentPane.add(textField);
		textField.setColumns(10);
	}
}
