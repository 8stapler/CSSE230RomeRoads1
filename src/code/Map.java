package code;


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
	
	
	HashMap<String,Site> sites;
	TreeSet<Site> siteList;
	
	
	
	
	private HashMap<String,Road> prohibitedRoads;
	
	public Map() {
		sites= new HashMap<String, Site>(approxSites);
		siteList= new TreeSet<Site>();
		
		
	
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
				double y= Integer.parseInt(temp[2]);
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
	private void addTwoRoads(Site end1, Site end2,int  beut, int time) {
		end1.addRoad(new Road(end1.getName(),end2,beut,time));
		end2.addRoad(new Road(end2.getName(),end1,beut,time));
	}
	public String toString() {
		return siteList.toString();
	}
	}
