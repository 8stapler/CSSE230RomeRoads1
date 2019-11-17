package graphics;

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

import main.DykstraMap;
import main.Road;
import main.Site;
import main.Path;


public class RouteMenuListener implements ActionListener {
	static JComboBox cost;
	static JComboBox fromSite;
	static JComboBox toSite;
	static JTextField amount;
	static JLabel route;
	static DykstraMap map = new DykstraMap();
	TreeSet<Site> siteList = map.getSiteList();
	
	public void actionPerformed(ActionEvent arg0) {
		RouteMenu menu = new RouteMenu();
		menu.createMenu();
	}
	
	public class RouteMenu implements ItemListener {
		
		public void createMenu() {
			JFrame frame = new JFrame("Browse Sites");
			frame.setSize(380, 500);
			
			RouteMenu r = new RouteMenu();
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.setBorder(new EmptyBorder(new Insets(50, 0, 0, 0)));
			
			try {
				map.generateMap("src/graphics/Roma.txt");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			Site[] sites = new Site[siteList.size()];
			String[] siteStrings = new String[siteList.size()]; 
			int i = 0;
			for (Site site : siteList) {
				siteStrings[i] = site.getName();
				i++;
			}
			
			String costs[] = {"distance", "scenery", "history"};
			
			JLabel costText = new JLabel("Find best route considering:");
			costText.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			JLabel specifyDistance = new JLabel("Specify a maximum distance (scenery or history only)");
			specifyDistance.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			JLabel from = new JLabel("Starting point:");
			from.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			JLabel to = new JLabel("Destination:");
			to.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			amount = new JTextField(10);
			amount.setMaximumSize(new Dimension(150,40));
			
			cost = new JComboBox(costs);
			cost.setSelectedItem("distance");
			cost.setMaximumSize(new Dimension(150,40));
			cost.addItemListener(r);
			
			fromSite = new JComboBox(siteStrings);
			fromSite.setSelectedItem("site1");
			fromSite.setMaximumSize(new Dimension(150,40));
			fromSite.addItemListener(r);
			
			toSite = new JComboBox(siteStrings);
			toSite.setSelectedItem("site1");
			toSite.setMaximumSize(new Dimension(150,40));
			toSite.addItemListener(r);
			
			route = new JLabel("");
			route.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			panel.add(costText);
			panel.add(cost);
			panel.add(specifyDistance);
			panel.add(amount);
			panel.add(from);
			panel.add(fromSite);
			panel.add(to);
			panel.add(toSite);
			panel.add(route);
			
			frame.add(panel);
			frame.setVisible(true);
		}
		
		public void itemStateChanged(ItemEvent e) {
			Site f = null;
			Site t = null;
			
			for(Site s : siteList) {
				if(s.getName().equals(fromSite.getSelectedItem())) {
					f = s;
				}
			}
			
			for(Site s : siteList) {
				if(s.getName().equals(toSite.getSelectedItem())) {
					t = s;
				}
			}
			
       		if (e.getSource() == fromSite || e.getSource() == toSite || e.getSource() == cost) { 
       			if (cost.getSelectedItem().equals("history")) {
           			if(amount.getText().equals("")) {
           				route.setText("Enter a value");
           			}
           			else {
           				Path path = map.historyestPath(f, t, Integer.parseInt(amount.getText()));
           				route.setText("<html><p>" + path.toString() + "</p></html>\"");
           			}
       			}
       			if (cost.getSelectedItem().equals("scenery")) {
           			if(amount.getText().equals("")) {
           				route.setText("Enter a value");
           			}
           			else {
           				Path path = map.scenestPath(f, t, Integer.parseInt(amount.getText()));
           				route.setText("<html><p>" + path.toString() + "</p></html>\"");
           			}
       			}
       			else {
       				Path path = map.shortestPath(f, t, new ArrayList<Road>());
       				route.setText("<html><p>" + path.toString() + "</p></html>\"");
       			}
       		}
		}
	}
}
