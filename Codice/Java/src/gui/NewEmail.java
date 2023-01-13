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

// TODO: Auto-generated Javadoc
/**
 * The Class NewEmail.
 */
public class NewEmail extends JFrame{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The c. */
	protected Contatto c;
	
	protected JTextField textFieldIndirizzo;
	
	protected JTextField textFieldUso;
	
	protected JButton btnRun;
	/**
	 * Instantiates a new new email.
	 *
	 * @param c the c
	 */
	public NewEmail(Contatto c) {
		setTitle("Nuova E-mail");
		setResizable(false);
		setAlwaysOnTop(true);
		setC(c);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setBounds(108, 34, 158, 17);
		contentPane.add(textFieldIndirizzo);
		textFieldIndirizzo.setColumns(10);
		
		textFieldUso = new JTextField();
		textFieldUso.setBounds(108, 53, 158, 17);
		contentPane.add(textFieldUso);
		textFieldUso.setColumns(10);
		
		btnRun = new JButton("Inserisci");
		btnRun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				run();
			}
		});
		btnRun.setBounds(108, 81, 86, 19);
		contentPane.add(btnRun);
	}
	protected void run() {
		if(textFieldIndirizzo.getText().contains("@") && textFieldIndirizzo.getText().contains(".")) {
			String data[]= {textFieldIndirizzo.getText().trim(),textFieldUso.getText().trim()};
			Controller.insertEmail(data,c);
			ModEmails.refreshTable();
			ContactWindow.refreshEmailModel();
			setVisible(false);
			dispose();
		}
		
		else 
			JOptionPane.showMessageDialog(null, "Inserisci un indirizzo email valido");
	}
	/**
	 * Gets the c.
	 *
	 * @return the c
	 */
	public Contatto getC() {
		return c;
	}

	/**
	 * Sets the c.
	 *
	 * @param c the new c
	 */
	public void setC(Contatto c) {
		this.c = c;
	}
}
