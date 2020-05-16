package gui;

import javax.swing.JButton;
import javax.swing.JPanel;

import baseClass.Dipendente;

public class gui_responsabile extends gui_commesso{

	 public gui_responsabile(Dipendente dip) {
		super(dip);
		this.setTitle("PANNELLO DI CONTROLLO RESPONSABILE");
		JPanel panel = super.getPanel();
		JButton btnTurno = new JButton("Pannello Turni");
		btnTurno.addActionListener(e ->{
			new gui_turno();
		});
		
		panel.add(btnTurno);
		this.pack();
	}
	
}
