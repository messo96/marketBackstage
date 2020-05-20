package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import baseClass.Dipendente;
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

	public gui_magazzino(Dipendente dip) {
		this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setTitle("MAGAZZINO" + dip.getNome() + " "+ dip.getCognome() + " ID: "+ dip.getId());
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		model = new DefaultTableModel(new Object[] {"Id","Nome Prodotto", "Prezzo","Reparto", "Quantità"},0);
		table = new JTable(model);
		refreshTable();
		JButton btnAddProdotto = new JButton("Aggiungi prodotto");
		btnAddProdotto.setBounds(780, 38, 160, 25);
		btnAddProdotto.addActionListener(e ->{
			new gui_prodotto();
		});
		JButton btn_aggiornaQuantity = new JButton("AGGIUNGI QUANTITA'");
		btn_aggiornaQuantity.setBounds(780, 96, 177, 25);
		btn_aggiornaQuantity.addActionListener(e ->{
			new gui_quantity();
		});
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(25, 0, 638, 422);
		panel.add(scrollPane);
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
