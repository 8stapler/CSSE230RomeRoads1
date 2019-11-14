package graphics;

import code.Site;
import code.Road;

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
		TreeSet<Site> sites = new TreeSet<Site>();
		Site site1 = new Site(350, 60, 0, "site1", "", new ArrayList<Road>());
		Site site2 = new Site(100, 400, 0, "site2longname", "", new ArrayList<Road>());
		Site site3 = new Site(600, 500, 0, "site3", "", new ArrayList<Road>());
		Road road1 = new Road("site1", site2, 0, 0);
		Road road2 = new Road("site1", site3, 0, 0);
		site1.addRoad(road1);
		site1.addRoad(road2);
		sites.add(site1);
		sites.add(site2);
		sites.add(site3);
		
		//draw roads
		for(Site site : sites) {
			for(Road road : site.getRoads()) {
				g2.draw(new Line2D.Double(site.getXPos(), site.getYPos(), road.getSite().getXPos(), road.getSite().getYPos()));
			}	
		}
		
		//draw sites with names
		for(Site site : sites) {
			double x = site.getXPos();
			double y = site.getYPos();
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
