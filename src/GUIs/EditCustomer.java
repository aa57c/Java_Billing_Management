/**
 * 
 */
package GUIs;

import java.awt.BorderLayout;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import EditCustomerOptions.CheckboxListItem;
import EditCustomerOptions.CheckboxListRenderer;
import db_connection.DB_Connect;



/**
 * @author ashna
 *
 */
@SuppressWarnings("serial")
public class EditCustomer extends JFrame {
	private JPanel main = new JPanel();
	private JPanel listOptions = new JPanel();
	private JPanel buttonPanel = new JPanel();
	private CheckboxListRenderer renderer = new CheckboxListRenderer();
	
	
	private HashMap<String, Boolean> textBoxesFilled = new HashMap<String, Boolean>();
	

	private JList<CheckboxListItem> list = new JList<CheckboxListItem>(
			new CheckboxListItem[] {
					//new CheckboxListItem("First Name"),
					//new CheckboxListItem("Last Name"),
					new CheckboxListItem("Meter Type"),
					new CheckboxListItem("Energy Tariff"),
					new CheckboxListItem("Address"),
					new CheckboxListItem("Postal Code"),
					new CheckboxListItem("Email")
					
			});
	
	private JScrollPane pane;
	
	private JPanel editTFs = new JPanel();
	//private JLabel firstName = new JLabel("Enter the customer's first name: ");
	//private JTextField firstNTF = new JTextField(20);
	//private JLabel lastName = new JLabel("Enter the customer's last name: ");
	//private JTextField lastNTF = new JTextField(20);
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
	
	private JButton button = new JButton("Edit Customer");
	
	
	private String cust_first_name;
	private String cust_last_name;

