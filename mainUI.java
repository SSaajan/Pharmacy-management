package project;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;

public class mainUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainUI frame = new mainUI();
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
	public mainUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("DRUG");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drug d = new drug();
				d.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Cambria", Font.PLAIN, 13));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBounds(25, 60, 100, 45);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("EMPLOYEE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employee e1 = new employee();
				e1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setFont(new Font("Cambria", Font.PLAIN, 13));
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setBounds(164, 60, 100, 45);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CUSTOMER");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customer c1 = new customer();
				c1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_2.setFont(new Font("Cambria", Font.PLAIN, 13));
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setBounds(301, 60, 100, 45);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_1 = new JLabel("PHARMACY MANAGEMENT");
		lblNewLabel_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 18));
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setBounds(63, 11, 299, 22);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_3 = new JButton("LOGOUT");
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				login Login = new login();
				Login.setVisible(true);
				setVisible(false);
			}
			
		});
		btnNewButton_3.setForeground(Color.BLACK);
		btnNewButton_3.setFont(new Font("Cambria", Font.PLAIN, 13));
		btnNewButton_3.setBounds(176, 227, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("BILL");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				bill b1 = new bill();
				b1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_4.setBackground(Color.LIGHT_GRAY);
		btnNewButton_4.setForeground(Color.BLACK);
		btnNewButton_4.setFont(new Font("Cambria", Font.PLAIN, 13));
		btnNewButton_4.setBounds(176, 193, 89, 23);
		contentPane.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("SUPPLIER");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				supplier s1 = new supplier();
				s1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_5.setBackground(Color.LIGHT_GRAY);
		btnNewButton_5.setForeground(Color.BLACK);
		btnNewButton_5.setFont(new Font("Cambria", Font.PLAIN, 13));
		btnNewButton_5.setBounds(99, 127, 100, 45);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("HISTORY");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				history h1 = new history();
				h1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_6.setBackground(Color.LIGHT_GRAY);
		btnNewButton_6.setForeground(Color.BLACK);
		btnNewButton_6.setFont(new Font("Cambria", Font.PLAIN, 13));
		btnNewButton_6.setBounds(239, 127, 100, 45);
		contentPane.add(btnNewButton_6);
	}

}
