package gui;

import java.util.List;

import javax.swing.JButton;
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

public class gui_prodotto extends JFrame{
	private JTable table_fornitore;
	private DefaultTableModel model_fornitore;
	private DBFornitore dbfornitore = new DBFornitore();
	
	
	public gui_prodotto() {
		this.setTitle("AGGIUNGI PRODOTTO");
		this.setPreferredSize(new Dimension(800,600));
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		
		JLabel label_nome = new JLabel("Nome:");
		JTextField text_nome = new JTextField();
		text_nome.setPreferredSize(new Dimension(200,30));
		
		JLabel label_descrizione = new JLabel("Descrizione:");
		JTextArea text_descrizione = new JTextArea();
		text_descrizione.setPreferredSize(new Dimension(200,200));
		
		JLabel label_reparto = new JLabel("Reparto:");
		JTextField text_reparto = new JTextField();
		text_reparto.setPreferredSize(new Dimension(200,30));
		
		JLabel label_prezzo = new JLabel("Prezzo:");
		JTextField text_prezzo = new JTextField();
		text_prezzo.setPreferredSize(new Dimension(200,30));
		
		JLabel label_quantity = new JLabel("Quantità:");
		JTextField text_quantity = new JTextField();
		text_quantity.setPreferredSize(new Dimension(200,30));
		
		JLabel label_PIVA = new JLabel("P.Iva Fornitore: ");
		JTextField text_PIVA = new JTextField();
		text_PIVA.setPreferredSize(new Dimension(200,30));
		
		 model_fornitore = new DefaultTableModel(new Object[] {"P.Iva", "Nome" , "Via" , "Città", "Nazione", "telefono", "fax","email","sito Web"},0);
		fillTableFornitori();
		table_fornitore = new JTable();
		table_fornitore.setModel(model_fornitore);
		JButton btn_aggiungi = new JButton("AGGIUNGI");
		
		btn_aggiungi.addActionListener(e ->{
			new DBProdotto().addProdotto(text_nome.getText(), text_descrizione.getText(),text_reparto.getText(),
							Double.valueOf(text_prezzo.getText()), Integer.valueOf(text_quantity.getText()), text_PIVA.getText());

		});
		
		JButton btn_fornitore = new JButton("Aggiungi fornitore");
		btn_fornitore.addActionListener(e ->{
			new gui_fornitore();
			fillTableFornitori();
		});
		
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
		panel.add(new JScrollPane(table_fornitore));
		panel.add(btn_fornitore);
		this.pack();
		this.setVisible(true);
	}
	
	private void fillTableFornitori(){
		List<Fornitore> list = dbfornitore.getAllFornitori();
		model_fornitore.getDataVector().removeAllElements();
		revalidate();
		
		for(Fornitore f : list) {
			model_fornitore.addRow(new Object[] {f.getP_IVA(), f.getNome(), f.getIndirizzo(), f.getCittà(), f.getNazione(), 
						f.getTelefono(), f.getFax(), f.getEmail(), f.getSitoWeb()});
		}
	};

}
