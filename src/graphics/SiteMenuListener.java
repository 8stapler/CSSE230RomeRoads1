package graphics;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.TreeSet;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.DykstraMap;
import main.Site;

public class SiteMenuListener implements ActionListener {
	static JLabel siteInfo;
	static JComboBox siteList;
	static DykstraMap map = new DykstraMap();
	TreeSet<Site> sites = map.getSiteList();
	
	public void actionPerformed(ActionEvent arg0) {
		SiteMenu menu = new SiteMenu();
		menu.createMenu();
	}
	public class SiteMenu implements ItemListener{
		
		public void createMenu() {
			JFrame frame = new JFrame("Browse Sites");
			frame.setSize(320, 500);
			
			SiteMenu s = new SiteMenu();
			
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.setBorder(new EmptyBorder(new Insets(50, 0, 0, 0)));
			;
			try {
				map.generateMap("src/graphics/Roma.txt");
			} catch (Exception e) {
				e.printStackTrace();
			}
			Site[] siteArray = new Site[sites.size()];
			String[] siteStrings = new String[sites.size()]; 
			int i = 0;
			for (Site site : sites) {
				siteStrings[i] = site.getName();
				i++;
			}
			
			siteList = new JComboBox(siteStrings);
			siteList.setMaximumSize(new Dimension(150,40));
			siteList.addItemListener(s);
			siteList.setEditable(true);
			
			siteInfo = new JLabel("Select a site");
			siteInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
			
			panel.add(siteList);
			panel.add(siteInfo);
			
			frame.add(panel);
			frame.setVisible(true);
		}
		
    	public void itemStateChanged(ItemEvent e) { 
			Site x = null;
			for(Site s : sites) {
				if(s.getName().equals(siteList.getSelectedItem())) {
					x = s;
				}
			}
			
       		if (e.getSource() == siteList) { 
       			if (x != null) {
       				siteInfo.setText("<html><p>" + x.getDescription() + "</p></html>\"");
       			}
       			else {
       				siteInfo.setText("Site not found");
       			}
       		} 
    	}
	}
}
