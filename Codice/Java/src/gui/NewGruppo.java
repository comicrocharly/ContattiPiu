package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Contatto;

import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewGruppo extends JFrame{
	private JTextField textFieldName;
	private JTextField textFieldDescrizione;
	private JTextField textFieldCerca;
	
	public NewGruppo(Contatto c) {
		setAlwaysOnTop(true);		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 265, 223);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList();
		list.setBounds(120, 17, 114, 51);
		contentPane.add(list);
		
		JLabel lblNomeGruppo = new JLabel("Nome Gruppo");
		lblNomeGruppo.setBounds(20, 92, 65, 11);
		contentPane.add(lblNomeGruppo);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(120, 89, 114, 17);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblDescrizioneG = new JLabel("Descrizione");
		lblDescrizioneG.setBounds(20, 111, 76, 11);
		contentPane.add(lblDescrizioneG);

		textFieldDescrizione = new JTextField();
		textFieldDescrizione.setBounds(120, 108, 114, 17);
		contentPane.add(textFieldDescrizione);
		textFieldDescrizione.setColumns(10);

		textFieldCerca = new JTextField();
		textFieldCerca.setBounds(20, 22, 86, 19);
		contentPane.add(textFieldCerca);
		textFieldCerca.setColumns(10);

		JButton btnCerca = new JButton("Cerca");
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		btnCerca.setBounds(20, 47, 86, 19);
		contentPane.add(btnCerca);

		JButton btnNewButton = new JButton("Inserisci");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String data[]= {textFieldName.getText().trim(),textFieldDescrizione.getText().trim()};
				Controller.insertGruppo(data, c);
				ModGruppi.updateTable();
				setVisible(false);
			}
		});
		btnNewButton.setBounds(85, 157, 86, 19);
		contentPane.add(btnNewButton);
	}


}
