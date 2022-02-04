/**
 * 
 */
package csc3a.miniproject.Models;

/**
 * @author HERVE NG
 *
 */
public class User implements Comparable<User> {

	private String userCode;
	private int xloc;
	private int yloc;
	private String locationName;
	private SinglyLinkedList<String> neighborsList;
	
	public User(String userCode, int xloc, int yloc, String locationName, SinglyLinkedList<String> neighborsList) {
		this.userCode = userCode;
		this.xloc = xloc;
		this.yloc = yloc;
		this.locationName = locationName;
		this.neighborsList = neighborsList;
	}
	
	/**
	 * @return the userCode
	 */
	public String getUserCode() {
		return userCode;
	}

	/**
	 * @param userCode the userCode to set
	 */
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	/**
	 * @return the xloc
	 */
	public int getXloc() {
		return xloc;
	}

	/**
	 * @param xloc the xloc to set
	 */
	public void setXloc(int xloc) {
		this.xloc = xloc;
	}

	/**
	 * @return the yloc
	 */
	public int getYloc() {
		return yloc;
	}

	/**
	 * @param yloc the yloc to set
	 */
	public void setYloc(int yloc) {
		this.yloc = yloc;
	}

	/**
	 * @return the locationName
	 */
	public String getLocationName() {
		return locationName;
	}

	/**
	 * @param locationName the locationName to set
	 */
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	/**
	 * @return the neighborsList
	 */
	public SinglyLinkedList<String> getNeighborsList() {
		return neighborsList;
	}

	/**
	 * @param neighborsList the neighborsList to set
	 */
	public void setNeighborsList(SinglyLinkedList<String> neighborsList) {
		this.neighborsList = neighborsList;
	}
	//
	@Override
	public String toString() {
		return String.format("Location Code: %s \n User's location: %s \n X_Location: %d \n Y_Location: %d \n");
	}
	//comparing to a another user location
	@Override
	public int compareTo(User user) {
		return this.getUserCode().compareTo(user.userCode);
	}

	/*public int compareTo(Hospital hospital) {
		return this.getUserCode().compareTo(hospital.getHospCode());
	}*/
	/**
	 * Display the list of neighbors
	 */
	public String displayNeighbors() {
		String neighbors = "";
		for(String n : neighborsList) {
			neighbors += " "+n+"/";
		}
		
		return neighbors;
	}
}
