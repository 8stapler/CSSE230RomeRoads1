package second_implementation_DMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;


public class DykstraMap<E extends Comparable<?super E>> {
	public static final int approxSites=50;
	public static final int MAXSHORTESTPATHS=50000;
	
	HashMap<String,Site> sites;
	TreeSet<Site> siteList;
	private MyInteger pathCount;
	private int minCost;
	private ArrayList<Path> paths;
	
	private HashMap<String,Road> prohibitedRoads;
	
	public DykstraMap() {
		sites= new HashMap<String, Site>(approxSites);
		siteList= new TreeSet<Site>();
		paths = new ArrayList<Path>();
		pathCount= new MyInteger(0);
		minCost=-1;
	}
	
	public boolean add(Site toAdd) {
		boolean added=siteList.add(toAdd);
		sites.put(toAdd.getName(),toAdd);
		return added;
	}
	public boolean remove(Site toKill) {
		if(siteList.size()==0)return false;
		boolean removed = siteList.remove(toKill);
		sites.remove(toKill.getName());
		return removed;
	}
	public ArrayList<String> listSites(){
		ArrayList<String> temp = new ArrayList<String>(siteList.size());
		for(Site site:siteList) {
			temp.add(site.getName());
		}
		
		return temp;
	}

	public TreeSet<Site> getSiteList() {
		return siteList;
	}
	
	public Path shortestPath(Site start, Site end, TreeSet<Site> fullGraph){

		//Set up known arrayList & unknown priorityQueue
		start.setDistFrom(0);
		
		ArrayList<CostCompSite> known = new ArrayList<CostCompSite>();
		PriorityQueue<CostCompSite> unknown = new PriorityQueue<CostCompSite>();
		
		for(Site s: fullGraph) {
			unknown.add(s.getCostComp());
		}
		
		//Calculate distTo Current's neighbors until current is destination
		CostCompSite current = start.getCostComp();
		
		while(unknown.peek()!=null) {
			
			current=unknown.poll();
			
			if(current.getMySite().getDistFrom()==Integer.MAX_VALUE) {
				return null; //no path
			}
			
			if(current.getMySite().toString().equals(end.toString())) {
				break;
			}
			
			for(Road r: current.getMySite().getRoads()) {
				
				
				int distFromHere = current.getMySite().getDistFrom() + r.getTimeCost();
				
				if(distFromHere < r.getSite().getDistFrom()) {
					r.getSite().setDistFrom(distFromHere);
					
					//Make able to trace back to start (determine path)
					r.getSite().setToRoad(r);
					r.getSite().setPrevSite(current.getMySite());
					
					//Update priorityQueue
					unknown.remove(r.getSite().getCostComp());
					unknown.add(r.getSite().getCostComp());
				}
				
			}
			
		}
		//At this point, we should have the shortest path
		//Work back from end to get shortest path
			
		
		
		
		LinkedList<Road>temp = new LinkedList<Road>();
		while(current!=null && current.getMySite().getToRoad()!=null) {
			
			temp.addFirst(current.getMySite().getToRoad());
			current=current.getMySite().getPrevSite().getCostComp();
			
		}
		return new Path(temp);
		
	}
	
	
	public Path scenestPath(Site start, Site end, int maxCost, ArrayList<Road> bannedRoads) { // Will only check at most the first 100,000 short paths (calculated)
		
		return null;
	}
	


public class CostCompSite implements Comparable{

	private Site mySite;
	
	public CostCompSite(Site s) {

		mySite = s;
		
	}
	
	public int compareTo(Object otherSite) {
		int compare =mySite.getDistFrom()-(((CostCompSite)otherSite).mySite.getDistFrom());
		
		if(compare==0)return 0; 
			
			
		return compare/Math.abs(compare);
	}
	public String toString() {
		return mySite.toString();
	}
	
	public Site getMySite() {
		return mySite;
	}
		
	}
	
public class Site implements Comparable {

		private int xPos;
		private int yPos;
		private int hist;
		private String name;
		private String desc;
		private ArrayList<Road> roads= new ArrayList<Road>();
		private int distFrom;
		private int sceneFrom;
		private int histFrom;
		private CostCompSite costComp;
		private Site prevSite;
		private Road toRoad;
		
		public Site(int xPosition, int yPosition, int history, String name, String description, ArrayList<Road> roadybois) {
			
			xPos=xPosition;
			yPos=yPosition;
			hist=history;
			this.name=name;
			desc=description;
			roads=roadybois;
			distFrom=Integer.MAX_VALUE;
			sceneFrom=-1;
			histFrom=-1;
			
			costComp=new CostCompSite(this);
		}
		
		public CostCompSite getCostComp() {
			return costComp;
			
		}
		public void setToRoad(Road a) {
			toRoad = a;
		}
		public Road getToRoad() {
			return toRoad;
		}
		public void setPrevSite(Site a) {
			prevSite=a;
		}
		public Site getPrevSite() {
			return prevSite;
		}
		public ArrayList<Road> getRoads(){
			return roads;
		}
		public void setRoads(ArrayList<Road>r) {
			roads = r;
		}
		public String toString() {
			return name;
		}
		public String getName() {
			return name;
		}
		public void setCostComp(CostCompSite a) {
			costComp=a;
		}

		@Override
		public int compareTo(Object otherSite) {
			
			return this.getName().compareTo(((Site)otherSite).getName());
		}
		
		public boolean equals(Site b) {
			return name.equals(b.getName());
			
		}

		public int getHistory() {
			return hist;
		}
		public String getDescription() {
			return desc;
		}
		public int getDistFrom() {
			return distFrom;
		}
		public void setDistFrom(int a) {
			distFrom=a;
		}
		public int getSceneFrom() {
			return sceneFrom;
		}
		public void setSceneFrom(int a) {
			sceneFrom=a;
		}
		public int getHistFrom() {
			return histFrom;
		}
		public void setHistFrom(int a) {
			histFrom=a;
		}
}

public class Road{
	private Site destination;
	private int beauty;
	private int timeCost;
	private String name;
	
	public Road(String n, Site dest, int beaut, int tic) {
		name=n;
		destination = dest;
		beauty = beaut;
		timeCost = tic;
	}
	
	public Site getSite() {
		return  destination;
	}
	
	public int getBeauty() {
		return beauty;
	}
	
	public int getTimeCost() {
		return timeCost;
	}

	public String getName() {

		return name;
	}
	
	
}

public class Path extends ArrayList<Road>{

		private int length;
		
		public Path(LinkedList<Road> roads) {
			super(roads);
		}
		
		public String toString() {
			ArrayList<String> result = new ArrayList<String>();
			for(Road r:this) {
				result.add(r.getName());
			}
			return result.toString();
		}
	}

public class MyInteger{
	
	private int value;
	
	public MyInteger(int a) {
		setValue(a);
	}
	
	public void setValue(int a) {
		value = a;
	}
	public int getValue() {
		return value;
	}
}

}