package gui;

import java.awt.Dimension;

import javax.swing.*;

import database.DBDipendente;

public class gui_newDip extends JFrame{
	
	public gui_newDip() {
		this.setTitle("NUOVO DIPENDENTE");
		JPanel panel = new JPanel();
		this.setPreferredSize(new Dimension(800,600));
		this.setContentPane(panel);
		JLabel label_nome = new JLabel("Nome:* ");
		label_nome.setBounds(40, 89, 54, 15);
		JTextField text_nome = new JTextField();
		text_nome.setBounds(112, 87, 149, 19);
		text_nome.setPreferredSize(new Dimension(50,30));
		JLabel label_cognome = new JLabel("Cognome:* ");
		label_cognome.setBounds(339, 89, 79, 15);
		JTextField text_cognome = new JTextField();
		text_cognome.setBounds(436, 87, 145, 19);
		text_cognome.setPreferredSize(new Dimension(50,30));
		JLabel label_cf = new JLabel("Codice Fiscale:* ");
		label_cf.setBounds(40, 38, 132, 15);
		JTextField text_cf = new JTextField();
		text_cf.setPreferredSize(new Dimension(50,30));
		text_cf.setBounds(174, 36, 159, 19);
		JComboBox<String> box = new JComboBox<>();
		box.setBounds(635, 84, 98, 24);
		box.addItem("Commesso");
		box.addItem("Responsabile");
		JLabel label_telefono = new JLabel("Telefono:* ");
		label_telefono.setBounds(56, 160, 73, 15);
		JTextField text_telefono = new JTextField();
		text_telefono.setBounds(147, 158, 139, 19);
		JButton btn = new JButton("Inserisci");
		btn.setBounds(378, 155, 90, 25);
		
		btn.addActionListener(e->{
			if(text_nome.getText().isEmpty())
				;
			else
				new DBDipendente().addNuovoDipendente(text_nome.getText(), text_cognome.getText()
																	, text_cf.getText(), String.valueOf(box.getSelectedItem()), text_telefono.getText());
		});
		panel.setLayout(null);
		
		
		panel.add(label_cf);
		panel.add(text_cf);
		panel.add(label_nome);
		panel.add(text_nome);
		panel.add(label_cognome);
		panel.add(text_cognome);
		panel.add(box);
		panel.add(label_telefono);
		panel.add(text_telefono);
		panel.add(btn);
		
		this.pack();
		this.setVisible(true);
	}

}
