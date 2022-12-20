package gui;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.*;
import javax.swing.JOptionPane;

// TODO: Auto-generated Javadoc
/**
 * The Class ModAttributes.
 */
public class ModAttributes extends JFrame {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The c. */
	protected static Contatto c;
	
	/** The content pane. */
	protected JPanel contentPane;
	
	/**
	 * Instantiates a new mod attributes.
	 *
	 * @param c the c
	 */
	protected ModAttributes(Contatto c) {
		
		try {
			setC(c);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			JOptionPane.showMessageDialog(contentPane, "Contatto nullo");
			this.setVisible(false);
		}
		
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 364, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		

	}
	
	/**
	 * Sets the c.
	 *
	 * @param c the new c
	 */
	protected void setC(Contatto c) {
		ModAttributes.c = c;
	}
	
}
