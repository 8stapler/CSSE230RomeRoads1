package main;

import java.util.ArrayList;


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
	public double getXPos() {
		return xPos;
	}
	public double getYPos() {
		return yPos;
	}
}
