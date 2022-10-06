package gui;
import gui.MainWireframe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Choice;

public class NewContact extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldNome;
	private JTextField textFieldCognome;
	private JLabel lblNewLabelTelefono;
	private JTextField textFieldNumeroIn;
	private JTextField textFieldNazione;
	private JTextField textFieldCitta;
	private JTextField textFieldVia;
	private JTextField textFieldCap;
	private JTextField textFieldPrefissoIn;
	private JLabel lblNewLabelTelefono_1;
	private JTextField textFieldPrefissoOut;
	private JTextField textFieldNumeroOut;


	

	
	public NewContact() {
		setTitle("Nuovo Contatto");
		setAlwaysOnTop(true);
		//Nasconde il jframe alla chiusura
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 519, 249);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Inserisci");
		
	
	
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(270, 160, 86, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabelNome = new JLabel("Nome");
		lblNewLabelNome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelNome.setBounds(25, 37, 67, 19);
		contentPane.add(lblNewLabelNome);
		
		JLabel lblNewLabelCognome = new JLabel("Cognome");
		lblNewLabelCognome.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelCognome.setBounds(25, 64, 67, 19);
		contentPane.add(lblNewLabelCognome);
		
		textFieldNome = new JTextField();
		textFieldNome.setBounds(112, 40, 127, 17);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);
		
		textFieldCognome = new JTextField();
		textFieldCognome.setBounds(112, 67, 127, 17);
		contentPane.add(textFieldCognome);
		textFieldCognome.setColumns(10);
		
		lblNewLabelTelefono = new JLabel("Telefono");
		lblNewLabelTelefono.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelTelefono.setBounds(25, 91, 67, 19);
		contentPane.add(lblNewLabelTelefono);
		
		textFieldNumeroIn = new JTextField();
		textFieldNumeroIn.setColumns(10);
		textFieldNumeroIn.setBounds(153, 92, 86, 19);
		contentPane.add(textFieldNumeroIn);
		
		JLabel lblNewLabelNazione = new JLabel("Nazione");
		lblNewLabelNazione.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelNazione.setBounds(270, 43, 67, 11);
		contentPane.add(lblNewLabelNazione);
		
		JLabel lblNewLabelCittaCap = new JLabel("Citta e CAP");
		lblNewLabelCittaCap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelCittaCap.setBounds(270, 70, 77, 11);
		contentPane.add(lblNewLabelCittaCap);
		
		JLabel lblNewLabelVia = new JLabel("Via");
		lblNewLabelVia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelVia.setBounds(270, 97, 67, 11);
		contentPane.add(lblNewLabelVia);
		
		textFieldNazione = new JTextField();
		textFieldNazione.setBounds(345, 40, 126, 17);
		contentPane.add(textFieldNazione);
		textFieldNazione.setColumns(10);
		
		textFieldCitta = new JTextField();
		textFieldCitta.setBounds(345, 67, 81, 17);
		contentPane.add(textFieldCitta);
		textFieldCitta.setColumns(10);
		
		textFieldVia = new JTextField();
		textFieldVia.setBounds(345, 94, 126, 17);
		contentPane.add(textFieldVia);
		textFieldVia.setColumns(10);
		
		textFieldCap = new JTextField();
		textFieldCap.setBounds(434, 67, 37, 17);
		contentPane.add(textFieldCap);
		textFieldCap.setColumns(10);
		
		textFieldPrefissoIn = new JTextField();
		textFieldPrefissoIn.setBounds(112, 92, 33, 19);
		contentPane.add(textFieldPrefissoIn);
		textFieldPrefissoIn.setColumns(10);
		
		lblNewLabelTelefono_1 = new JLabel("Telefono");
		lblNewLabelTelefono_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelTelefono_1.setBounds(25, 118, 67, 19);
		contentPane.add(lblNewLabelTelefono_1);
		
		textFieldPrefissoOut = new JTextField();
		textFieldPrefissoOut.setColumns(10);
		textFieldPrefissoOut.setBounds(112, 119, 33, 19);
		contentPane.add(textFieldPrefissoOut);
		
		textFieldNumeroOut = new JTextField();
		textFieldNumeroOut.setColumns(10);
		textFieldNumeroOut.setBounds(153, 119, 86, 19);
		contentPane.add(textFieldNumeroOut);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Controllo presenza campi
				if(textFieldNome.getText().equals("")||textFieldCognome.getText().equals("")
						||textFieldPrefissoIn.getText().equals("")||textFieldNumeroIn.getText().equals("")
						||textFieldPrefissoOut.getText().equals("")||textFieldNumeroOut.getText().equals("")
						||textFieldNazione.getText().equals("")||textFieldCitta.getText().equals("")
						||textFieldVia.getText().equals("")||textFieldCap.getText().equals("")) {

					JOptionPane.showMessageDialog(NewContact.this,"Errore: Compila i campi richisti.");
				}
				else {

					String data[]= {textFieldNome.getText(), textFieldCognome.getText(), textFieldPrefissoIn.getText(),textFieldNumeroIn.getText(),textFieldPrefissoOut.getText(),textFieldNumeroOut.getText(),
							textFieldNazione.getText(), textFieldCitta.getText(), textFieldVia.getText(), textFieldCap.getText()};

					MainWireframe.addToTable(data);

					JOptionPane.showMessageDialog(NewContact.this,"Inserimento riuscito.");
					setVisible(false);
				}
			}
			
			
		});
	}

}
