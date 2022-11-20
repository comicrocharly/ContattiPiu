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

public class NewRecapito extends JFrame{
	private JTextField textFieldNumeroIn;
	private JTextField textFieldNumeroOut;

	public NewRecapito(Contatto c) {
		
		setAlwaysOnTop(true);		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 299, 180);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumeroIn = new JLabel("NumeroIn");
		lblNumeroIn.setBounds(26, 37, 58, 11);
		contentPane.add(lblNumeroIn);
		
		JLabel lblNumeroOut = new JLabel("NumeroOut");
		lblNumeroOut.setBounds(26, 56, 58, 14);
		contentPane.add(lblNumeroOut);
		
		JTextField textFieldPrefissoIn = new JTextField();
		textFieldPrefissoIn.setBounds(92, 34, 29, 17);
		contentPane.add(textFieldPrefissoIn);
		textFieldPrefissoIn.setColumns(10);
		
		JTextField textFieldPrefissoOut = new JTextField();
		textFieldPrefissoOut.setBounds(92, 53, 29, 17);
		contentPane.add(textFieldPrefissoOut);
		textFieldPrefissoOut.setColumns(10);
		
		textFieldNumeroIn = new JTextField();
		textFieldNumeroIn.setBounds(129, 34, 86, 17);
		contentPane.add(textFieldNumeroIn);
		textFieldNumeroIn.setColumns(10);
		
		textFieldNumeroOut = new JTextField();
		textFieldNumeroOut.setBounds(129, 53, 86, 17);
		contentPane.add(textFieldNumeroOut);
		textFieldNumeroOut.setColumns(10);

		JButton btnTipoIn = new JButton("F");
		btnTipoIn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnTipoIn.getText().equals("F"))
					btnTipoIn.setText("M");
				else if(btnTipoIn.getText().equals("M"))
					btnTipoIn.setText("F");

			}

		});
		btnTipoIn.setToolTipText("TipoIn");
		btnTipoIn.setBounds(223, 33, 54, 19);
		contentPane.add(btnTipoIn);

		JButton btnTipoOut = new JButton("M");
		btnTipoOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnTipoOut.getText().equals("F"))
					btnTipoOut.setText("M");
				else if(btnTipoOut.getText().equals("M"))
					btnTipoOut.setText("F");

			}
		});
		btnTipoOut.setToolTipText("TipoOut");
		btnTipoOut.setBounds(223, 52, 54, 19);
		contentPane.add(btnTipoOut);
		
		JButton btnNewButton = new JButton("Inserisci");
		btnNewButton.setBounds(92, 116, 86, 19);
		contentPane.add(btnNewButton);
		
		btnNewButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				//Telefono 1, Telefono 2: prefisso, numero, tipo 
				String tipoIn, tipoOut;
				if(btnTipoIn.getText().equals("F"))
					tipoIn="Fisso";
				else 
					tipoIn="Mobile";
				
				if(btnTipoOut.getText().equals("F"))
					tipoOut="Fisso";
				else 
					tipoOut="Mobile";
				
				String data[]= {textFieldPrefissoIn.getText().trim(), textFieldNumeroIn.getText().trim(), tipoIn,
						textFieldPrefissoOut.getText().trim(), textFieldNumeroOut.getText().trim(), tipoOut};
				Controller.insertRecapito(data, c);
				ModRecapiti.updateTable();
				setVisible(false);
			}

		});
	}
}
