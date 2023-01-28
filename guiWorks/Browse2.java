package guiWorks;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import components.Bike;
import components.Frameset;
import components.Handlebars;
import components.Order;
import components.Wheels;
import users.Customer;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class Browse2 extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public Browse2(Customer cus, JPanel jp) {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel_1.setPreferredSize(new Dimension(60,100));
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.X_AXIS));
		
		JPanel panel_3 = new JPanel();
		panel_1.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		panel_1.add(panel_4);
		panel_4.setLayout(new BoxLayout(panel_4, BoxLayout.Y_AXIS));
		
		JPanel panel_5 = new JPanel();
		panel_4.add(panel_5);
		
		JPanel panel_6 = new JPanel();
		panel_4.add(panel_6);
		
		JPanel panel_7 = new JPanel();
		panel_4.add(panel_7);
		
		JPanel panel_8 = new JPanel();
		panel_4.add(panel_8);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		
		
		
		
		
		panel_3.setPreferredSize(new Dimension(80,80));
		panel_3.setLayout(new GridLayout(5, 2, 0, 10));
		
		//arraylists of the components
		ArrayList<Frameset> allFramesets = Frameset.getFramesetList();
		ArrayList<Wheels> allWheels = Wheels.getWheelList();
		ArrayList<Handlebars> allHandlebars = Handlebars.getHandlebarsList();
		
		//getting a lists of all the components, and saving them by their product name and brand name
		String[] handlebars = new String[allHandlebars.size()];
		for (int i = 0 ; i < allHandlebars.size(); i++) {
			handlebars[i] = allHandlebars.get(i).getProductName() + " by " + allHandlebars.get(i).getBrandName();
		}
		
		String[] wheels = new String[allWheels.size()];
		for (int i = 0 ; i < allWheels.size(); i++) {
			wheels[i] = allWheels.get(i).getProductName() + " by " + allWheels.get(i).getBrandName();
		}
		
		String[] framesets = new String[allFramesets.size()];
		for (int i = 0 ; i < allFramesets.size(); i++) {
			framesets[i] = allFramesets.get(i).getProductName() + " by " + allFramesets.get(i).getBrandName();
		}
		
		
		
		
		
		JLabel framesetLabel = new JLabel("FrameSet");
		panel_3.add(framesetLabel);
		
		JComboBox framesetComboBox = new JComboBox(framesets);
		panel_3.add(framesetComboBox);
		
		JLabel framesetDetails = new JLabel("Frameset Details : ");
		panel_5.add(framesetDetails);
		
		//when a combo box option is chosen 
		framesetComboBox.addActionListener(e -> {
		    String framedeets;
		    int selectedFramesetInd = framesetComboBox.getSelectedIndex();
		    
		    //checks if the frameset has shocks or not
		    String shock;
    		if(allFramesets.get(selectedFramesetInd).hasShocks()) {
    			shock = "Has Shocks";
    		}
    		else {
    			shock = "Doesn't have Shocks";
    		}
    		//a string which contains all the frameset details 
    		framedeets = "<br/><br/>Brand Name : " + allFramesets.get(selectedFramesetInd).getBrandName() + "<br/>Product Name : "
    		+ allFramesets.get(selectedFramesetInd).getProductName() + "<br/>Cost : $" 
    				+ allFramesets.get(selectedFramesetInd).getCost() 
    		+ "<br/>Size : " + allFramesets.get(selectedFramesetInd).getSize() + "<br/>Shocks : " + shock
					+ "<br/>No of Gears : " + allFramesets.get(selectedFramesetInd).getGears()+ "<br/>No in Stock " 
    		+ allFramesets.get(selectedFramesetInd).getStock() + "</html>";
    		
    		//this will display the frameset details
    		framesetDetails.setText("<html>Frameset Details : " + framedeets);
		});
		
		
		
		
		
		
		JLabel handlebarLabel = new JLabel("Handlebars");
		panel_3.add(handlebarLabel);
		
		JComboBox handlebarComboBox = new JComboBox(handlebars);
		panel_3.add(handlebarComboBox);
		
		JLabel handlebarDetails = new JLabel("HandleBar Details : ");
		panel_6.add(handlebarDetails);
		
		//when handlebar combo box action is selected this runs
		handlebarComboBox.addActionListener(e -> {
			
		    String handlebardeets;
		    //gets the index of the selected option
		    int selectedHandlebarInd = handlebarComboBox.getSelectedIndex();
		    
		    //the details of the handlebar go into this string
		    handlebardeets = "<br/><br/>Brand Name : " + allHandlebars.get(selectedHandlebarInd).getBrandName() 
		    		+ "<br/>Product Name : " + allHandlebars.get(selectedHandlebarInd).getProductName()
					+ "<br/>Cost : $" + allHandlebars.get(selectedHandlebarInd).getCost() 
					+ "<br/>Style : " + allHandlebars.get(selectedHandlebarInd).getStyle()
					+ "<br/>No in Stock : " + allHandlebars.get(selectedHandlebarInd).getStock() + "</html>";
		    //handlebar details outputted
			handlebarDetails.setText("<html>HandleBar Details : " + handlebardeets);
		});
		
		
		
		
		
		
		
		JLabel wheelLabel = new JLabel("Wheels");
		panel_3.add(wheelLabel);
		
		JComboBox wheelComboBox = new JComboBox(wheels);
		panel_3.add(wheelComboBox);
		
		
		JLabel wheelDetails = new JLabel("Wheel Details : ");
		panel_7.add(wheelDetails);
	
		//when an option in the wheel combo box is selected then this runs
		wheelComboBox.addActionListener(e -> {
			
		    String wheeldeets;
		    int selectedWheelInd = wheelComboBox.getSelectedIndex();
		    
		    wheeldeets = "<br/><br/>Brand Name : " + allWheels.get(selectedWheelInd).getBrandName() 
		    		+ "<br/>Product Name : " + allWheels.get(selectedWheelInd).getProductName()
					+ "<br/>Cost : $" + allWheels.get(selectedWheelInd).getCost() + "<br/>Size : " 
		    		+ allWheels.get(selectedWheelInd).getDiameter() + " cm"
					+ "<br/>No in Stock : " + allWheels.get(selectedWheelInd).getStock() + "</html>";
			wheelDetails.setText("<html>Wheel Details : " + wheeldeets);
		});
		
		
		//when collecting you have two options, now or later
		JLabel collectionLabel = new JLabel("Collection Time:");
		panel_3.add(collectionLabel);
		
		String[] collect = {"NOW","LATER"};
		JComboBox collectComboBox = new JComboBox(collect);
		panel_3.add(collectComboBox);
		
		
		
		//getting the name of the bike
		JLabel bikeNameLabel = new JLabel("Bike Name");
		panel_3.add(bikeNameLabel);
		
		JTextField bikeNameText = new JTextField();
		panel_3.add(bikeNameText);
		bikeNameText.setColumns(10);
		
		
		
		
		//cost of bike label
		JLabel bikeCostLabel = new JLabel("Bike Cost : " );
		panel_8.add(bikeCostLabel);
		
		//if the user is not a customer 
		if (cus == null) {
			
			JButton btnNewButton = new JButton("Back to Customer Menu");
			panel_2.add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					MainFr fr = new MainFr();
					fr.setVisible(true);
					
				}
			});
			
			
			
			//saves all the details
			JButton saveBtn = new JButton("Save");
			panel_2.add(saveBtn);
			//when save button is clicked
			saveBtn.addActionListener(e ->{
				//working out the cost of the bike
				double bikeCost = 10 + (allFramesets.get(framesetComboBox.getSelectedIndex()).getCost()) + 
						(allHandlebars.get(handlebarComboBox.getSelectedIndex()).getCost())+ 
						(allWheels.get(wheelComboBox.getSelectedIndex()).getCost());
				//displaying the cost of the bike
				bikeCostLabel.setText("Bike Cost : $" + Double.toString(bikeCost));
			});
			
			//if user is not a customer it allows them to register when placing an order
			JButton registerLabel = new JButton("Register to place order");
			panel_2.add(registerLabel);
			//when register button is clicked
			registerLabel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Frameset frm = allFramesets.get(framesetComboBox.getSelectedIndex());
					Handlebars hdl = allHandlebars.get(handlebarComboBox.getSelectedIndex());
					Wheels whl = allWheels.get(wheelComboBox.getSelectedIndex());
					Bike bk = new Bike(frm,hdl,whl);
					
					boolean collect ; 
					//if the user has chosen collect now then true, else flase
					if (String.valueOf(collectComboBox.getSelectedItem()).equals("NOW")) {
						collect = true ;
					}else {
						collect = false ;
					}
					
					//creates an order object
					Order ord = new Order(cus,bk,bikeNameText.getText(), collect);
					
					panel.setVisible(false);
					
					jp.removeAll();
					RegisterCustomer rC = new RegisterCustomer(ord,jp);
					jp.add(rC);
					rC.setVisible(true);
					
				}
			});
			
		}else {
			
			JButton btnNewButton = new JButton("Back to Main Menu");
			panel_2.add(btnNewButton);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panel.setVisible(false);
					jp.removeAll();
					CustomerMenu m = new CustomerMenu(cus,jp) ; 
					jp.add(m);
					m.setVisible(true);
				}
			});
			
			panel_2.add(btnNewButton);
			panel_2.add(Box.createRigidArea(new Dimension(0, 100)));
			

			JButton saveBtn = new JButton("Save");
			panel_2.add(saveBtn);
			saveBtn.addActionListener(e ->{
				double bikeCost = 10 + (allFramesets.get(framesetComboBox.getSelectedIndex()).getCost()) + 
						(allHandlebars.get(handlebarComboBox.getSelectedIndex()).getCost())+ 
						(allWheels.get(wheelComboBox.getSelectedIndex()).getCost());
				bikeCostLabel.setText("Bike Cost : " + Double.toString(bikeCost));
			});
			
			//when user is a customer then they can click the place order button, and they do not need to enter any deatils
			JButton placeOrderBtn = new JButton("Place Order");
			panel_2.add(placeOrderBtn);
			//when button clicked do
			placeOrderBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Frameset frm = allFramesets.get(framesetComboBox.getSelectedIndex());
					Handlebars hdl = allHandlebars.get(handlebarComboBox.getSelectedIndex());
					Wheels whl = allWheels.get(wheelComboBox.getSelectedIndex());
					Bike bk = new Bike(frm,hdl,whl);
					
					boolean collect ; 
					if (String.valueOf(collectComboBox.getSelectedItem()).equals("NOW")) {
						collect = true ;
					}else {
						collect = false ;
					}
					
					Order ord = new Order(cus,bk,bikeNameText.getText(), collect);
					
					//when an order has successfully been placed
					if (ord.placeOrder()) {
						JOptionPane.showMessageDialog((Component)e.getSource(),"Successfully Placed Order. You will"
								+ " now return Customer Menu. You can head to checkout to pay for order.","Alert",JOptionPane.WARNING_MESSAGE);
						panel.setVisible(false);
						jp.removeAll();
						CustomerMenu m = new CustomerMenu(cus,jp) ;  
						jp.add(m);
						m.setVisible(true);
					}
					//if order has not been placed successfully then shows an error message 
					else {
						JOptionPane.showMessageDialog((Component)e.getSource(),"Order failed. Try again","Alert",JOptionPane.WARNING_MESSAGE);
						
					}
					
				}
			});	
		
	}
		
		
		
		
		
		
		
		

	}

}
