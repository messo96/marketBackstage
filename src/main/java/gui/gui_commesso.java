package gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import baseClass.Dipendente;
import database.DBCassa;


public class gui_commesso extends JFrame {
	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	private final int N_CASSE=6;
	private DBCassa cassa = new DBCassa();
	private Map<Integer, JButton> allCasse = new HashMap<>();
	private JPanel panel;
	
	
	public gui_commesso(final Dipendente dip) {
		this.setTitle("PANNELLO DI CONTROLLO COMMESSO " + dip.getNome() + " "+ dip.getCognome() + " ID: "+ dip.getId());
		this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2, 
												(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
		panel = new JPanel();
		this.setContentPane(panel);
		for(int i=0;i<N_CASSE;i++) {
			JButton bt = new JButton(String.valueOf(i+1));
			
			bt.addActionListener(e ->{
				JButton b = (JButton)e.getSource();
				System.out.println(b.getText());
				if(cassa.getisOccupata(Integer.valueOf(b.getText())))
					JOptionPane.showMessageDialog(null, "Questa cassa è al momento occupata");
				else {
					cassa.occupaCassa(Integer.valueOf(bt.getText()));
					new gui_cassa(dip, Integer.valueOf(b.getText()));
				}
			});
			panel.add(new JLabel("CASSA: "));
			panel.add(bt,BorderLayout.EAST);
			allCasse.put(i, bt);
			
		}
		
		this.pack();
		this.setVisible(true);
	}
	
	
	public JPanel getPanel() {
		return this.panel;
	}
	
}
