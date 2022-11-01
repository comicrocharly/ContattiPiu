package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewEmail extends JFrame{
	
	public NewEmail() {
		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 250, 170);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Inserisci");
		btnNewButton.setBounds(72, 108, 86, 19);
		contentPane.add(btnNewButton);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo");
		lblIndirizzo.setBounds(26, 37, 40, 11);
		contentPane.add(lblIndirizzo);
		
		JLabel lblUso = new JLabel("Uso");
		lblUso.setBounds(26, 56, 40, 11);
		contentPane.add(lblUso);
		
		JTextField textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setBounds(92, 34, 100, 17);
		contentPane.add(textFieldIndirizzo);
		textFieldIndirizzo.setColumns(10);
		
		JTextField textFieldUso = new JTextField();
		textFieldUso.setBounds(92, 53, 100, 17);
		contentPane.add(textFieldUso);
		textFieldUso.setColumns(10);
	}
}
