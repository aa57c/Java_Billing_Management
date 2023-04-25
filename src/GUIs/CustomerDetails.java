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

import javax.swing.table.DefaultTableModel;

import db_connection.DB_Connect;

/**
 * @author ashna
 *
 */
@SuppressWarnings("serial")
public class CustomerDetails extends JFrame {
	private JPanel main = new JPanel();
	private JFrame searchResult;
	private static JTable table;
	private String[] columnNames = {"Customer ID", "Customer Name", "Meter Type", "RESHRAM_Rate", "FAC_Rate", "DCIM_Rate", "STD_Rate", "Address", "Zip Code", "Email"};
	private JPanel searchForCustomer = new JPanel();
	private JLabel search = new JLabel("Search for Customer: ");
	private JTextField searchTF = new JTextField(10);
	private JButton searchBtn = new JButton("Search!");
	//private JButton editBtn = new JButton("Edit Customer Details");
	

	/**
	 * 
	 */
	public CustomerDetails() {
		super("Search for Customer Details");
		buildPanel();
		add(main, BorderLayout.NORTH);
		setSize(500, 200);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
		
		main.add(searchForCustomer, BorderLayout.CENTER);

	}
	private class SearchBtnListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			showTableData(searchTF.getText());
			
		}
		
	}
	public void showTableData(String n) {
		searchResult = new JFrame("Database Search Result");
		searchResult.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		searchResult.setLayout(new BorderLayout());
		DefaultTableModel model = new DefaultTableModel();
		model.setColumnIdentifiers(columnNames);
		table = new JTable();
		table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setFillsViewportHeight(true);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		try {
			DB_Connect conn = new DB_Connect();
			CallableStatement stmt = conn.query("{call GETCustomerDetails(?)}");
			stmt.setString(1, n);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			if(rs.next()) {
				model.addRow(new Object[] {rs.getString("customerID"), rs.getString("customerName"), rs.getString("meterType"), rs.getString("RESHRAM_rate"), rs.getString("FAC_rate"), rs.getString("DCIM_rate"), rs.getString("STD_rate"), rs.getString("address"), rs.getString("postalCode"), rs.getString("email")});
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		searchResult.add(scroll, BorderLayout.NORTH);
		searchResult.pack();
		//searchResult.add(editBtn, BorderLayout.CENTER);
		searchResult.setVisible(true);
		searchResult.setSize(700, 600);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new CustomerDetails();

	}

}
