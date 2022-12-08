package gui;
import gui.MainWireframe;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.Controller;
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
	private JLabel lblNewLabelTFisso;
	private JTextField textFieldNumeroFisso;
	private JTextField textFieldNazione;
	private JTextField textFieldCitta;
	private JTextField textFieldVia;
	private JTextField textFieldCap;
	private JTextField textFieldPrefissoFisso;
	private JLabel lblNewLabelTMobile;
	private JTextField textFieldPrefissoMobile;
	private JTextField textFieldNumeroMobile;


	

	
	public NewContact() {
		setResizable(false);
		setTitle("Nuovo Contatto");
		setAlwaysOnTop(true);
		//Nasconde il jframe alla chiusura
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 286, 343);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Inserisci");
		
	
	
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.setBounds(92, 268, 86, 25);
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
		
		lblNewLabelTFisso = new JLabel("T.Fisso");
		lblNewLabelTFisso.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelTFisso.setBounds(25, 91, 67, 19);
		contentPane.add(lblNewLabelTFisso);
		
		textFieldNumeroFisso = new JTextField();
		textFieldNumeroFisso.setColumns(10);
		textFieldNumeroFisso.setBounds(153, 92, 86, 19);
		contentPane.add(textFieldNumeroFisso);
		
		JLabel lblNewLabelNazione = new JLabel("Nazione");
		lblNewLabelNazione.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelNazione.setBounds(25, 162, 67, 11);
		contentPane.add(lblNewLabelNazione);
		
		JLabel lblNewLabelCittaCap = new JLabel("Citta e CAP");
		lblNewLabelCittaCap.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelCittaCap.setBounds(25, 189, 77, 11);
		contentPane.add(lblNewLabelCittaCap);
		
		JLabel lblNewLabelVia = new JLabel("Via");
		lblNewLabelVia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelVia.setBounds(25, 216, 67, 11);
		contentPane.add(lblNewLabelVia);
		
		textFieldNazione = new JTextField();
		textFieldNazione.setBounds(112, 159, 126, 17);
		contentPane.add(textFieldNazione);
		textFieldNazione.setColumns(10);
		
		textFieldCitta = new JTextField();
		textFieldCitta.setBounds(112, 186, 81, 17);
		contentPane.add(textFieldCitta);
		textFieldCitta.setColumns(10);
		
		textFieldVia = new JTextField();
		textFieldVia.setBounds(112, 213, 126, 17);
		contentPane.add(textFieldVia);
		textFieldVia.setColumns(10);
		
		textFieldCap = new JTextField();
		textFieldCap.setBounds(201, 186, 37, 17);
		contentPane.add(textFieldCap);
		textFieldCap.setColumns(10);
		
		textFieldPrefissoFisso = new JTextField();
		textFieldPrefissoFisso.setBounds(112, 92, 33, 19);
		contentPane.add(textFieldPrefissoFisso);
		textFieldPrefissoFisso.setColumns(10);
		
		lblNewLabelTMobile = new JLabel("T.Mobile");
		lblNewLabelTMobile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabelTMobile.setBounds(25, 118, 67, 19);
		contentPane.add(lblNewLabelTMobile);

		textFieldPrefissoMobile = new JTextField();
		textFieldPrefissoMobile.setColumns(10);
		textFieldPrefissoMobile.setBounds(112, 119, 33, 19);
		contentPane.add(textFieldPrefissoMobile);

		textFieldNumeroMobile = new JTextField();
		textFieldNumeroMobile.setColumns(10);
		textFieldNumeroMobile.setBounds(153, 119, 86, 19);
		contentPane.add(textFieldNumeroMobile);

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Controllo presenza campi
				if(textFieldNome.getText().equals("")||textFieldCognome.getText().equals("")
						||textFieldPrefissoFisso.getText().equals("")||textFieldNumeroFisso.getText().equals("")
						||textFieldPrefissoMobile.getText().equals("")||textFieldNumeroMobile.getText().equals("")
						||textFieldNazione.getText().equals("")||textFieldCitta.getText().equals("")
						||textFieldVia.getText().equals("")||textFieldCap.getText().equals("")) 
				{
					JOptionPane.showMessageDialog(NewContact.this,"Errore: Compila i campi richisti.");
				}
				else if(!(textFieldPrefissoFisso.getText().matches("[0-9]+") && textFieldNumeroFisso.getText().matches("[0-9]+")
						&& textFieldPrefissoMobile.getText().matches("[0-9]+") && textFieldNumeroMobile.getText().matches("[0-9]+")) ) 
				{
					JOptionPane.showMessageDialog(NewContact.this,"Errore: I telefoni devono contenere solo numeri.");
				}

				else {

					String data[]= {textFieldNome.getText(), textFieldCognome.getText(), 
							textFieldPrefissoFisso.getText(),textFieldNumeroFisso.getText(),
							textFieldPrefissoMobile.getText(),textFieldNumeroMobile.getText(),
							textFieldNazione.getText(), textFieldCitta.getText(), textFieldVia.getText(), textFieldCap.getText()};

					String dataView[] = {data[0],data[1],data[7]+" "+data[8],data[2]+" "+data[3]};

					try {
						Controller.insertContatto(data);
						MainWireframe.addToTable(dataView);
						JOptionPane.showMessageDialog(NewContact.this,"Inserimento Riuscito.");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(NewContact.this,"Inserimento Fallito.");
						e1.printStackTrace();
					}


					setVisible(false);
				}
			}


		});
	}

}
