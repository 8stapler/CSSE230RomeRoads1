package graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainUI extends JFrame{
	
	public MainUI() {
		
		JFrame frame = new JFrame("Rome Roads");
		MainMenu m = new MainMenu();
		frame.setSize(1000, 700);
		frame.setVisible(true);
		frame.add(m);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public class MainMenu extends JPanel{
		
		public MainMenu() {
			GridBagConstraints gbc = new GridBagConstraints();
			setLayout(new GridBagLayout());
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.insets = new Insets(0, 75, 0, 0);
			
			JLabel label = new JLabel("Roman Roads");
			label.setFont(new Font("Verdana", Font.PLAIN, 18));
			gbc.fill = GridBagConstraints.VERTICAL;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.weightx = 1;
			gbc.weighty = 1;
			add(label, gbc);
			
			gbc.insets = new Insets(20, 20, 60, 0);
			
			JButton b1 = new JButton("Browse Sites");
			gbc.ipadx = 150;
			gbc.gridy = 1;
			b1.addActionListener(new SiteMenuListener());
			b1.setPreferredSize(new Dimension(80, 40));
			add(b1, gbc);
			
			JButton b2 = new JButton("Search Routes"); 
			gbc.gridy = 2;
			b2.addActionListener(new RouteMenuListener());
			b2.setPreferredSize(new Dimension(80, 40));
			add(b2, gbc);
			
			JButton b3 = new JButton("Trip Planner"); 
			gbc.gridy = 3;
			b3.addActionListener(new PlannerMenuListener());
			b3.setPreferredSize(new Dimension(80, 40));
			add(b3, gbc);
			
			MapComponent map = new MapComponent();
			gbc.gridx = 1;
			gbc.gridy = 0;
			gbc.weightx = 50;
			gbc.gridheight = 4;
			add(map, gbc);
		}
	}
}
