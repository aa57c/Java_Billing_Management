package GUIs;

import java.awt.BorderLayout;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;


import db_connection.DB_Connect;

@SuppressWarnings("serial")
public class PayBill extends JFrame{
	private JPanel main = new JPanel();
	private JPanel selectCustomerDetails = new JPanel();
	private JLabel selectACustomer = new JLabel("Select A Customer: ");
	private JComboBox<String> customerList;
	private JLabel selectABill = new JLabel("Select A Bill: ");
	private JComboBox<Integer> billNumbers;
	
	private JPanel billInfo = new JPanel();
	private JLabel date = new JLabel("Payment Date (dd-MM-yyyy): ");
	private JFormattedTextField payment_date;
	private JLabel time = new JLabel("Payment Time (HH:ss am/pm): ");
	private JFormattedTextField payment_time;
	private JLabel bill_amount = new JLabel("Bill Amount: ");
	private JFormattedTextField b_amount;
	private JLabel payment_amount = new JLabel("Payment Amount: ");
	private JFormattedTextField p_amount;
	
	private JPanel btnPanel = new JPanel();
	private JButton PayBillBtn = new JButton("Pay Bill");
	
	

	public PayBill(){
		super("Pay Bill");
		buildCustomerPanel();
		buildBillPanel();
		buildBtnPanel();
		add(main, BorderLayout.NORTH);
		setSize(900, 800);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);		
	}
	public void buildBtnPanel() {
		btnPanel.setBorder(BorderFactory.createTitledBorder("Pay Bill"));
		btnPanel.add(PayBillBtn);
		
		PayBillBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int billNum = Integer.parseInt(String.valueOf(billNumbers.getSelectedItem()));
				String customerName = String.valueOf(customerList.getSelectedItem());
				String date = new SimpleDateFormat("yyyy-MM-dd").format(payment_date.getValue());
				String time = new SimpleDateFormat("hh:mm:ss").format(payment_time.getValue());
				float bill_amount = Float.parseFloat(String.valueOf(b_amount.getValue()));
				float payment_amount = Float.parseFloat(String.valueOf(p_amount.getValue()));
				
				DB_Connect conn = new DB_Connect();
				try {
					CallableStatement stmt = conn.query("{call PayBill(?, ?, ?, ?, ?)}");
					stmt.setInt(1, billNum);
					stmt.setString(2, customerName);
					stmt.setString(3, date + time);
					stmt.setFloat(4, bill_amount);
					stmt.setFloat(5, payment_amount);
					stmt.execute();
					JOptionPane.showMessageDialog(null, "Bill Paid!");
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
			
		});
		main.add(btnPanel, BorderLayout.SOUTH);
		
		
		
	}
	
	public void buildCustomerPanel() {
		main.setLayout(new BorderLayout());
		main.setBorder(new EmptyBorder(20,20,20,20));
		
		selectCustomerDetails.setBorder(BorderFactory.createTitledBorder("Select A Customer"));
		createComboBoxes();
		selectCustomerDetails.add(selectACustomer);
		selectCustomerDetails.add(customerList);
		selectCustomerDetails.add(selectABill);
		selectCustomerDetails.add(billNumbers);
		
		main.add(selectCustomerDetails, BorderLayout.NORTH);
		
		
	}
	public void buildBillPanel() {
		billInfo.setLayout(new GridLayout(4, 2));
		billInfo.setBorder(BorderFactory.createTitledBorder("Bill Information"));
		
		NumberFormat money_format = NumberFormat.getCurrencyInstance();
		
		billInfo.add(date);
		DateFormat dformat = new SimpleDateFormat("MM-dd-yyyy");
		payment_date = new JFormattedTextField(dformat);
		payment_date.setValue(new Date());
		billInfo.add(payment_date);
		
		billInfo.add(time);
		DateFormat tformat = new SimpleDateFormat("hh:mm a");
		payment_time = new JFormattedTextField(tformat);
		payment_time.setValue(new Date());
		billInfo.add(payment_time);
		
		billInfo.add(bill_amount);
		b_amount = new JFormattedTextField(money_format);
		b_amount.setEditable(false);
		billInfo.add(b_amount);
		
		billInfo.add(payment_amount);
		p_amount = new JFormattedTextField(money_format);
		p_amount.setValue(0.00);
		billInfo.add(p_amount);
		
		main.add(billInfo, BorderLayout.CENTER);

		
	}
	public List<String> listCustomer(){
		List<String> list = new ArrayList<>();
		DB_Connect conn = new DB_Connect();
		try {
			Statement stmt = conn.createQuery();
			ResultSet rs = stmt.executeQuery("SELECT customerName FROM dbo.Customers");
			while(rs.next()) {
				list.add(rs.getString("customerName"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public void pullBillAmount(Object selectedBill, Object selectedCustomer) {
		DB_Connect conn = new DB_Connect();
		try {
			CallableStatement stmt = conn.query("{call GetBillAmount(?, ?)}");
			stmt.setString(1, (String) selectedCustomer );
			stmt.setInt(2, (Integer) selectedBill);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while(rs.next()) {
				b_amount.setValue(rs.getFloat("total_Amount"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public List<Integer> billList(Object selectedItem){
		List<Integer> list = new ArrayList<>();
		DB_Connect conn = new DB_Connect();
		try {
			CallableStatement stmt = conn.query("{call GetBillNumbersForCustomer(?)}");
			stmt.setString(1, (String)selectedItem);
			stmt.execute();
			ResultSet rs = stmt.getResultSet();
			while(rs.next()) {
				list.add(rs.getInt("bill_no"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
		
	}
	public void createComboBoxes(){
		customerList = new JComboBox<String>();
		billNumbers = new JComboBox<Integer>();
		customerList.removeAllItems();
		customerList.addItem("");
		for(String customer : listCustomer()) {
			customerList.addItem(customer);
			
		}
		customerList.addItemListener(new ItemListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					Object source = e.getSource();
					if(source instanceof JComboBox) {
						JComboBox<Integer> cb = (JComboBox<Integer>) source;
						Object selectedItem = cb.getSelectedItem();
						billNumbers.removeAllItems();
						for(Integer billNum : billList(selectedItem)) {
							billNumbers.addItem(billNum);
							
						}
						
						
					}
				}
				
				
			}
			
		});
		
		billNumbers.addItemListener(new ItemListener() {

			@SuppressWarnings("unchecked")
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.SELECTED) {
					Object source = e.getSource();
					if(source instanceof JComboBox) {
						JComboBox<String> cb = (JComboBox<String>) source;
						Object selectedItem = cb.getSelectedItem();
						//System.out.println(Integer.parseInt(String.valueOf(selectedItem)));
						pullBillAmount(selectedItem, customerList.getSelectedItem());
						
					}
				}
				
			}
			
		});
		

		
		
		

		
		
	}

	public static void main(String[] args) {
		
		new PayBill();

	}

}
