
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;


public class Map<E extends Comparable<?super E>> {
	public static final int approxSites=50;
	public static final int MAXSHORTESTPATHS=50000;
	
	HashMap<String,Site> sites;
	TreeSet<Site> siteList;
	private MyInteger pathCount;
	private int minCost;
	private ArrayList<Path> paths;
	
	private HashMap<String,Road> prohibitedRoads;
	
	public Map() {
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
	public void generateMap(String filename) throws Exception{
		File file = new File(filename);
		BufferedReader in = new BufferedReader(new FileReader(file));
		
		String line;
		in.mark(1000000);
		while((line=in.readLine())!=null) {//go through the lines looking to add sites
			
			String[] temp=line.split("\t|`");
			System.out.println(Arrays.toString(temp));
			if(temp[0].equals("Site")) {
				int x= Integer.parseInt(temp[1]);
				int y= Integer.parseInt(temp[2]);
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
				sites.get(startName).addRoad(new Road("name",sites.get(stopName),beut,time));
			}
		}
		in.close();
	}	
	
	public Path shortestPath(Site start, Site end, TreeSet<Site> fullGraph){

		PriorityQueue<CostCompSite> unknown = new PriorityQueue<CostCompSite>();
		ArrayList<CostCompSite> known = new ArrayList<CostCompSite>();
		Path result;
		
		CostCompSite beginning = new CostCompSite(start);
		beginning.setDistFrom(0);
		unknown.add(beginning);
		
		CostCompSite destination = new CostCompSite(end);
		unknown.add(destination);
		
		for(Site s : fullGraph) {
			if(s!=start && s!=end) {
			unknown.add(new CostCompSite(s));
			}
		}
		
		CostCompSite current;
		while(!(unknown.isEmpty())) {
			
			current=unknown.poll();
			known.add(current);
			if(current.getDistFrom()==Integer.MAX_VALUE) {
				return null;
			}
			if(current==destination) {
				if(current.toRoad==null)return null;
				
				LinkedList<Road> goBetween = new LinkedList<Road>();
				
				while(current.toRoad!=null) {
					goBetween.addFirst(current.toRoad);
					current=current.prevSite;
				}
				
				result = new Path(goBetween);
				return result;
			}
			
			for(Road r: current.roads) {
					
				CostCompSite neighbor = r.getSite();
				if(!(known.contains(neighbor))){
					
					int distToNeighbor = current.getDistFrom()+r.getTimeCost();
					
					if(distToNeighbor<neighbor.getDistFrom()) {
						neighbor.setDistFrom(distToNeighbor);
						neighbor.toRoad=r;
						neighbor.prevSite=current;
						
						//Update Heap
						int index = unknown.indexOf(neighbor);
						unknown.remove(index);
						unknown.add(neighbor);
					}
				}
			}
			
		}
		
		
		return null; //not in graph
	}
	
	
	public Path scenestPath(Site start, Site end, int maxCost) { // Will only check at most the first 100,000 short paths (calculated)
		
		return null;
	}
	


private class CostCompSite extends Site{


	private CostCompSite prevSite;
	private Integer toComp;
	private Road toRoad;
	private ArrayList<Road> roads= new ArrayList<Road>();
	private int hist;
	private String name;
	private String desc;
	
	public CostCompSite(Site s) {
		super(s.xPos,s.yPos,s.hist,s.name,s.desc,s.roads);

		toComp=s.distFrom;
	}
	
	public int compareTo(Object otherSite) {
		return toComp.compareTo(((CostCompSite)otherSite).toComp);
	}
		
	}
	
private class Site implements Comparable {

		private int xPos;
		private int yPos;
		private int hist;
		private String name;
		private String desc;
		private ArrayList<Road> roads= new ArrayList<Road>();
		private int distFrom;
		private int sceneFrom;
		private int histFrom;
		
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
		}
		
		public void addRoad(Road roadyboi) {
			roads.add(roadyboi);
		}
		public ArrayList<Road> getRoads(){
			return roads;
		}
		public String getName() {
			return name;
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
	private CostCompSite destination;
	private int beauty;
	private int timeCost;
	private String name;
	
	public Road(String n, CostCompSite dest, int beaut, int tic) {
		name=n;
		destination = dest;
		beauty = beaut;
		timeCost = tic;
	}
	
	public Map<E>.CostCompSite getSite() {
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
			length=Integer.MAX_VALUE;
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

public class PriorityQueue<E> extends ArrayList<E>{
	
	private int cap;
	private int size;
	
	public PriorityQueue() {
		cap = 11;
		super.ensureCapacity(cap);
		size=0;
	}
	public boolean add(E e) {
		
		if(e==null)throw new NullPointerException();
		
		if(super.add(e)) {
			size++;
			heapifyUp(size-1);
			updateCap();
			return true;
		}
		return false;
	}
	public boolean offer(E e) {
		if(size<cap) {
			return add(e);
		}
		return false;
	}
	public E peek() {
		if(isEmpty())return null;
		return get(0);
	}
	public E poll() {
		if(isEmpty())return null;
		E result = peek();
		remove(result);
		return result;
	}
	public boolean remove(Object e) {
		boolean inList=false;
		int index = indexOf((E)e);
		if(index!=-1) {
			inList=true;
			
			swapElementsAt(index,size-1);
			super.remove(size-1);
			size--;
			
			if(!isEmpty()) {
				if(0<((CostCompSite) get(index)).compareTo((Comparable) get((index-1)/2))) {
					heapifyUp(index);
				}
				else {
					heapifyDown(index);
				}
			}
		}
		return inList;
	}
	public int size() {
		return size;
	}
	public Object[] toArray() {
		return super.toArray();
	}
	public <E> E[] toArray(E[] a) {
		return super.toArray(a);
	}

	
	private void heapifyUp(int ind) {
		
		int index = ind;
		
		while(index>0 && !(0<((CostCompSite) get(index)).compareTo((Comparable) get((index-1)/2)))) {
			
				swapElementsAt((index-1)/2,index);
				index/=2;

		}
	}	
	private void heapifyDown(int ind) {
		
		int index = ind;
		
		
		while(((size)>(index+1)*2 && (0<((CostCompSite) get(index)).compareTo((Integer) get((index+1)*2)) || (int)get(index)<0))
			  ||
			  ((size)>(index+1)*2-1 && (0<((CostCompSite) get(index)).compareTo((Integer) get((index+1)*2-1)) || (int)get(index)<0)))
		{
			
			if((size)>(index+1)*2) {
				if(0>((CostCompSite) get((index+1)*2-1)).compareTo((Integer) get((index+1)*2))) {
					swapElementsAt(index, (index+1)*2-1);
					index=(index+1)*2-1;
				}
				else {
					swapElementsAt(index,(index+1)*2);
					index=(index+1)*2;
				}
			}
	
			else {
				swapElementsAt(index,(index+1)*2-1);
				index=(index+1)*2-1;
			}
		}
	}
	private void swapElementsAt(int index1, int index2) {
		E temp = get(index2);
		set(index2,get(index1));
		set(index1,temp);
	}
	private void updateCap() {
		if(size>=cap) {
			cap*=2;
		}
	}
}
}
