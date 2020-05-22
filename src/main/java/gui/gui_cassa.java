package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import java.text.DecimalFormat;
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

import baseClass.Cliente;
import baseClass.Dipendente;
import baseClass.Prodotto;
import database.DBCassa;
import database.DBCliente;
import database.DBLavora;
import database.DBProdotto;
import database.DBScontrino;

public class gui_cassa extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Integer SIZE_CF = 16;
//	private final Integer idCassa;
//	private Dipendente dip;
	private DBProdotto dbprodotto = new DBProdotto();
	private DBScontrino dbscontrino = new DBScontrino();
	private DBCliente dbcliente = new DBCliente();
	private DBCassa dbcassa = new DBCassa();
	private DBLavora dblavora;
	private Double totale = 0.0;
	private Cliente cliente = null;
	private static DecimalFormat df = new DecimalFormat("#.##");
	
	public gui_cassa(final Dipendente dip, final Integer idCassa) {
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setPreferredSize(new Dimension(1024,768));
		this.setTitle("CASSA N. " + idCassa + "\tDIPENDENTE: " + dip.getNome() + " " + dip.getCognome());
		this.setLocation((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
//		this.idCassa = idCassa;
//		this.dip = dip;
		dblavora = new DBLavora(dip, idCassa);
		dblavora.iniziaLavoro();
		dbcassa.occupaCassa(idCassa);
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		DefaultTableModel model = new DefaultTableModel(new Object[] {"Nome Prodotto", "Prezzo"},0);
		JTable table = new JTable();
		table.setModel(model);
		JTextField fieldProducts = new JTextField("");
		fieldProducts.setBounds(613, 19, 64, 30);
		fieldProducts.setPreferredSize(new Dimension(50, 30));
		JButton btnEnter = new JButton("Aggiungi");
		btnEnter.setBounds(547, 61, 130, 38);
		JButton btnCliente = new JButton("Inserisci codice cliente");
		btnCliente.setBounds(613, 216, 250, 36);
		JLabel label_cliente2 = new JLabel();
		label_cliente2.setBounds(499, 264, 484, 178);
		JLabel label_id = new JLabel("Id prodotto:");
		label_id.setBounds(499, 19, 106, 30);

		JTextField textCliente = new JTextField("");
		textCliente.setBounds(613, 174, 250, 30);
		textCliente.setPreferredSize(new Dimension(50, 30));
		textCliente.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {}

			@Override
			public void focusLost(FocusEvent e) {
				textCliente.setText(textCliente.getText().toUpperCase());
			}
		});

		JLabel label_cliente = new JLabel("Codice cliente: ");
		label_cliente.setBounds(499, 180, 178, 18);
		JLabel label_text_totale = new JLabel("Totale:");
		label_text_totale.setBounds(195, 440, 120, 18);
		
		btnCliente.addActionListener(e ->{
			if (textCliente.getText().length() == SIZE_CF && dbcliente.isClienteEsiste(textCliente.getText())) {
				this.cliente =dbcliente.getClienteFromCodiceFiscale(textCliente.getText());
				JOptionPane.showMessageDialog(null, "Cliente inserito correttamente");
				textCliente.setText("");
				label_cliente2.setText("Cliente:  "+cliente.getNome() + " "+cliente.getCognome()+"  Totale Punti: "+cliente.getPunti());

			}
			else {
				JOptionPane.showMessageDialog(null, "Il cliente non esiste oppure il codice inserito è errato.\nRiprovare!");
			}
			
		});
		JLabel label_totale = new JLabel(totale.toString() + " €");
		label_totale.setBounds(385, 434, 130, 38);
		
		btnEnter.addActionListener(e ->{
			try {
				Integer.parseInt(fieldProducts.getText());
				if(fieldProducts.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Inserire codice prodotto");
				else {
					Integer idProdotto = Integer.valueOf(fieldProducts.getText());
					Optional<Integer> offer = dbprodotto.getOfferforProduct(idProdotto);
					Prodotto prod = dbprodotto.getProductFromId(idProdotto); 
					model.addRow(new Object[] {prod.getNome(),prod.getPrezzo() + " €"});
					totale = totale + prod.getPrezzo();
					if(offer.isPresent()) {
						model.addRow(new Object[] {"Sconto","-"+ (prod.getPrezzo() * offer.get())/100 + " €"});
						totale -= (double)(prod.getPrezzo() * offer.get())/100;
					}
						
					label_totale.setText(df.format(totale) + " €");
					fieldProducts.setText("");
					dbprodotto.aggiornaMagazzino(idProdotto,-1);	
				}
				
			}
			catch(NumberFormatException n) {
				JOptionPane.showMessageDialog(null, "Il codice prodotto inserito non è valido");
			}
			
		});
		
		JButton btnStampaScontrino = new JButton("StampaScontrino");
		btnStampaScontrino.setBounds(421, 504, 261, 59);
		btnStampaScontrino.addActionListener(e ->{
			if(cliente==null)
			dbscontrino.stampaScontrino(idCassa, totale);
			else
			dbscontrino.stampaScontrino(idCassa, totale, cliente.getCodiceFiscale());
			totale=0.0;
			label_totale.setText(df.format(totale) + "€");
			cliente=null;
			model.getDataVector().removeAllElements();
			revalidate();
			repaint();
			label_cliente2.setText("");
			
		});
		JButton btnFineTurno = new JButton("Fine Turno");
		btnFineTurno.setBounds(865, 0, 120, 59);
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
		scrollPane.setBounds(12, 0, 475, 428);
		panel.add(scrollPane);
		panel.add(fieldProducts);
		panel.add(btnEnter);
		panel.add(label_totale);
		panel.add(label_cliente);
		panel.add(textCliente);
		panel.add(btnStampaScontrino);
		panel.add(btnFineTurno);
		panel.add(label_text_totale);
		panel.add(btnCliente);
		panel.add(label_cliente2);
		panel.add(label_id);
		this.pack();
		this.setVisible(true);
		
	}

}
