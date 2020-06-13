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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable table;
	
	public gui_quantity() {
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		model = new DefaultTableModel(new Object[] {"Id","Nome Prodotto", "Prezzo","Reparto", "Quantità"},0);
		table = new JTable(model);
		this.setPreferredSize(new Dimension(800,600));
		JLabel label_id = new JLabel("Inserisci ID prodotto: ");
		label_id.setBounds(321, 61, 147, 15);
		JLabel label_q = new JLabel("Inserisci quantità che si vuole aggiungere: ");
		label_q.setBounds(183, 108, 295, 15);
		JTextField text_id = new JTextField();
		text_id.setBounds(532, 54, 64, 30);
		text_id.setPreferredSize(new Dimension(50,30));
		JTextField text_q = new JTextField();
		text_q.setBounds(532, 101, 64, 30);
		text_q.setPreferredSize(new Dimension(50,30));
		refreshTable();
		JButton bt = new JButton("AGGIUNGI");
		bt.setBounds(642, 84, 101, 44);
		bt.addActionListener(e ->{
			new DBProdotto().aggiornaMagazzino(Integer.valueOf(text_id.getText()), Integer.valueOf(text_q.getText()));
			Prodotto p = new DBProdotto().getProductFromId(Integer.valueOf(text_id.getText()));
			JOptionPane.showMessageDialog(null, "Prodotto: "+p.getNome() + "\nQuantità: " + p.getQuantity());
			model.getDataVector().removeAllElements();
			revalidate();
			refreshTable();

		});
		panel.setLayout(null);
		panel.add(bt);
		panel.add(label_id);
		panel.add(text_id);
		panel.add(label_q);
		panel.add(text_q);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(12, 148, 766, 422);
		panel.add(scrollPane);
		
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
