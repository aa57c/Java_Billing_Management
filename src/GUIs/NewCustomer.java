/**
 * 
 */
package GUIs;

import java.awt.BorderLayout;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import db_connection.DB_Connect;

/**
 * @author ashna
 *
 */
@SuppressWarnings("serial")
public class NewCustomer extends JFrame {
	private JPanel main = new JPanel();
	
	private JPanel form = new JPanel();
	private JLabel firstName = new JLabel("Enter the customer's first name: ");
	private JTextField firstNTF = new JTextField(20);
	private JLabel lastName = new JLabel("Enter the customer's last name: ");
	private JTextField lastNTF = new JTextField(20);
	private JLabel meterType = new JLabel("Enter the customer's meter type: ");
	private JTextField meterTTF = new JTextField(10);
	private JLabel address = new JLabel("Enter the customer's address: ");
	private JTextField addressTF = new JTextField(20);
	private JLabel postal = new JLabel("Enter the customer's zip code: ");
	private JTextField postalTF = new JTextField(10);
	private JLabel email = new JLabel("Enter the customer's email: ");
	private JTextField emailTF = new JTextField(20);
	private JLabel std = new JLabel("Standard Rate: ");
	private JTextField STD_Rate = new JTextField(10);
	private JLabel dcim = new JLabel("DCIM Rate: ");
	private JTextField DCIM_Rate = new JTextField(10);
	private JLabel fac = new JLabel("FAC Rate: ");
	private JTextField FAC_Rate = new JTextField(10);
	private JLabel reshram = new JLabel("RESHRAM Rate: ");
	private JTextField RESHRAM_Rate = new JTextField(10);
	
	private JPanel submitPanel = new JPanel();
	
	private JPanel sliderPanel = new JPanel();
	private JSlider ForDCIM = new JSlider(0, 100, 1);
	private JSlider ForFAC = new JSlider(0, 100, 1);
	private JSlider ForRESHRAM = new JSlider(0, 100, 1);
	private JSlider ForSTD = new JSlider(0, 100, 1);
	
	private JPanel btnPanel = new JPanel();
	private JButton submit = new JButton("Add Customer");

	/**
	 * 
	 */
	public NewCustomer() {
		super("New Customer");
		buildFormPanel();
		buildSubmissionPanel();
		add(main, BorderLayout.NORTH);
		setSize(600, 600);
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);		
		
	}
	public void buildFormPanel() {
		main.setLayout(new BorderLayout());
		main.setBorder(new EmptyBorder(20,20,20,20));
		
		
		form.setLayout(new GridLayout(10, 2));
		form.setBorder(BorderFactory.createTitledBorder("New Customer"));
		
		form.add(firstName);
		form.add(firstNTF);
		form.add(lastName);
		form.add(lastNTF);
		form.add(meterType);
		form.add(meterTTF);
		form.add(address);
		form.add(addressTF);
		form.add(postal);
		form.add(postalTF);
		form.add(email);
		form.add(emailTF);
		form.add(std);
		STD_Rate.setEditable(false);
		form.add(STD_Rate);
		form.add(dcim);
		DCIM_Rate.setEditable(false);
		form.add(DCIM_Rate);
		form.add(fac);
		FAC_Rate.setEditable(false);
		form.add(FAC_Rate);
		form.add(reshram);
		RESHRAM_Rate.setEditable(false);
		form.add(RESHRAM_Rate);
		
		main.add(form, BorderLayout.CENTER);
		
		
	}
	public void buildSubmissionPanel() {
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		
		labelTable.put(100, new JLabel("1.0"));
		labelTable.put(75, new JLabel("0.75"));
		labelTable.put(50, new JLabel("0.50"));
		labelTable.put(25, new JLabel("0.25"));
		labelTable.put(0, new JLabel("0.0"));
		
		
		submitPanel.setLayout(new BorderLayout());
		submitPanel.setBorder(BorderFactory.createTitledBorder("Create Customer"));
		
		sliderPanel.setLayout(new GridLayout(8,1));
		
		ForDCIM.setMajorTickSpacing(25);
		ForDCIM.setPaintTicks(true);
		ForDCIM.setLabelTable(labelTable);
		ForDCIM.setPaintLabels(true);
		ForDCIM.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				DCIM_Rate.setText(String.valueOf((float) ForDCIM.getValue() / 100));
				
			}
			
		});
		sliderPanel.add(new JLabel("DCIM Rate"));
		sliderPanel.add(ForDCIM);
		
		ForFAC.setMajorTickSpacing(25);
		ForFAC.setPaintTicks(true);
		ForFAC.setLabelTable(labelTable);
		ForFAC.setPaintLabels(true);
		ForFAC.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				FAC_Rate.setText(String.valueOf((float) ForFAC.getValue() / 100));
				
			}
			
		});
		sliderPanel.add(new JLabel("FAC Rate"));
		sliderPanel.add(ForFAC);
		
		
		
		
		ForSTD.setMajorTickSpacing(25);
		ForSTD.setPaintTicks(true);
		ForSTD.setLabelTable(labelTable);
		ForSTD.setPaintLabels(true);
		ForSTD.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				STD_Rate.setText(String.valueOf((float) ForSTD.getValue() / 100));
				
			}
			
		});
		sliderPanel.add(new JLabel("STD Rate"));
		sliderPanel.add(ForSTD);

		ForRESHRAM.setMajorTickSpacing(25);
		ForRESHRAM.setPaintTicks(true);
		ForRESHRAM.setLabelTable(labelTable);
		ForRESHRAM.setPaintLabels(true);
		ForRESHRAM.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				RESHRAM_Rate.setText(String.valueOf((float) ForRESHRAM.getValue() / 100));
				
			}
			
		});
		sliderPanel.add(new JLabel("RESHRAM Rate"));
		sliderPanel.add(ForRESHRAM);
		
		
		submit.addActionListener(new SubmitListener());
		btnPanel.add(submit);
		submitPanel.add(sliderPanel, BorderLayout.WEST);
		submitPanel.add(btnPanel, BorderLayout.EAST);
		
		main.add(submitPanel, BorderLayout.SOUTH);
		
	}
	
	private class SubmitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int postalCode;
			float std;
			float dcim;
			float fac;
			float resh;
			postalCode = Integer.parseInt(postalTF.getText());
			std = Float.parseFloat(STD_Rate.getText());
			dcim = Float.parseFloat(DCIM_Rate.getText());
			fac = Float.parseFloat(FAC_Rate.getText());
			resh = Float.parseFloat(RESHRAM_Rate.getText());
			
	
			queryDB(firstNTF.getText(), 
					lastNTF.getText(), 
					meterTTF.getText(), 
					addressTF.getText(), 
					postalCode, 
					emailTF.getText(),
					std,
					dcim,
					fac,
					resh);
		}
		
	}
	public void queryDB(
			String firstName, 
			String lastName, 
			String meter, 
			String address, 
			int postal, 
			String email,
			float std,
			float dcim,
			float fac,
			float resh) {
		
		
		try {
			DB_Connect conn = new DB_Connect();
			CallableStatement stmt =  conn.query("{call CreateCustomer(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			stmt.setString(1, firstName + " " + lastName);
			stmt.setString(2, meter);
			stmt.setString(3, address);
			stmt.setInt(4, postal);
			stmt.setString(5, email);
			stmt.setFloat(6, std);
			stmt.setFloat(7, dcim);
			stmt.setFloat(8, fac);
			stmt.setFloat(9, resh);
			stmt.execute();
			JOptionPane.showMessageDialog(null, "Customer in system now!");
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new NewCustomer();

	}

}
