/**
 * 
 */
package GUIs;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author ashna
 *
 */
@SuppressWarnings("serial")
public class Main extends JFrame{
	private JPanel panel;
	private JButton adminLoginBtn;
	private JButton userLoginBtn;
	
	final int WINDOW_WIDTH = 350;
	final int WINDOW_HEIGHT = 250;
	

	/**
	 * 
	 */
	public Main() {
		super("KC ENERGY BILLING");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocation(new Point(500, 300));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		buildPanel();
		add(panel);
		setVisible(true);
		
	}
	
	public void buildPanel() {
		panel = new JPanel();
		adminLoginBtn = new JButton("Admin Login");
		adminLoginBtn.setForeground(Color.WHITE);
		adminLoginBtn.setBackground(Color.BLACK);
		userLoginBtn = new JButton("User Login");
		userLoginBtn.setForeground(Color.WHITE);
		userLoginBtn.setBackground(Color.BLACK);
		adminLoginBtn.addActionListener(new AdminLoginListener());
		userLoginBtn.addActionListener(new UserLoginListener());
		panel.add(adminLoginBtn);
		panel.add(userLoginBtn);
	}
	public void openLoginWindow(char user) {
		this.dispose();
		new Login(user);
		
	}
	private class AdminLoginListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			openLoginWindow('a');
			
			
		}
		
	}
	private class UserLoginListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			openLoginWindow('u');
			
		}
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Main();

	}

}
