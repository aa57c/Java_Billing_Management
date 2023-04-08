package EditCustomerOptions;

public class CheckboxListItem {
	private String label;
	private boolean isSelected = false;

	public CheckboxListItem(String l) {
		this.label = l;
		
		
	}
	public boolean isSelected() {
		return this.isSelected;
	}
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	public String toString() {
		return label;
	}
	

}
