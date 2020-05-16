package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import baseClass.Dipendente;
import database.DBDipendente;
import database.DBManager;

public class login extends JFrame{
	private DBDipendente dataDip = new DBDipendente();
	
	public login() {
		this.setTitle("LOGIN MARKETBACKSTAGE");
		this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
		JPanel panel = new JPanel(new FlowLayout());
		this.setContentPane(panel);
		JTextField codice = new JTextField();
		codice.setPreferredSize(new Dimension(50, 20));
		JLabel label_codice = new JLabel("Inserisci codice Dipendente : ");
		JButton button = new JButton("Entra");
		
		panel.add(label_codice);
		panel.add(codice);
		panel.add(button);
		button.addActionListener(e ->{
			Dipendente dip = dataDip.getDipendenteFromId(Integer.valueOf(codice.getText()));
				if( dip != null) {
					if(dip.getTipo() == "Commesso") {
						JOptionPane.showMessageDialog(null, "Benvenuto " + dip.getNome() + "\nTipo : " + dip.getTipo());
						new gui_commesso(dip);
					}
					else {
					JOptionPane.showMessageDialog(null, "Benvenuto " + dip.getNome() + "\nTipo : " + dip.getTipo());
					 new gui_responsabile(dip);
					}
					this.dispose();
				}
				else
					JOptionPane.showMessageDialog(null, "Codice errato, riprovare!");

					
					
		});
		
	}
	
	public void start() {
		this.pack();
		this.setVisible(true);
	}

	
	
}
