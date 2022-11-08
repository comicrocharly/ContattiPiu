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

public class NewRecapito extends JFrame{
	private JTextField textField;
	private JTextField textField_1;
	
	public NewRecapito() {
		setAlwaysOnTop(true);		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 299, 180);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Inserisci");
		btnNewButton.setBounds(92, 116, 86, 19);
		contentPane.add(btnNewButton);
		
		JLabel lblNumeroIn = new JLabel("NumeroIn");
		lblNumeroIn.setBounds(26, 37, 58, 11);
		contentPane.add(lblNumeroIn);
		
		JLabel lblNumeroOut = new JLabel("NumeroOut");
		lblNumeroOut.setBounds(26, 56, 58, 14);
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
	}
}
