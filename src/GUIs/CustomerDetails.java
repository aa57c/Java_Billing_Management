/**
 * 
 */
package GUIs;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import db_connection.DB_Connect;

/**
 * @author ashna
 *
 */
@SuppressWarnings("serial")
public class CustomerDetails extends JFrame {
	private JPanel main = new JPanel();
	private JPanel searchForCustomer = new JPanel();
	private JPanel result = new JPanel();
	private JLabel search = new JLabel("Search for Customer: ");
	private JTextField searchTF = new JTextField(10);
	private JButton searchBtn = new JButton("Search!");
	private JLabel address = new JLabel("Address: ");
	private JTextField addressTF = new JTextField(20);
	private JLabel meterType = new JLabel("Meter Type: ");
	private JTextField meterTypeTF = new JTextField(10);
	

	/**
	 * 
	 */
	public CustomerDetails() {
		super("Search for Customer Details");
		buildPanel();
		add(main, BorderLayout.NORTH);
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public void buildPanel() {
		main.setLayout(new BorderLayout());
		main.setBorder(new EmptyBorder(20, 20, 20, 20));
		
		//search panel
		searchForCustomer.setLayout(new GridLayout(1, 2));
		searchForCustomer.add(search);
		searchForCustomer.add(searchTF);
		searchBtn.addActionListener(new SearchBtnListener());
		searchForCustomer.add(searchBtn);
		
		searchForCustomer.setBorder(BorderFactory.createTitledBorder("Search Bar"));
		
		//result panel
		result.setLayout(new GridLayout(2, 2));
		result.add(address);
		result.add(addressTF);
		result.add(meterType);
		result.add(meterTypeTF);
		
		result.setBorder(BorderFactory.createTitledBorder("Results"));
		
		//set result text fields to uneditable
		addressTF.setEditable(false);
		meterTypeTF.setEditable(false);
		
		main.add(searchForCustomer, BorderLayout.CENTER);
		main.add(result, BorderLayout.SOUTH);
	}
	public void queryDB(String customer) {
		try {
			DB_Connect conn = new DB_Connect();
			CallableStatement stmt = conn.query("{call GETAddressAndMeter(?)}");
			stmt.setString(1, customer);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			if(rs.next()) {
				if(!searchTF.getText().isEmpty()) {
					addressTF.setText(rs.getString("address"));
					meterTypeTF.setText(rs.getString("meterType"));
				}
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	private class SearchBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name_of_customer;
			name_of_customer = searchTF.getText();
			queryDB(name_of_customer);
			
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CustomerDetails();

	}

}
