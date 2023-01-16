package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import model.MessagingPr;

// TODO: Auto-generated Javadoc
/**
 * The Class ViewSocial.
 */
public class ViewSocial extends JFrame{
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Instantiates a new new social.
	 *
	 * @param mpr the mpr
	 */
	public ViewSocial(MessagingPr mpr) {
		setTitle("Vista Profilo Social");
		setResizable(false);
		
		String nickname, provider, fraseBenvenuto;
		
		nickname = mpr.getNickname();
		provider = mpr.getFornitore();
		fraseBenvenuto = mpr.getFraseBenvenuto();
		
		setAlwaysOnTop(true);		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 314, 193);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(20, 40, 105, 11);
		contentPane.add(lblNickname);
		
		JTextField textFieldNickname = new JTextField();
		textFieldNickname.setEditable(false);
		textFieldNickname.setText(nickname);
		textFieldNickname.setBounds(135, 34, 153, 17);
		contentPane.add(textFieldNickname);
		textFieldNickname.setColumns(10);
		
		JLabel lblProvider = new JLabel("Provider");
		lblProvider.setBounds(20, 59, 105, 11);
		contentPane.add(lblProvider);
		
		JTextField textFieldProvider = new JTextField();
		textFieldProvider.setEditable(false);
		textFieldProvider.setText(provider);
		textFieldProvider.setBounds(135, 53, 153, 17);
		contentPane.add(textFieldProvider);
		textFieldProvider.setColumns(10);
		
		JLabel lblWFrase = new JLabel("Frase di Benvenuto");
		lblWFrase.setBounds(20, 78, 106, 11);
		contentPane.add(lblWFrase);
		
		JTextField textFieldWFrase = new JTextField();
		textFieldWFrase.setEditable(false);
		textFieldWFrase.setText(fraseBenvenuto);
		textFieldWFrase.setBounds(135, 72, 153, 17);
		contentPane.add(textFieldWFrase);
		textFieldWFrase.setColumns(10);

	}

}
