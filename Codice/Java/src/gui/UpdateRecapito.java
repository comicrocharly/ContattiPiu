package gui;

import javax.swing.JOptionPane;

import controller.Controller;
import model.Contatto;
import model.Recapito;

// TODO: Auto-generated Javadoc
/**
 * The Class UpdateRecapito.
 */
public class UpdateRecapito extends NewRecapito{

	/** The r. */
	private Recapito r;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new update recapito.
	 *
	 * @param c the c
	 * @param r the r
	 */
	public UpdateRecapito(Contatto c, Recapito r) {
		super(c);
		this.r=r;

		textFieldPrefissoIn.setText(r.getTelefonoIn().getPrefisso());
		textFieldNumeroIn.setText(r.getTelefonoIn().getNumero());
		textFieldPrefissoOut.setText(r.getTelefonoOut().getPrefisso());
		textFieldNumeroOut.setText(r.getTelefonoOut().getNumero());

		if(r.getTelefonoIn().getTipo().equals("Fisso"))
			btnTipoIn.setText("F");
		else 
			btnTipoIn.setText("M");

		if(r.getTelefonoOut().getTipo().equals("Fisso"))
			btnTipoOut.setText("F");
		else 
			btnTipoOut.setText("M");

		btnTipoIn.setEnabled(false);
		btnTipoOut.setEnabled(false);

		btnRun.setText("Modifica");


	}

	/**
	 * Switch in.
	 */
	protected void switchIn() {
		//Switching has been disabled
	}

	/**
	 * Switch out.
	 */
	protected void switchOut() {
		//Switching has been disabled

	}

	/**
	 * Run.
	 */
	protected void run() {
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
				Controller.updateRecapito(data, c, r);
			} catch (Exception e2) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e2.getMessage());
				e1=e2;

			}
			if(e1==null) {
				ModRecapiti.refreshTable();
				ContactWindow.refreshRecapitoModel();
				setVisible(false);
				dispose();
			}

		}

		else
			JOptionPane.showMessageDialog(null, "Errore: sono ammessi solo numeri");
	}


}

