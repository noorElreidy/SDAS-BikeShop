package GUI;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JSpinner;
import javax.swing.JTextPane;

public class PartSelection extends JFrame {

	private JPanel contentPane;

	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PartSelection frame = new PartSelection();
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
	public PartSelection() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(8,3,3,3));
		
		JLabel lblNewLabel = new JLabel("customise your very own bike ");
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_8 = new JLabel("remaining stock ");
		contentPane.add(lblNewLabel_8);
		
		JLabel lblNewLabel_2 = new JLabel("type of frameset: ");
		contentPane.add(lblNewLabel_2);
		
	
		

		

		JLabel lblNewLabel_3 = new JLabel("New label");
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {
				lblNewLabel_3.setText("lol");
				if(comboBox.getSelectedItem()=="without shock absorbers")
				{
					
					lblNewLabel_3.setText("lol");
				}
				else{
					lblNewLabel_3.setText("not lol");
				}
			}
		});
		contentPane.add(comboBox);
		
		comboBox.addItem("with shock absorbers");
		comboBox.addItem("without shock absorbers");
		
		
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("type of handle bars:");
		contentPane.add(lblNewLabel_4);
		
		
		JComboBox comboBox_1 = new JComboBox();
		contentPane.add(comboBox_1);
		comboBox_1.addItem("straight");
		comboBox_1.addItem("high");
		comboBox_1.addItem("dropped");
		
		JTextPane textPane = new JTextPane();
		contentPane.add(textPane);
		
		JLabel lblNewLabel_5 = new JLabel("type of wheel: ");
		contentPane.add(lblNewLabel_5);

		
		JComboBox comboBox_2 = new JComboBox();
		contentPane.add(comboBox_2);
		comboBox_2.addItem("mountain");
		comboBox_2.addItem("road");
		comboBox_2.addItem("hybrid");
		
		textField_3 = new JTextField();
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		JLabel lblNewLabel_6 = new JLabel("type of brake system: ");
		contentPane.add(lblNewLabel_6);
		JComboBox<String> comboBox_3 = new JComboBox<String>();
		contentPane.add(comboBox_3);
		comboBox_3.addItem("none");
		comboBox_3.addItem("rim brake system");
		comboBox_3.addItem("disk brake system");
		
		textField_4 = new JTextField();
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		JCheckBox chckbxNewCheckBox = new JCheckBox("store assembly ");
		contentPane.add(chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("save order");
		JButton btnNewButton_1 = new JButton("place order");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("waiting for stock check");
				int orderNum=10;
				String orderName=textField.getText();
				JOptionPane.showMessageDialog((Component)e.getSource(), "your order "+orderName+" is now being proccessed wait for confirmation your oder number is : "
	                    +orderNum,"order recieved", JOptionPane.INFORMATION_MESSAGE);

				
			}
		});
	
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println(comboBox.getSelectedItem().toString()+","+comboBox_1.getSelectedItem().toString()+","+comboBox_2.getSelectedItem().toString()+","+chckbxNewCheckBox.isSelected());
				String orderName=textField.getText();
				JOptionPane.showMessageDialog((Component)e.getSource(), "your order "+orderName+ " has now been saved "
	                    ,"order saved", JOptionPane.INFORMATION_MESSAGE);

		
				
			}
		});
		
		JLabel lblNewLabel_7 = new JLabel("order name:");
		contentPane.add(lblNewLabel_7);
		
		textField = new JTextField();
		contentPane.add(textField);
		textField.setColumns(10);
		
		contentPane.add(btnNewButton);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("");
		contentPane.add(lblNewLabel_1_1);
		
	
		
	
		

		
		
	}

}
