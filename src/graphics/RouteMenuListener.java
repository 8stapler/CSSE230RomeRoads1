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
import javax.swing.border.EmptyBorder;


public class RouteMenuListener implements ActionListener {
	static JComboBox cost;
	static JComboBox fromSite;
	static JComboBox toSite;
	static JLabel route;
	
	public void actionPerformed(ActionEvent arg0) {
		RouteMenu menu = new RouteMenu();
		menu.createMenu();
	}
	
	public class RouteMenu implements ItemListener {
		
		public void createMenu() {
			JFrame frame = new JFrame("Browse Sites");
			frame.setSize(320, 500);
			
			RouteMenu r = new RouteMenu();
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.setBorder(new EmptyBorder(new Insets(50, 0, 0, 0)));
			
			String test[] = {"site1", "site2", "site3"};
			String testCost[] = {"time", "distance"};
			
			JLabel costText = new JLabel("Find best route considering:");
			costText.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			JLabel from = new JLabel("Starting point:");
			from.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			JLabel to = new JLabel("Destination:");
			to.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			cost = new JComboBox(testCost);
			cost.setSelectedItem("time");
			cost.setMaximumSize(new Dimension(150,40));
			cost.addItemListener(r);
			
			fromSite = new JComboBox(test);
			fromSite.setSelectedItem("site1");
			fromSite.setMaximumSize(new Dimension(150,40));
			fromSite.addItemListener(r);
			
			toSite = new JComboBox(test);
			toSite.setSelectedItem("site1");
			toSite.setMaximumSize(new Dimension(150,40));
			toSite.addItemListener(r);
			
			route = new JLabel("");
			route.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			panel.add(costText);
			panel.add(cost);
			panel.add(from);
			panel.add(fromSite);
			panel.add(to);
			panel.add(toSite);
			panel.add(route);
			
			frame.add(panel);
			frame.setVisible(true);
		}
		
		public void itemStateChanged(ItemEvent e) {
       		if (e.getSource() == fromSite || e.getSource() == toSite ||e.getSource() == cost) { 
       			route.setText("route from " + fromSite.getSelectedItem() + " to " + toSite.getSelectedItem() + " considering " + cost.getSelectedItem()); 
       		} 
		}
		
	}
}
