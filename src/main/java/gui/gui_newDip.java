package gui;

import java.awt.Dimension;

import javax.swing.*;

import baseClass.Dipendente;
import database.DBDipendente;

public class gui_newDip extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Integer NUM_CF = 16;
	
	public gui_newDip() {
		this.setTitle("NUOVO DIPENDENTE");
		JPanel panel = new JPanel();
		this.setPreferredSize(new Dimension(800,250));
		this.setContentPane(panel);
		JLabel label_nome = new JLabel("Nome:* ");
		label_nome.setBounds(93, 89, 54, 25);
		JTextField text_nome = new JTextField();
		text_nome.setBounds(165, 89, 150, 25);
		text_nome.setPreferredSize(new Dimension(50,30));
		JLabel label_cognome = new JLabel("Cognome:* ");
		label_cognome.setBounds(339, 89, 79, 15);
		JTextField text_cognome = new JTextField();
		text_cognome.setBounds(436, 87, 150, 25);
		JLabel label_tipo = new JLabel("Tipo:* ");
		label_tipo.setBounds(94, 155, 54, 25);
		text_cognome.setPreferredSize(new Dimension(50,30));
		JLabel label_cf = new JLabel("Codice Fiscale:* ");
		label_cf.setBounds(40, 41, 132, 15);
		JTextField text_cf = new JTextField();
		text_cf.setPreferredSize(new Dimension(50,30));
		text_cf.setBounds(165, 36, 150, 25);
		JComboBox<String> box = new JComboBox<>();
		box.setBounds(183, 155, 132, 24);
		box.addItem("Commesso");
		box.addItem("Responsabile");
		JLabel label_telefono = new JLabel("Telefono:* ");
		label_telefono.setBounds(345, 160, 73, 15);
		JTextField text_telefono = new JTextField();
		text_telefono.setBounds(436, 155, 150, 25);
		JButton btn = new JButton("Inserisci");
		btn.setBounds(660, 155, 90, 25);
		
		btn.addActionListener(e->{
			if(text_nome.getText().isEmpty() || text_cognome.getText().isEmpty() || text_cf.getText().length() != NUM_CF || text_telefono.getText().isEmpty())
				JOptionPane.showMessageDialog(null, "Compilare tutti i campi obbligatori");
			else
				new DBDipendente().addNuovoDipendente(new Dipendente(null,text_nome.getText(), text_cognome.getText(),String.valueOf(box.getSelectedItem())
																	, text_cf.getText(), text_telefono.getText()));
			this.dispose();
		});
		panel.setLayout(null);
		
		
		panel.add(label_cf);
		panel.add(text_cf);
		panel.add(label_nome);
		panel.add(text_nome);
		panel.add(label_cognome);
		panel.add(text_cognome);
		panel.add(label_tipo);
		panel.add(box);
		panel.add(label_telefono);
		panel.add(text_telefono);
		panel.add(btn);
		
		this.pack();
		this.setVisible(true);
	}

}
