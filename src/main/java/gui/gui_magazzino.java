package gui;


import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import baseClass.Dipendente;
import baseClass.Fornitore;
import baseClass.Prodotto;
import database.DBFornitore;
import database.DBProdotto;

public class gui_magazzino extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Integer NUM_LESS = 50;
	private DefaultTableModel model;
	private JTable table;
	private DefaultTableModel model_less;
	private JTable table_less;

	private DBProdotto dbprodotto;
	
	public gui_magazzino(Dipendente dip) {
		this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setTitle("MAGAZZINO" + dip.getNome() + " "+ dip.getCognome() + " ID: "+ dip.getId());
		dbprodotto = new DBProdotto();
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		model = new DefaultTableModel(new Object[] {"Id","Nome Prodotto", "Prezzo","Reparto", "Quantità"},0);
		table = new JTable(model);
		
		model_less = new DefaultTableModel(new Object[] {"Id","Nome Prodotto", "Prezzo","Reparto", "Quantità", "PIVA", "nomeAzienda"},0);
		table_less = new JTable(model_less);
		refreshTableLess();
		refreshTable();
		JButton btnAddProdotto = new JButton("AGGIUNGI PRODOTTO");
		btnAddProdotto.setBounds(688, 6, 176, 38);
		btnAddProdotto.addActionListener(e ->{
			new gui_prodotto();
		});
		JButton btn_aggiornaQuantity = new JButton("AGGIUNGI QUANTITA'");
		btn_aggiornaQuantity.setBounds(1117, 202, 227, 49);
		btn_aggiornaQuantity.addActionListener(e ->{
			new gui_quantity();
		});
		panel.setLayout(null);
		JLabel label_termine = new JLabel("Prodotti quasi al termine:");
		label_termine.setBounds(688, 220, 176, 38);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 6, 638, 422);
		panel.add(scrollPane);
		JScrollPane scrollPane_less = new JScrollPane(table_less);
		scrollPane_less.setBounds(688, 261, 656, 422);
		panel.add(scrollPane_less);
		panel.add(btnAddProdotto);
		panel.add(label_termine);
		panel.add(btn_aggiornaQuantity);
		this.pack();
		this.setVisible(true);
	}
	
	private void refreshTableLess() {
		List<Prodotto>  list= dbprodotto.getAllProductsLess(NUM_LESS);
		for(Prodotto p : list) {
			Fornitore f = new DBFornitore().getFornitore(p.getPIVA());
			model.addRow(new Object[] {p.getIdProdotto(),p.getNome(),p.getPrezzo()+ " €",p.getReparto(), p.getQuantity(), f.getP_IVA(), f.getNome()});
		}
	}
		
	
	private void refreshTable() {
		List<Prodotto>  list= dbprodotto.getAllProducts();
		for(Prodotto p : list) {
			model.addRow(new Object[] {p.getIdProdotto(),p.getNome(),p.getPrezzo()+ " €",p.getReparto(), p.getQuantity()});
		}
	}
}
