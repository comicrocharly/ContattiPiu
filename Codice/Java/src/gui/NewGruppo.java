package gui;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import controller.Controller;
import model.Contatto;
import model.Gruppo;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// TODO: Auto-generated Javadoc
/**
 * The Class NewGruppo.
 */
public class NewGruppo extends JFrame{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The text field name. */
	protected JTextField textFieldName;
	
	/** The text field descrizione. */
	protected JTextField textFieldDescrizione;
	
	/** The text field cerca. */
	protected JTextField textFieldCerca;
	
	/** The list gruppi model. */
	DefaultListModel<String> listGruppiModel;
	
	protected JButton btnRun;
	
	/** The g list. */
	protected ArrayList<Gruppo> gList;

	protected JList<String> listGruppi;

	protected Contatto c;
	
	/**
	 * Instantiates a new new gruppo.
	 *
	 * @param c the c
	 */
	public NewGruppo(Contatto c) {
		
		this.c = c;
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 265, 223);
		
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listGruppiModel = new DefaultListModel<String>();
		loadGruppiModel();
		
		listGruppi = new JList<String>(listGruppiModel);
		JScrollPane scrollPaneGruppi = new JScrollPane(listGruppi);
		scrollPaneGruppi.setBounds(120, 17, 114, 51);
		contentPane.add(scrollPaneGruppi,listGruppi);
		
		JLabel lblNomeGruppo = new JLabel("Nome Gruppo");
		lblNomeGruppo.setBounds(20, 92, 65, 11);
		contentPane.add(lblNomeGruppo);
		
		textFieldName = new JTextField();
		textFieldName.setBounds(120, 89, 114, 17);
		contentPane.add(textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblDescrizioneG = new JLabel("Descrizione");
		lblDescrizioneG.setBounds(20, 111, 76, 11);
		contentPane.add(lblDescrizioneG);

		textFieldDescrizione = new JTextField();
		textFieldDescrizione.setBounds(120, 108, 114, 17);
		contentPane.add(textFieldDescrizione);
		textFieldDescrizione.setColumns(10);

		textFieldCerca = new JTextField();
		textFieldCerca.setBounds(20, 22, 86, 19);
		contentPane.add(textFieldCerca);
		textFieldCerca.setColumns(10);

		JButton btnCerca = new JButton("Cerca");
		btnCerca.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				searchGruppo(textFieldCerca.getText());
			}

		});
		btnCerca.setBounds(20, 47, 86, 19);
		contentPane.add(btnCerca);

		btnRun = new JButton("Inserisci");
		btnRun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				run();
			}
		});
		btnRun.setBounds(85, 157, 86, 19);
		contentPane.add(btnRun);
	}
	
	protected void run() {
		Exception ex = null;
		
		if(textFieldName.getText().isBlank() && listGruppi.getSelectedIndex()==-1) {
			JOptionPane.showMessageDialog(null, "Inserisci un nome valido!","Warning",3);
		}
		
		else if(textFieldName.getText().isBlank() && listGruppi.getSelectedIndex()!=-1) {
			Gruppo g = gList.get(listGruppi.getSelectedIndex());
			
			try {
				Controller.insertAggregazione(g,c);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, "Sei già aggregato a questo gruppo!\n" + e1.getMessage() ,"Warning", 3);
				ex=e1;
			}
		}

		else {
			String data[] = {textFieldName.getText().trim(),textFieldDescrizione.getText().trim()};
			try {
				Controller.insertGruppo(data, c);
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
		dispose();
	}

	/**
	 * Load gruppi model.
	 */
	private void loadGruppiModel() {
		ArrayList<Gruppo> gList = Controller.caricaGruppi();
		setgList(gList);
		
		for(Gruppo g:gList) {
			listGruppiModel.addElement(g.getNomeG() +" "+ g.getDescrizione());
		}
		
	}
	
	/**
	 * Search gruppo.
	 *
	 * @param textFieldCerca the text field cerca
	 */
	private void searchGruppo(String textFieldCerca) {
		ArrayList<Gruppo> searched = null;
		
		
		for(Gruppo g: getgList()){
			if(g.getNomeG().contains(textFieldCerca.trim())){
				if(searched==null) {
					searched = new ArrayList<>();
				}
				searched.add(g);
			}
		}
		
		if (searched !=null)
			loadGruppiModel(searched);
		else
			listGruppiModel.removeAllElements();
		
	}
	
	/**
	 * Load gruppi model.
	 *
	 * @param searched the searched
	 */
	private void loadGruppiModel(ArrayList<Gruppo> searched) {
		listGruppiModel.removeAllElements();
		for(Gruppo g: searched) {
			listGruppiModel.addElement(g.getNomeG() +" "+ g.getDescrizione());
		}
		
	}

	/**
	 * Gets the g list.
	 *
	 * @return the g list
	 */
	public ArrayList<Gruppo> getgList() {
		return gList;
	}

	/**
	 * Sets the g list.
	 *
	 * @param gList the new g list
	 */
	public void setgList(ArrayList<Gruppo> gList) {
		this.gList = gList;
	}

}
