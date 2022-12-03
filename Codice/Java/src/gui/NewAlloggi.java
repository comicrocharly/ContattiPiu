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

public class NewAlloggi extends JFrame{
	
	private JTextField textFieldNazione;
	private JTextField textFieldCitta;
	private JTextField textFieldVia;
	private JTextField textFieldCap;

	public NewAlloggi(Contatto c) {
		
		setAlwaysOnTop(true);		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 260, 207);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNazione = new JLabel("Nazione");
		lblNazione.setBounds(26, 37, 58, 11);
		contentPane.add(lblNazione);
		
		JLabel lblCitta = new JLabel("Città");
		lblCitta.setBounds(26, 56, 58, 14);
		contentPane.add(lblCitta);
		
		textFieldNazione = new JTextField();
		textFieldNazione.setBounds(92, 34, 104, 17);
		contentPane.add(textFieldNazione);
		textFieldNazione.setColumns(10);
		
		textFieldCitta = new JTextField();
		textFieldCitta.setBounds(92, 55, 104, 17);
		contentPane.add(textFieldCitta);
		textFieldCitta.setColumns(10);
		
		JButton btnInserisci = new JButton("Inserisci");
		btnInserisci.setBounds(92, 143, 86, 19);
		contentPane.add(btnInserisci);
		
		JLabel lblCap = new JLabel("CAP");
		lblCap.setBounds(26, 81, 58, 11);
		contentPane.add(lblCap);
		
		JLabel lblVia = new JLabel("Via");
		lblVia.setBounds(26, 100, 58, 14);
		contentPane.add(lblVia);
		
		textFieldVia = new JTextField();
		textFieldVia.setColumns(10);
		textFieldVia.setBounds(92, 99, 104, 17);
		contentPane.add(textFieldVia);
		
		textFieldCap = new JTextField();
		textFieldCap.setColumns(10);
		textFieldCap.setBounds(92, 78, 104, 17);
		contentPane.add(textFieldCap);
		
		btnInserisci.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String nazione, citta, cap, via;
				nazione = textFieldNazione.getText();
				citta = textFieldCitta.getText();
				cap = textFieldCap.getText();
				via = textFieldVia.getText();
				
				String data[]= {nazione, citta, cap, via};
				Controller.insertAlloggio(data, c);
				ModAlloggio.updateTable();
				ContactWindow.refreshAlloggiModel();
				setVisible(false);
			}

		});
	}
}
