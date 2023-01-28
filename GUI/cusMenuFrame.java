package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class cusMenuFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					cusMenuFrame frame = new cusMenuFrame();
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
	public cusMenuFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(2, 0, 0, 0));
		
		JButton btnNewButton = new JButton("view previous orders");
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("view pending orders");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				collectOrderIdFrame sf= new collectOrderIdFrame();
				sf.setVisible(true);
			}
		});
		panel_1.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("edit address");
		panel_1.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("part selection ");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PartSelection sf= new PartSelection();
				sf.setVisible(true);
				
				dispose();
			}
		});
		panel_1.add(btnNewButton_4);
		
		JButton btnNewButton_1 = new JButton("log out and return to main menu");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame sf= new mainFrame();
				sf.setVisible(true);
				
				dispose();
			}
		});
		contentPane.add(btnNewButton_1, BorderLayout.SOUTH);
		
		JLabel lblNewLabel = new JLabel("welcome back ! NAME,select one of the follwing options");
		contentPane.add(lblNewLabel, BorderLayout.NORTH);
	}

}
