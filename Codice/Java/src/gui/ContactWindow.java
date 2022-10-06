package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class ContactWindow extends JFrame {

	private JPanel contentPane;


	public ContactWindow() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 312, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 2, 2);
		contentPane.add(scrollPane);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(39, 56, 40, 11);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(134, 29, 40, 11);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(197, 29, 40, 11);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(134, 56, 40, 11);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(134, 89, 40, 11);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(39, 152, 40, 11);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("New label");
		lblNewLabel_6.setBounds(197, 152, 40, 11);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("New label");
		lblNewLabel_7.setBounds(39, 267, 40, 11);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("New label");
		lblNewLabel_8.setBounds(197, 267, 40, 11);
		contentPane.add(lblNewLabel_8);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(8, 170, 125, 79);
		contentPane.add(textArea);

		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(169, 170, 121, 79);
		contentPane.add(textArea_1);

		JTextArea textArea_2 = new JTextArea();
		textArea_2.setBounds(8, 290, 125, 79);
		contentPane.add(textArea_2);

		JTextArea textArea_3 = new JTextArea();
		textArea_3.setBounds(169, 290, 121, 79);
		contentPane.add(textArea_3);
	}
}
