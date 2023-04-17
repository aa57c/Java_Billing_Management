/**
 * 
 */
package GUIs;

import java.awt.*;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.*;
/**
 * @author ashna
 *
 */
@SuppressWarnings("serial")
public class UserHome extends JFrame{
	
	private JMenuBar mb;
	private JMenu user;
	private JMenuItem u1; //New Customer
	private JMenuItem u2; //Search for Customer Details (name, address, zip code, phone num, etc)
	private JMenuItem u3; //Search for Customer Deposits (payments, bills, etc.)
	
	private JMenu report; 
	private JMenuItem r1; //Generate new bill for customer
	
	private JMenu utility;
	private JMenuItem ut1; //open notepad
	private JMenuItem ut2; //open calculator
	private JMenuItem ut3; //open web browser
	
	private JMenu exit; 
	private JMenuItem e1; // exit application
	
	public UserHome() {
		super("User Portal");
		buildMenu();
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setVisible(true);
		
	}
	
	public void buildMenu() {
		mb = new JMenuBar();
		user = new JMenu("User");
		u1 = new JMenuItem("New Customer");
		u2 = new JMenuItem("Search for Customer Details");
		u3 = new JMenuItem("Search for Customer Deposits");
		u1.addActionListener(new MenuActionListener());
		u2.addActionListener(new MenuActionListener());
		u3.addActionListener(new MenuActionListener());
		user.setForeground(Color.RED);
		
		report = new JMenu("Report");
		r1 = new JMenuItem("Generate Bill");
		r1.addActionListener(new MenuActionListener());
		report.setForeground(Color.RED);
		
		utility = new JMenu("Utility");
		ut1 = new JMenuItem("Notepad");
		ut2 = new JMenuItem("Calculator");
		ut3 = new JMenuItem("Web Browser");
		ut1.addActionListener(new MenuActionListener());
		ut2.addActionListener(new MenuActionListener());
		ut3.addActionListener(new MenuActionListener());
		utility.setForeground(Color.RED);
		
		exit = new JMenu("Exit");
		e1 = new JMenuItem("Exit");
		e1.addActionListener(new MenuActionListener());
		exit.setForeground(Color.RED);
		
		user.add(u1);
		user.add(u2);
		user.add(u3);
		
		report.add(r1);
		
		utility.add(ut1);
		utility.add(ut2);
		utility.add(ut3);
		
		exit.add(e1);
		
		mb.add(user);
		mb.add(report);
		mb.add(utility);
		mb.add(exit);
		
		this.setJMenuBar(mb);
	
	}
	private class MenuActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String msg = e.getActionCommand();
			if(msg.equals("Search for Customer Details")) {
				new CustomerDetails().setVisible(true);
			}
			else if(msg.equals("New Customer")) {
				new NewCustomer().setVisible(true);
			}
			else if(msg.equals("Generate Bill")) {
				new GenerateInvoice().setVisible(true);
			}
			else if(msg.equals("Notepad")) {
				try {
					Runtime.getRuntime().exec("notepad.exe");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(msg.equals("Calculator")) {
				try {
					Runtime.getRuntime().exec("calc.exe");
				}catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(msg.equals("Web Browser")) {
				Desktop desk = Desktop.getDesktop();
				try {
					desk.browse( new URI("www.google.com"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(msg.equals("Exit")) {
				System.exit(0);
			}
			
		}
		
	}
	
	
	/*
	
	
	//private JPanel menubar = new JPanel();
	
	
	
	private JLabel address = new JLabel("Address: ");
	private JTextField addressTF = new JTextField(20);
	private JLabel meterType = new JLabel("Meter Type: ");
	private JTextField meterTypeTF = new JTextField(10);
	
	
	private JMenuBar mb;
	private JMenu user;
	private JMenuItem u1;
	private JMenuItem u2;
	private JMenuItem u3;
	
	

	
	public UserHome() {
		super("User Portal");
		buildPanel();
		add(main, BorderLayout.NORTH);
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	private void buildPanel() {
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
		
		//user.add(u1);
		//user.add(u2);
		//user.add(u3);
		
		//mb.add(user);
		//setJMenuBar(mb);
		
		//menubar.add(mb);
		
		//main.add(menubar, BorderLayout.NORTH);
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
	*/
	
	public static void main(String args[]) {
		new UserHome();
	}
	

	

}
