package csc3a.miniproject.gui;

import java.io.InputStream;
//import java.net.URL;
import java.util.Scanner;
import java.util.StringTokenizer;

import csc3a.miniproject.Models.Hospital;
import csc3a.miniproject.Models.SinglyLinkedList;

/**
 * @author 217092052
 * Helper Class for reading file and calculating distance
 */
public class FileReader {
	
	/**
	 * Read File
	 * @param FileName
	 * @return an ArrayList of the Hospitals
	 */
	public static SinglyLinkedList<Hospital> readFile(String FileName)
	{
		SinglyLinkedList<Hospital> hospitalList = new SinglyLinkedList<Hospital>();
		InputStream filestream = FileReader.class.getResourceAsStream("/././././" + FileName);
		if (filestream == null) System.out.println("File not found");
		Scanner sc ;
		try {
			sc = new Scanner(filestream);
			int counter = 0;
			while(sc.hasNextLine())
			{
				counter++;
				String newHospital = sc.nextLine();
				StringTokenizer myTokens = new StringTokenizer(newHospital, "#");
				while(myTokens.hasMoreTokens())
				{
					//HOSPITAL # SERVICE # LAT # LONG # TIME_START # TIME_FINISH # CONTACT
					String hospitalName = myTokens.nextToken();
					String hospitalService = myTokens.nextToken();
					double hospitalLatitude = Double.parseDouble(myTokens.nextToken());
					double hospitalLongitude = Double.parseDouble(myTokens.nextToken());
					double hospitalStartTime = Double.parseDouble(myTokens.nextToken());
					double hospitalCloseTime = Double.parseDouble(myTokens.nextToken());
					String hospitalContact = myTokens.nextToken();
					String Code = "HOSP" + counter;
					Hospital theHospital = new Hospital(Code, hospitalName, hospitalService, hospitalLatitude, hospitalLongitude, hospitalStartTime, hospitalCloseTime, hospitalContact);
					hospitalList.insert(theHospital);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hospitalList;
	}
	
	/**
	 * Calculate the distance between Locations
	 * @param Source Latitude
	 * @param destination Latitude
	 * @param source Latitude
	 * @param destination Latitude
	 * @return the distance(KM)
	 */
	public static double calcDistance(double src_Lat, double dest_Lat, double src_Long,  double dest_Long) {
	    final int R = 6371; // Radius of the earth
	    double latDistance = Math.toRadians(dest_Lat - src_Lat);
	    double lonDistance = Math.toRadians(dest_Long - src_Long);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(src_Lat)) * Math.cos(Math.toRadians(dest_Lat))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters
	    distance = Math.pow(distance, 2) + Math.pow(0, 2);
	    //Convert to kilometer by dividing by 1000
	    return (Math.sqrt(distance) / 1000) + 0.6;
	}
}
