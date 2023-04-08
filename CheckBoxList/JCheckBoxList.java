import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CheckBoxList extends JList {

    DefaultListModel listModel = new DefaultListModel();

    public CheckBoxList() {
        
        setModel(listModel);

        for (int i = 0; i < 10; i++) {
            listModel.addElement(Boolean.TRUE);
        }
        setCellRenderer(new MyCellRenderer());
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                int index = locationToIndex(e.getPoint());
                if (index != -1) {
                    listModel.set(index, !(Boolean)listModel.get(index));
                }
            }
        });
    }

    protected class MyCellRenderer implements ListCellRenderer {

        private JCheckBox checkBox = new JCheckBox();

        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            checkBox.setBackground(isSelected ? getSelectionBackground() : getBackground());
            checkBox.setForeground(isSelected ? getSelectionForeground() : getForeground());
            checkBox.setEnabled(isEnabled());
            checkBox.setFont(getFont());
            checkBox.setFocusPainted(false);
            checkBox.setBorderPainted(true);
            checkBox.setText("checkbox no. " + index);
            checkBox.setSelected(Boolean.parseBoolean(value.toString()));
            checkBox.setEnabled(true);

            return checkBox;
        }
    }

    public static void main(String args[]) {
        JFrame frame = new JFrame("JList CheckBox Example");
        frame.getContentPane().setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        CheckBoxList myList = new CheckBoxList();

        panel.add(new JScrollPane(myList));

        frame.getContentPane().add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
