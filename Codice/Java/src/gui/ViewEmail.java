package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.Email;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewEmail.
 */
public class ViewEmail extends JFrame{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new new email.
	 *
	 * @param e the e
	 */
	public ViewEmail(Email e) {
		
		setTitle("Vista E-mail");
		setResizable(false);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 303, 149);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIndirizzo = new JLabel("Indirizzo");
		lblIndirizzo.setBounds(26, 37, 72, 11);
		contentPane.add(lblIndirizzo);
		
		JLabel lblUso = new JLabel("Uso");
		lblUso.setBounds(26, 56, 72, 11);
		contentPane.add(lblUso);
		
		JTextField textFieldIndirizzo = new JTextField();
		textFieldIndirizzo.setBounds(108, 34, 158, 17);
		textFieldIndirizzo.setEditable(false);
		contentPane.add(textFieldIndirizzo);
		textFieldIndirizzo.setColumns(10);
		textFieldIndirizzo.setText(e.getIndirizzo());
		
		JTextField textFieldUso = new JTextField();
		textFieldUso.setBounds(108, 53, 158, 17);
		textFieldUso.setEditable(false);
		contentPane.add(textFieldUso);
		textFieldUso.setColumns(10);
		textFieldUso.setText(e.getUtilizzo());
		
	}

}
