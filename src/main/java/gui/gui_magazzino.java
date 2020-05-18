package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import baseClass.Prodotto;
import database.DBProdotto;

public class gui_magazzino extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable table;
	private DBProdotto dbprodotto = new DBProdotto();

	public gui_magazzino() {
		this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setTitle("MAGAZZINO");
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		model = new DefaultTableModel(new Object[] {"Id","Nome Prodotto", "Prezzo","Reparto", "Quantità"},0);
		table = new JTable(model);
		refreshTable();
		JButton btnAddProdotto = new JButton("Aggiungi prodotto");
		btnAddProdotto.addActionListener(e ->{
			new gui_prodotto();
		});
		JButton btn_aggiornaQuantity = new JButton("AGGIUNGI QUANTITA'");
		btn_aggiornaQuantity.addActionListener(e ->{
			new gui_quantity();
		});
		
		panel.add(new JScrollPane(table));
		panel.add(btnAddProdotto);
		panel.add(btn_aggiornaQuantity);
		this.pack();
		this.setVisible(true);
	}
	
	private void refreshTable() {
		List<Prodotto>  list= dbprodotto.getAllProducts();
		for(Prodotto p : list) {
			model.addRow(new Object[] {p.getIdProdotto(),p.getNome(),p.getPrezzo()+ " €",p.getReparto(), p.getQuantity()});
		}
	}
}
