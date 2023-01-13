package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Gruppo;

public class ViewGruppo extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ViewGruppo(Gruppo g) {
		setTitle("Vista Gruppo");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 265, 160);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNomeGruppo = new JLabel("Nome Gruppo");
		lblNomeGruppo.setBounds(20, 48, 65, 11);
		contentPane.add(lblNomeGruppo);

		JTextField textFieldName = new JTextField();
		textFieldName.setEditable(false);
		textFieldName.setText(g.getNomeG());
		textFieldName.setBounds(120, 45, 114, 17);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);

		JLabel lblDescrizioneG = new JLabel("Descrizione");
		lblDescrizioneG.setBounds(20, 67, 76, 11);
		contentPane.add(lblDescrizioneG);

		JTextField textFieldDescrizione = new JTextField();
		textFieldDescrizione.setEditable(false);
		textFieldDescrizione.setText(g.getDescrizione());
		textFieldDescrizione.setBounds(120, 64, 114, 17);
		contentPane.add(textFieldDescrizione);
		textFieldDescrizione.setColumns(10);

	}
}
