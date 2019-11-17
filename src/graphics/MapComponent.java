package graphics;

import main.Site;
import main.DykstraMap;
import main.Road;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.TreeSet;

import javax.swing.JComponent;

public class MapComponent extends JComponent {
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		setSize(800, 700);
		
		//test sites delete later 
		DykstraMap map = new DykstraMap();
		try {
			map.generateMap("src/graphics/Roma.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		TreeSet<Site> sites = map.getSiteList();
		
		//draw roads
		for(Site site : sites) {
			for(Road road : site.getRoads()) {
				g2.draw(new Line2D.Double((site.getXPos() - 8) * 100, 700 - ((site.getYPos() - 40) * 100), (road.getSite().getXPos() - 8) * 100, (700 - (road.getSite().getYPos() - 40) * 100)));
			}	
		}
		
		//draw sites with names
		for(Site site : sites) {
			double x = (site.getXPos() - 8) * 100;
			double y = 700 - ((site.getYPos() - 40) * 100);
			Ellipse2D.Double circ = new Ellipse2D.Double(x - 15, y - 15, 30, 30);
			String name = site.getName();
			int width = g2.getFontMetrics().stringWidth(name);
			
			g2.setColor(Color.RED);
			g2.fill(circ);
			g2.setColor(Color.BLACK);
			g2.draw(circ);
			
			int stringx = (int) x - width/2;
			int stringy = (int) y - 25;
			g2.setColor(Color.WHITE);
			g2.fillRect(stringx - 10, stringy - 15, width + 20, 20);
			g2.setColor(Color.BLACK);
			g2.drawRect(stringx - 10, stringy - 15, width + 20, 20);
			g2.drawString(name, stringx, stringy);
		}
	}
}
