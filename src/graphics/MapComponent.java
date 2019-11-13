package graphics;

import code.Site;
import code.Road;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComponent;

public class MapComponent extends JComponent {
	
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		setSize(800, 700);
		
		HashMap<String,Site> sites = new HashMap<String,Site>();
		Site site1 = new Site(350, 60, 0, "site1", "", new ArrayList<Road>());
		Site site2 = new Site(100, 400, 0, "site2", "", new ArrayList<Road>());
		Site site3 = new Site(600, 500, 0, "site3", "", new ArrayList<Road>());
		Road road1 = new Road("site1", site2, 0, 0);
		Road road2 = new Road("site1", site3, 0, 0);
		site1.addRoad(road1);
		site1.addRoad(road2);
		sites.put("site1", site1);
		sites.put("site2", site2);
		sites.put("site3", site3);
		
		for(Site site : sites.values()) {
			ArrayList<Road> roads = site.getRoads();
			for(Road road : roads) {
				g2.draw(new Line2D.Double(site.getXPos(), site.getYPos(), road.getSite().getXPos(), road.getSite().getYPos()));
			}	
		}
		
		for(Site site : sites.values()) {
			Ellipse2D.Double circ = new Ellipse2D.Double(site.getXPos() - 15, site.getYPos() - 15, 30, 30);
			g2.setColor(Color.RED);
			g2.fill(circ);
			g2.setColor(Color.BLACK);
			g2.draw(circ);		
			}
		

		
		
	}
}
