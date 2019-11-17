package second_implementation_DMap;



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
