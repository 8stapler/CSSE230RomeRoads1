package main;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import org.junit.Test;





public class DykstraTest {
	DykstraMap m = new DykstraMap();
	
	Site site1 = new Site(0,0,4,"1", "desc1", new ArrayList<Road>());
	Site site2 = new Site(0,0,7,"2", "desc2", new ArrayList<Road>());
	Site site3 = new Site(0,0,2,"3", "desc3", new ArrayList<Road>());
	Site site4 = new Site(0,0,5,"4", "desc4", new ArrayList<Road>());
	Site site5 = new Site(0,0,3,"5", "desc5", new ArrayList<Road>());
	Site site6 = new Site(0,0,6,"6", "desc6", new ArrayList<Road>());
	Site site7 = new Site(0,0,4,"7", "desc7", new ArrayList<Road>());
	Site site8 =  new Site(0,0,2,"8", "desc8", new ArrayList<Road>());
	
	Road r1_8 =  new Road("1-8", site8, 1, 1);
	Road r8_1 =  new Road("8-1", site1, 1, 1);
	
	Road r1_3 =  new Road("1-3", site3, 7, 7);
	Road r3_1 =  new Road("3-1", site1, 7, 7);
	
	Road r1_4 =  new Road("1-4", site4, 7, 7);
	Road r4_1 =  new Road("4-1", site1, 7, 7);
	
	Road r2_8 =  new Road("2-8", site8, 2, 2);
	Road r8_2 =  new Road("8-2", site2, 2, 2);
	
	Road r2_3 =  new Road("2-3", site3, 3, 3);
	Road r3_2 =  new Road("3-2", site2, 3, 3);
	
	Road r3_6 =  new Road("3-6", site6, 3, 3);
	Road r6_3 =  new Road("6-3", site3, 3, 3);
	
	Road r4_6 =  new Road("4-6", site6, 4, 4);
	Road r6_4 =  new Road("6-4", site4, 4, 4);
	
	Road r2_5 =  new Road("2-5", site5, 5, 5);
	Road r5_2 =  new Road("5-2", site2, 5, 5);
	
	Road r5_7 =  new Road("5-7", site7, 2, 2);
	Road r7_5 =  new Road("7-5", site5, 2, 2);
	
	Road r6_7 =  new Road("6-7", site7, 3, 3);
	Road r7_6 =  new Road("7-6", site6, 3, 3);
	
	public void testDykstraConstruct() {//tests constructors + makes the graph we'll use for the rest of the tests
	
	ArrayList<Road> roads = new ArrayList<Road>();
	
	site1.getRoads().add(r1_8);
	site1.getRoads().add(r1_3);
	site1.getRoads().add(r1_4);
	
	
	site2.getRoads().add(r2_8);
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
	
	site8.getRoads().add(r8_1);
	site8.getRoads().add(r8_2);
	
	m.add(site1);
	m.add(site2);
	m.add(site3);
	m.add(site4);
	m.add(site5);
	m.add(site6);
	m.add(site7);
	m.add(site8);
}
	@Test
	public void testShortestPath1_2() {
		testDykstraConstruct();
		//from 1 to 2
		LinkedList<Road> spbase = new LinkedList<Road>();
		spbase.add(r1_8);
		spbase.add(r8_2);
		Path sp = new Path(spbase);
		assertEquals(sp,(m.shortestPath(site1, site2, new ArrayList<Road>())));
		
		
	}
	
	@Test
	public void testShortestPath1_3(){
		testDykstraConstruct();
		LinkedList<Road> spbase = new LinkedList<Road>();
		spbase.add(r1_8);
		spbase.add(r8_2);
		spbase.add(r2_3);
		Path sp = new Path(spbase);
		assertEquals(sp,(m.shortestPath(site1, site3,new ArrayList<Road>())));
	}
	

	@Test
	public void testShortestPath4_7(){
		testDykstraConstruct();
		LinkedList<Road> spbase = new LinkedList<Road>();
		spbase.add(r4_6);
		spbase.add(r6_7);
		
		Path sp = new Path(spbase);
		assertEquals(sp,(m.shortestPath(site4, site7,new ArrayList<Road>())));
	}
	
	@Test
	public void testShortestPath4_5(){
		testDykstraConstruct();
		LinkedList<Road> spbase = new LinkedList<Road>();
		spbase.add(r4_6);
		spbase.add(r6_7);
		spbase.add(r7_5);
		
		Path sp = new Path(spbase);
		assertEquals(sp,(m.shortestPath(site4, site5,new ArrayList<Road>())));
	}
	
	@Test
	public void testTripPlanner() {
		testDykstraConstruct();
		ArrayList<Site> destinations = new ArrayList<Site>();
		destinations.add(site3);
		destinations.add(site2);
		destinations.add(site6);
		
		assertEquals(destinations, m.tripPlanner(site3, 3));
		destinations.clear();
		
		destinations.add(site3);
		destinations.add(site2);
		destinations.add(site6);
		destinations.add(site8);
		destinations.add(site7);
		destinations.add(site1);
		
		assertEquals(destinations, m.tripPlanner(site3,  6));
	}
	
	@Test
	public void testScenestPath() {
		testDykstraConstruct();
		LinkedList<Road> spbase = new LinkedList<Road>();
				
		spbase.add(r1_8);
		spbase.add(r8_2);
		Path sp = new Path(spbase);
		assertEquals(sp, m.scenestPath(site1, site2, 0));
		spbase.clear();
		
		spbase.add(r1_8);
		spbase.add(r8_2);
		
		sp = new Path(spbase);
	
		assertEquals(sp,m.scenestPath(site1, site2, 3));
		spbase.clear();
		
		spbase.add(r1_3);
		spbase.add(r3_2);
		
		sp = new Path(spbase);
		spbase.clear();
		assertEquals(sp,m.scenestPath(site1, site2, 11));
		
		spbase.add(r1_4);
		spbase.add(r4_6);
		spbase.add(r6_3);
		spbase.add(r3_2);
		sp = new Path(spbase);
		spbase.clear();
		assertEquals(sp,m.scenestPath(site1, site2, 17));
	}
	
	@Test
	public void testHistestPath() {
		testDykstraConstruct();
		LinkedList<Road> spbase = new LinkedList<Road>();
		
		spbase.add(r1_8);
		spbase.add(r8_2);
		spbase.add(r2_5);
		spbase.add(r5_7);
		spbase.add(r7_6);
		Path sp = new Path(spbase);
		spbase.clear();
		assertEquals(sp, m.historyestPath(site1, site6, 20));
		
		spbase.add(r1_4);
		sp = new Path(spbase);
		assertEquals(sp,m.historyestPath(site1, site4, 7));
	}
}
