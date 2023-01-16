package gui;

import javax.swing.JOptionPane;
import controller.Controller;
import model.Contatto;
import model.Gruppo;
import javax.swing.JLabel;
import java.awt.Color;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateGruppo.
 */
public class UpdateGruppo extends NewGruppo{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The gruppo. */
	protected Gruppo gruppo;
	
	/**
	 * Instantiates a new update gruppo.
	 *
	 * @param c the c
	 * @param gruppo the gruppo
	 */
	public UpdateGruppo(Contatto c, Gruppo gruppo) {
		super(c);
		setResizable(false);
		this.gruppo=gruppo;
		
		btnRun.setBounds(74, 159, 95, 19);
		textFieldName.setText(gruppo.getNomeG());
		textFieldDescrizione.setText(gruppo.getDescrizione());
		btnRun.setText("Aggiorna");
		
		JLabel lblNewLabel = new JLabel("La modifica agisce su tutti i gruppi.");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setToolTipText("Per modificare il singolo gruppo procedere all'inserimento del nuovo gruppo.");
		lblNewLabel.setBounds(20, 136, 214, 14);
		getContentPane().add(lblNewLabel);
		
	}

	/**
	 * Run.
	 */
	protected void run() {
		Exception ex = null;

		String data[] = {textFieldName.getText().trim(),textFieldDescrizione.getText().trim()};
		try {
			Controller.updateGruppo(data, c, gruppo);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e1.getMessage());
			ex=e1;
		}

		if(ex==null) {
			ModGruppi.refreshTable();
			ContactWindow.refreshGruppiModel();
		}
		setVisible(false);
		dispose();
	}
}
