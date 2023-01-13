package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Recapito;

// TODO: Auto-generated Javadoc
/**
 * The Class NewRecapito.
 */
public class ViewRecapito extends JFrame{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new new recapito.
	 *
	 * @param c the c
	 */
	public ViewRecapito(Recapito r) {
		setTitle("Vista Recapito");
		setResizable(false);
		setAlwaysOnTop(true);		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 382, 162);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNumeroIn = new JLabel("Numero");
		lblNumeroIn.setBounds(10, 37, 107, 11);
		contentPane.add(lblNumeroIn);
		
		JLabel lblNumeroOut = new JLabel("Reindirizzamento");
		lblNumeroOut.setBounds(10, 56, 107, 14);
		contentPane.add(lblNumeroOut);
		
		JTextField textFieldPrefissoIn = new JTextField();
		textFieldPrefissoIn.setText(r.getTelefonoIn().getPrefisso());
		textFieldPrefissoIn.setEditable(false);
		textFieldPrefissoIn.setBounds(127, 31, 35, 17);
		contentPane.add(textFieldPrefissoIn);
		textFieldPrefissoIn.setColumns(10);

		JTextField textFieldPrefissoOut = new JTextField();
		textFieldPrefissoOut.setText(r.getTelefonoOut().getPrefisso());
		textFieldPrefissoOut.setEditable(false);
		textFieldPrefissoOut.setBounds(127, 51, 35, 17);
		contentPane.add(textFieldPrefissoOut);
		textFieldPrefissoOut.setColumns(10);

		JTextField textFieldNumeroIn = new JTextField();
		textFieldNumeroIn.setText(r.getTelefonoIn().getNumero());
		textFieldNumeroIn.setEditable(false);
		textFieldNumeroIn.setBounds(172, 31, 120, 17);
		contentPane.add(textFieldNumeroIn);
		textFieldNumeroIn.setColumns(10);

		JTextField textFieldNumeroOut = new JTextField();
		textFieldNumeroOut.setText(r.getTelefonoOut().getNumero());
		textFieldNumeroOut.setEditable(false);
		textFieldNumeroOut.setBounds(172, 50, 120, 17);
		contentPane.add(textFieldNumeroOut);
		textFieldNumeroOut.setColumns(10);

		JButton btnTipoIn;
		String TipoIn = r.getTelefonoIn().getTipo();
		if(TipoIn.equals("Mobile"))
			btnTipoIn = new JButton("M");
		else
			btnTipoIn = new JButton("F");

		btnTipoIn.setEnabled(false);
		btnTipoIn.setToolTipText("TipoIn");
		btnTipoIn.setBounds(302, 30, 54, 19);
		contentPane.add(btnTipoIn);

		JButton btnTipoOut;
		String TipoOut = r.getTelefonoOut().getTipo();
		if(TipoOut.equals("Mobile"))
			btnTipoOut = new JButton("M");
		else
			btnTipoOut = new JButton("F");

		btnTipoOut.setEnabled(false);
		btnTipoOut.setToolTipText("TipoOut");
		btnTipoOut.setBounds(302, 51, 54, 19);
		contentPane.add(btnTipoOut);


	}
}