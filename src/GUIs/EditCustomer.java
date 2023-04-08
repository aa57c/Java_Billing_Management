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

import javax.swing.*;
import javax.swing.border.EmptyBorder;

//import EditCustomerOptions.CheckboxList;
import EditCustomerOptions.CheckboxListItem;
import EditCustomerOptions.CheckboxListRenderer;



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

	private JList<CheckboxListItem> list = new JList<CheckboxListItem>(
			new CheckboxListItem[] {
					new CheckboxListItem("First Name"),
					new CheckboxListItem("Last Name"),
					new CheckboxListItem("Meter Type"),
					new CheckboxListItem("Energy Tariff"),
					new CheckboxListItem("Address"),
					new CheckboxListItem("Postal Code"),
					new CheckboxListItem("Email")
					
			});
	
	private JScrollPane pane;
	
	private JPanel editTFs = new JPanel();
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
	public void buildEditOptionPanel() {
		listOptions.setLayout(new BorderLayout());
		listOptions.setBorder(BorderFactory.createTitledBorder("Field Options"));
		list.setCellRenderer(renderer);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.addMouseListener(new CheckboxListListener());
		pane = new JScrollPane(list);
		listOptions.add(pane);
		main.add(listOptions, BorderLayout.NORTH);
	}
	public void buildTextFieldPanel() {
		editTFs.setLayout(new GridLayout(7, 2));
		editTFs.setBorder(BorderFactory.createTitledBorder("Edit Customer"));
		editTFs.add(firstName);
		firstNTF.setEditable(false);
		editTFs.add(firstNTF);
		
		editTFs.add(lastName);
		lastNTF.setEditable(false);
		editTFs.add(lastNTF);
		
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
		/*
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		editTFs.add(button);
		*/
		
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

		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println("Customer Updated!");
			
			
		}
		
	}
	
	private class CheckboxListListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			@SuppressWarnings("unchecked")
			JList<CheckboxListItem> list = (JList<CheckboxListItem>) e.getSource();
			int index = list.locationToIndex(e.getPoint());
			CheckboxListItem item = (CheckboxListItem) list.getModel().getElementAt(index);
			item.setSelected(!item.isSelected());
			
			list.repaint(list.getCellBounds(index, index));
			switch(item.toString()) {
			case "First Name":
				firstNTF.setEditable(!firstNTF.isEditable());
				break;
			case "Last Name":
				lastNTF.setEditable(!lastNTF.isEditable());
				break;
			case "Meter Type":
				meterTTF.setEditable(!meterTTF.isEditable());
				break;
			case "Energy Tariff":
				energyTTF.setEditable(!energyTTF.isEditable());
			case "Address":
				addressTF.setEditable(!addressTF.isEditable());
				break;
			case "Postal Code":
				postalTF.setEditable(!postalTF.isEditable());
				break;
			case "Email":
				emailTF.setEditable(!emailTF.isEditable());
				break;
					
			}
			
			
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new EditCustomer("Ashna", "Ali");

	}

}
