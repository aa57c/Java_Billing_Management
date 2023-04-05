/**
 * 
 */
package GUIs;

import java.awt.Color;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.SQLException;
//import java.util.ArrayList;
import java.util.Arrays;
//import java.util.Enumeration;
import java.util.HashSet;
//import java.util.Hashtable;
import java.util.Set;

import javax.swing.*;

import db_connection.DB_Connect;

/**
 * @author ashna
 *
 */
@SuppressWarnings("serial")
public class Login extends JFrame{
	private JPanel panel;
	private JLabel userLabel;
	private JLabel passLabel;
	private JTextField user;
	private JPasswordField password;
	private JButton loginBtn;
	
	
	final int WINDOW_WIDTH = 350;
	final int WINDOW_HEIGHT = 250;
	
	
	

	/**
	 * 
	 */
	public Login() {
		super("Sign In");
		setSize(400, 200);
		setLocation(new Point(500, 300));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		buildPanel();
		add(panel);
		setVisible(true);
	}
	private void buildPanel() {
		userLabel = new JLabel("Username: ");
		user = new JTextField(10);
		passLabel = new JLabel("Password: ");
		password = new JPasswordField(10);
		password.setEchoChar('*');
		loginBtn = new JButton("Login");
		loginBtn.setForeground(Color.WHITE);
		loginBtn.setBackground(Color.BLACK);
		loginBtn.addActionListener(new LoginBtnListener());
		panel = new JPanel();
		panel.add(userLabel);
		panel.add(user);
		panel.add(passLabel);
		panel.add(password);
		panel.add(loginBtn);
	}
	public void openUserHome() {
		this.dispose();
		new UserHome();
		
	}
	private class LoginBtnListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String username;
			String pass;
			
			username = user.getText();
			pass =  new String(password.getPassword());
			
			try {
				DB_Connect conn = new DB_Connect();
				CallableStatement stmt = conn.query("{call UserAuth(?, ?, ?)}");
				stmt.setString(1, username);
				stmt.setString(2, pass);
				stmt.registerOutParameter(3, java.sql.Types.INTEGER);
				stmt.execute();
				boolean exists = stmt.getBoolean(3);
				if(!user.getText().isEmpty() && pass.length() != 0 && exists) {
					JOptionPane.showMessageDialog(null, 
							"Username: "+ username +
							"\nPassword: "+ pass +
							"\nPassword Strength: " + PasswordStrength(pass));
					openUserHome();
					
				}
				else if(!user.getText().isEmpty() && pass.length() != 0 && !exists) {
					JOptionPane.showMessageDialog(null, "Invalid username/password");
				}
				else {
					JOptionPane.showMessageDialog(null, "Please enter a username and password");
				}
			}catch(SQLException e1) {
				e1.printStackTrace();
				
			}
			
		}
		
	}
	private String PasswordStrength(String p) {
		int n = p.length();
		boolean hasLower = false, hasUpper = false, hasDigit = false, specialChar = false;
		Set<Character> set = new HashSet<Character>(
				Arrays.asList('!', '@', '#', '$', '%', '^', '&',
                        '*', '(', ')', '-', '+'));
		for(char i : p.toCharArray()) {
			if(Character.isLowerCase(i)) {
				hasLower = true;
			}
			if(Character.isUpperCase(i)) {
				hasUpper = true;
			}
			if(Character.isDigit(i)) {
				hasDigit = true;
			}
			if(set.contains(i)) {
				specialChar = true;
			}
			
		}
		if(hasDigit && hasLower && hasUpper && specialChar && (n >= 8)) {
			return "Strong";
		}
		else if((hasLower || hasUpper || specialChar) && (n >= 6)) {
			return "Moderate";
		}
		else {
			return "Weak";
		}
		

		
	}
	public static void main(String args[]) {
		new Login();
	}
	

}
