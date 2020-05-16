package project;

import java.awt.BorderLayout;
import java.sql.*;
import java.util.Vector;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class drug extends JFrame {

	private JPanel contentPane;
	private JTextField search;
	private JTextField drugID;
	private JTextField drugName;
	private JTextField drugSupply;
	private JTextField stock;
	private JTextField category;
	private JTextField price;
	private JTextField mrp;
	private JTextField remove;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					drug frame = new drug();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	Connection con;
	PreparedStatement ps;
	public JTable table;
	private JTextField stockid;
	private JTextField stockqnt;
	
	
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
		try {
			ps = con.prepareStatement("SELECT * FROM drug");
			ResultSet rs = ps.executeQuery();
			
			ResultSetMetaData rsd = rs.getMetaData();
			c = rsd.getColumnCount();
			DefaultTableModel d1 = (DefaultTableModel)table.getModel();
			d1.setRowCount(0);
			
			while(rs.next()) {
				Vector v = new Vector();
				for(int i = 0; i < c; i++) {
					v.add(rs.getString("drug_id"));
					v.add(rs.getString("drug_name"));
					v.add(rs.getString("category"));
					v.add(rs.getString("stock"));
					v.add(rs.getString("supplier_id"));
					v.add(rs.getString("price"));
					v.add(rs.getString("mrp"));
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
	public drug() {
		connect();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 593, 402);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setForeground(new Color(176, 224, 230));
		contentPane.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("DRUG");
		lblNewLabel.setBackground(Color.DARK_GRAY);
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 18));
		lblNewLabel.setBounds(199, 0, 97, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Search:");
		lblNewLabel_1.setBackground(Color.DARK_GRAY);
		lblNewLabel_1.setForeground(Color.BLACK);
		lblNewLabel_1.setFont(new Font("Cambria", Font.BOLD, 13));
		lblNewLabel_1.setBounds(315, 33, 53, 22);
		contentPane.add(lblNewLabel_1);
		
		search = new JTextField();
		search.setBounds(365, 35, 119, 20);
		contentPane.add(search);
		search.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		panel.setBounds(10, 33, 212, 248);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Drug ID:");
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_2.setBounds(10, 30, 46, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Name:");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_3.setBounds(10, 55, 46, 14);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Supplier ID:");
		lblNewLabel_4.setForeground(Color.BLACK);
		lblNewLabel_4.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_4.setBounds(10, 82, 74, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Stock:");
		lblNewLabel_5.setForeground(Color.BLACK);
		lblNewLabel_5.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_5.setBounds(10, 109, 46, 14);
		panel.add(lblNewLabel_5);
		
		JLabel cate = new JLabel("Category:");
		cate.setForeground(Color.BLACK);
		cate.setFont(new Font("Cambria", Font.BOLD, 12));
		cate.setBounds(10, 136, 65, 14);
		panel.add(cate);
		
		JLabel lblNewLabel_7 = new JLabel("Price:");
		lblNewLabel_7.setForeground(Color.BLACK);
		lblNewLabel_7.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_7.setBounds(10, 163, 46, 14);
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("MRP:");
		lblNewLabel_8.setForeground(Color.BLACK);
		lblNewLabel_8.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_8.setBounds(114, 163, 46, 14);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("ADD DRUG");
		lblNewLabel_9.setForeground(Color.BLACK);
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setFont(new Font("Cambria", Font.BOLD, 14));
		lblNewLabel_9.setBounds(49, 5, 86, 14);
		panel.add(lblNewLabel_9);
		
		drugID = new JTextField();
		drugID.setBounds(85, 28, 117, 20);
		panel.add(drugID);
		drugID.setColumns(10);
		
		drugName = new JTextField();
		drugName.setBounds(85, 53, 117, 20);
		panel.add(drugName);
		drugName.setColumns(10);
		
		drugSupply = new JTextField();
		drugSupply.setBounds(85, 80, 117, 20);
		panel.add(drugSupply);
		drugSupply.setColumns(10);
		
		stock = new JTextField();
		stock.setBounds(85, 107, 117, 20);
		panel.add(stock);
		stock.setColumns(10);
		
		category = new JTextField();
		category.setBounds(85, 134, 117, 20);
		panel.add(category);
		category.setColumns(10);
		
		price = new JTextField();
		price.setBounds(49, 161, 55, 20);
		panel.add(price);
		price.setColumns(10);
		
		mrp = new JTextField();
		mrp.setBounds(151, 161, 51, 20);
		panel.add(mrp);
		mrp.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(233, 59, 334, 125);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(224, 255, 255));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Name", "Category", "Stock", "Supplier ID", "Price", "MRP"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Object.class, Object.class, Object.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(63);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		scrollPane.setViewportView(table);
		load_table();
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int ID = Integer.parseInt(drugID.getText());
				String Name = drugName.getText();
				String Category = category.getText();
				int Stock = Integer.parseInt(stock.getText());
				int SupplierID = Integer.parseInt(drugSupply.getText());
				int Price = Integer.parseInt(price.getText());
				int MRP = Integer.parseInt(mrp.getText());
				
				try {
					ps = con.prepareStatement("INSERT INTO drug VALUES(?,?,?,?,?,?,?)");
					ps.setInt(1, ID);
					ps.setString(2, Name);
					ps.setString(3, Category);
					ps.setInt(4, Stock);
					ps.setInt(5, SupplierID);
					ps.setInt(6, Price);
					ps.setInt(7, MRP);
					
					int flag = ps.executeUpdate();
					
					if(flag == 1) {
						JOptionPane.showMessageDialog(null, "Added Successfully!");
						load_table();
						drugID.setText("");
						drugName.setText("");
						category.setText("");
						stock.setText("");
						price.setText("");
						drugSupply.setText("");
						mrp.setText("");
						drugID.requestFocus();
					}
					else {
						JOptionPane.showMessageDialog(null, "Process Failed.");
						drugID.setText("");
						drugName.setText("");
						category.setText("");
						stock.setText("");
						price.setText("");
						drugSupply.setText("");
						mrp.setText("");
						drugID.requestFocus();
					}
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
				
			}
		});
		
		btnNewButton.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(10, 192, 74, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("CANCEL");
		btnNewButton_1.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setBounds(114, 192, 84, 23);
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(176, 196, 222));
		panel_1.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		panel_1.setBounds(284, 195, 247, 85);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_10 = new JLabel("REMOVE DRUG");
		lblNewLabel_10.setFont(new Font("Cambria", Font.BOLD, 13));
		lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10.setBounds(66, 11, 102, 14);
		panel_1.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("Drug ID:");
		lblNewLabel_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_11.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_11.setBounds(10, 46, 46, 14);
		panel_1.add(lblNewLabel_11);
		
		remove = new JTextField();
		remove.setBounds(66, 44, 70, 20);
		panel_1.add(remove);
		remove.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("REMOVE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int ID = Integer.parseInt(remove.getText());
				try {
					ps = con.prepareStatement("DELETE FROM drug WHERE drug_id = ?");
					ps.setInt(1, ID);
					int flag = ps.executeUpdate();
					if(flag == 1) {
						JOptionPane.showMessageDialog(null, "Removed Successfully!");
						load_table();
						remove.setText("");
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

		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		btnNewButton_2.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton_2.setBounds(148, 43, 89, 23);
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("<-BACK");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainUI UI1 = new mainUI();
				UI1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_3.setBackground(Color.LIGHT_GRAY);
		btnNewButton_3.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton_3.setBounds(10, 3, 89, 23);
		contentPane.add(btnNewButton_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.DARK_GRAY));
		panel_2.setBackground(new Color(176, 196, 222));
		panel_2.setBounds(10, 292, 557, 60);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_10_1 = new JLabel("ADD STOCK");
		lblNewLabel_10_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_10_1.setFont(new Font("Cambria", Font.BOLD, 13));
		lblNewLabel_10_1.setBounds(2, 7, 102, 14);
		panel_2.add(lblNewLabel_10_1);
		
		JLabel lblNewLabel_2_1 = new JLabel("Drug ID:");
		lblNewLabel_2_1.setForeground(Color.BLACK);
		lblNewLabel_2_1.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_2_1.setBounds(133, 7, 46, 14);
		panel_2.add(lblNewLabel_2_1);
		
		stockid = new JTextField();
		stockid.setColumns(10);
		stockid.setBounds(189, 5, 117, 20);
		panel_2.add(stockid);
		
		JLabel lblNewLabel_5_1 = new JLabel("Stock:");
		lblNewLabel_5_1.setForeground(Color.BLACK);
		lblNewLabel_5_1.setFont(new Font("Cambria", Font.BOLD, 12));
		lblNewLabel_5_1.setBounds(353, 7, 46, 14);
		panel_2.add(lblNewLabel_5_1);
		
		stockqnt = new JTextField();
		stockqnt.setColumns(10);
		stockqnt.setBounds(409, 5, 117, 20);
		panel_2.add(stockqnt);
		
		JButton btnNewButton_4 = new JButton("ADD");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(stockid.getText());
				int stock = Integer.parseInt(stockqnt.getText());
				try {
					PreparedStatement ps = con.prepareStatement("UPDATE drug SET stock = stock + ? WHERE drug_id = ?");
					ps.setInt(1, stock);
					ps.setInt(2, id);
					int flag = ps.executeUpdate();
					if(flag == 1) {
						JOptionPane.showMessageDialog(null, "Stock Updated.");
						stockid.setText("");
						stockqnt.setText("");
						stockid.requestFocus();
						load_table();
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
		btnNewButton_4.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton_4.setBackground(Color.LIGHT_GRAY);
		btnNewButton_4.setBounds(298, 30, 74, 23);
		panel_2.add(btnNewButton_4);
		
		
		
	}
		
	
	
	
}

