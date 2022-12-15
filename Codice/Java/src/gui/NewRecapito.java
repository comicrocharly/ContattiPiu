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
import javax.swing.SwingConstants;

public class NewRecapito extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JTextField textFieldNumeroIn;
	private JTextField textFieldNumeroOut;

	public NewRecapito(Contatto c) {
		
		setResizable(false);
		setAlwaysOnTop(true);		
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 382, 162);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumeroIn = new JLabel("Numero");
		lblNumeroIn.setBounds(10, 37, 107, 11);
		contentPane.add(lblNumeroIn);
		
		JLabel lblNumeroOut = new JLabel("Reindirizzamento");
		lblNumeroOut.setBounds(10, 56, 107, 14);
		contentPane.add(lblNumeroOut);
		
		JTextField textFieldPrefissoIn = new JTextField();
		textFieldPrefissoIn.setBounds(127, 31, 35, 17);
		contentPane.add(textFieldPrefissoIn);
		textFieldPrefissoIn.setColumns(10);
		
		JTextField textFieldPrefissoOut = new JTextField();
		textFieldPrefissoOut.setBounds(127, 51, 35, 17);
		contentPane.add(textFieldPrefissoOut);
		textFieldPrefissoOut.setColumns(10);
		
		textFieldNumeroIn = new JTextField();
		textFieldNumeroIn.setBounds(172, 31, 120, 17);
		contentPane.add(textFieldNumeroIn);
		textFieldNumeroIn.setColumns(10);
		
		textFieldNumeroOut = new JTextField();
		textFieldNumeroOut.setBounds(172, 50, 120, 17);
		contentPane.add(textFieldNumeroOut);
		textFieldNumeroOut.setColumns(10);

		JButton btnTipoIn = new JButton("F");
		
		btnTipoIn.setToolTipText("TipoIn");
		btnTipoIn.setBounds(302, 30, 54, 19);
		contentPane.add(btnTipoIn);

		JButton btnTipoOut = new JButton("M");
		
		btnTipoOut.setToolTipText("TipoOut");
		btnTipoOut.setBounds(302, 51, 54, 19);
		contentPane.add(btnTipoOut);
		
		btnTipoIn.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnTipoIn.getText().equals("F")) {
					btnTipoIn.setText("M");
					btnTipoOut.setText("F");
				}
				else if(btnTipoIn.getText().equals("M")) {
					btnTipoIn.setText("F");
					btnTipoOut.setText("M");
				}
				
			}

		});
		
		btnTipoOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(btnTipoOut.getText().equals("F")) {
					btnTipoOut.setText("M");
					btnTipoIn.setText("F");
					}
				
				else if(btnTipoOut.getText().equals("M")) {
					btnTipoOut.setText("F");
					btnTipoIn.setText("M");
				}
			}
		});
		
		
		JButton btnNewButton = new JButton("Inserisci");
		btnNewButton.setBounds(124, 87, 86, 19);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Tipo");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(302, 11, 54, 14);
		contentPane.add(lblNewLabel);
		
		btnNewButton.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if(textFieldPrefissoIn.getText().matches("[0-9]+") && textFieldNumeroIn.getText().matches("[0-9]+")
						&& textFieldPrefissoOut.getText().matches("[0-9]+") && textFieldNumeroOut.getText().matches("[0-9]+") ){
					//Telefono 1, Telefono 2: prefisso, numero, tipo 
					String tipoIn, tipoOut;
					Exception e1=null;
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
					try {
						Controller.insertRecapito(data, c);
					} catch (Exception e2) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, e2.getMessage());
						e1=e2;
						
					}
					if(e1==null) {
						ModRecapiti.updateTable();
						ContactWindow.refreshRecapitoModel();
						setVisible(false);
					}
					
				}
				
				else 
					JOptionPane.showMessageDialog(null, "Errore: sono ammessi solo numeri");
			}

		});
	}
}
