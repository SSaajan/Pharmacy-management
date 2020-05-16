package project;

import java.awt.BorderLayout;
import java.sql.*;
import java.util.Vector;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class employee extends JFrame {

	private JPanel contentPane;
	private JTextField employeeID;
	private JTextField employeeName;
	private JTextField salary;
	private JTextField posting;
	private JTable table;
	private JTextField remove;
	private JTextField phone;
	private JTextField address;
	

	Connection con;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employee frame = new employee();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void connect() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/pharmacymgmt?serverTimezone=IST", "root", "");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void load_table() {
		int c;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("SELECT * FROM employee");
			ResultSet rs = ps.executeQuery();
			
			ResultSetMetaData rsd = rs.getMetaData();
			c = rsd.getColumnCount();
			DefaultTableModel d1 = (DefaultTableModel)table.getModel();
			d1.setRowCount(0);
			
			while(rs.next()) {
				Vector v = new Vector();
				for(int i = 0; i < c; i++) {
					v.add(rs.getString("employee_id"));
					v.add(rs.getString("employee_name"));
					v.add(rs.getString("salary"));
					v.add(rs.getString("posting"));
					v.add(rs.getString("phone"));
					v.add(rs.getString("address"));
				}
				d1.addRow(v);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the frame.
	 */
	public employee() {
		connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 633, 332);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setForeground(new Color(176, 224, 230));
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("EMPLOYEE");
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 18));
		lblNewLabel.setBounds(232, 0, 97, 22);
		contentPane.add(lblNewLabel);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		panel.setBounds(10, 33, 212, 248);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Employee ID:");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 42, 80, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Name:");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 71, 70, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_7 = new JLabel("Salary:");
		lblNewLabel_7.setForeground(Color.BLACK);
		lblNewLabel_7.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_7.setBounds(10, 98, 46, 14);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Posting:");
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_8.setBounds(10, 125, 46, 14);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("ADD EMPLOYEE");
		lblNewLabel_9.setForeground(Color.BLACK);
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setFont(new Font("Cambria", Font.BOLD, 14));
		lblNewLabel_9.setBounds(43, 11, 122, 14);
		panel.add(lblNewLabel_9);
		
		employeeID = new JTextField();
		employeeID.setBounds(100, 40, 86, 20);
		panel.add(employeeID);
		employeeID.setColumns(10);
		
		employeeName = new JTextField();
		employeeName.setBounds(100, 69, 86, 20);
		panel.add(employeeName);
		employeeName.setColumns(10);
		
		salary = new JTextField();
		salary.setBounds(100, 96, 86, 20);
		panel.add(salary);
		salary.setColumns(10);
		
		posting = new JTextField();
		posting.setBounds(100, 123, 86, 20);
		panel.add(posting);
		posting.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(232, 33, 375, 159);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.setBackground(new Color(224, 255, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Salary", "Posting", "Phone", "Address"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Integer.class, Object.class, Integer.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		load_table();
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(employeeID.getText());
				String Name = employeeName.getText();
				int Salary = Integer.parseInt(salary.getText());
				String Posting = posting.getText();
				int Phone = Integer.parseInt(phone.getText());
				String Address = address.getText();
				PreparedStatement ps;
				try {
					ps = con.prepareStatement("INSERT INTO employee VALUES(?,?,?,?,?,?)");
					ps.setInt(1, ID);
					ps.setString(2, Name);
					ps.setInt(3, Salary);
					ps.setString(4, Posting);
					ps.setInt(5, Phone);
					ps.setString(6, Address);
					
					int flag = ps.executeUpdate();
					
					if(flag == 1) {
						JOptionPane.showMessageDialog(null, "Added Successfully!");
						employeeID.setText("");
						employeeName.setText("");
						salary.setText("");
						posting.setText("");
						phone.setText("");
						address.setText("");
						employeeID.requestFocus();
						load_table();
					}
					else {
						JOptionPane.showMessageDialog(null, "Failed.");
						employeeID.setText("");
						employeeName.setText("");
						salary.setText("");
						posting.setText("");
						phone.setText("");
						address.setText("");
						employeeID.requestFocus();
					}
						
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton.setBounds(10, 213, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton_1.setBounds(113, 214, 89, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_8_1 = new JLabel("Phone:");
		lblNewLabel_8_1.setForeground(Color.BLACK);
		lblNewLabel_8_1.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_8_1.setBounds(10, 152, 46, 14);
		panel.add(lblNewLabel_8_1);
		
		phone = new JTextField();
		phone.setColumns(10);
		phone.setBounds(100, 150, 86, 20);
		panel.add(phone);
		
		JLabel lblNewLabel_8_1_1 = new JLabel("Address:");
		lblNewLabel_8_1_1.setForeground(Color.BLACK);
		lblNewLabel_8_1_1.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_8_1_1.setBounds(10, 179, 70, 14);
		panel.add(lblNewLabel_8_1_1);
		
		address = new JTextField();
		address.setColumns(10);
		address.setBounds(100, 177, 86, 20);
		panel.add(address);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		panel_1.setBackground(new Color(176, 196, 222));
		panel_1.setBounds(255, 203, 335, 77);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("REMOVE");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 14));
		lblNewLabel_1.setBounds(121, 11, 83, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Employee ID:");
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(28, 35, 80, 14);
		panel_1.add(lblNewLabel_2_1);
		
		remove = new JTextField();
		remove.setColumns(10);
		remove.setBounds(118, 33, 86, 20);
		panel_1.add(remove);
		
		JButton btnNewButton_2 = new JButton("REMOVE");
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Remove = Integer.parseInt(remove.getText());
				PreparedStatement ps;
				try {
					ps = con.prepareStatement("DELETE FROM employee WHERE employee_id=?");
					ps.setInt(1, Remove);
					
					int flag = ps.executeUpdate();
					
					if(flag == 1) {
						JOptionPane.showMessageDialog(null, "Removed Successfully!");
						remove.setText("");
						remove.requestFocus();
						load_table();
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton_2.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton_2.setBounds(224, 32, 89, 23);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("<-BACK");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainUI m1 = new mainUI();
				m1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton_3.setBounds(10, 3, 89, 23);
		contentPane.add(btnNewButton_3);
		
		
	}
}
