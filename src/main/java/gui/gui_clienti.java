package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
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

import com.toedter.calendar.JDateChooser;

import baseClass.Cliente;
import baseClass.Dipendente;
import database.DBCliente;

public class gui_clienti extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private DefaultTableModel model;
	private JTable table;
	private final Integer NUM_CF = 16;

	
	public gui_clienti(Dipendente dip) {
		this.setTitle("GESTIONE CLIENTI" + dip.getNome() + " "+ dip.getCognome() + " ID: "+ dip.getId());
		this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		model = new DefaultTableModel(new Object[] {"Nome", "Cognome","Punti", "Data di Nascita"},0);
		table = new JTable();
		refreshTable();
		table.setModel(model);
		JLabel label_codiceFiscale = new JLabel("Codice Fiscale:");
		label_codiceFiscale.setBounds(639, 137, 209, 18);
		JTextField text_codiceFiscale = new JTextField();
		text_codiceFiscale.setBounds(774, 131, 200, 30);
		text_codiceFiscale.setPreferredSize(new Dimension(200,30));
		JLabel label = new JLabel("Crea nuovo Cliente");
		label.setBounds(968, 49, 173, 34);

		JLabel label_nome = new JLabel("Nome:");
		label_nome.setBounds(639, 199, 45, 18);
		JTextField text_nome = new JTextField();
		text_nome.setBounds(774, 193, 200, 30);
		text_nome.setPreferredSize(new Dimension(200,30));
		JLabel label_cognome = new JLabel("Cognome:");
		label_cognome.setBounds(1028, 199, 70, 18);
		JTextField text_cognome = new JTextField();
		text_cognome.setBounds(1126, 193, 200, 30);
		text_cognome.setPreferredSize(new Dimension(200,30));
		JLabel label_dataDiNascita = new JLabel("Data di Nascita:");
		label_dataDiNascita.setBounds(639, 300, 107, 18);
		JDateChooser date_choose = new JDateChooser();
		date_choose.setBounds(787, 284, 209, 34);
		text_codiceFiscale.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {}

			@Override
			public void focusLost(FocusEvent e) {
				text_codiceFiscale.setText(text_codiceFiscale.getText().toUpperCase());
			}
			
		});
		
		JButton btn_creaCliente = new JButton("Crea");
		btn_creaCliente.setBounds(1126, 280, 153, 38);
		btn_creaCliente.addActionListener(e ->{
			if(text_codiceFiscale.getText().length() !=  NUM_CF ||  text_nome.getText().isEmpty() || text_cognome.getText().isEmpty() || date_choose.getDate().equals(null))
				JOptionPane.showMessageDialog(null, "Compilare tutti i campi correttamente");
			else {
				new DBCliente().addCliente(text_codiceFiscale.getText(), text_nome.getText(), text_cognome.getText(), date_choose.getDate());
				refreshTable();	
			}
			
		});
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 5, 597, 682);
		panel.add(scrollPane);
		panel.add(label_codiceFiscale);
		panel.add(text_codiceFiscale);
		panel.add(label_nome);
		panel.add(text_nome);
		panel.add(label_cognome);
		panel.add(text_cognome);
		panel.add(label_dataDiNascita);
		panel.add(date_choose);
		panel.add(btn_creaCliente);
		panel.add(label);
		
		this.pack();
		this.setVisible(true);
	}


	private void refreshTable() {
		List<Cliente> list = new DBCliente().getAllClienti();
		model.getDataVector().removeAllElements();
		revalidate();
		
		for( Cliente c : list) {
			model.addRow(new Object[] {c.getNome(), c.getCognome(), c.getPunti(), c.getDataDiNascita()});
		}
	}		
}