	/**
	 * 
	 */
	public EditCustomer(String f, String l) {
		super("Edit Customer: "+ f + " " + l);
		cust_first_name = f;
		cust_last_name = l;
		main.setLayout(new BorderLayout());
		main.setBorder(new EmptyBorder(20,20,20,20));
		initializeHashMap();
		buildEditOptionPanel();
		buildTextFieldPanel();
		buildButtonPanel();
		//main.add(listOptions);
		add(main, BorderLayout.NORTH);
		setSize(600, 600);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
		
	}
	public void initializeHashMap() {
		//textBoxesFilled.put("first", false);
		//textBoxesFilled.put("last", false);
		textBoxesFilled.put("meter", false);
		textBoxesFilled.put("energy", false);
		textBoxesFilled.put("address", false);
		textBoxesFilled.put("postal", false);
		textBoxesFilled.put("email", false);
	}
	public void buildEditOptionPanel() {
		listOptions.setLayout(new BorderLayout());
		listOptions.setBorder(BorderFactory.createTitledBorder("Field Options"));
		list.setCellRenderer(renderer);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				@SuppressWarnings("unchecked")
				JList<CheckboxListItem> list = (JList<CheckboxListItem>) e.getSource();
				int index = list.locationToIndex(e.getPoint());
				CheckboxListItem item = (CheckboxListItem) list.getModel().getElementAt(index);
				item.setSelected(!item.isSelected());
				
				list.repaint(list.getCellBounds(index, index));
				switch(item.toString()) {
				case "Meter Type":
					meterTTF.setEditable(!meterTTF.isEditable());
					textBoxesFilled.put("meter", !textBoxesFilled.get("meter"));
					System.out.println("Meter TF: " + textBoxesFilled.get("meter"));
					break;
				case "Energy Tariff":
					energyTTF.setEditable(!energyTTF.isEditable());
					textBoxesFilled.put("energy", !textBoxesFilled.get("energy"));
					System.out.println("Energy Tariff TF: " + textBoxesFilled.get("energy"));
					break;
				case "Address":
					addressTF.setEditable(!addressTF.isEditable());
					textBoxesFilled.put("address", !textBoxesFilled.get("address"));
					System.out.println("Address TF: " + textBoxesFilled.get("address"));
					break;
				case "Postal Code":
					postalTF.setEditable(!postalTF.isEditable());
					textBoxesFilled.put("postal", !textBoxesFilled.get("postal"));
					System.out.println("Postal TF: " + textBoxesFilled.get("postal"));
					break;
				case "Email":
					emailTF.setEditable(!emailTF.isEditable());
					textBoxesFilled.put("email", !textBoxesFilled.get("email"));
					System.out.println("Email TF: " + textBoxesFilled.get("email"));
					break;
						
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		pane = new JScrollPane(list);
		listOptions.add(pane);
		main.add(listOptions, BorderLayout.NORTH);
	}
	public void buildTextFieldPanel() {
		editTFs.setLayout(new GridLayout(5, 2));
		editTFs.setBorder(BorderFactory.createTitledBorder("Edit Customer"));
		/*
		editTFs.add(firstName);
		firstNTF.setEditable(false);
		editTFs.add(firstNTF);
		
		editTFs.add(lastName);
		lastNTF.setEditable(false);
		editTFs.add(lastNTF);
		*/
		
		editTFs.add(meterType);
		meterTTF.setEditable(false);
		editTFs.add(meterTTF);
		
		editTFs.add(energyT);
		energyTTF.setEditable(false);
		editTFs.add(energyTTF);
		
		editTFs.add(address);
		addressTF.setEditable(false);
		editTFs.add(addressTF);
		
		editTFs.add(postal);
		postalTF.setEditable(false);
		editTFs.add(postalTF);
		
		editTFs.add(email);
		emailTF.setEditable(false);
		editTFs.add(emailTF);
		
		
		
		main.add(editTFs, BorderLayout.CENTER);
		
		
	}
	public void buildButtonPanel() {
		buttonPanel.setLayout(new BorderLayout());
		buttonPanel.setBorder(BorderFactory.createTitledBorder("Submit Changes"));
		button.addActionListener(new SubmitChangesListener());
		buttonPanel.add(button);
		main.add(buttonPanel, BorderLayout.SOUTH);
		
	}
	
	private class SubmitChangesListener implements ActionListener{
		Iterator<Entry<String, Boolean>> it = textBoxesFilled.entrySet().iterator();
		ArrayList<String> keys = new ArrayList<String>();

		@Override
		public void actionPerformed(ActionEvent e) {
			while(it.hasNext()) {
				Map.Entry<String, Boolean> element = (Map.Entry<String, Boolean>) it.next();
				if(element.getValue() == false) {
					continue;
				}
				else if(element.getValue() == true) {
					keys.add(element.getKey());
					
				}
					
			}
			try {
				DB_Connect conn = new DB_Connect();
				CallableStatement stmt = conn.query("{call UpdateCustomer(?,?,?,?,?,?,?)}");
				stmt.setString(1, cust_first_name);
				stmt.setString(2, cust_last_name);
				for(int i = 0; i < keys.size(); i++) {
					if(keys.get(i) == "meter") {
						stmt.setString(3, meterTTF.getText());
					}
					else {
						stmt.setNull(3, java.sql.Types.NULL);
					}
					if(keys.get(i) == "energy") {
						stmt.setFloat(4, Float.parseFloat(energyTTF.getText()));
					}
					else {
						stmt.setNull(4, java.sql.Types.NULL);
						
					}
					if(keys.get(i) == "address") {
						stmt.setString(5, addressTF.getText());
					}
					else {
						stmt.setNull(5, java.sql.Types.NULL);
					}
					if(keys.get(i) == "postal") {
						stmt.setInt(6, Integer.parseInt(postalTF.getText()));
					}
					else {
						stmt.setNull(6, java.sql.Types.NULL);
					}
					if(keys.get(i) == "email") {
						stmt.setString(7, emailTF.getText());
					}
					else {
						stmt.setNull(7, java.sql.Types.NULL);
					}
					/*
					switch(keys.get(i)) {
					case "meter":
						stmt.setString(3, meterTTF.getText());
						break;
					case "energy":
						stmt.setFloat(4, Float.parseFloat(energyTTF.getText()));
						break;
					case "address":
						stmt.setString(5, addressTF.getText());
						break;
					case "postal":
						stmt.setInt(6, Integer.parseInt(postalTF.getText()));
						break;
					case "email":
						stmt.setString(7, emailTF.getText());
						break;
					default:
						break;
						
					}
					*/
					
				}
				stmt.execute();
				if(stmt.isClosed()) {
					JOptionPane.showMessageDialog(null, "Customer successfully updated!");
				}
				else {
					JOptionPane.showMessageDialog(null, "Something went wrong...");
				}
				
			}catch(SQLException e1) {
				e1.printStackTrace();
			}

		}
			
			
			
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new EditCustomer("Ashna", "Ali");

	}

}
