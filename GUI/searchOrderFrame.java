package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class searchOrderFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPanel panel_1;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnNewButton;
	private JPanel panel_2;
	private JButton btnNewButton_1;
	private JScrollPane scrollPane;
	private JTable table;
	private JLabel lblNewLabel_3;
	private JButton btnNewButton_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					searchOrderFrame frame = new searchOrderFrame();
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
	public searchOrderFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(2, 2, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("search by order");
		panel.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("serach by address(flat/house number,postcode)");
		panel.add(lblNewLabel);
		
		panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		textField_2 = new JTextField();
		panel_1.add(textField_2);
		textField_2.setColumns(10);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		btnNewButton = new JButton("find oder");
		panel_1.add(btnNewButton);
		
		panel_2 = new JPanel();
		contentPane.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));
		
		btnNewButton_1 = new JButton("edit selected order");
		panel_2.add(btnNewButton_1);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{"efjfje", "efe", "fe", "fe", "fe"},
				{"feff", "efffefefefeef", "efff", "ef", "efe"},
				{"df", "ef", "f", "efe", "fef"},
				{"fdff", "f", "fef", "efe", "fef"},
				{"d", "ef", "ef", "f", "df"},
				{"fe", "f", "ef", "ef", "ef"},
				{"f", "fg", "feef", "fdf", "f"},
				{"ef", "d", "F", "d", "fe"},
				{"dffe", "dd", "fe", "df", "eeddf"},
				{"fed", "effe", "f", "f", "d"},
				{"fee", "dd", "fe", null, "ef"},
				{"f", "ef", "ef", "f", "ef"},
			},
			new String[] {
				"New column", "New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(table);
		
		lblNewLabel_3 = new JLabel("lol");
		scrollPane.setColumnHeaderView(lblNewLabel_3);
		
		btnNewButton_2 = new JButton("clear");
		scrollPane.setRowHeaderView(btnNewButton_2);
	}

}
