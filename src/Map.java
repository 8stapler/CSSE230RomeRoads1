import java.util.ArrayList;
import java.util.HashMap;

import java.util.TreeSet;

public class Map {
	public static final int approxSites=50;
	HashMap<String,Site> sites;
	TreeSet<Site> siteList;
	public Map() {
		sites= new HashMap<String, Site>(approxSites);
		siteList= new TreeSet<Site>();
	}
	
	public boolean add(Site toAdd) {
		boolean added=siteList.add(toAdd);
		sites.add(toAdd.getName(),toAdd);
		return added;
	}
	public boolean remove(Site toKill) {
		if(siteList.size()==0)return false;
		boolean removed = siteList.remove(toKill);
		sites.remove(toKill);
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
	
	
}


