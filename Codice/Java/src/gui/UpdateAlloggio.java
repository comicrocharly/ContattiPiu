package gui;

import javax.swing.JOptionPane;
import controller.Controller;
import model.Contatto;
import model.Indirizzo;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateAlloggio.
 */
public class UpdateAlloggio extends NewAlloggio{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The i. */
	private Indirizzo i;
	
	/**
	 * Instantiates a new update alloggio.
	 *
	 * @param c the c
	 * @param i the i
	 */
	public UpdateAlloggio(Contatto c, Indirizzo i) {
		super(c);
		this.i=i;
		
		btnRun.setBounds(108, 136, 95, 19);
		textFieldNazione.setText(i.getNazione());
		textFieldCitta.setText(i.getCitta());
		textFieldVia.setText(i.getVia());
		textFieldCap.setText(i.getCap());
		btnRun.setText("Aggiorna");
		
		
		
	}

	/**
	 * Run.
	 */
	protected void run() {

		String nazione, citta, cap, via;
		nazione = textFieldNazione.getText();
		citta = textFieldCitta.getText();
		cap = textFieldCap.getText();
		via = textFieldVia.getText();

		if(nazione.trim().length()==0||citta.trim().length()==0||cap.trim().length()==0||via.trim().length()==0)
			JOptionPane.showMessageDialog(null,"Non puoi inserire campi vuoti!");
		else {
			String data[]= {nazione, citta, cap, via};
			try {
				Controller.updateAlloggio(data, c, i);
				ModAlloggio.refreshTable();
				ContactWindow.refreshAlloggiModel();
				setVisible(false);
				dispose();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}

		}
	}
}
