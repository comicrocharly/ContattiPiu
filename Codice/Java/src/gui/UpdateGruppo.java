package gui;

import javax.swing.JOptionPane;
import controller.Controller;
import model.Contatto;
import model.Gruppo;

public class UpdateGruppo extends NewGruppo{
	
	private static final long serialVersionUID = 1L;
	protected Gruppo gruppo;
	
	public UpdateGruppo(Contatto c, Gruppo gruppo) {
		super(c);
		this.gruppo=gruppo;
		
		btnRun.setBounds(79, 148, 95, 19);
		textFieldName.setText(gruppo.getNomeG());
		textFieldDescrizione.setText(gruppo.getDescrizione());
		btnRun.setText("Aggiorna");
		
	}

	protected void run() {
		Exception ex = null;
		if(textFieldName.getText().isBlank() && listGruppi.getSelectedIndex()!=-1) {
			Gruppo g = gList.get(listGruppi.getSelectedIndex());
			
		}

		else {
			String data[] = {textFieldName.getText().trim(),textFieldDescrizione.getText().trim()};
			try {
				Controller.updateGruppo(data, c, gruppo);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e1.getMessage());
				ex=e1;
			}
		}
		if(ex==null) {
			ModGruppi.refreshTable();
			ContactWindow.refreshGruppiModel();
		}
		setVisible(false);
	}

}
