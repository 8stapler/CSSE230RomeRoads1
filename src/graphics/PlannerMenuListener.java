package graphics;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PlannerMenuListener implements ActionListener {
	static JComboBox cost;
	static JComboBox site;
	static JTextField amount;
	static JLabel route;
	
	public void actionPerformed(ActionEvent e) {
		PlannerMenu menu = new PlannerMenu();
		menu.createMenu();
	}

	public class PlannerMenu implements ItemListener {
		
		public void createMenu() {
			JFrame frame = new JFrame("Route Planner");
			frame.setSize(320, 500);
			
			PlannerMenu p = new PlannerMenu();
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.setBorder(new EmptyBorder(new Insets(50, 0, 0, 0)));
			
			String test[] = {"site1", "site2", "site3"};
			String testCost[] = {"time", "distance"};
			
			JLabel costText = new JLabel("Find a route with:");
			costText.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			JLabel start = new JLabel("Starting point:");
			start.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			amount = new JTextField(10);
			amount.setMaximumSize(new Dimension(150,40));

			cost = new JComboBox(testCost);
			cost.setSelectedItem("distance");
			cost.setMaximumSize(new Dimension(150,40));
			cost.addItemListener(p);
			
			site = new JComboBox(test);
			site.setSelectedItem("site1");
			site.setMaximumSize(new Dimension(150,40));
			site.addItemListener(p);
			
			route = new JLabel("");
			route.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			panel.add(costText);
			panel.add(amount);
			panel.add(cost);
			panel.add(start);
			panel.add(site);
			panel.add(route);
			
			frame.add(panel);
			frame.setVisible(true);
		}
		
		public void itemStateChanged(ItemEvent e) {
       		if (e.getSource() == cost || e.getSource() == site) { 
       			route.setText("Trip starting at " + site.getSelectedItem() + " with " + amount.getText() + " " + cost.getSelectedItem()); 
       		} 
		}
	}
}

