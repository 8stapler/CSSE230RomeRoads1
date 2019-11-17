package graphics;

import second_implementation_DMap.DykstraMap;
import second_implementation_DMap.DykstraMap.Site;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import MapWithSearchAlgo.DykstraMap.Path;

public class PlannerMenuListener implements ActionListener {
	static JComboBox site;
	static JTextField amount;
	static JLabel route;
	static DykstraMap map = new DykstraMap();
	TreeSet<Site> siteList = map.getSiteList();

	
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
			
			try {
				map.generateMap("C:\\\\Users\\\\stapler\\\\git\\\\CSSE230RomeRoads3\\\\src\\\\code\\\\Roma.txt");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String[] siteStrings = new String[siteList.size()]; 
			int i = 0;
			for (Site site : siteList) {
				siteStrings[i] = site.getName();
				i++;
			}

			
			JLabel costText = new JLabel("Find a route with a distance of:");
			costText.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			JLabel start = new JLabel("Starting point:");
			start.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			amount = new JTextField(10);
			amount.setMaximumSize(new Dimension(150,40));
			
			site = new JComboBox(siteStrings);
			site.setSelectedItem("site1");
			site.setMaximumSize(new Dimension(150,40));
			site.addItemListener(p);
			
			route = new JLabel("");
			route.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			panel.add(costText);
			panel.add(amount);
			panel.add(start);
			panel.add(site);
			panel.add(route);
			
			frame.add(panel);
			frame.setVisible(true);
		}
		
		public void itemStateChanged(ItemEvent e) {
			Site x = null;
			for(Site s : siteList) {
				if(s.getName().equals(site.getSelectedItem())) {
					x = s;
				}
			}
			
			
       		if (e.getSource() == site) { 
       			route.setText("");
       			if(amount.getText().equals("")) {
       				route.setText("Enter a value");
       			}
       			else {
       				ArrayList<Path> path = map.tripPlanner(x, Integer.parseInt(amount.getText()));
       				route.setText("<html><p>" + path.toString() + "</p></html>\"");
       			}
       		} 
		}
	}
}

