package second_implementation_DMap;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;

import second_implementation_DMap.DykstraMap.Road;
import second_implementation_DMap.DykstraMap.Site;
import second_implementation_DMap.DykstraMap.CostCompSite;
import second_implementation_DMap.DykstraMap.Path;

public class DykstraTest {
	DykstraMap m = new DykstraMap();
	
	Site site1 = m.new Site(0,0,0,"1", "desc1", new ArrayList<Road>());
	Site site2 = m.new Site(0,0,0,"2", "desc2", new ArrayList<Road>());
	Site site3 = m.new Site(0,0,0,"3", "desc3", new ArrayList<Road>());
	Site site4 = m.new Site(0,0,0,"4", "desc4", new ArrayList<Road>());
	Site site5 = m.new Site(0,0,0,"5", "desc5", new ArrayList<Road>());
	Site site6 = m.new Site(0,0,0,"6", "desc6", new ArrayList<Road>());
	Site site7 = m.new Site(0,0,0,"7", "desc7", new ArrayList<Road>());
	
	Road r1_2 = m.new Road("1-2", site2, 10, 3);
	Road r2_1 = m.new Road("2-1", site1, 10, 3);
	Road r1_3 = m.new Road("1-3", site3, 5, 7);
	Road r3_1 = m.new Road("3-1", site1, 5, 7);
	Road r1_4 = m.new Road("1-4", site4, 7, 4);
	Road r4_1 = m.new Road("4-1", site1, 7, 4);
	Road r2_3 = m.new Road("2-3", site3, 3, 3);
	Road r3_2 = m.new Road("3-2", site2, 3, 3);
	Road r3_6 = m.new Road("3-6", site6, 3, 3);
	Road r6_3 = m.new Road("6-3", site3, 3, 3);
	Road r4_6 = m.new Road("4-6", site6, 4, 4);
	Road r6_4 = m.new Road("6-4", site4, 4, 4);
	Road r2_5 = m.new Road("2-5", site5, 5, 5);
	Road r5_2 = m.new Road("5-2", site2, 5, 5);
	Road r5_7 = m.new Road("5-7", site7, 2, 2);
	Road r7_5 = m.new Road("7-5", site5, 2, 2);
	Road r6_7 = m.new Road("6-7", site7, 3, 3);
	Road r7_6 = m.new Road("7-6", site6, 3, 3);
	
	public void testDykstraConstruct() {//tests constructors + makes the graph we'll use for the rest of the tests
	
	ArrayList<Road> roads = new ArrayList<Road>();
	
	site1.getRoads().add(r1_2);
	site1.getRoads().add(r1_3);
	site1.getRoads().add(r1_4);
	
	
	site2.getRoads().add(r2_1);
	site2.getRoads().add(r2_3);
	site2.getRoads().add(r2_5);
	
	
	site3.getRoads().add(r3_1);
	site3.getRoads().add(r3_2);
	site3.getRoads().add(r3_6);
	
	
	
	site4.getRoads().add(r4_1);
	site4.getRoads().add(r4_6);
	
	
	
	site5.getRoads().add(r5_2);
	site5.getRoads().add(r5_7);
	
	site6.getRoads().add(r6_3);
	site6.getRoads().add(r6_4);
	site6.getRoads().add(r6_7);

	
	site7.getRoads().add(r7_5);
	site7.getRoads().add(r7_6);
	
	site7.setRoads(roads);
	roads.clear();
	
	m.add(site1);
	m.add(site2);
	m.add(site3);
	m.add(site4);
	m.add(site5);
	m.add(site6);
	m.add(site7);
}
	@Test
	public void basicTest() {
		testDykstraConstruct();
		//from 1 to 2
		LinkedList<Road> spbase = new LinkedList<Road>();
		spbase.add(r1_2);
		Path sp = m.new Path(spbase);
		assertEquals(sp,(m.shortestPath(site1, site2, m.getSiteList())));
		
		spbase.add(r2_3);
		sp = m.new Path(spbase);
		assertEquals(sp,(m.shortestPath(site1, site3,m.getSiteList())));
	}
}
