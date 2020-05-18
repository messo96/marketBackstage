package gui;

import java.awt.Dimension;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import baseClass.Prodotto;
import database.DBProdotto;

public class gui_quantity extends JFrame{

	private DefaultTableModel model;
	private JTable table;
	
	public gui_quantity() {
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		model = new DefaultTableModel(new Object[] {"Id","Nome Prodotto", "Prezzo","Reparto", "Quantità"},0);
		table = new JTable(model);
		
		JLabel label_id = new JLabel("Inserisci ID prodotto: ");
		JLabel label_q = new JLabel("Inserisci quantità che si vuole aggiungere: ");
		JTextField text_id = new JTextField();
		text_id.setPreferredSize(new Dimension(50,30));
		JTextField text_q = new JTextField();
		text_q.setPreferredSize(new Dimension(50,30));
		refreshTable();
		JButton bt = new JButton("AGGIUNGI");
		bt.addActionListener(e ->{
			new DBProdotto().aggiungiQuantità(Integer.valueOf(text_id.getText()), Integer.valueOf(text_q.getText()));
			Prodotto p = new DBProdotto().getProductFromId(Integer.valueOf(text_id.getText()));
			JOptionPane.showMessageDialog(null, "Prodotto: "+p.getNome() + "\nQuantità: " + p.getQuantity());
			model.getDataVector().removeAllElements();
			revalidate();
			refreshTable();

		});
		panel.add(bt);
		panel.add(label_id);
		panel.add(text_id);
		panel.add(label_q);
		panel.add(text_q);
		panel.add(new JScrollPane(table));
		
		this.pack();
		this.setVisible(true);
		
	}
	
	private void refreshTable() {
		List<Prodotto>  list= new DBProdotto().getAllProducts();
		for(Prodotto p : list) {
			model.addRow(new Object[] {p.getIdProdotto(),p.getNome(),p.getPrezzo()+ " €",p.getReparto(), p.getQuantity()});
		}
	}
}
