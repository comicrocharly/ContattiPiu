package gui;

import javax.swing.JOptionPane;
import controller.Controller;
import model.Contatto;
import model.Email;

public class UpdateEmail extends NewEmail{
	
	private static final long serialVersionUID = 1L;
	private Email email;
	
	public UpdateEmail(Contatto c, Email email) {
		super(c);
		this.email=email;
		
		btnRun.setBounds(108, 81, 95, 19);
		textFieldIndirizzo.setText(email.getIndirizzo());
		textFieldUso.setText(email.getUtilizzo());
		btnRun.setText("Aggiorna");
		
	}

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
