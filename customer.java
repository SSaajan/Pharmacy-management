package project;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class customer extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection con;
	PreparedStatement ps;
	ResultSet rs;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					customer frame = new customer();
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
			ps = con.prepareStatement("SELECT * FROM customer");
			ResultSet rs = ps.executeQuery();
			
			ResultSetMetaData rsd = rs.getMetaData();
			c = rsd.getColumnCount();
			DefaultTableModel d1 = (DefaultTableModel)table.getModel();
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

	/**
	 * Create the frame.
	 */
	public customer() {
		connect();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(176, 196, 222));
		panel.setBounds(10, 35, 414, 215);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 394, 193);
		panel.add(scrollPane);
		
		table = new JTable();
		table.setBackground(new Color(224, 255, 255));
		table.setModel(new DefaultTableModel(
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
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(90);
		table.getColumnModel().getColumn(3).setPreferredWidth(90);
		scrollPane.setViewportView(table);
		
		JLabel lblNewLabel = new JLabel("CUSTOMER");
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(155, 10, 110, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("<-BACK");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainUI m1 = new mainUI();
				m1.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton.setBounds(10, 8, 89, 23);
		contentPane.add(btnNewButton);
		load_table();
	}
}
