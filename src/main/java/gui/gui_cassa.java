package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import baseClass.Dipendente;
import baseClass.Prodotto;
import database.DBCassa;
import database.DBCliente;
import database.DBLavora;
import database.DBProdotto;
import database.DBScontrino;

public class gui_cassa extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Integer idCassa;
	private Dipendente dip;
	private DBProdotto dbprodotto = new DBProdotto();
	private DBScontrino dbscontrino = new DBScontrino();
	private DBCliente dbcliente = new DBCliente();
	private DBCassa dbcassa = new DBCassa();
	private DBLavora dblavora;
	private Double totale=0.0;
	private String codiceFiscale = null;
	
	public gui_cassa(final Dipendente dip, final Integer idCassa) {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.setTitle("CASSA N. " + idCassa + "\tDIPENDENTE: " + dip.getNome() + " " + dip.getCognome());
		this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
		this.idCassa = idCassa;
		this.dip = dip;
		dblavora = new DBLavora(dip, idCassa);
		dblavora.iniziaLavoro();
		dbcassa.occupaCassa(idCassa);
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		DefaultTableModel model = new DefaultTableModel(new Object[] {"Nome Prodotto", "Prezzo"},0);
		JTable table = new JTable();
		table.setModel(model);
		JTextField fieldProducts = new JTextField("");
		fieldProducts.setBounds(614, 204, 50, 30);
		fieldProducts.setPreferredSize(new Dimension(50, 30));
		JButton btnEnter = new JButton("Aggiungi");
		btnEnter.setBounds(669, 200, 130, 38);
		JButton btnCliente = new JButton("Inserisci codice cliente");
		JTextField textCliente = new JTextField(" ");
		textCliente.setBounds(845, 309, 250, 30);
		textCliente.setPreferredSize(new Dimension(50, 30));
		JLabel label_cliente = new JLabel("Codice cliente: ");
		label_cliente.setBounds(621, 315, 178, 18);
		JLabel label_text_totale = new JLabel("Totale:");
		label_text_totale.setBounds(367, 445, 120, 18);
		
		btnCliente.addActionListener(e ->{
			if (dbcliente.isClienteEsiste(textCliente.getText())) {
				this.codiceFiscale = textCliente.getText();
			}
			else {
				JOptionPane.showMessageDialog(null, "Il cliente non esiste!");
			}
			
		});
		JLabel label_totale = new JLabel(totale.toString() + " €");
		label_totale.setBounds(578, 434, 86, 30);
		
		btnEnter.addActionListener(e ->{
			Integer idProdotto = Integer.valueOf(fieldProducts.getText());
			Optional<Integer> offer = dbprodotto.getOfferforProduct(idProdotto);
			Prodotto prod = dbprodotto.getProductFromId(idProdotto); 
			model.addRow(new Object[] {prod.getNome(),prod.getPrezzo() + " €"});
			totale = totale + prod.getPrezzo();
			if(offer.isPresent()) {
				model.addRow(new Object[] {"Sconto","-"+ (prod.getPrezzo() * offer.get())/100 + " €"});
				totale -= (double)(prod.getPrezzo() * offer.get())/100;
			}
				
			label_totale.setText(totale.toString() + " €");
			fieldProducts.setText("");
			dbprodotto.aggiornaMagazzino(idProdotto);
		});
		
		JButton btnStampaScontrino = new JButton("StampaScontrino");
		btnStampaScontrino.setBounds(578, 504, 261, 59);
		btnStampaScontrino.addActionListener(e ->{
			if(codiceFiscale!=null)
			dbscontrino.stampaScontrino(idCassa, totale);
			else
			dbscontrino.stampaScontrino(idCassa, totale, codiceFiscale);

			totale=0.0;
			label_totale.setText(totale.toString() + " €");
			codiceFiscale=null;
			model.getDataVector().removeAllElements();
			revalidate();
			repaint();
			
		});
		JButton btnFineTurno = new JButton("Fine Turno");
		btnFineTurno.setBounds(1213, 5, 120, 59);
		btnFineTurno.addActionListener(e ->{
			if(model.getDataVector().isEmpty()) {
				dblavora.fineLavoro();
				dbcassa.liberaCassa(idCassa);
				this.dispose();	
			}
			else
				JOptionPane.showMessageDialog(null, "Devi stampare lo scontrino attuale prima di poter terminare il turno");
			
		});
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(157, 5, 452, 428);
		panel.add(scrollPane);
		panel.add(fieldProducts);
		panel.add(btnEnter);
		panel.add(label_totale);
		panel.add(label_cliente);
		panel.add(textCliente);
		panel.add(btnStampaScontrino);
		panel.add(btnFineTurno);
		panel.add(label_text_totale);
		this.pack();
		this.setVisible(true);
		
	}
	
}
