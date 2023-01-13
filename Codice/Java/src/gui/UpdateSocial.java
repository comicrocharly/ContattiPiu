package gui;


import javax.swing.JOptionPane;
import controller.Controller;
import model.Contatto;
import model.Email;
import model.MessagingPr;


public class UpdateSocial extends NewSocial{

	private static Email email;
	
	private MessagingPr s;
	
	private static final long serialVersionUID = 1L;

	public UpdateSocial(Contatto c, MessagingPr s) {
		super(c, email);
		this.s=s;
		
		textFieldNickname.setText(s.getNickname());
		textFieldProvider.setText(s.getFornitore());
		textFieldWFrase.setText(s.getFraseBenvenuto());

		btnRun.setText("Modifica");


	}

	protected void run() {

		if(textFieldNickname.getText().isBlank()==false && textFieldWFrase.getText().isBlank() == false && textFieldProvider.getText().isBlank() == false) {
			String data[]= {textFieldNickname.getText().trim(),textFieldProvider.getText().trim(),textFieldWFrase.getText().trim()};
			Controller.updateSocial(data, c, email, s);
			ModSocials.updateTable();
			ContactWindow.refreshSocialModel();
			setVisible(false);
			dispose();
		}
		else {
			JOptionPane.showMessageDialog(null, "Compila i campi richiesti!");
		}


	}


}

