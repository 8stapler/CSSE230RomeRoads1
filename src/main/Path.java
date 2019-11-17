package main;

import java.util.ArrayList;
import java.util.LinkedList;



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
	public void setsCost(int sCost) {
		this.sCost = sCost;
	}

	public void setdCost(int dCost) {
		this.dCost = dCost;
	}

	public void sethCost(int hCost) {
		this.hCost = hCost;
	}
	public int getsCost() {
		return sCost;
	}

	public int getdCost() {
		return dCost;
	}

	public int gethCost() {
		return hCost;
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
