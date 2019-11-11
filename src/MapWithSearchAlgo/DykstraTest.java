package MapWithSearchAlgo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;

import MapWithSearchAlgo.DykstraMap.Road;
import MapWithSearchAlgo.DykstraMap.Site;
import MapWithSearchAlgo.DykstraMap.CostCompSite;
import MapWithSearchAlgo.DykstraMap.Path;

public class DykstraTest {
	DykstraMap m = new DykstraMap();
	
	Site site1 = m.new Site(0,0,0,"a1", "desc1", null);
	Site site2 = m.new Site(0,0,0,"b2", "desc2", null);
	Site site3 = m.new Site(0,0,0,"c3", "desc3", null);
	Site site4 = m.new Site(0,0,0,"d4", "desc4", null);
	Site site5 = m.new Site(0,0,0,"e5", "desc5", null);
	Site site6 = m.new Site(0,0,0,"f6", "desc6", null);
	Site site7 = m.new Site(0,0,0,"g7", "desc7", null);
	
	Road r1_2 = m.new Road("1-2", site2, 10, 3);
	Road r2_1 = m.new Road("2-1", site1, 10, 3);
	Road r1_3 = m.new Road("1-3", site3, 5, 7);
	Road r3_1 = m.new Road("3-1", site1, 5, 7);
	Road r1_4 = m.new Road("1-4", site4, 7, 4);
	Road r4_1 = m.new Road("4-1", site1, 7, 4);
	Road r2_3 = m.new Road("2-3", site3, 3, 3);
	Road r3_2 = m.new Road("3-2", site2, 3, 3);
	Road r3_6 = m.new Road("3-6", site6, 2, 2);
	Road r6_3 = m.new Road("6-3", site3, 2, 2);
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
	
	roads.add(r1_2);
	roads.add(r1_3);
	roads.add(r1_4);
	
	site1.setRoads(roads);
	roads.clear();
	
	roads.add(r2_1);
	roads.add(r2_3);
	roads.add(r2_5);
	
	site2.setRoads(roads);
	roads.clear();
	
	roads.add(r3_1);
	roads.add(r3_2);
	roads.add(r3_6);
	
	site3.setRoads(roads);
	roads.clear();
	
	roads.add(r4_1);
	roads.add(r4_6);
	
	site4.setRoads(roads);
	roads.clear();
	
	roads.add(r5_2);
	roads.add(r5_7);
	
	site5.setRoads(roads);
	roads.clear();
	
	roads.add(r6_3);
	roads.add(r6_4);
	roads.add(r6_7);
	
	site6.setRoads(roads);
	roads.clear();
	
	roads.add(r7_5);
	roads.add(r7_6);
	
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
	public void testShortestPath() {
		
		//from 1 to 2
		LinkedList<Road> spbase = new LinkedList<Road>();
		spbase.add(r1_2);
		Path sp = m.new Path(spbase);
		assertEquals(sp,(m.shortestPath(site1, site2, m.getSiteList())));
	}
}
