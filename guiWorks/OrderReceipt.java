package guiWorks;

import javax.swing.JPanel;

import components.Order;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class OrderReceipt extends JPanel {

	/**
	 * Create the panel.
	 */
	public OrderReceipt(Order ord, JPanel jp) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		add(Box.createRigidArea(new Dimension(20,50)));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new GridLayout(4, 0, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Order serial Number : " + ord.getSerialNo() );
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel);
		String receipt = "  Bike Configuration : 1 X FrameSet : " + ord.getBike().getFrameset().getProductName() + "\n" + 
		                 "                         : 1 X Handlebars : " + ord.getBike().getHandlebars().getProductName() + "\n" +
				         "                         : 2 X Wheels  : " + ord.getBike().getWheels().getProductName() + "\n" +
		                 "  Final Cost             : " + String.valueOf(ord.getFinalCost())  + " ";
		
		JLabel lblNewLabel_1 = new JLabel("Bike Name : " + ord.getProductName());
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setText(receipt);
		textArea.setEditable(false);
		panel.add(textArea);
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		
		JButton btnNewButton = new JButton("Return to main menu");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MainFr fr = new MainFr();
				fr.setVisible(true);
			}
		});
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Delete order");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (ord.deleteOrder()) {
					JOptionPane.showMessageDialog((Component)e.getSource(),"Order successfully deleted. "
							+ "You will now return to the main menu" ,"Alert",JOptionPane.WARNING_MESSAGE);
					setVisible(false);
					MainFr fr = new MainFr();
					fr.setVisible(true);
				}
				
			}
		});
		panel_1.add(btnNewButton_1);
		
		add(Box.createRigidArea(new Dimension(20,50)));

	}

}
