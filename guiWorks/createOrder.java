package guiWorks;

import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JTextField;

import components.Bike;
import components.Frameset;
import components.Handlebars;
import components.Order;
import components.Wheels;
import users.Address;
import users.Customer;
import users.Staff;

import javax.swing.JComboBox;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class createOrder extends JPanel {
	private final JLabel lblNewLabel_1 = new JLabel("New label");
	private JTextField firstNameInp;
	private JTextField lastNameInp;
	private JTextField houseNumInp;
	private JTextField streetInp;
	private JTextField postcodeInp;
	private JTextField cityInp;
	private JTextField orderNameInp;

	/**
	 * Create the panel.
	 */
	public createOrder(Staff staffLoggedIn,JPanel jp) {
		JPanel coPanel=new JPanel();
		add(coPanel);
		
	    ArrayList<Frameset> allFrames = Frameset.getFramesetList();
		ArrayList<Handlebars> allHandlebars = Handlebars.getHandlebarsList();
		ArrayList<Wheels> allWheels = Wheels.getWheelList();

	    int numberOfFrames = allFrames.size();
	    int numberOfHandlebars = allHandlebars.size();
	    int numberOfWheels = allWheels.size();

	    
		
		coPanel.setLayout(new GridLayout(6, 1, 0, 0));

		
		JLabel promptLabel = new JLabel("enter the customer details to attach the order");
		coPanel.add(promptLabel);
		
		JPanel customerDetailPanel=new JPanel();
		coPanel.add(customerDetailPanel);
		
		
		customerDetailPanel.setLayout(new GridLayout(3, 4, 0, 0));
		
		
		JPanel componentSelPanel=new JPanel();
		coPanel.add(componentSelPanel);
		
		JPanel componentInfoPanel=new JPanel();
		coPanel.add(componentInfoPanel);
		
		componentSelPanel.setLayout(new GridLayout(5, 2, 0, 0));
		componentInfoPanel.setLayout(new GridLayout(4, 1, 0, 0));

		
		JLabel firstNameLabel = new JLabel("Customer First Name:");
		customerDetailPanel.add(firstNameLabel);
		
		firstNameInp = new JTextField();
		customerDetailPanel.add(firstNameInp);
		firstNameInp.setColumns(10);
		
		JLabel lastNameLabel = new JLabel("Customer Lastname:");
		customerDetailPanel.add(lastNameLabel);
		
		lastNameInp = new JTextField();
		customerDetailPanel.add(lastNameInp);
		lastNameInp.setColumns(10);
		
		JLabel houseNumLabel = new JLabel("Customer House Number:");
		customerDetailPanel.add(houseNumLabel);
		
		houseNumInp = new JTextField();
		customerDetailPanel.add(houseNumInp);
		houseNumInp.setColumns(10);
		
		JLabel streetLabel = new JLabel("Customer Street Name:");
		customerDetailPanel.add(streetLabel);
		
		streetInp = new JTextField();
		customerDetailPanel.add(streetInp);
		streetInp.setColumns(10);
		
		JLabel postcodeLabel = new JLabel("Customer Postcode:");
		customerDetailPanel.add(postcodeLabel);
		
		postcodeInp = new JTextField();
		customerDetailPanel.add(postcodeInp);
		postcodeInp.setColumns(10);
		
		JLabel cityLabel = new JLabel("Customer City:");
		customerDetailPanel.add(cityLabel);
		
		cityInp = new JTextField();
		customerDetailPanel.add(cityInp);
		cityInp.setColumns(10);
		
		JLabel framesetLabel = new JLabel("Frameset:");
		componentSelPanel.add(framesetLabel);
		
		
		String[] framesets = new String[numberOfFrames];
		for (int i = 0 ; i < numberOfFrames; i++) {
			framesets[i] = allFrames.get(i).getProductName() + " by " + allFrames.get(i).getBrandName();
		}
		
		String[] handlebars = new String[numberOfHandlebars];
		for (int i = 0 ; i < numberOfHandlebars; i++) {
			handlebars[i] = allHandlebars.get(i).getProductName() + " by " + allHandlebars.get(i).getBrandName();
		}
		
		String[] wheels = new String[numberOfWheels];
		for (int i = 0 ; i < numberOfWheels; i++) {
			wheels[i] = allWheels.get(i).getProductName() + " by " + allWheels.get(i).getBrandName();
		}
		
		JComboBox framesetComboBox = new JComboBox(framesets);

		componentSelPanel.add(framesetComboBox);
		JLabel framesetDetails = new JLabel("");
		componentInfoPanel.add(framesetDetails);
		
		
		framesetComboBox.addActionListener(e -> {;
		    String framedeets;
		    int selectedFramesetInd = framesetComboBox.getSelectedIndex();
		    
		    
    		
		    
		    framedeets = "Brand Name : " + allFrames.get(selectedFramesetInd).getBrandName() + ", product Name : " + allFrames.get(selectedFramesetInd).getProductName()
					+ ", Cost : $" + allFrames.get(selectedFramesetInd).getCost() + ", Size : " + allFrames.get(selectedFramesetInd).getSize() 
					+ ", No of Gears : " + allFrames.get(selectedFramesetInd).getGears()+ ", No in Stock " + allFrames.get(selectedFramesetInd).getStock();
			framesetDetails.setText("Frameset Details : " + framedeets);
		});
		
		System.out.println(framesetComboBox.getSelectedIndex());
		
		JLabel handlebarsLabel = new JLabel("Handlebars:");
		componentSelPanel.add(handlebarsLabel);
		
		JComboBox handlebarComboBox = new JComboBox(handlebars);
		componentSelPanel.add(handlebarComboBox);
		
		JLabel handlebarDetails = new JLabel("");
		componentInfoPanel.add(handlebarDetails);
		
		handlebarComboBox.addActionListener(e -> {
		    String handlebardeets;
		    int selectedHandlebarInd = handlebarComboBox.getSelectedIndex();
		    handlebardeets = "Brand Name : " + allHandlebars.get(selectedHandlebarInd).getBrandName() + ", product Name : " 
		    + allHandlebars.get(selectedHandlebarInd).getProductName()
					+ ", Cost : $" + allHandlebars.get(selectedHandlebarInd).getCost() + ", Style : " + allHandlebars.get(selectedHandlebarInd).getStyle()
					+ " No in Stock : " + allHandlebars.get(selectedHandlebarInd).getStock();
			handlebarDetails.setText("HandleBar Details : " + handlebardeets);
		});

		
		
		JLabel wheelsLabel = new JLabel("Wheels:");
		componentSelPanel.add(wheelsLabel);
		
		JComboBox wheelComboBox = new JComboBox(wheels);
		componentSelPanel.add(wheelComboBox);
		
		JLabel wheelDetails = new JLabel("");
		componentInfoPanel.add(wheelDetails);
		
		
		wheelComboBox.addActionListener(e -> {
		    String wheeldeets;
		    int selectedWheelInd = wheelComboBox.getSelectedIndex();
		    
		    wheeldeets = "Brand Name : " + allWheels.get(selectedWheelInd).getBrandName() + ", product Name : " + allWheels.get(selectedWheelInd).getProductName()
					+ ", Cost : $" + allWheels.get(selectedWheelInd).getCost() + ", Size : " + allWheels.get(selectedWheelInd).getDiameter()
					+ "cm, No in Stock : " + allWheels.get(selectedWheelInd).getStock();
		    
		    wheelDetails.setText("Wheel Details : " + wheeldeets);

		    
		    
		});
		
		//collection dropdown
		JLabel collectLabel = new JLabel("Collection Time:");
		componentSelPanel.add(collectLabel);
		
		
		String[] collectOptions = {"NOW","LATER"};
		JComboBox collectComboBox = new JComboBox(collectOptions);
		componentSelPanel.add(collectComboBox);
		
		collectComboBox.addActionListener(e -> {
			collectComboBox.getSelectedIndex();
		});
		
		Boolean collect;
		if (collectComboBox.getSelectedIndex() == 0) {
			collect = true;
		}
		else {
			collect = false;
		}
		
		JLabel costLabel = new JLabel("Bike cost:");
		componentInfoPanel.add(costLabel);
		
		
		JLabel bikeNameLabel = new JLabel("Bike Name : ");
		componentSelPanel.add(bikeNameLabel);
		
		JTextField bikeNameT = new JTextField();
		bikeNameT.setColumns(2);
		componentSelPanel.add(bikeNameT);
		
		
		JPanel buttonPanel=new JPanel();
		coPanel.add(buttonPanel);


		
		buttonPanel.setLayout(new GridLayout(1, 5, 0, 0));
		
		
		
		JLabel orderLabel = new JLabel("order name:");
		buttonPanel.add(orderLabel);
		
		orderNameInp = new JTextField();
		buttonPanel.add(orderNameInp);
		orderNameInp.setColumns(10);
		
		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double orderCost = (allFrames.get(framesetComboBox.getSelectedIndex()).getCost() 
						+ allHandlebars.get(handlebarComboBox.getSelectedIndex()).getCost()
						+ allWheels.get(wheelComboBox.getSelectedIndex()).getCost());
				
				costLabel.setText("Bike cost: $" + String.valueOf(orderCost));
				System.out.println("Frameset cost");
				System.out.println(allFrames.get(framesetComboBox.getSelectedIndex()).getCost());				
				System.out.println("Handlebar cost");
				System.out.println(allHandlebars.get(handlebarComboBox.getSelectedIndex()).getCost());				
				System.out.println("Wheel cost");
				System.out.println(allWheels.get(wheelComboBox.getSelectedIndex()).getCost());
				System.out.println("Final Cost");
				System.out.println(String.valueOf(orderCost));

			}
		});
		buttonPanel.add(btnNewButton);
		
		JButton placeOrderButton = new JButton("place customer order");
		buttonPanel.add(placeOrderButton);
		
		placeOrderButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Place Order!");
				
				String userFirstname = firstNameInp.getText();
				String userLastname = lastNameInp.getText();
				String userHouseNum = houseNumInp.getText();
				String userStreetName = streetInp.getText();
				String userPostcode = postcodeInp.getText();
				String userCity = cityInp.getText();
				String orderName = orderNameInp.getText();
				
				Frameset selectedFrame = allFrames.get(framesetComboBox.getSelectedIndex());
				Handlebars selectedHandle = allHandlebars.get(handlebarComboBox.getSelectedIndex());
				Wheels selectedWheels = allWheels.get(wheelComboBox.getSelectedIndex());
				
				
				




				
				
				if ((userFirstname.equals("")) || (userLastname.equals("")) || (orderName.equals("")) 
						|| (userHouseNum.equals("")) || (userPostcode.equals("")) || userStreetName.equals("") || userStreetName.equals("")) {
					System.out.println("Error Message enter details!");

				}
				else {
					Address add = new Address(userHouseNum, userStreetName, userPostcode,userCity);

					Customer chkCustomer = new Customer(userFirstname, userLastname, add);
					Bike bk = new Bike(selectedFrame, selectedHandle, selectedWheels);
					if (chkCustomer.customerLogin()) {
						Order ord = new Order(chkCustomer, bk, bikeNameT.getText(),
								staffLoggedIn ,  collect);
						
						if (ord.placeOrder()) {
							JOptionPane.showMessageDialog((Component)e.getSource(),"Successfully Registered order placed. You "
									+ "will return to the staff menu","Alert",JOptionPane.WARNING_MESSAGE);
							coPanel.setVisible(false);
							jp.removeAll();
							StaffMenu stf = new StaffMenu(staffLoggedIn,jp);
							jp.add(stf);
							stf.setVisible(true);
						}
						else {
							JOptionPane.showMessageDialog((Component)e.getSource(),"There was a problem placing order."
									+ "Try again.","Alert",JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						
						
						JOptionPane.showMessageDialog((Component)e.getSource(),"Customer doesn't exist please create customer first!"
								,"Alert",JOptionPane.WARNING_MESSAGE);
						

						
						
						

					}
				}



				
				
			}
		});
		
		
		
	}

}
