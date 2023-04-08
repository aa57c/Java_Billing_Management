/**
 * 
 */
package EditCustomerOptions;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

/**
 * @author ashna
 *
 */
@SuppressWarnings("serial")
public class CheckboxList extends JPanel {
	private JScrollPane pane;
	private JList<CheckboxListItem> list;
	private CheckboxListRenderer renderer;
	

	/**
	 * 
	 */
	public CheckboxList() {
		super();
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createTitledBorder("Field Options"));
		initializeComponents();
		add(pane);
		
	}
	
	public void initializeComponents() {
		list = new JList<CheckboxListItem>(
				new CheckboxListItem[] {
						new CheckboxListItem("First Name"),
						new CheckboxListItem("Last Name"),
						new CheckboxListItem("Meter Type"),
						new CheckboxListItem("Energy Tariff"),
						new CheckboxListItem("Address"),
						new CheckboxListItem("Postal Code"),
						new CheckboxListItem("Email")
						
				});
		renderer = new CheckboxListRenderer();
		pane = new JScrollPane();
		list.setCellRenderer(renderer);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list.addMouseListener(new CheckboxListListener());
		pane = new JScrollPane(list);
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

}
