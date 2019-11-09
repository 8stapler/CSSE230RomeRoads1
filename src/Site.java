import java.util.ArrayList;

public class Site implements Comparable {

	private int xPos;
	private int yPos;
	private int hist;
	private String name;
	private String desc;
	private ArrayList<Road> roads= new ArrayList<Road>();
	
	public Site(int xPosition, int yPosition, int history, String name, String description, ArrayList<Road> roadybois) {
		xPos=xPosition;
		yPos=yPosition;
		hist=history;
		this.name=name;
		desc=description;
		roads=roadybois;
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
	
	
	public int getXPos() {
		return xPos;
	}
	public int getYPos() {
		return yPos;
	}
	public int getHistory() {
		return hist;
	}
	public String getDescription() {
		return desc;
	}
	public String toString() {
		String s="";
		s+=name+":";
		s+="("+xPos+","+yPos+"),";
		s+=roads.toString();
		return s;
	}
}
