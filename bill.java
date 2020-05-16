package project;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class bill extends JFrame {

	private JPanel contentPane;
	Connection con;
	private JTable customerTable;
	private JTable drugTable;
	private JTextField billID;
	private JTextField custID;
	private JTextField drgID;
	private JTextField quantity;
	private JTextField id1;
	private JTextField phone1;
	private JTextField name1;
	private JTextField address1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					bill frame = new bill();
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
	
	public void load_drug() {
		int c;
		PreparedStatement psd;
		try {
			psd = con.prepareStatement("SELECT * FROM drug");
			ResultSet rs1 = psd.executeQuery();
			
			ResultSetMetaData rsd = rs1.getMetaData();
			c = rsd.getColumnCount();
			DefaultTableModel d1 = (DefaultTableModel)drugTable.getModel();
			d1.setRowCount(0);
			
			while(rs1.next()) {
				Vector v = new Vector();
				for(int i = 0; i < c; i++) {
					v.add(rs1.getString("drug_id"));
					v.add(rs1.getString("drug_name"));
					v.add(rs1.getString("category"));
					v.add(rs1.getString("stock"));
					v.add(rs1.getString("supplier_id"));
					v.add(rs1.getString("price"));
					v.add(rs1.getString("mrp"));
				}
				d1.addRow(v);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void load_customer() {
		int c;
		PreparedStatement ps;
		try {
			ps = con.prepareStatement("SELECT * FROM customer");
			ResultSet rs = ps.executeQuery();
			
			ResultSetMetaData rsd = rs.getMetaData();
			c = rsd.getColumnCount();
			DefaultTableModel d1 = (DefaultTableModel)customerTable.getModel();
			d1.setRowCount(0);
			
			while(rs.next()) {
				Vector v = new Vector();
				for(int i = 0; i < c; i++) {
					v.add(rs.getString("customer_id"));
					v.add(rs.getString("customer_name"));
					v.add(rs.getString("customer_phone"));
					v.add(rs.getString("customer_address"));
				}
				d1.addRow(v);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void final_bill(int bill, int cust, int cost) {
		try {
			PreparedStatement pss = con.prepareStatement("INSERT INTO bill VALUES(?,?,curdate(),?)");
			pss.setInt(1, bill);
			pss.setInt(2, cust);
			pss.setInt(3, cost);
			int flag = pss.executeUpdate();
			if(flag == 1) {
				JOptionPane.showMessageDialog(null, "BILLED COST: Rs." + cost);
				billID.setText("");
				custID.setText("");
				drgID.setText("");
				quantity.setText("");
				load_drug();
			}
			else {
				JOptionPane.showMessageDialog(null, "Failed.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void decrement(int drugID, int quantity) {
		try {
			PreparedStatement dec= con.prepareStatement("UPDATE drug SET stock = stock - ? WHERE drug_id = ?");
			dec.setInt(1, quantity);
			dec.setInt(2, drugID);
			int flag = dec.executeUpdate();
			if(flag != 1) {
				JOptionPane.showMessageDialog(null, "Stock Change Failed.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Create the frame.
	 */
	public bill() {
		connect();
		load_customer();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 664, 463);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BILLING");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 18));
		lblNewLabel.setBounds(261, 11, 98, 14);
		contentPane.add(lblNewLabel);
		
		JPanel customerPanel = new JPanel();
		customerPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		customerPanel.setBackground(new Color(176, 196, 222));
		customerPanel.setBounds(261, 154, 377, 259);
		contentPane.add(customerPanel);
		customerPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 357, 237);
		customerPanel.add(scrollPane);
		
		customerTable = new JTable();
		customerTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Phone", "Address"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Integer.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		customerTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		customerTable.getColumnModel().getColumn(1).setPreferredWidth(90);
		customerTable.getColumnModel().getColumn(3).setPreferredWidth(90);
		customerTable.setBackground(new Color(224, 255, 255));
		scrollPane.setViewportView(customerTable);
		
		JPanel drugPanel = new JPanel();
		drugPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		drugPanel.setBackground(new Color(176, 196, 222));
		drugPanel.setBounds(261, 154, 377, 259);
		contentPane.add(drugPanel);
		drugPanel.setLayout(null);
		
		JScrollPane scrollPanel1 = new JScrollPane();
		scrollPanel1.setBounds(10, 11, 357, 237);
		drugPanel.add(scrollPanel1);
		
		drugTable = new JTable();
		drugTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Category", "Stock", "Supplier", "Price", "MRP"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Integer.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		drugTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		drugTable.getColumnModel().getColumn(1).setPreferredWidth(90);
		drugTable.getColumnModel().getColumn(3).setPreferredWidth(90);
		drugTable.getColumnModel().getColumn(3).setPreferredWidth(60);
		drugTable.getColumnModel().getColumn(3).setPreferredWidth(65);
		drugTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		drugTable.getColumnModel().getColumn(3).setPreferredWidth(50);
		drugTable.setBackground(new Color(224, 255, 255));
		scrollPanel1.setViewportView(drugTable);
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		panel.setBackground(new Color(176, 196, 222));
		panel.setBounds(10, 154, 235, 259);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Bill ID:");
		lblNewLabel_1.setBounds(10, 11, 46, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Customer ID:");
		lblNewLabel_2.setBounds(10, 36, 72, 14);
		panel.add(lblNewLabel_2);
		
		billID = new JTextField();
		billID.setBounds(92, 5, 86, 20);
		panel.add(billID);
		billID.setColumns(10);
		
		custID = new JTextField();
		custID.setBounds(92, 30, 86, 20);
		panel.add(custID);
		custID.setColumns(10);
		
		JButton customerButton = new JButton("CUSTOMER");
		customerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drugPanel.setVisible(false);
				customerPanel.setVisible(true);
				load_customer();
			}
		});
		customerButton.setBounds(10, 61, 89, 23);
		panel.add(customerButton);
		
		JLabel lblNewLabel_3 = new JLabel("Drug ID:");
		lblNewLabel_3.setBounds(10, 104, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Quantity:");
		lblNewLabel_4.setBounds(10, 129, 46, 14);
		panel.add(lblNewLabel_4);
		
		drgID = new JTextField();
		drgID.setBounds(79, 101, 86, 20);
		panel.add(drgID);
		drgID.setColumns(10);
		
		quantity = new JTextField();
		quantity.setBounds(79, 126, 86, 20);
		panel.add(quantity);
		quantity.setColumns(10);
		
		JButton drugButton = new JButton("DRUG");
		drugButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerPanel.setVisible(false);
				drugPanel.setVisible(true);
				load_drug();
			}
		});
		drugButton.setBounds(136, 61, 89, 23);
		panel.add(drugButton);
		
		JButton btnNewButton = new JButton("ADD ITEM");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int BillID = Integer.parseInt(billID.getText());
				int CustID = Integer.parseInt(custID.getText());
				int DrgID = Integer.parseInt(drgID.getText());
				int Quantity = Integer.parseInt(quantity.getText());
				try {
					PreparedStatement bills = con.prepareStatement("INSERT INTO bill_det VALUES(?,?,?)");
					bills.setInt(1, BillID);
					bills.setInt(2, DrgID);
					bills.setInt(3, Quantity);
					int flag = bills.executeUpdate();
					if(flag == 1) {
						drgID.setText("");
						quantity.setText("");
						decrement(DrgID, Quantity);
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				

			}
		});
		btnNewButton.setBounds(79, 157, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("BILL");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int bill_idd = Integer.parseInt(billID.getText());
				int customer_idd = Integer.parseInt(custID.getText());
				try {
					PreparedStatement total = con.prepareStatement("SELECT SUM(drug.mrp * bill_det.quantity) FROM drug, bill_det WHERE drug.drug_id = bill_det.drug_id AND bill_det.bill_id = ?");
					total.setInt(1, bill_idd);
					ResultSet cost1 = total.executeQuery();
					cost1.next();
					int cost =  ((Number) cost1.getObject(1)).intValue();
					final_bill(bill_idd, customer_idd, cost);
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(26, 208, 89, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("CANCEL");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(billID.getText());
				drgID.setText("");
				billID.setText("");
				custID.setText("");
				quantity.setText("");
				PreparedStatement ps;
				try {
					ps = con.prepareStatement("DELETE FROM bill_det WHERE bill_id=?");
					ps.setInt(1, ID);
					int flag = ps.executeUpdate();
					if(flag != 1) {
						JOptionPane.showMessageDialog(null, "Failed.");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			}
		});
		btnNewButton_2.setBounds(125, 208, 89, 23);
		panel.add(btnNewButton_2);
		
		JButton btnNewButton_1_1 = new JButton("<< BACK");
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainUI m1 = new mainUI();
				m1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1_1.setBounds(10, 10, 89, 23);
		contentPane.add(btnNewButton_1_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		panel_1.setBackground(new Color(176, 196, 222));
		panel_1.setBounds(10, 44, 628, 95);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_5 = new JLabel("NEW CUSTOMER");
		lblNewLabel_5.setFont(new Font("Cambria", Font.BOLD, 14));
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setBounds(10, 11, 117, 14);
		panel_1.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Customer ID:");
		lblNewLabel_6.setFont(new Font("Cambria", Font.BOLD, 13));
		lblNewLabel_6.setBounds(20, 36, 96, 14);
		panel_1.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Name:");
		lblNewLabel_7.setFont(new Font("Cambria", Font.BOLD, 13));
		lblNewLabel_7.setBounds(216, 36, 69, 14);
		panel_1.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Phone:");
		lblNewLabel_8.setFont(new Font("Cambria", Font.BOLD, 13));
		lblNewLabel_8.setBounds(20, 61, 62, 14);
		panel_1.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Address:");
		lblNewLabel_9.setFont(new Font("Cambria", Font.BOLD, 13));
		lblNewLabel_9.setBounds(216, 61, 69, 14);
		panel_1.add(lblNewLabel_9);
		
		id1 = new JTextField();
		id1.setBounds(107, 34, 86, 20);
		panel_1.add(id1);
		id1.setColumns(10);
		
		phone1 = new JTextField();
		phone1.setBounds(106, 59, 86, 20);
		panel_1.add(phone1);
		phone1.setColumns(10);
		
		name1 = new JTextField();
		name1.setBounds(276, 34, 86, 20);
		panel_1.add(name1);
		name1.setColumns(10);
		
		address1 = new JTextField();
		address1.setBounds(276, 59, 86, 20);
		panel_1.add(address1);
		address1.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("ADD");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(id1.getText());
				String Name = name1.getText();
				int Phone = Integer.parseInt(phone1.getText());
				String Address = address1.getText();
				try {
					PreparedStatement ps = con.prepareStatement("INSERT INTO customer VALUES(?,?,?,?)");
					ps.setInt(1, ID);
					ps.setString(2, Name);
					ps.setInt(3, Phone);
					ps.setString(4, Address);
					int flag = ps.executeUpdate();
					if(flag == 1) {
						JOptionPane.showMessageDialog(null, "Added Successfully!");
						id1.setText("");
						name1.setText("");
						phone1.setText("");
						address1.setText("");
						billID.requestFocus();
						load_customer();
					}
					else {
						JOptionPane.showMessageDialog(null, "Failed.");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton_3.setBounds(451, 21, 89, 23);
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("CANCEL");
		btnNewButton_4.setBackground(Color.LIGHT_GRAY);
		btnNewButton_4.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton_4.setBounds(451, 58, 89, 23);
		panel_1.add(btnNewButton_4);
		
		
	}
}
