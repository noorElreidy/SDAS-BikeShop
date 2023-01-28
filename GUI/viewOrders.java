package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import components.Order;

public class viewOrders extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTextField txtenterTheOrder;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					viewOrders frame = new viewOrders();
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
	public viewOrders() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("here is a list of previous orders");
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setEnabled(false);
		
			table.setModel(new DefaultTableModel(
				new Object[][] {
					{"ff", "dse", "sff", "dsd"},
					{"df", "f", "df", "d"},
					{"g", "hh", "tr", null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
					{null, null, null, null},
				},
				new String[] {
					"New column", "New column", "New column", "New column"
				}
			));
			
			panel = new JPanel();
			contentPane.add(panel, BorderLayout.SOUTH);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			
			btnNewButton = new JButton("return customer menu ");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cusMenuFrame sf= new cusMenuFrame();
					sf.setVisible(true);
					
					dispose();
				}
			});
			panel.add(btnNewButton);
			
			btnNewButton_1 = new JButton("quit");
			panel.add(btnNewButton_1);
			
			btnNewButton_2 = new JButton("edit active order ");

			panel.add(btnNewButton_2);
			
			txtenterTheOrder = new JTextField();
			txtenterTheOrder.setText("(enter the order id please)");
			panel.add(txtenterTheOrder);
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					editOrderFrame sf= new editOrderFrame();

					sf.setVisible(true);
					// obj created for class Second()
					//obj.my_update(str); // Execute the method my_update to pass str
					//obj.setVisible(true); // Open the Second.java window
					
					
					
					
				}
			});
			txtenterTheOrder.setColumns(10);
	}

}
