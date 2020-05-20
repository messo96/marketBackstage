package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import baseClass.Dipendente;
import baseClass.Turno;
import database.DBScontrino;
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
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

	
	public gui_turno(Dipendente dipendente) {
		this.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), 
				(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()));
		this.setTitle("GESTIONE TURNI " + dipendente.getNome() + " "+ dipendente.getCognome() + "ID: "+ dipendente.getId());
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		text = new JTextField();
		text.setBounds(507, 92, 95, 30);
		text.setPreferredSize(new Dimension(50, 30));
		JLabel label_text = new JLabel("Inserire id Dipendente da cercare: ");
		label_text.setBounds(497, 62, 246, 18);
		JButton btnSearch = new JButton("Cerca");
		btnSearch.setBounds(614, 88, 120, 38);
		JLabel label_nuovo = new JLabel("Inserisci nuovo Turno:\n");
		label_nuovo.setBounds(213, 445, 151, 18);
		JDateChooser date_choose = new JDateChooser();
		date_choose.setBounds(126, 487, 208, 40);
		JLabel label_data = new JLabel("Data");
		label_data.setBounds(33, 498, 33, 18);
		JTextField text_oraInizio = new JTextField();
		text_oraInizio.setBounds(137, 557, 83, 30);
		text_oraInizio.setPreferredSize(new Dimension(50, 30));
		JLabel label_oraInizio = new JLabel("Ora inizio");
		label_oraInizio.setBounds(33, 563, 66, 18);
		JTextField text_oraFine = new JTextField();
		text_oraFine.setBounds(381, 557, 83, 30);
		text_oraFine.setPreferredSize(new Dimension(50, 30));
		JLabel label_oraFine = new JLabel("Ora fine");
		label_oraFine.setBounds(278, 563, 56, 18);
		JTextField text_idDip = new JTextField();
		text_idDip.setBounds(229, 639, 105, 30);
		text_idDip.setPreferredSize(new Dimension(50, 30));
		JLabel label_idDip = new JLabel("id dipendente");
		label_idDip.setBounds(101, 645, 95, 18);
		JButton btnInserisci = new JButton("Inserisci");
		btnInserisci.setBounds(363, 627, 200, 55);
		JLabel label_ricerca = new JLabel("Ricerca dipendente da scontrino");
		label_ricerca.setBounds(908, 445, 246, 18);
		JLabel label_dataEmissione = new JLabel("Data emissione:");
		label_dataEmissione.setBounds(857, 498, 265, 38);
		JDateChooser date_choose_scontrino = new JDateChooser();
		date_choose_scontrino.setBounds(1045, 498, 265, 38);
		JLabel label_ora = new JLabel("Ora emissione: ");
		label_ora.setBounds(772, 596, 246, 18);
		
		JDateChooser date_search = new JDateChooser();
		date_search.setBounds(497, 216, 232, 38);
		JLabel label_search = new JLabel("Cerca turni per data: ");
		label_search.setBounds(487, 186, 246, 18);
		JButton btn_searchData = new JButton("Cerca per Data");
		btn_searchData.setBounds(507, 271, 189, 38);
		JButton btn_newDip = new JButton("Nuovo Dipendente");
		btn_newDip.setBounds(1155, 314, 189, 55);
		JTextField text_ora = new JTextField();
		text_ora.setBounds(903, 594, 140, 22);
		JLabel label_idCassa = new JLabel("Id cassa: ");
		label_idCassa.setBounds(1098, 598, 246, 18);
		JTextField text_idCassa = new JTextField();
		text_idCassa.setBounds(1204, 594, 140, 25);
		JButton btnCercaDipendente = new JButton("Cerca");
		btnCercaDipendente.setBounds(1127, 644, 217, 55);
//		JButton btn_refresh = new JButton();
//		btn_refresh.setSelectedIcon(new ImageIcon(gui_turno.class.getResource("/gui/icon_refresh.jpg")));
//		btn_refresh.setIcon(new ImageIcon(gui_turno.class.getResource("/gui/icon_refresh.jpg")));
//		 btn_refresh.setBounds(696, 0, 33, 30);
//		btn_refresh.addActionListener(e->{
//			refreshTableDip();
//		});
		
		btn_newDip.addActionListener(e->{
			new gui_newDip();
		});
		
		btnCercaDipendente.addActionListener(e ->{
			Dipendente dip = new DBScontrino().ricercaDipDaScontrino(date_choose_scontrino.getDate(), text_ora.getText(), Integer.valueOf(text_idCassa.getText()));
			if(dip != null)
				JOptionPane.showMessageDialog(null,"Id: "+dip.getId() + "\nNome: "+ dip.getNome() +" \tCognome: "+ dip.getCognome() + "\nTipo:" + dip.getTipo());
			else
				JOptionPane.showMessageDialog(null,"Nessun dipendente trovato");

		});
		
		
		btnInserisci.addActionListener(e ->{
			if(text_oraInizio.getText() != "" && text_oraFine.getText() != "" && text_idDip.getText() != "")
			dbturno.InserisciTurno(sdf.format(date_choose.getDate()), text_oraInizio.getText(),
															text_oraFine.getText(), Integer.valueOf(text_idDip.getText()));
			refreshTable();
		});
		

		model = new DefaultTableModel(new String[] {"ID Dipendente", "data", "Ora Inizio", "Ora Fine"},0);
		model_dip = new DefaultTableModel(new String[] {"ID Dipendente", "nome", "cognome", "tipo", "telefono"},0);
		refreshTableDip();
		refreshTable();
		
		btnSearch.addActionListener(e ->{
			refreshTable();
		});
		
		btn_searchData.addActionListener(e->{
			refreshTable(date_search.getDate());
		});
		
		table = new JTable();
		table.setModel(model);
		table.setFocusable(true);
		table.setEditingColumn(1);
		panel.setLayout(null);
		table_dip = new JTable();
		table_dip.setModel(model_dip);		
		table_dip.setFocusable(false);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(23, 5, 452, 428);		
		panel.add(scrollPane);
		JScrollPane scrollPane_dip = new JScrollPane(table_dip);
		panel.add(scrollPane_dip);
		scrollPane_dip.setBounds(741, 5, 603, 297);		

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
		panel.add(label_ricerca);
		panel.add(label_dataEmissione);
		panel.add(date_choose_scontrino);
		panel.add(label_ora);
		panel.add(text_ora);
		panel.add(label_idCassa);
		panel.add(text_idCassa);
		panel.add(btnCercaDipendente);
		panel.add(label_search);
		panel.add(date_search);
		panel.add(btn_searchData);
		panel.add(btn_newDip);
//		panel.add(btn_refresh);
		
		this.pack();
		this.setVisible(true);
	}

	private void refreshTable(Date data) {
		List<Turno> list = dbturno.allTurni(data);
		model.getDataVector().removeAllElements();
		revalidate();
		
		if(!list.isEmpty()) {
			Iterator<Turno> ite = list.iterator();
			while(ite.hasNext()) {
				Turno t = ite.next();
				model.addRow(new Object [] {t.getIdDipendente(), sdf_normal.format(t.getData()), t.getOraInizio(), t.getOraFine()});
			}
		}
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
				model.addRow(new Object [] {t.getIdDipendente(), sdf_normal.format(t.getData()), timeFormat.format(t.getOraInizio()), timeFormat.format(t.getOraFine())});
			}
				
		}
	}
	
	private void refreshTableDip() {
		List<Dipendente> list = dbturno.allDipendenti();
		model_dip.getDataVector().removeAllElements();
		revalidate();	
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
