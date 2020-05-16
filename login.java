package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class login extends JFrame {

	private JPanel contentPane;
	private JTextField txtusername;
	private JPasswordField txtpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PHARMACY MANAGEMENT");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setBackground(SystemColor.textHighlightText);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 18));
		lblNewLabel.setBounds(10, 11, 283, 19);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username:");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 13));
		lblNewLabel_1.setBounds(117, 82, 79, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password:");
		lblNewLabel_2.setForeground(Color.DARK_GRAY);
		lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD, 13));
		lblNewLabel_2.setBounds(117, 119, 79, 26);
		contentPane.add(lblNewLabel_2);
		
		txtusername = new JTextField();
		txtusername.setBounds(190, 90, 86, 20);
		contentPane.add(txtusername);
		txtusername.setColumns(10);
		
		JButton btnNewButton = new JButton("LOGIN");
		btnNewButton.addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) {
				String username = txtusername.getText();
				String password = txtpass.getText();
				
				if(username.equals("Tejasvi") && password.equals("tejasvi17")){
					mainUI UI1 = new mainUI();
					setVisible(false);
					UI1.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "Wrong Username or password");
					txtusername.setText("");
					txtpass.setText("");
					txtusername.requestFocus();
				}
			}
			
			
		});
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Rockwell", Font.PLAIN, 11));
		btnNewButton.setBounds(127, 182, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtusername.setText("");
				txtpass.setText("");
				txtusername.requestFocus();
			}
		});
		btnNewButton_1.setForeground(Color.DARK_GRAY);
		btnNewButton_1.setBackground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Rockwell", Font.PLAIN, 11));
		btnNewButton_1.setBounds(246, 182, 89, 23);
		contentPane.add(btnNewButton_1);
		
		txtpass = new JPasswordField();
		txtpass.setBounds(190, 125, 86, 20);
		contentPane.add(txtpass);
	}
}
