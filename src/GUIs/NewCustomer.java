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
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

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
	private JLabel energyT = new JLabel("Enter the customer's energy tariff: ");
	private JTextField energyTTF = new JTextField(10);
	private JLabel address = new JLabel("Enter the customer's address: ");
	private JTextField addressTF = new JTextField(20);
	private JLabel postal = new JLabel("Enter the customer's zip code: ");
	private JTextField postalTF = new JTextField(10);
	private JLabel email = new JLabel("Enter the customer's email: ");
	private JTextField emailTF = new JTextField(20);
	private JPanel btnPanel = new JPanel();
	private JButton submit = new JButton("Add Customer");

	/**
	 * 
	 */
	public NewCustomer() {
		super("New Customer");
		buildPanel();
		add(main, BorderLayout.NORTH);
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);		
		
	}
	public void buildPanel() {
		main.setLayout(new BorderLayout());
		main.setBorder(new EmptyBorder(20,20,20,20));
		
		
		form.setLayout(new GridLayout(7, 2));
		form.setBorder(BorderFactory.createTitledBorder("New Customer"));
		
		form.add(firstName);
		form.add(firstNTF);
		form.add(lastName);
		form.add(lastNTF);
		form.add(meterType);
		form.add(meterTTF);
		form.add(energyT);
		form.add(energyTTF);
		form.add(address);
		form.add(addressTF);
		form.add(postal);
		form.add(postalTF);
		form.add(email);
		form.add(emailTF);
		
		btnPanel.setLayout(new GridLayout(1, 2));
		btnPanel.setBorder(BorderFactory.createTitledBorder("Submit"));
		submit.addActionListener(new SubmitListener());
		btnPanel.add(submit);
		
		main.add(form, BorderLayout.CENTER);
		main.add(btnPanel, BorderLayout.SOUTH);
		
		
		
	}
	private class SubmitListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			float energyTariff;
			int postalCode;
			energyTariff = Float.parseFloat(energyTTF.getText());
			postalCode = Integer.parseInt(postalTF.getText());
			queryDB(firstNTF.getText(), lastNTF.getText(), meterTTF.getText(), energyTariff, addressTF.getText(), postalCode, emailTF.getText());
			
		}
		
	}
	public void queryDB(
			String firstName, 
			String lastName, 
			String meter, 
			float energy, 
			String address, 
			int postal, 
			String email) {
		
		Random r = new Random();
		int low = 1;
		int high = 100;
		
		int cust_id = r.nextInt(high - low) + low;
		
		try {
			DB_Connect conn = new DB_Connect();
			CallableStatement stmt =  conn.query("{call CreateCustomer(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
			stmt.setInt(1, cust_id);
			stmt.setString(2, firstName);
			stmt.setString(3, lastName);
			stmt.setString(4, meter);
			stmt.setFloat(5, energy);
			stmt.setString(6, address);
			stmt.setInt(7, postal);
			stmt.setString(8, email);
			stmt.registerOutParameter(9, java.sql.Types.INTEGER);
			stmt.execute();
			boolean alreadyExists = stmt.getBoolean(9);
			if(alreadyExists) {
				JOptionPane.showMessageDialog(null, "Each customer should have unique customer ID. Please enter an ID that is not taken.");
			
			}
			else if(!alreadyExists) {
				JOptionPane.showMessageDialog(null, "New Customer Created!");
			}
			
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
