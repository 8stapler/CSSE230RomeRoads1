package code;

public class Road {
	private Site destination;
	private int beauty;
	private int timeCost;
	String name;
	
	public Road(String fromName,Site dest, int beaut, int tic) {
		destination = dest;
		beauty = beaut;
		timeCost = tic;
		name= fromName+" to "+dest.getName();
	}
	
	public Site getSite() {
		return destination;
	}
	
	public int getBeauty() {
		return beauty;
	}
	
	public int getTimeCost() {
		return timeCost;
	}
	public String toString() {
		String s="";
		s+=name;
		
		return s;
	}
}
