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

public class SiteMenuListener implements ActionListener {
	static JFrame frame;
	static JLabel siteInfo;
	static JComboBox siteList;
	static SiteMenu menu;
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		menu = new SiteMenu();
		menu.createMenu();
	}
	public class SiteMenu implements ItemListener{
		public void createMenu() {
			frame = new JFrame("Site List");
			frame.setSize(300, 500);
			SiteMenu s = new SiteMenu();
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
			panel.setBorder(new EmptyBorder(new Insets(50, 10, 0, 0)));
			String test[] = {"site1", "site2", "site3"};
			siteList = new JComboBox(test);
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
       		if (e.getSource() == siteList) { 
       			siteInfo.setText(siteList.getSelectedItem() + " info here"); 
       		} 
    	}
	}
}
