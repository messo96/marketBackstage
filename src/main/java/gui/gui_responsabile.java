package gui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import baseClass.Dipendente;

public class gui_responsabile extends gui_commesso{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public gui_responsabile(Dipendente dip) {
		super(dip);
		this.setTitle("PANNELLO DI CONTROLLO RESPONSABILE " + dip.getNome() + " "+ dip.getCognome() + "  ID: "+ dip.getId());
		JPanel panel = super.getPanel();
		JButton btnTurno = new JButton("Pannello Turni");
		btnTurno.addActionListener(e ->{
			new gui_turno(dip);
		});
		JButton btnMagazzino = new JButton("Magazzino");
		btnMagazzino.addActionListener(e ->{
			new gui_magazzino(dip);
		});
		
		JButton btnClienti = new JButton("Clienti");
		btnClienti.addActionListener(e ->{
			new gui_clienti(dip);
		});
		
		panel.add(btnTurno, BorderLayout.SOUTH);
		panel.add(btnMagazzino, BorderLayout.SOUTH);
		panel.add(btnClienti, BorderLayout.SOUTH);
		
		this.pack();
	}
	
}
