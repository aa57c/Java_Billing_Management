package GUIs;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import db_connection.DB_Connect;


@SuppressWarnings("serial")
public class GenerateInvoice extends JFrame{
	private JPanel main = new JPanel();
	private JPanel selectCustomer = new JPanel();
	private JComboBox<String> customerList;
	private JLabel DCIM = new JLabel("DCIM Rate");
	private JTextField dcim_rate = new JTextField(10);
	private JLabel FAC = new JLabel("FAC Rate");
	private JTextField fac_rate = new JTextField(10);
	private JLabel RESHRAM = new JLabel("RESHRAM Rate");
	private JTextField resh_rate = new JTextField(10);
	private JLabel STD = new JLabel("STD Rate");
	private JTextField std_rate = new JTextField(10);
	
	
	

	
	
	private JPanel billInfo = new JPanel();
	private JLabel name = new JLabel("Name of Customer: ");
	private JTextField customer_name = new JTextField(10);
	private JLabel cust_chrg = new JLabel("Customer Charge: ");
	private JTextField customer_chrg = new JTextField(10);
	private JLabel std = new JLabel("Standard Usage: ");
	private JTextField STD_Usage = new JTextField(10);
	private JLabel dcim = new JLabel("DCIM Usage: ");
	private JTextField DCIM_Usage = new JTextField(10);
	private JLabel fac = new JLabel("FAC Usage: ");
	private JTextField FAC_Usage = new JTextField(10);
	private JLabel reshram = new JLabel("RESHRAM Usage: ");
	private JTextField RESHRAM_Usage = new JTextField(10);
	
	private JPanel SendPnl = new JPanel();
	private JButton generate = new JButton("Generate Invoice");
	private JSlider ForDCIM = new JSlider(0, 800);
	private JSlider ForFAC = new JSlider(0, 800);
	private JSlider ForRESHRAM = new JSlider(0, 800);
	private JSlider ForSTD = new JSlider(0, 800);

