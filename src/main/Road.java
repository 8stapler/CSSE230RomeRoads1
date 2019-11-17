package main;



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
