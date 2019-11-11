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
		
		JButton b1, b2, b3;
		JLabel l1;
		GridBagConstraints gbc = new GridBagConstraints();
		
		public MainMenu() {
			
			setLayout(new GridBagLayout());
			
			gbc.anchor = GridBagConstraints.NORTHWEST;
			
			gbc.insets = new Insets(0, 65, 0, 0);
			
			l1 = new JLabel("Roman Roads");
			l1.setFont(new Font("Verdana", Font.PLAIN, 18));
			gbc.fill = GridBagConstraints.VERTICAL;
			gbc.ipadx = 150;
			gbc.gridx = 0;
			gbc.gridy = 0;
			gbc.gridwidth = 1;
			gbc.weightx = 1;
			gbc.weighty = 1;
			add(l1, gbc);
			
			gbc.insets = new Insets(20, 10, 60, 0);
			
			b1 = new JButton("Browse Sites");
			gbc.fill = GridBagConstraints.VERTICAL;
			gbc.gridx = 0;
			gbc.gridy = 1;
			gbc.gridwidth = 1;
			gbc.weightx = 1;
			gbc.weighty = 1;
			b1.addActionListener(new SiteMenuListener());
			b1.setPreferredSize(new Dimension(80, 40));
			add(b1, gbc);
			
			b2 = new JButton("Search Routes"); 
			gbc.fill = GridBagConstraints.VERTICAL;
			gbc.gridx = 0;
			gbc.gridy = 2;
			gbc.gridwidth = 1;
			gbc.weightx = 1;
			b2.setPreferredSize(new Dimension(80, 40));
			add(b2, gbc);
			
			b3 = new JButton("Trip Planner"); 
			gbc.fill = GridBagConstraints.VERTICAL;
			gbc.gridx = 0;
			gbc.gridy = 3;
			gbc.gridwidth = 1;
			gbc.weightx = 1;
			b3.setPreferredSize(new Dimension(80, 40));
			add(b3, gbc);
		}
	}
}