	public GenerateInvoice() {
		super("Generate Invoice");
		buildCustomerPanel();
		buildBillPanel();
		buildButtonPanel();

		add(main, BorderLayout.NORTH);
		setSize(900, 800);
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);		
	}
	
	public void buildCustomerPanel() {
		main.setLayout(new BorderLayout());
		main.setBorder(new EmptyBorder(20,20,20,20));
		
	
		selectCustomer.setBorder(BorderFactory.createTitledBorder("Select A Customer"));
		createComboBox();
		selectCustomer.add(customerList);
		selectCustomer.add(DCIM);
		selectCustomer.add(dcim_rate);
		dcim_rate.setEditable(false);
		selectCustomer.add(FAC);
		selectCustomer.add(fac_rate);
		fac_rate.setEditable(false);
		selectCustomer.add(RESHRAM);
		selectCustomer.add(resh_rate);
		resh_rate.setEditable(false);
		selectCustomer.add(STD);
		selectCustomer.add(std_rate);
		std_rate.setEditable(false);
		main.add(selectCustomer, BorderLayout.NORTH);
		
	}
	public void createComboBox() {
		ArrayList<String> customers = new ArrayList<String>();
		customerList = new JComboBox<String>();
		try {
			DB_Connect conn = new DB_Connect();
			Statement stmt = conn.createQuery();
			ResultSet rs = stmt.executeQuery("SELECT customerName from dbo.Customers");
			while(rs.next()) {
				customers.add(rs.getString("customerName"));
				
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		customerList.addItem("");
		for(int i = 0; i < customers.size(); i++) {
			customerList.addItem(customers.get(i));
		}
		
		customerList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> box = (JComboBox<String>) e.getSource();
				
				String name = String.valueOf(box.getSelectedItem());
				
				try {
					DB_Connect conn = new DB_Connect();
					CallableStatement stmt = conn.query("{call GetMeterRates(?)}");
					stmt.setString(1, name);
					stmt.execute();
					ResultSet rs = stmt.getResultSet();
					while(rs.next()) {
						dcim_rate.setText(Float.toString(rs.getFloat("DCIM_rate")));
						fac_rate.setText(Float.toString(rs.getFloat("FAC_rate")));
						resh_rate.setText(Float.toString(rs.getFloat("RESHRAM_rate")));
						std_rate.setText(Float.toString(rs.getFloat("STD_rate")));
						customer_name.setText(String.valueOf(box.getSelectedItem()));
					}
					
					
				}catch(SQLException e1) {
					e1.printStackTrace();
				}

				
				
				
			}
			
		});
		
	}
	private void buildBillPanel() {
		billInfo.setLayout(new GridLayout(6, 2));
		billInfo.setBorder(BorderFactory.createTitledBorder("Generate Bill"));
		
		billInfo.add(name);
		billInfo.add(customer_name);
		customer_name.setEditable(false);
		billInfo.add(cust_chrg);
		billInfo.add(customer_chrg);
		customer_chrg.setEditable(false);
		customer_chrg.setText("12.00");
		billInfo.add(std);
		billInfo.add(STD_Usage);
		billInfo.add(dcim);
		billInfo.add(DCIM_Usage);
		billInfo.add(fac);
		billInfo.add(FAC_Usage);
		billInfo.add(reshram);
		billInfo.add(RESHRAM_Usage);
		
		main.add(billInfo, BorderLayout.CENTER);
	}
	
	private void buildButtonPanel() {
		DecimalFormat df = new DecimalFormat("##.00");
		SendPnl.setLayout(new BorderLayout());
		SendPnl.setBorder(BorderFactory.createTitledBorder("Send Bill"));
		
		JPanel sliderPanel = new JPanel(new GridLayout(8, 1));
		
		ForDCIM.setMajorTickSpacing(200);
		ForDCIM.setMinorTickSpacing(50);
		ForDCIM.setPaintTicks(true);
		ForDCIM.setPaintLabels(true);
		ForDCIM.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				double usage = ForDCIM.getValue();
				DCIM_Usage.setText(df.format(usage));
				
			}
			
		});
		sliderPanel.add(new JLabel("DCIM Usage"));
		sliderPanel.add(ForDCIM);
		
		ForFAC.setMajorTickSpacing(200);
		ForFAC.setMinorTickSpacing(50);
		ForFAC.setPaintTicks(true);
		ForFAC.setPaintLabels(true);
		ForFAC.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				double usage = ForFAC.getValue();
				FAC_Usage.setText(df.format(usage));
				
			}
			
		});
		sliderPanel.add(new JLabel("FAC Usage"));
		sliderPanel.add(ForFAC);

		ForRESHRAM.setMajorTickSpacing(200);
		ForRESHRAM.setMinorTickSpacing(50);
		ForRESHRAM.setPaintTicks(true);
		ForRESHRAM.setPaintLabels(true);
		ForRESHRAM.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				double usage = ForRESHRAM.getValue();
				RESHRAM_Usage.setText(df.format(usage));
				
			}
			
		});
		sliderPanel.add(new JLabel("RESHRAM Usage"));
		sliderPanel.add(ForRESHRAM);
		
		ForSTD.setMajorTickSpacing(200);
		ForSTD.setMinorTickSpacing(50);
		ForSTD.setPaintTicks(true);
		ForSTD.setPaintLabels(true);
		ForSTD.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				double usage = ForSTD.getValue();
				STD_Usage.setText(df.format(usage));
				
			}
			
		});
		sliderPanel.add(new JLabel("STD Usage"));
		sliderPanel.add(ForSTD);
		
		JPanel btnPanel = new JPanel();
		
		generate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				double std_use = 0;
				double dcim_use = 0;
				double fac_use = 0;
				double resh_use = 0;
				
				double std_total = 0;
				double dcim_total = 0;
				double fac_total = 0;
				double resh_total = 0;
				
				double total = 0;
				
				String status = "recieved";
				
				
				if(!STD_Usage.getText().isEmpty()) {
					std_use = Double.parseDouble(STD_Usage.getText());
				}
				if(!DCIM_Usage.getText().isEmpty()) {
					dcim_use  = Double.parseDouble(DCIM_Usage.getText());
				}
				if(!FAC_Usage.getText().isEmpty()) {
					fac_use  = Double.parseDouble(DCIM_Usage.getText());
				}
				if(!RESHRAM_Usage.getText().isEmpty()) {
					resh_use = Double.parseDouble(RESHRAM_Usage.getText());
				}
				if(std_use != 0) {
					std_total = Double.parseDouble(std_rate.getText()) * std_use;
				}
				if(dcim_use != 0) {
					dcim_total = Double.parseDouble(dcim_rate.getText()) * dcim_use;
				}
				if(fac_use != 0) {
					fac_total = Double.parseDouble(fac_rate.getText()) * fac_use;
				}
				if(resh_use != 0) {
					resh_total = Double.parseDouble(resh_rate.getText()) * resh_use;
				}
				if(std_total + dcim_total + fac_total + resh_total != 0) {
					total = std_total + dcim_total + fac_total + resh_total + Double.parseDouble(customer_chrg.getText());
				}
				
				try {
					DB_Connect conn = new DB_Connect();
					CallableStatement stmt = conn.query("{call SendInvoice(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
					stmt.setString(1, customer_name.getText());
					stmt.setFloat(2, (float) std_use);
					stmt.setFloat(3, (float) dcim_use);
					stmt.setFloat(4, (float) fac_use);
					stmt.setFloat(5, (float) resh_use);
					stmt.setFloat(6, (float) std_total);
					stmt.setFloat(7, (float) dcim_total);
					stmt.setFloat(8, (float) fac_total);
					stmt.setFloat(9, (float) resh_total);
					stmt.setFloat(10, (float) total);
					stmt.setString(11, status);
					stmt.execute();
					JOptionPane.showMessageDialog(null, "Invoice sent to customer!");
					
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
			
		});
		
		btnPanel.add(generate);
		
		SendPnl.add(sliderPanel, BorderLayout.WEST);
		SendPnl.add(btnPanel, BorderLayout.EAST);
		
		
		
		main.add(SendPnl, BorderLayout.SOUTH);
		
		
		
		
	}
	
	public static void main(String args[]) {
		new GenerateInvoice();
	}

}
