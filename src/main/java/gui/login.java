package gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLightLaf;

import baseClass.Dipendente;
import database.DBDipendente;
import database.DBManager;

public class login extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DBDipendente dataDip = new DBDipendente();
	
	public login() {
	
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setTitle("LOGIN MARKETBACKSTAGE");
		this.setIconImage(new ImageIcon(getClass().getResource("logo.jpg")).getImage());
		this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
		JPanel panel = new JPanel(new FlowLayout());
		this.setContentPane(panel);
		JTextField codice = new JTextField();
		codice.setPreferredSize(new Dimension(50, 30));
		JLabel label_codice = new JLabel("Inserisci codice Dipendente : ");
		JButton button = new JButton("Entra");
		
		panel.add(label_codice);
		panel.add(codice);
		panel.add(button);
		button.addActionListener(e ->{
			try {
				Integer.parseInt(codice.getText());
				Dipendente dip = dataDip.getDipendenteFromId(Integer.valueOf(codice.getText()));
				if( dip != null) {
					if(dip.getTipo().equals("Commesso") ) {
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

					
					
			}
			catch(NumberFormatException n) {
				JOptionPane.showMessageDialog(null, "Inserire un codice valido");
			}
			
		});
		
	}
	
	public void start() {
		this.pack();
		this.setVisible(true);
	}

	
	
}
