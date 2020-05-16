package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class supplier extends JFrame {

	private JPanel contentPane;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	private JTextField supplierID;
	private JTextField supplierName;
	private JTextField phone;
	private JTextField address;
	private JTextField rating;
	private JTable table;
	private JTextField remove;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					supplier frame = new supplier();
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
			ps = con.prepareStatement("SELECT * FROM supplier");
			ResultSet rs = ps.executeQuery();
			
			ResultSetMetaData rsd = rs.getMetaData();
			c = rsd.getColumnCount();
			DefaultTableModel d1 = (DefaultTableModel)table.getModel();
			d1.setRowCount(0);
			
			while(rs.next()) {
				Vector v = new Vector();
				for(int i = 0; i < c; i++) {
					v.add(rs.getString("supplier_id"));
					v.add(rs.getString("supplier_name"));
					v.add(rs.getString("supplier_phone"));
					v.add(rs.getString("supplier_address"));
					v.add(rs.getString("rating"));
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
	public supplier() {
		connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 634, 340);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setForeground(new Color(176, 224, 230));
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SUPPLIER");
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 18));
		lblNewLabel.setBounds(232, 0, 97, 22);
		contentPane.add(lblNewLabel);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		panel.setBounds(10, 33, 212, 257);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Supplier ID:");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 57, 80, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Name:");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 86, 70, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_7 = new JLabel("Phone:");
		lblNewLabel_7.setForeground(Color.BLACK);
		lblNewLabel_7.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_7.setBounds(10, 113, 46, 14);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Address:");
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_8.setBounds(10, 140, 58, 14);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("ADD EMPLOYEE");
		lblNewLabel_9.setForeground(Color.BLACK);
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setFont(new Font("Cambria", Font.BOLD, 14));
		lblNewLabel_9.setBounds(43, 11, 122, 14);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_7_1 = new JLabel("Rating:");
		lblNewLabel_7_1.setForeground(Color.BLACK);
		lblNewLabel_7_1.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_7_1.setBounds(10, 165, 46, 14);
		panel.add(lblNewLabel_7_1);
		
		supplierID = new JTextField();
		supplierID.setBounds(100, 55, 86, 20);
		panel.add(supplierID);
		supplierID.setColumns(10);
		
		supplierName = new JTextField();
		supplierName.setBounds(100, 84, 86, 20);
		panel.add(supplierName);
		supplierName.setColumns(10);
		
		phone = new JTextField();
		phone.setBounds(100, 111, 86, 20);
		panel.add(phone);
		phone.setColumns(10);
		
		address = new JTextField();
		address.setBounds(100, 138, 86, 20);
		panel.add(address);
		address.setColumns(10);
		
		rating = new JTextField();
		rating.setBounds(100, 163, 86, 20);
		panel.add(rating);
		rating.setColumns(10);
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(supplierID.getText());
				String Name = supplierName.getText();
				int Phone = Integer.parseInt(phone.getText());
				int Rating = Integer.parseInt(rating.getText());
				String Address = address.getText();
				
				try {
					ps = con.prepareStatement("INSERT INTO supplier VALUES(?,?,?,?,?)");
					ps.setInt(1, ID);
					ps.setString(2, Name);
					ps.setInt(3, Phone);
					ps.setString(4, Address);
					ps.setInt(5, Rating);
					
					int flag = ps.executeUpdate();
					
					if(flag == 1) {
						JOptionPane.showMessageDialog(null, "Added Successfully!");
						supplierID.setText("");
						supplierName.setText("");
						phone.setText("");
						rating.setText("");
						address.setText("");
						supplierID.requestFocus();
						load_table();
					}
					else {
						JOptionPane.showMessageDialog(null, "Failed.");
						supplierID.setText("");
						supplierName.setText("");
						phone.setText("");
						rating.setText("");
						address.setText("");
						supplierID.requestFocus();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton.setBounds(10, 210, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton_1.setBounds(113, 210, 89, 23);
		panel.add(btnNewButton_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(232, 33, 375, 173);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(224, 255, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Phone", "Address", "Rating"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Integer.class, Object.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		load_table();
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 196, 222));
		panel_1.setBounds(234, 212, 373, 78);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("REMOVE");
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(149, 11, 66, 14);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Supplier ID:");
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(47, 38, 80, 14);
		panel_1.add(lblNewLabel_2_1);
		
		remove = new JTextField();
		remove.setColumns(10);
		remove.setBounds(129, 36, 86, 20);
		panel_1.add(remove);
		
		JButton btnRemove = new JButton("REMOVE");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Remove = Integer.parseInt(remove.getText());
				try {
					ps = con.prepareStatement("DELETE FROM supplier WHERE supplier_id=?");
					ps.setInt(1, Remove);
					
					int flag = ps.executeUpdate();
					
					if(flag == 1) {
						JOptionPane.showMessageDialog(null, "Removed Successfully!");
						remove.setText("");
						remove.requestFocus();
						load_table();
					}
					else {
						JOptionPane.showMessageDialog(null, "Failed.");
						remove.setText("");
						remove.requestFocus();
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnRemove.setFont(new Font("Cambria", Font.BOLD, 12));
		btnRemove.setBackground(Color.LIGHT_GRAY);
		btnRemove.setBounds(246, 35, 89, 23);
		panel_1.add(btnRemove);
		
		JButton btnNewButton_2 = new JButton("<-BACK");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainUI m1 = new mainUI();
				m1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_2.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setBounds(10, 3, 89, 23);
		contentPane.add(btnNewButton_2);
		
		
	}
}
