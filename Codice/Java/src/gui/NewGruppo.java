package gui;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.postgresql.util.PSQLException;

import controller.Controller;
import model.Contatto;
import model.Gruppo;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NewGruppo extends JFrame{
	
	private JTextField textFieldName;
	private JTextField textFieldDescrizione;
	private JTextField textFieldCerca;
	DefaultListModel<String> listGruppiModel;
	ArrayList<Gruppo> gList;
	
	public NewGruppo(Contatto c) {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 265, 223);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		listGruppiModel = new DefaultListModel<String>();
		loadGruppiModel();
		
		JList<String> listGruppi = new JList<String>(listGruppiModel);
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

		JButton btnNewButton = new JButton("Inserisci");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Exception ex = null;
				if(textFieldName.getText().isBlank()&& listGruppi.getSelectedIndex()!=-1) {
					Integer groupID = gList.get(listGruppi.getSelectedIndex()).getGroupID();
					Integer contID = c.getContID();
					try {
						Controller.insertAggregazione(groupID,contID);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Sei già aggregato a questo gruppo!" ,"Warning", 3);
						ex=e1;
					}
				}

				else {
					String data[] = {textFieldName.getText().trim(),textFieldDescrizione.getText().trim()};
					try {
						Controller.insertGruppo(data, c);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Sei già aggregato a questo gruppo!" ,"Warning", 3);
						ex=e1;
					}
				}
				if(!(ex.getCause()==null)) {
					System.out.println("errore");
					ModGruppi.updateTable();
					ContactWindow.refreshGruppiModel();
				}
				setVisible(false);
			}
		});
		btnNewButton.setBounds(85, 157, 86, 19);
		contentPane.add(btnNewButton);
	}

	private void loadGruppiModel() {
		ArrayList<Gruppo> gList = Controller.caricaGruppi();
		setgList(gList);
		
		for(Gruppo g:gList) {
			listGruppiModel.addElement(g.getNomeG() +" "+ g.getDescrizione());
		}
		
	}
	
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
		
		if(searched==null) {
			loadGruppiModel();
		}
		
		else loadGruppiModel(searched);
		
	}
	
	private void loadGruppiModel(ArrayList<Gruppo> searched) {
		listGruppiModel.removeAllElements();
		for(Gruppo g: searched) {
			listGruppiModel.addElement(g.getNomeG() +" "+ g.getDescrizione());
		}
		
	}

	public ArrayList<Gruppo> getgList() {
		return gList;
	}

	public void setgList(ArrayList<Gruppo> gList) {
		this.gList = gList;
	}

}
