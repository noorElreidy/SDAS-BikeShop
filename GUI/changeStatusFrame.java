package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;

public class changeStatusFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTable table;
	private JTextField txtEnterFirstName;
	private JTextField txtEnterLastName;
	private JTextField txtEnterPostcode;
	private JTextField txtEnterOrderid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					changeStatusFrame frame = new changeStatusFrame();
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
	public changeStatusFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("enter the oderID:");
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		
		JComboBox<String> comboBox = new JComboBox<String>();
		panel.add(comboBox);
		comboBox.addItem("PENDING");
		comboBox.addItem("CONFIRMED");
		comboBox.addItem("COMPLETE");
		
		JButton btnNewButton = new JButton("update");
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Search and Edit orders");
		contentPane.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{"tetssd", "fef", "ef", "e", "ef"},
				{"ed", "d", "sd", "d", "d"},
				{"f", "s", "e", "fd", "d"},
				{"sd", "df", "ee", "ssd", "s"},
				{"f", "e", "fee", null, "ffe"},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		scrollPane.setRowHeaderView(panel_1);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2);
		panel_2.setLayout(new GridLayout(6, 2, 0, 0));
		
		txtEnterFirstName = new JTextField();
		txtEnterFirstName.setText("enter first name");
		panel_2.add(txtEnterFirstName);
		txtEnterFirstName.setColumns(10);
		
		txtEnterLastName = new JTextField();
		txtEnterLastName.setText("enter last name");
		panel_2.add(txtEnterLastName);
		txtEnterLastName.setColumns(10);
		
		txtEnterPostcode = new JTextField();
		txtEnterPostcode.setText("enter postcode");
		panel_2.add(txtEnterPostcode);
		txtEnterPostcode.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("search by customer info");
		panel_2.add(btnNewButton_2);
		
		txtEnterOrderid = new JTextField();
		txtEnterOrderid.setText("enter orderID");
		panel_2.add(txtEnterOrderid);
		txtEnterOrderid.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("search by orderID");
		panel_2.add(btnNewButton_3);
	}

}
