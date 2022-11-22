package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Contatto;
import model.Email;

import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import java.awt.Font;

public class NewSocial extends JFrame{
	private Contatto c;
	
	private JTextField textFieldNickname;
	private JTextField textFieldProvider;
	private JTextField textFieldWFrase;
	
	public NewSocial(Contatto c, Email email) {
		
		setAlwaysOnTop(true);		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 265, 251);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(20, 40, 74, 11);
		contentPane.add(lblNickname);
		
		textFieldNickname = new JTextField();
		textFieldNickname.setBounds(135, 37, 100, 17);
		contentPane.add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		JLabel lblProvider = new JLabel("Provider");
		lblProvider.setBounds(20, 59, 40, 11);
		contentPane.add(lblProvider);
		
		textFieldProvider = new JTextField();
		textFieldProvider.setBounds(135, 56, 100, 17);
		contentPane.add(textFieldProvider);
		textFieldProvider.setColumns(10);
		
		JLabel lblWFrase = new JLabel("Frase di Benvenuto");
		lblWFrase.setBounds(20, 78, 106, 11);
		contentPane.add(lblWFrase);
		
		textFieldWFrase = new JTextField();
		textFieldWFrase.setBounds(135, 75, 100, 17);
		contentPane.add(textFieldWFrase);
		textFieldWFrase.setColumns(10);

		JButton btnNewButton = new JButton("Inserisci");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String data[]= {textFieldNickname.getText().trim(),textFieldProvider.getText().trim(),textFieldWFrase.getText().trim()};
				Controller.insertSocial(data, c, email);
				ModSocials.updateTable();
				setVisible(false);
			}
		});
		btnNewButton.setBounds(85, 184, 86, 19);
		contentPane.add(btnNewButton);
	}

	public void setC(Contatto c) {
		this.c = c;
	}

}
