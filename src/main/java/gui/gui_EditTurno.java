package gui;

import java.awt.Dimension;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;

import baseClass.Turno;
import database.DBTurno;

public class gui_EditTurno extends JFrame {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

	
	public gui_EditTurno(Turno t) {
		this.setTitle("Modifica Turno ");
		JPanel panel = new JPanel();
		this.setContentPane(panel);
		String oraInizio = timeFormat.format(t.getOraInizio());
		String oraFine = timeFormat.format(t.getOraFine());
		
		
		JTextField text_id = new JTextField(String.valueOf(t.getIdDipendente()));
		text_id.setBounds(135, 23, 83, 25);
		JTextField text_ti = new JTextField(oraInizio);
		text_ti.setBounds(516, 23, 83, 25);
		JTextField text_tf = new JTextField(oraFine);
		text_tf.setBounds(680, 21, 83, 25);
		JDateChooser date_choose = new JDateChooser(t.getData());
		date_choose.setBounds(294, 21, 127, 21);
		panel.setLayout(null);
		JButton btn = new JButton("Modifica");
		btn.setBounds(636, 78, 127, 34);

		btn.addActionListener(e ->{

			
			try {
				Turno t_mod = new Turno(date_choose.getDate(), new Time(timeFormat.parse(text_ti.getText()).getTime()),
						new Time(timeFormat.parse(text_tf.getText()).getTime()), Integer.valueOf(text_id.getText()));
				if(new DBTurno().modificaTurno(t, t_mod));

			} catch (NumberFormatException | ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				JOptionPane.showMessageDialog(null, "Turno correttamente modificato");
		
		});
		
		
		JLabel label = new JLabel("Id Dipendente: ");
		label.setBounds(12, 26, 106, 15);
		panel.add(label);
		panel.add(text_id);
		JLabel label_1 = new JLabel("Data: ");
		label_1.setBounds(236, 25, 41, 15);
		panel.add(label_1);
		panel.add(date_choose);
		JLabel label_2 = new JLabel("Ora Inizio: ");
		label_2.setBounds(439, 25, 73, 15);
		panel.add(label_2);
		panel.add(text_ti);
		JLabel label_3 = new JLabel("Ora Fine: ");
		label_3.setBounds(608, 25, 66, 15);
		panel.add(label_3);
		panel.add(text_tf);
		panel.add(btn);
		
		this.setPreferredSize(new Dimension(800,200));
		this.pack();
		this.setVisible(true);
		
	}
}
