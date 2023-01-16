package gui;

import javax.swing.JOptionPane;
import controller.Controller;
import model.Contatto;
import model.Email;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateEmail.
 */
public class UpdateEmail extends NewEmail{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The email. */
	private Email email;
	
	/**
	 * Instantiates a new update email.
	 *
	 * @param c the c
	 * @param email the email
	 */
	public UpdateEmail(Contatto c, Email email) {
		super(c);
		this.email=email;
		
		btnRun.setBounds(108, 81, 95, 19);
		textFieldIndirizzo.setText(email.getIndirizzo());
		textFieldUso.setText(email.getUtilizzo());
		btnRun.setText("Aggiorna");
		
	}

	/**
	 * Run.
	 */
	protected void run() {
		if(textFieldIndirizzo.getText().contains("@") && textFieldIndirizzo.getText().contains(".")) {
			String data[]= {textFieldIndirizzo.getText().trim(),textFieldUso.getText().trim()};
			Controller.updateEmail(data,c,email);
			ModEmails.refreshTable();
			ContactWindow.refreshEmailModel();
			setVisible(false);
			dispose();
			
		}
		
		else 
			JOptionPane.showMessageDialog(null, "Inserisci un indirizzo email valido");
	}

}
