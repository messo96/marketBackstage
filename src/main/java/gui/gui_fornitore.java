package gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import baseClass.Fornitore;
import database.DBFornitore;

public class gui_fornitore extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Integer NUM_PIVA = 11;
	
	public gui_fornitore() {
		this.setTitle("NUOVO FORNITORE");
		this.setPreferredSize(new Dimension(800,600));
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		
		JLabel label_PIVA = new JLabel("P.Iva*: ");
		label_PIVA.setBounds(29, 40, 49, 15);
		JTextField text_PIVA = new JTextField();
		text_PIVA.setBounds(113, 33, 202, 30);
		text_PIVA.setPreferredSize(new Dimension(200,30));
		
		
		JLabel label_nomeAzienda = new JLabel("Nome Azienda*:");
		label_nomeAzienda.setBounds(367, 101, 108, 15);
		JTextField text_nome = new JTextField();
		text_nome.setBounds(499, 94, 200, 30);
		text_nome.setPreferredSize(new Dimension(200,30));
		
		JLabel label_indirizzo = new JLabel("Indirizzo*:");
		label_indirizzo.setBounds(29, 181, 69, 15);
		JTextField text_indirizzo = new JTextField();
		text_indirizzo.setBounds(113, 174, 202, 30);
		text_indirizzo.setPreferredSize(new Dimension(200,30));
		
		JLabel label_citta = new JLabel("Città*:");
		label_citta.setBounds(405, 181, 44, 15);
		JTextField text_citta = new JTextField();
		text_citta.setBounds(499, 166, 200, 30);
		text_citta.setPreferredSize(new Dimension(200,30));
		
		JLabel label_nazione = new JLabel("Nazione*:");
		label_nazione.setBounds(29, 254, 65, 15);
		
		
		JLabel label_telefono = new JLabel("Telefono*:");
		label_telefono.setBounds(29, 344, 69, 15);
		JTextField text_telefono = new JTextField();
		text_telefono.setBounds(113, 339, 202, 26);

		JTextField text_nazione = new JTextField();
		text_nazione.setBounds(113, 247, 202, 30);
		
		JLabel label_fax = new JLabel("Fax:");
		label_fax.setBounds(405, 344, 29, 15);
		JTextField text_fax = new JTextField();
		text_fax.setBounds(499, 337, 200, 30);
		text_fax.setPreferredSize(new Dimension(200,30));
		
		JLabel label_email = new JLabel("Email*:");
		label_email.setBounds(29, 101, 48, 15);
		JTextField text_email = new JTextField();
		text_email.setBounds(113, 94, 200, 30);
		text_email.setPreferredSize(new Dimension(200,30));
		
		JLabel label_sito = new JLabel("Sito Web: ");
		label_sito.setBounds(405, 254, 70, 15);
		JTextField text_sito = new JTextField();
		text_sito.setBounds(499, 254, 200, 30);
		text_PIVA.setPreferredSize(new Dimension(200,30));
		
		JButton btn_inserisci = new JButton("INSERISCI");
		btn_inserisci.setBounds(293, 445, 148, 30);
		btn_inserisci.addActionListener(e ->{
			if(text_PIVA.getText().length() != NUM_PIVA || text_nome.getText().isEmpty() || text_indirizzo.getText().isEmpty() ||
								text_citta.getText().isEmpty() || text_nazione.getText().isEmpty() || text_telefono.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Compilare i campi obbligatori");
			else 
			new DBFornitore().addFornitore(new Fornitore(text_PIVA.getText(), text_nome.getText(), text_indirizzo.getText(), text_citta.getText(),
						text_nazione.getText(), text_telefono.getText(), text_fax.getText(), text_sito.getText(), text_email.getText()));
						
		});
		panel.setLayout(null);
		
		
		panel.add(label_PIVA);
		panel.add(text_PIVA);
		panel.add(label_nomeAzienda);
		panel.add(text_nome);
		panel.add(label_indirizzo);
		panel.add(text_indirizzo);
		panel.add(label_citta);
		panel.add(text_citta);
		panel.add(label_nazione);
		panel.add(label_telefono);
		panel.add(text_telefono);
		panel.add(label_fax);
		panel.add(text_fax);
		panel.add(label_email);
		panel.add(text_email);
		panel.add(label_sito);
		panel.add(text_sito);
		panel.add(btn_inserisci);
		
		
		panel.add(text_nazione);
		
		this.pack();
		this.setVisible(true);
		
	}
}
