public class Road {
	private Site destination;
	private int beauty;
	private int timeCost;
	
	public Road(Site dest, int beaut, int tic) {
		destination = dest;
		beauty = beaut;
		timeCost = tic;
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
	
}
