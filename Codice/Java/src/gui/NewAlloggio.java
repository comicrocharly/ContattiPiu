package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.Controller;
import model.Contatto;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class NewAlloggi.
 */
public class NewAlloggio extends JFrame{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The text field nazione. */
	protected JTextField textFieldNazione;
	
	/** The text field citta. */
	protected JTextField textFieldCitta;
	
	/** The text field via. */
	protected JTextField textFieldVia;
	
	/** The text field cap. */
	protected JTextField textFieldCap;
	
	/** The btn run. */
	protected JButton btnRun;
	
	/** The c. */
	protected Contatto c;
	/**
	 * Instantiates a new new alloggi.
	 *
	 * @param c the c
	 */
	public NewAlloggio(Contatto c) {
		setTitle("Nuovo Indirizzo");
		setResizable(false);
		
		this.c = c;
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
		
		textFieldNazione = new JTextField();
		textFieldNazione.setToolTipText("");
		textFieldNazione.setBounds(113, 34, 186, 17);
		contentPane.add(textFieldNazione);
		textFieldNazione.setColumns(10);
		
		textFieldCitta = new JTextField();
		textFieldCitta.setBounds(113, 55, 186, 17);
		contentPane.add(textFieldCitta);
		textFieldCitta.setColumns(10);
		
		btnRun = new JButton("Inserisci");
		btnRun.setBounds(92, 143, 86, 19);
		contentPane.add(btnRun);
		
		JLabel lblCap = new JLabel("CAP");
		lblCap.setBounds(26, 81, 58, 11);
		contentPane.add(lblCap);
		
		JLabel lblVia = new JLabel("Via e N.Civico");
		lblVia.setBounds(26, 100, 77, 14);
		contentPane.add(lblVia);
		
		textFieldVia = new JTextField();
		textFieldVia.setColumns(10);
		textFieldVia.setBounds(113, 99, 186, 17);
		contentPane.add(textFieldVia);

		textFieldCap = new JTextField();
		textFieldCap.setColumns(10);
		textFieldCap.setBounds(113, 78, 186, 17);
		contentPane.add(textFieldCap);

		btnRun.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				run();	
			}
		});
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
				Controller.insertAlloggio(data, c);
				ModAlloggio.updateTable();
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

