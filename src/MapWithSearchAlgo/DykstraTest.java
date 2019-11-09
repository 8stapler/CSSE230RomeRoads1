package MapWithSearchAlgo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import MapWithSearchAlgo.DykstraMap.Road;
import MapWithSearchAlgo.DykstraMap.Site;
import MapWithSearchAlgo.DykstraMap.CostCompSite;

public class DykstraTest {
	DykstraMap m = new DykstraMap();
	
	public void testDykstra() {
	
	ArrayList<Road> roads = new ArrayList<Road>();
	
	Road r1 = m.new Road("1-2", null, 10, 3);
	Road r2 = m.new Road("1-3", null, 5, 7);
	Road r3 = m.new Road("1-4", null, 7, 4);
	
	roads.add(r1);
	roads.add(r2);
	roads.add(r3);
	
	Site site1 = m.new Site(0, 0, 0, "1", "desc", roads);
	
	
	}
}
