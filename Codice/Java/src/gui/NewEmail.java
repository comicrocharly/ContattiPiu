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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class NewEmail extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private Contatto c;
	
	public NewEmail(Contatto c) {
		setResizable(false);
		setAlwaysOnTop(true);
		setC(c);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 303, 149);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo");
		lblIndirizzo.setBounds(26, 37, 72, 11);
		contentPane.add(lblIndirizzo);
		
		JLabel lblUso = new JLabel("Uso");
		lblUso.setBounds(26, 56, 72, 11);
		contentPane.add(lblUso);
		
		JTextField textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setBounds(108, 34, 158, 17);
		contentPane.add(textFieldIndirizzo);
		textFieldIndirizzo.setColumns(10);
		
		JTextField textFieldUso = new JTextField();
		textFieldUso.setBounds(108, 53, 158, 17);
		contentPane.add(textFieldUso);
		textFieldUso.setColumns(10);
		
		JButton btnNewButton = new JButton("Inserisci");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textFieldIndirizzo.getText().contains("@") && textFieldIndirizzo.getText().contains(".")) {
					String data[]= {textFieldIndirizzo.getText().trim(),textFieldUso.getText().trim()};
					Controller.insertEmail(data,c);
					ModEmails.refreshTable();
					ContactWindow.refreshEmailModel();
					setVisible(false);
				}
				
				else 
					JOptionPane.showMessageDialog(null, "Inserisci un indirizzo email valido");
			}
		});
		btnNewButton.setBounds(108, 81, 86, 19);
		contentPane.add(btnNewButton);
	}
	
	public Contatto getC() {
		return c;
	}

	public void setC(Contatto c) {
		this.c = c;
	}
}
