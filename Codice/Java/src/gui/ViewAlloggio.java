package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Indirizzo;

public class ViewAlloggio extends JFrame{
	/** The Constant serialVersionUID. */

	private static final long serialVersionUID = 1L;

	//TODO: Auto-generated Javadoc
	/**
	 * The Class NewAlloggi.
	 */
	ViewAlloggio(Indirizzo i){
		String nazione, citta, cap, via;
		
		nazione = i.getNazione();
		citta = i.getCitta();
		cap = i.getCap();
		via = i.getVia();
		
		setTitle("Vista Indirizzo");
		setResizable(false);

		setAlwaysOnTop(true);		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 325, 207);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNazione = new JLabel("Nazione");
		lblNazione.setBounds(26, 37, 58, 11);
		contentPane.add(lblNazione);

		JLabel lblCitta = new JLabel("Città");
		lblCitta.setBounds(26, 56, 58, 14);
		contentPane.add(lblCitta);

		JTextField textFieldNazione = new JTextField();
		textFieldNazione.setEditable(false);
		textFieldNazione.setText(nazione);
		textFieldNazione.setToolTipText("Nazione");
		textFieldNazione.setBounds(113, 34, 186, 17);
		contentPane.add(textFieldNazione);
		textFieldNazione.setColumns(10);

		JTextField textFieldCitta = new JTextField();
		textFieldCitta.setEditable(false);
		textFieldCitta.setText(citta);
		textFieldCitta.setToolTipText("Cittá");
		textFieldCitta.setBounds(113, 55, 186, 17);
		contentPane.add(textFieldCitta);
		textFieldCitta.setColumns(10);

		JLabel lblCap = new JLabel("CAP");
		lblCap.setBounds(26, 81, 58, 11);
		contentPane.add(lblCap);

		JLabel lblVia = new JLabel("Via e N.Civico");
		lblVia.setBounds(26, 100, 77, 14);
		contentPane.add(lblVia);

		JTextField textFieldVia = new JTextField();
		textFieldVia.setEditable(false);
		textFieldVia.setText(via);
		textFieldVia.setColumns(10);
		textFieldVia.setBounds(113, 99, 186, 17);
		contentPane.add(textFieldVia);

		JTextField textFieldCap = new JTextField();
		textFieldCap.setEditable(false);
		textFieldCap.setText(cap);
		textFieldCap.setColumns(10);
		textFieldCap.setBounds(113, 78, 186, 17);
		contentPane.add(textFieldCap);

	}

}
