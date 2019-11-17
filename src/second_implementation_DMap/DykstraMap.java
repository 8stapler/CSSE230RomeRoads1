package second_implementation_DMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

import code.Road;
import code.Site;


public class DykstraMap<E extends Comparable<?super E>> {
	public static final int approxSites=50;
	public static final int MAXSHORTESTPATHS=50000;
	
	HashMap<String,Site> sites;
	TreeSet<Site> siteList;
	private int minCost;
	private ArrayList<Path> paths;
	
	private HashMap<String,Road> prohibitedRoads;
	
	public DykstraMap() {
		sites= new HashMap<String, Site>(approxSites);
		siteList= new TreeSet<Site>();
		paths = new ArrayList<Path>();
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
	public void generateMap(String filename) throws Exception{
		File file = new File(filename);
		BufferedReader in = new BufferedReader(new FileReader(file));
		
		String line;
		in.mark(1000000);
		while((line=in.readLine())!=null) {//go through the lines looking to add sites
			
			String[] temp=line.split("\t|`");
			System.out.println(Arrays.toString(temp));
			if(temp[0].equals("Site")) {
				double x= Double.parseDouble(temp[1]);
				double y= Double.parseDouble(temp[2]);
				int hist= Integer.parseInt(temp[3]);
				String name= temp[4];
				add(new Site(x,y,hist,name,temp[5],new ArrayList<Road>())); //csv has Site| x|y|history|name|description
			}
		}
		in.reset(); //read back through the file for roads
		while((line=in.readLine())!=null) {
			String[] temp=line.split("\t|`");
			if(temp[0].equals("Road")) {
				//road lines are in the form Road`start`end`dist`beut
				String startName=temp[1];
				String stopName=temp[2];
				int time = Integer.parseInt(temp[3]);
				int beut= Integer.parseInt(temp[4]);
				Site end1= sites.get(startName), end2= sites.get(stopName);
				if(end1!=null&end2!=null)
					addTwoRoads(end1,end2,beut,time);
			}
		}
		in.close();
	}	
	public Path shortestPath(Site start, Site end, ArrayList<Road> bannedRoads){

		//Set up known arrayList & unknown priorityQueue
		start.setDistFrom(0);
		start.setSceneFrom(0);
		start.setHistFrom(start.getHistory());
		
		ArrayList<CostCompSite> known = new ArrayList<CostCompSite>();
		PriorityQueue<CostCompSite> unknown = new PriorityQueue<CostCompSite>();
		
		ArrayList<Road> myBanned = new ArrayList<Road>();
		myBanned.addAll(bannedRoads);
		
		for(Site s: siteList) {
			unknown.add(s.getCostComp());
		}
		
		//Calculate distTo Current's neighbors until current is destination
		CostCompSite current = start.getCostComp();

		
		while(unknown.peek()!=null) { 
			
			
			current=unknown.poll();
			known.add(current);
			
			if(current.getMySite().getDistFrom()==Integer.MAX_VALUE) {
				return null; //no path
			}
			
			if(current.getMySite().toString().equals(end.toString())) {
				
				break;
			}
			
			for(Road r: current.getMySite().getRoads()) {
				
				if(!known.contains(r.destination.costComp)) {
				if(!myBanned.contains(r)) {
					int distFromHere = current.getMySite().getDistFrom() + r.getTimeCost();
					int sceneFromHere = current.getMySite().getSceneFrom()+r.getBeauty();
					int histFromHere = current.getMySite().getHistFrom()+r.getSite().getHistory();
					
					if(distFromHere < r.getSite().getDistFrom()) {
						r.getSite().setDistFrom(distFromHere);
						r.getSite().setSceneFrom(sceneFromHere);
						r.getSite().setHistFrom(histFromHere);
						
						//Make able to trace back to start (determine path)
						r.getSite().setToRoad(r);
						r.getSite().setPrevSite(current.getMySite());
						
						//Update priorityQueue
						unknown.remove(r.getSite().getCostComp());
						unknown.add(r.getSite().getCostComp());
					}
				}
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
		Path result = new Path(temp);
		result.dCost = end.distFrom;
		result.sCost = end.sceneFrom;
		result.hCost = end.histFrom;
		
		//Reset sites to how they were before
		for(Site s: siteList) {
			s.setPrevSite(null);
			s.setToRoad(null);
			s.setDistFrom(Integer.MAX_VALUE);
			s.setSceneFrom(Integer.MAX_VALUE);
			s.setHistFrom(Integer.MAX_VALUE);
		}
		
		return result;
		
	}
	
	private void addTwoRoads(Site end1, Site end2,int  beut, int time) {
		end1.addRoad(new Road(end1.getName(),end2,beut,time));
		end2.addRoad(new Road(end2.getName(),end1,beut,time));
	}
	
	public ArrayList<Site> tripPlanner(Site start, int maxCost){ //Pass in Integer.MAX_VALUE for maxCost to get array list of Sites (with distances from start & names)
		//Set up known arrayList & unknown priorityQueue		 // Different enough from other methods to get its own method
				start.setDistFrom(0);
				
				ArrayList<Site> known = new ArrayList<Site>();
				PriorityQueue<CostCompSite> unknown = new PriorityQueue<CostCompSite>();
				
				for(Site s: siteList) {
					unknown.add(s.getCostComp());
				}
				
				//Calculate distTo Current's neighbors until current is destination
				CostCompSite current = start.getCostComp();
				
				while(unknown.peek()!=null) {
					
					current = unknown.poll();
					
					if(current.getMySite()==null || current.getMySite().getDistFrom()==Integer.MAX_VALUE) {
						break;
					}
					
					known.add(current.mySite);
					
					for(Road r: current.getMySite().getRoads()) {
						
						
						int distFromHere = current.getMySite().getDistFrom() + r.getTimeCost();
						
						if(distFromHere < r.getSite().getDistFrom() && distFromHere <= maxCost) {
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
		return known;
	}

	
	public Path scenestPath(Site start, Site end, int maxCost) { // Will only check at most the first 100,000 short paths (calculated)
		
		PriorityQueue<Path> solutions = new PriorityQueue<Path>();
		PriorityQueue<Path> solvedSolns = new PriorityQueue<Path>();
		Path shortest = new Path( new LinkedList<Road>() );
		
		shortest = shortestPath(start ,end ,new ArrayList<Road>());//the first shortest path
		Path result = shortest;

		solutions.add(shortest);

		
		while((solutions.peek()!=null && solutions.peek().dCost<=maxCost)) {
			Path problem = solutions.poll();//take current shortest path and
			
			solvedSolns.add(problem);
			
			for(Road r: problem) {			//find the shortest path for similar graphs( -1 road )
				ArrayList<Road> tempBan = new ArrayList<Road>();
				tempBan.add(r);
				tempBan.addAll(problem.getBannedRoads());
				
				Path p = shortestPath(start, end, tempBan);
				
				if(p!=null) {
					p.setBannedRoads(tempBan);
					if(p.dCost<=maxCost) solutions.add(p);
				}
			}
			
			
			
		}
		solvedSolns.addAll(solutions);
		for (Path p: solvedSolns) {
			
			if(result.sCost<p.sCost) {
				result = p;
			}
		}
		
		return result;
	} 
	
	public Path historyestPath(Site start, Site end, int maxCost) { // Will only check at most the first 100,000 short paths (calculated)
		
		PriorityQueue<Path> solutions = new PriorityQueue<Path>();
		PriorityQueue<Path> solvedSolns = new PriorityQueue<Path>();
		Path shortest = new Path( new LinkedList<Road>() );
		
		shortest = shortestPath(start ,end ,new ArrayList<Road>());//the first shortest path
		Path result = shortest;

		solutions.add(shortest);

		
		while((solutions.peek()!=null && solutions.peek().dCost<=maxCost)) {
			Path problem = solutions.poll();//take current shortest path and
			
			solvedSolns.add(problem);
			
			for(Road r: problem) {			//find the shortest path for similar graphs( -1 road )
				ArrayList<Road> tempBan = new ArrayList<Road>();
				tempBan.add(r);
				tempBan.addAll(problem.getBannedRoads());
				
				Path p = shortestPath(start, end, tempBan);
				if(p!=null) {
					p.setBannedRoads(tempBan);
					if(p.dCost<=maxCost) solutions.add(p);
				}
			}
			
			
			
		}
		solvedSolns.addAll(solutions);
		for (Path p: solvedSolns) {
			
			if(result.hCost<p.hCost) {
				result = p;
			}
		}
		
		return result;
	} 
	


	
public class CostCompSite implements Comparable{

	private Site mySite;
	
	public CostCompSite(Site s) {

		mySite = s;
		
	}
	
	public int compareTo(Object otherSite) {
		int compare =mySite.getDistFrom()-(((CostCompSite)otherSite).mySite.getDistFrom());
		
		if (compare==0) return 0; 
			
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

		private double xPos;
		private double yPos;
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
		
		public Site(double xPosition, double yPosition, int history, String name, String description, ArrayList<Road> roadybois) {
			
			xPos=xPosition;
			yPos=yPosition;
			hist=history;
			this.name=name;
			desc=description;
			roads=roadybois;
			distFrom=Integer.MAX_VALUE;
			sceneFrom=Integer.MAX_VALUE;
			histFrom=Integer.MAX_VALUE;
			
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
		public void addRoad(Road roadyboi) {
			roads.add(roadyboi);
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
	
	public String toString() {
		return name;
	}
	public int getTimeCost() {
		return timeCost;
	}

	public String getName() {

		return name;
	}
	
	
}

public class Path extends ArrayList<Road> implements Comparable{

	
		private ArrayList<Road> bannedRoads;
		private int sCost;
		private int dCost;
		private int hCost;
		
		public Path(LinkedList<Road> roads) {
			super(roads);
			bannedRoads = new ArrayList<Road>();
			if(!roads.isEmpty()) {
			sCost=roads.getLast().getSite().getSceneFrom();
			dCost=roads.getLast().getSite().getDistFrom();
			}
		}
		
		public ArrayList<Road> getBannedRoads(){
			return bannedRoads;
		}
		public void setBannedRoads(ArrayList<Road> a) {
			bannedRoads=new ArrayList<Road>();
			bannedRoads.addAll(a);
		}
		
		public String toString() {
			ArrayList<String> result = new ArrayList<String>();
			for(Road r:this) {
				result.add(r.getName());
			}
			return result.toString();
		}

		@Override
		public int compareTo(Object o) {
			
			int result = dCost-((Path) o).dCost;
			
			if(result == 0) return 0;
			
			return result/Math.abs(result);
		}
	}

}
