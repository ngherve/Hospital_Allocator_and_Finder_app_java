package csc3a.miniproject.Models;

/**
 * @author HERVE NG
 *
 */
public class Hospital implements Comparable<Hospital> {
	/**
	 * Instance variables
	 */
	private String hospCode;
	private String hospitalName;
	/**
	 * @return the hospCode
	 */
	public String getHospCode() {
		return hospCode;
	}

	/**
	 * @param hospCode the hospCode to set
	 */
	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}

	private String specialityCare;
	private double dblLatitude;
	private double dblLongitude;
	private double dblStartTime;
	private double dblCloseTime;
	private String strContact;
	
	/**
	 * Constructor
	 */
	public Hospital(String hospCode, String hospitalName, String specialityCare, double dblLatitude, double dblLong, double dblStartTime, double dblCloseTime, String strContact) {
		this.hospCode = hospCode;
		this.hospitalName = hospitalName;
		this.specialityCare = specialityCare;
		this.dblLatitude = dblLatitude;
		this.dblLongitude = dblLong;
		this.dblStartTime = dblStartTime;
		this.dblCloseTime = dblCloseTime;
		this.strContact = strContact;
	}

	/**
	 * @return the hospitalName
	 */
	public String getHospitalName() {
		return hospitalName;
	}

	/**
	 * @param hospitalName the hospitalName to set
	 */
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	/**
	 * @return the specialityCare
	 */
	public String getSpecialityCare() {
		return specialityCare;
	}

	/**
	 * @param specialityCare the specialityCare to set
	 */
	public void setSpecialityCare(String specialityCare) {
		this.specialityCare = specialityCare;
	}

	/**
	 * @return the dblLatitude
	 */
	public double getDblLatitude() {
		return dblLatitude;
	}

	/**
	 * @param dblLatitude the dblLatitude to set
	 */
	public void setDblLatitude(double dblLatitude) {
		this.dblLatitude = dblLatitude;
	}

	/**
	 * @return the dblLongitude
	 */
	public double getDblLongitude() {
		return dblLongitude;
	}

	/**
	 * @param dblLongitude the dblLongitude to set
	 */
	public void setDblLongitude(double dblLongitude) {
		this.dblLongitude = dblLongitude;
	}

	/**
	 * @return the dblStartTime
	 */
	public double getDblStartTime() {
		return dblStartTime;
	}

	/**
	 * @param dblStartTime the dblStartTime to set
	 */
	public void setDblStartTime(double dblStartTime) {
		this.dblStartTime = dblStartTime;
	}

	/**
	 * @return the dblCloseTime
	 */
	public double getDblCloseTime() {
		return dblCloseTime;
	}

	/**
	 * @param dblCloseTime the dblCloseTime to set
	 */
	public void setDblCloseTime(double dblCloseTime) {
		this.dblCloseTime = dblCloseTime;
	}

	/**
	 * @return the strContact
	 */
	public String getStrContact() {
		return strContact;
	}

	/**
	 * @param strContact the strContact to set
	 */
	public void setStrContact(String strContact) {
		this.strContact = strContact;
	}

	@Override
	public int compareTo(Hospital hosp) {
		// TODO Auto-generated method stub
		return this.getHospCode().compareTo(hosp.getHospCode());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Hospital: " + getHospitalName() + "Code: " + getHospCode() + "\n"
				+ "Opening time: " + getDblStartTime() + "Closing time: " + getDblCloseTime() + "\n"
				+ "Speciality Care: " + getSpecialityCare() + "Contact: " + getStrContact() + "\n"
				+ "Longitude: " + getDblLongitude() + "Lattitude: " + getDblCloseTime() + "\n";
	}

}