package gui;

import java.awt.Dimension;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import baseClass.Turno;
import database.DBTurno;

public class gui_turno extends JFrame{
	private DBTurno dbturno = new DBTurno();
	private DefaultTableModel model;
	private JTable table;
	private JTextField text ;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	
	public gui_turno() {
		this.setPreferredSize(new Dimension(800,600));
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		text = new JTextField();
		text.setPreferredSize(new Dimension(50, 30));
		JLabel label_text = new JLabel("Inserire id Dipendente da cercare: ");
		JButton btnSearch = new JButton("Cerca");
		JLabel label_nuovo = new JLabel("Inserisci nuovo Turno:\n");
		JTextField text_data = new JTextField();
		text_data.setPreferredSize(new Dimension(50, 30));
		JLabel label_data = new JLabel("Data");
		JTextField text_oraInizio = new JTextField();
		text_oraInizio.setPreferredSize(new Dimension(50, 30));
		JLabel label_oraInizio = new JLabel("Ora inizio");
		JTextField text_oraFine = new JTextField();
		text_oraFine.setPreferredSize(new Dimension(50, 30));
		JLabel label_oraFine = new JLabel("Ora fine");
		JTextField text_idDip = new JTextField();
		text_idDip.setPreferredSize(new Dimension(50, 30));
		JLabel label_idDip = new JLabel("id dipendente");
		JButton btnInserisci = new JButton("Inserisci");
		
		btnInserisci.addActionListener(e ->{
			if(text_data.getText() != "" && text_oraInizio.getText() != "" && text_oraFine.getText() != "" 
																							&& text_idDip.getText() != "")
			dbturno.InserisciTurno(java.sql.Date.valueOf(text_data.getText()), text_oraInizio.getText(),
															text_oraFine.getText(), Integer.valueOf(text_idDip.getText()));
			refreshTable();
		});
		
		model = new DefaultTableModel();
		model.addColumn("ID Dipendente");
		model.addColumn("data");
		model.addColumn("Ora Inizio");
		model.addColumn("Ora Fine");
		refreshTable();
		btnSearch.addActionListener(e ->{
			refreshTable();
		});
		table = new JTable(model);
		table.getTableHeader();
		
		// mettere nomi colonne jtable
		// scontrino se nessun prodotto errore
		// titoli
		// cassa occupata
		
		panel.add(table);
		panel.add(label_text);
		panel.add(text);
		panel.add(btnSearch);
		panel.add(label_nuovo);
		panel.add(label_data);
		panel.add(text_data);
		panel.add(label_oraInizio);
		panel.add(text_oraInizio);
		panel.add(label_oraFine);
		panel.add(text_oraFine);
		panel.add(label_idDip);
		panel.add(text_idDip);
		panel.add(btnInserisci);
		
		
		this.pack();
		this.setVisible(true);
	}

	private void refreshTable() {
		List<Turno> list;
		model.getDataVector().removeAllElements();
		revalidate();
		if(text.getText().equals("")) 
			list = dbturno.allTurni();
		else
			list = dbturno.allTurnoFromIdDipendente(Integer.valueOf(text.getText()));
		
		if(!list.isEmpty()) {
			Iterator<Turno> ite = list.iterator();
			while(ite.hasNext()) {
				Turno t = ite.next();
				model.addRow(new Object [] {t.getIdDipendente(), sdf.format(t.getData()), t.getOraInizio(), t.getOraFine()});
				
			}
				
		}
	}
}
