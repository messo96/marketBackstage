package gui;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import baseClass.Fornitore;
import baseClass.Prodotto;
import database.DBFornitore;
import database.DBProdotto;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

public class gui_prodotto extends JFrame{
	private JTable table_fornitore;
	private DefaultTableModel model_fornitore;
	private DBFornitore dbfornitore = new DBFornitore();
	private JComboBox<String> box_reparto;
	
	public gui_prodotto() {
		this.setTitle("AGGIUNGI PRODOTTO");
		this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		
		JLabel label_nome = new JLabel("Nome:");
		label_nome.setBounds(12, 57, 88, 15);
		JTextField text_nome = new JTextField();
		text_nome.setBounds(96, 49, 200, 30);
		text_nome.setPreferredSize(new Dimension(200,30));
		
		JLabel label_descrizione = new JLabel("Descrizione:");
		label_descrizione.setBounds(588, 23, 247, 15);
		JTextArea text_descrizione = new JTextArea();
		text_descrizione.setBounds(753, 5, 340, 146);
		text_descrizione.setPreferredSize(new Dimension(200,200));
		
		JLabel label_reparto = new JLabel("Reparto:");
		label_reparto.setBounds(334, 250, 83, 15);
		JLabel label_euro = new JLabel(" €");
		label_euro.setBounds(1016, 250, 83, 15);
		
		box_reparto = new JComboBox<>();
		fillTheBox();
		box_reparto.setPreferredSize(new Dimension(200,30));
		box_reparto.setBounds(428, 242, 209, 30);

		
		JLabel label_prezzo = new JLabel("Prezzo:");
		label_prezzo.setBounds(748, 250, 50, 15);
		JTextField text_prezzo = new JTextField();
		text_prezzo.setBounds(821, 242, 194, 30);
		text_prezzo.setPreferredSize(new Dimension(200,30));
		
		JLabel label_quantity = new JLabel("Quantità:");
		label_quantity.setBounds(12, 122, 130, 15);
		JTextField text_quantity = new JTextField();
		text_quantity.setBounds(96, 114, 200, 30);
		text_quantity.setPreferredSize(new Dimension(200,30));
		
		JLabel label_PIVA = new JLabel("P.Iva Fornitore: ");
		label_PIVA.setBounds(359, 122, 110, 15);
		JTextField text_PIVA = new JTextField();
		text_PIVA.setBounds(481, 114, 234, 30);
		text_PIVA.setPreferredSize(new Dimension(200,30));
		
		 model_fornitore = new DefaultTableModel(new Object[] {"P.Iva", "Nome" , "Via" , "Città", "Nazione", "telefono", "fax","email","sito Web"},0);
		fillTableFornitori();
		table_fornitore = new JTable();
		table_fornitore.setModel(model_fornitore);
		JButton btn_aggiungi = new JButton("AGGIUNGI PRODOTTO");
		btn_aggiungi.setBounds(1054, 227, 185, 60);
		
		btn_aggiungi.addActionListener(e ->{
			new DBProdotto().addProdotto(text_nome.getText(), text_descrizione.getText(),String.valueOf(box_reparto.getSelectedItem()),
							Double.valueOf(text_prezzo.getText()), Integer.valueOf(text_quantity.getText()), text_PIVA.getText());
		});
		
		JButton btn_fornitore = new JButton("Aggiungi fornitore");
		btn_fornitore.setBounds(22, 280, 163, 60);
		btn_fornitore.addActionListener(e ->{
			new gui_fornitore();
			fillTableFornitori();
		});
		panel.setLayout(null);
		
		panel.add(label_nome);
		panel.add(text_nome);
		panel.add(label_descrizione);
		panel.add(text_descrizione);
		panel.add(label_prezzo);
		panel.add(text_prezzo);
		panel.add(label_quantity);
		panel.add(text_quantity);
		panel.add(label_PIVA);
		panel.add(text_PIVA);
		panel.add(btn_aggiungi);
		JScrollPane scrollPane = new JScrollPane(table_fornitore);
		scrollPane.setBounds(28, 352, 914, 359);
		panel.add(scrollPane);
		panel.add(btn_fornitore);
		panel.add(box_reparto);
		panel.add(label_reparto);
		panel.add(label_euro);
		this.pack();
		this.setVisible(true);
	}
	
	private void fillTheBox() {
		List<String> reparti = new DBProdotto().getAllReparti();
		for(String r : reparti) {
			box_reparto.addItem(r);
		}
		
	}

	private void fillTableFornitori(){
		List<Fornitore> list = dbfornitore.getAllFornitori();
		model_fornitore.getDataVector().removeAllElements();
		revalidate();
		
		for(Fornitore f : list) {
			model_fornitore.addRow(new Object[] {f.getP_IVA(), f.getNome(), f.getIndirizzo(), f.getCittà(), f.getNazione(), 
						f.getTelefono(), f.getFax(), f.getEmail(), f.getSitoWeb()});
		}
	}

}
