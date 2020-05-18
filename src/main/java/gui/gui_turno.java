package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import baseClass.Dipendente;
import baseClass.Turno;
import database.DBTurno;

public class gui_turno extends JFrame{
	private DBTurno dbturno = new DBTurno();
	private DefaultTableModel model;
	private DefaultTableModel model_dip;
	private JTable table;
	private JTable table_dip;
	private JTextField text ;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat sdf_normal = new SimpleDateFormat("dd-MM-yyyy");

	
	public gui_turno() {
		this.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 
				(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		this.setTitle("GESTIONE TURNI");
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		text = new JTextField();
		text.setBounds(487, 60, 95, 30);
		text.setPreferredSize(new Dimension(50, 30));
		JLabel label_text = new JLabel("Inserire id Dipendente da cercare: ");
		label_text.setBounds(487, 30, 350, 18);
		JButton btnSearch = new JButton("Cerca");
		btnSearch.setBounds(594, 56, 120, 38);
		JLabel label_nuovo = new JLabel("Inserisci nuovo Turno:\n");
		label_nuovo.setBounds(139, 437, 151, 18);
		JDateChooser date_choose = new JDateChooser();
		date_choose.setBounds(130, 498, 100, 40);
		JLabel label_data = new JLabel("Data");
		label_data.setBounds(33, 498, 33, 18);
		JTextField text_oraInizio = new JTextField();
		text_oraInizio.setBounds(139, 541, 83, 30);
		text_oraInizio.setPreferredSize(new Dimension(50, 30));
		JLabel label_oraInizio = new JLabel("Ora inizio");
		label_oraInizio.setBounds(33, 547, 66, 18);
		JTextField text_oraFine = new JTextField();
		text_oraFine.setBounds(363, 541, 83, 30);
		text_oraFine.setPreferredSize(new Dimension(50, 30));
		JLabel label_oraFine = new JLabel("Ora fine");
		label_oraFine.setBounds(278, 547, 56, 18);
		JTextField text_idDip = new JTextField();
		text_idDip.setBounds(165, 628, 50, 30);
		text_idDip.setPreferredSize(new Dimension(50, 30));
		JLabel label_idDip = new JLabel("id dipendente");
		label_idDip.setBounds(33, 634, 95, 18);
		JButton btnInserisci = new JButton("Inserisci");
		btnInserisci.setBounds(363, 627, 200, 55);
		
		btnInserisci.addActionListener(e ->{
			if(text_oraInizio.getText() != "" && text_oraFine.getText() != "" && text_idDip.getText() != "")
			dbturno.InserisciTurno(sdf.format(date_choose.getDate()), text_oraInizio.getText(),
															text_oraFine.getText(), Integer.valueOf(text_idDip.getText()));
			refreshTable();
		});
		

		model = new DefaultTableModel(new String[] {"ID Dipendente", "data", "Ora Inizio", "Ora Fine"},0);
		model_dip = new DefaultTableModel(new String[] {"ID Dipendente", "nome", "cognome", "tipo", "telefono"},0);
		fillTableDip();
		refreshTable();
		
		btnSearch.addActionListener(e ->{
			refreshTable();
		});
		table = new JTable();
		table.setModel(model);
		panel.setLayout(null);
		table_dip = new JTable();
		table_dip.setModel(model_dip);		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(23, 5, 452, 428);		
		panel.add(scrollPane);
		JScrollPane scrollPane_dip = new JScrollPane(table_dip);
		panel.add(scrollPane_dip);
		scrollPane_dip.setBounds(770, 5, 507, 428);		

		panel.add(label_text);
		panel.add(text);
		panel.add(btnSearch);
		panel.add(label_nuovo);
		panel.add(label_data);
		panel.add(date_choose);
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
				model.addRow(new Object [] {t.getIdDipendente(), sdf_normal.format(t.getData()), t.getOraInizio(), t.getOraFine()});
			}
				
		}
	}
	
	private void fillTableDip() {
		List<Dipendente> list = dbturno.allDipendenti();
		if(!list.isEmpty()) {
			Iterator<Dipendente> ite = list.iterator();
			while(ite.hasNext()) {
				Dipendente d = ite.next();
				model_dip.addRow(new Object [] {d.getId(), d.getNome(), d.getCognome(), d.getTipo(), d.getTelefono()});		
				System.out.println(d.getNome());

			}
				
		}
		
	}
}
