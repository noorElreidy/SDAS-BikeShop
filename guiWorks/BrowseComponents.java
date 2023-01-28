package guiWorks;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import components.Bike;
import users.Customer;
import components.Frameset;
import components.Handlebars;
import components.Order;
import components.Wheels;

import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class BrowseComponents extends JPanel {
	private double framesetCost; 
	private double wheelCost;
	private double handleCost;

	/**
	public BrowseComponents() {
		this.setLayout(new BorderLayout(100, 100));
	}
	**/
	

	public BrowseComponents(Customer cus ,JPanel jp) {

		setLayout(new GridLayout(0, 2, 80, 0));
		
		
		

		
		JPanel selectionPanel = new JPanel();
		selectionPanel.setLayout(new BoxLayout(selectionPanel, BoxLayout.Y_AXIS));
		add(selectionPanel,BorderLayout.WEST);

		JPanel detailPanel = new JPanel();
		detailPanel.setLayout(new BoxLayout(detailPanel, BoxLayout.Y_AXIS));
		add(detailPanel,BorderLayout.CENTER);
		
	
		//frameset selection
		JLabel framesetLabel = new JLabel("Frameset :");
		selectionPanel.add(framesetLabel);
		
		ArrayList<Frameset> allFrames = Frameset.getFramesetList();
		String[] framesets = new String[allFrames.size()];
		for (int i = 0 ; i < allFrames.size(); i++) {
			framesets[i] = allFrames.get(i).getProductName() + " by " + allFrames.get(i).getBrandName();
		}
		
		
		JComboBox<String> framesetComboBox = new JComboBox<String>(framesets);
		
		
		
		
		
		JLabel framesetDetails = new JLabel("Frameset Details : ");
		detailPanel.add(framesetDetails);
		detailPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		selectionPanel.add(framesetComboBox);
		
		
		framesetComboBox.addActionListener(e -> {
		    String framedeets;

		    for (int i = 0; i < allFrames.size(); i++) {
		    	if (framesetComboBox.getSelectedIndex() == i) {
		    		String shock;
		    		if(allFrames.get(i).hasShocks()) {
		    			shock = "Has Shocks";
		    		}
		    		else {
		    			shock = "Doesn't have Shocks";
		    		}
		    		framedeets = "<br/><br/>Brand Name : " + allFrames.get(i).getBrandName() + "<br/>Product Name : " + allFrames.get(i).getProductName()
							+ "<br/>Cost : £" + allFrames.get(i).getCost() + "<br/>Size : " + allFrames.get(i).getSize() + "<br/>Shocks : " + shock
							+ "<br/>No of Gears : " + allFrames.get(i).getGears()+ "<br/>No in Stock " + allFrames.get(i).getStock() + "</html>";
					framesetDetails.setText("<html>Frameset Details : " + framedeets);
					setFramesetCost(allFrames.get(i).getCost());
		    	}
		    }
		});
		
		selectionPanel.add(Box.createRigidArea(new Dimension(0, 30)));

		
		
		
		//wheel selection
		JLabel wheelLabel = new JLabel("Wheels : ");
		selectionPanel.add(wheelLabel);
		
		ArrayList<Wheels> allWheels = Wheels.getWheelList();
		String[] wheels = new String[allWheels.size()];
		for (int i = 0 ; i < allWheels.size(); i++) {
			wheels[i] = allWheels.get(i).getProductName() + " by " + allWheels.get(i).getBrandName();
		}
		
		JComboBox <String>wheelComboBox = new JComboBox<String>(wheels);
		selectionPanel.add(wheelComboBox);
		
		JLabel wheelDetails = new JLabel("Wheel Details : ");
		detailPanel.add(wheelDetails);
		detailPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		selectionPanel.add(wheelComboBox);
		
		
		/*
		for (Wheels whl : allWheels) {
			wheelComboBox.addItem(whl.getProductName() + " by " + whl.getBrandName());
		}
		*/
		
		
		wheelComboBox.addActionListener(e -> {
		    String wheeldeets;

		    for (int i = 0; i < allWheels.size(); i++) {
		    	if (wheelComboBox.getSelectedIndex() == i) {
		    		wheeldeets = "<br/><br/>Brand Name : " + allWheels.get(i).getBrandName() + "<br/>Product Name : " + allWheels.get(i).getProductName()
							+ "<br/>Cost : £" + allWheels.get(i).getCost() + "<br/>Size : " + allWheels.get(i).getDiameter() + " cm"
							+ "<br/>No in Stock : " + allWheels.get(i).getStock() + "</html>";
					wheelDetails.setText("<html>Wheel Details : " + wheeldeets);
					setWheelCost(allWheels.get(i).getCost());
		    	}
		    }
		});
		selectionPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		
		
		
		//handle bar selection
		JLabel handlebarLabel = new JLabel("HandleBars : ");
		selectionPanel.add(handlebarLabel);
		
		
		ArrayList<Handlebars> allHandlebars = Handlebars.getHandlebarsList();
		String[] handlebars = new String[allHandlebars.size()];
		for (int i = 0 ; i < allHandlebars.size(); i++) {
			handlebars[i] = allHandlebars.get(i).getProductName() + " by " + allHandlebars.get(i).getBrandName();
		}
		
		//String[] handlebarOptions = {"HandleBar 1", "HandleBar 2", "HandleBar 3", "HandleBar 4", "Handlebar 5" } ;
		JComboBox <String>handlebarComboBox = new JComboBox<String>(handlebars);
		selectionPanel.add(handlebarComboBox);
		
		JLabel handlebarDetails = new JLabel("HandleBar Details : ");
		detailPanel.add(handlebarDetails);
		detailPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		
		
		
		
		
		
		handlebarComboBox.addActionListener(e -> {
			
		    String handlebardeets;
		    for (int i = 0; i < allHandlebars.size(); i++) {
		    	if (handlebarComboBox.getSelectedIndex() == i) {
		    		handlebardeets = "<br/><br/>Brand Name : " + allHandlebars.get(i).getBrandName() + "<br/>Product Name : " + allHandlebars.get(i).getProductName()
							+ "<br/>Cost : £" + allHandlebars.get(i).getCost() + "<br/>Style : " + allHandlebars.get(i).getStyle()
							+ "<br/>No in Stock : " + allHandlebars.get(i).getStock() + "</html>";
					handlebarDetails.setText("<html>HandleBar Details : " + handlebardeets);
					setHandleCost(allHandlebars.get(i).getCost());
		    	}
		    }
		});
		selectionPanel.add(Box.createRigidArea(new Dimension(0, 30)));
		System.out.println(framesetComboBox.getSelectedIndex());
		
		
		
		//collection dropdown
		JLabel collectLabel = new JLabel("Collect");
		selectionPanel.add(collectLabel);
		
		String[] collectOptions = {"Now", "Later"};
		JComboBox <String>collectComboBox = new JComboBox<String>(collectOptions);
		selectionPanel.add(collectComboBox);
		
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
		
		selectionPanel.add(Box.createRigidArea(new Dimension(0, 30)));
	
		
		
		//finds what index the components have. 
		int frameIndex = framesetComboBox.getSelectedIndex();
		int wheelIndex = wheelComboBox.getSelectedIndex();
		int handleIndex = handlebarComboBox.getSelectedIndex();
		
		
		
		//bike name
		JLabel bikeNameLabel = new JLabel("Bike Name : ");
		selectionPanel.add(bikeNameLabel);
		
		JTextField bikeNameT = new JTextField();
		bikeNameT.setColumns(2);
		selectionPanel.add(bikeNameT);
		
		
		//working out cost of bike
		//double bikeCost = getFrameCost() + getWheelCost() + getHandleCost();
		
		JLabel bikeCostLabel = new JLabel("Bike Cost : " );
		detailPanel.add(bikeCostLabel);
		detailPanel.add(Box.createRigidArea(new Dimension(0, 50)));
		//bikeCostLabel.
		
		
		
		
		//can't check if bike is null or not because object was made in the action listener
		//can try making null object outside, but then it says its a duplicate variable 
		
		
		
		
		
		JButton btnNewButton = new JButton("Back to Homepage");
		selectionPanel.add(Box.createRigidArea(new Dimension(0, 300)));//
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (cus == null) {
					setVisible(false);
					MainFr fr = new MainFr();
					fr.setVisible(true);
				}else {
					selectionPanel.setVisible(false);
					detailPanel.setVisible(false);
					jp.removeAll();
					CustomerMenu m = new CustomerMenu(cus,jp) ; 
					//CustomerOptions m = new CustomerOptions() ; 
					jp.add(m);
					m.setVisible(true);
				}
				
				
				
			}
		});
		selectionPanel.add(btnNewButton);
		selectionPanel.add(Box.createRigidArea(new Dimension(0, 100)));
		

		JButton saveBtn = new JButton("Save");
		detailPanel.add(saveBtn);
		saveBtn.addActionListener(e ->{
			double bikeCost = (allFrames.get(framesetComboBox.getSelectedIndex()).getCost()) + 
					(allHandlebars.get(handlebarComboBox.getSelectedIndex()).getCost())+ 
					(allWheels.get(wheelComboBox.getSelectedIndex()).getCost());
			bikeCostLabel.setText("Bike Cost : " + Double.toString(bikeCost));
		});
		

		if (cus == null) {
			System.out.println("this works");
			JButton registerLabel = new JButton("Register to place order");
			detailPanel.add(registerLabel);
			registerLabel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.out.println("this works");
					Frameset frm = allFrames.get(frameIndex);
					Wheels whl = allWheels.get(wheelIndex);
					Handlebars hdl = allHandlebars.get(handleIndex);
					
							
					Bike bk = new Bike(frm, hdl, whl);
					Order ord = new Order(cus,bk, bikeNameT.getText(), collect);
					
					selectionPanel.setVisible(false);
					detailPanel.setVisible(false);
					jp.removeAll();
					//BrowseComponents b = new BrowseComponents(cus,null,jp);
					RegisterCustomer rC = new RegisterCustomer(ord,jp);//Bike bk = new Bike (uhfeijskd)
					System.out.println("this works");
					jp.add(rC);
					rC.setVisible(true);
					System.out.println("this works");
					//after this it will take to reguester, after a persons registered it will 
					//make them come back to this page and the details of their bike should stay so they can place an order.
				}
			});
			
		}else {
			JButton placeOrderBtn = new JButton("Place Order");
			detailPanel.add(placeOrderBtn);
			placeOrderBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//panel.setVisible(false); 
					Frameset frm = allFrames.get(frameIndex);
					Wheels whl = allWheels.get(wheelIndex);
					Handlebars hdl = allHandlebars.get(handleIndex);
					System.out.print("frem");
							
					Bike bk = new Bike(frm, hdl, whl);
					Order ord = new Order(cus,bk, bikeNameT.getText(), collect);
					
					if (ord.placeOrder()) {
						JOptionPane.showMessageDialog((Component)e.getSource(),"Successfully Placed Order. You will"
								+ " now return Customer Menu. You can head to checkout to pay for order.","Alert",JOptionPane.WARNING_MESSAGE);
						jp.removeAll();
						CustomerMenu m = new CustomerMenu(cus,jp) ; 
						//CustomerOptions m = new CustomerOptions() ; 
						jp.add(m);
						m.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog((Component)e.getSource(),"Order failed. Try again","Alert",JOptionPane.WARNING_MESSAGE);
						
					}
					
				}
			});	
		}
		//System.out.print(framesetCost);
	}
	
	
	public void setFramesetCost(double cost) {
	    framesetCost = cost ; 
	}
	
	public void setWheelCost(double cost) {
		wheelCost = cost;
	}

	public void setHandleCost(double cost) {
		handleCost = cost;
	}
	
	public double getFrameCost() {
		return framesetCost;
	}
	
	public double getWheelCost() {
		return wheelCost;
	}
	
	public double getHandleCost() {
		return handleCost;
	}
	
	
	

}
