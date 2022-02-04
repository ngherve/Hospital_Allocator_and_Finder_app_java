package csc3a.miniproject.gui;

import java.util.StringTokenizer;

/**
 * @author HERVE NG
 *
 */

import com.teamdev.jxmaps.*;
import com.teamdev.jxmaps.swing.MapView;

import csc3a.miniproject.Models.Hospital;
import csc3a.miniproject.Models.SinglyLinkedList;

public class GraphView extends MapView {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Marker marker;
	private String StrDest;
	private String UserLoc;

	/**
	 * @param strDest the strDest to set
	 */
	public void setStrDest(String strDest) {
		this.StrDest = strDest;
		this.repaint(); 
	}
	
	public void setUserLoc(String loc) {
		this.UserLoc = loc;
		this.repaint(); 
	}
	
	/**
	 * Initialising the map with the user's location
	 * @param options
	 * @param userLoc
	 */
	public GraphView(MapViewOptions options, String userLoc) {
        super(options);
        this.UserLoc = userLoc;
        setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                if (status == MapStatus.MAP_STATUS_OK) {
                	final Map map = getMap();
                    map.setZoom(10.0);
					@SuppressWarnings("deprecation")
					GeocoderRequest request = new GeocoderRequest(map); 
					StringTokenizer st = new StringTokenizer(UserLoc);
					
                    request.setAddress(st.nextToken());

                    getServices().getGeocoder().geocode(request, new GeocoderCallback(map) {
                       
						@Override
                        public void onComplete(GeocoderResult[] result, GeocoderStatus status) {
                            if (status == GeocoderStatus.OK) {
                                map.setCenter(result[0].getGeometry().getLocation());
                                marker = new Marker(map);
                                marker.setPosition(result[0].getGeometry().getLocation());
                               
                                final InfoWindow window = new InfoWindow(map);
                                window.setContent(UserLoc);
                                window.open(map, marker);
                                
                                //Marking all the nodes(hospitals in the map)
                                SinglyLinkedList<Hospital> hospitalList = new SinglyLinkedList<>();
                                hospitalList = FileReader.readFile("HospitalFile.txt");
                              
                                for(Hospital h : hospitalList)
                                {
                                    Marker markerName = new Marker(map);
                                    markerName.setPosition(new LatLng(h.getDblLatitude(), h.getDblLongitude()));
                                }
                                
                                // Creating a directions request
                                DirectionsRequest request = new DirectionsRequest();
                                // Setting of the origin location to the request
                                request.setOriginString(UserLoc);
                                // Setting of the destination location to the request
                                request.setDestinationString(StrDest);
                                // Setting of the travel mode
                                request.setTravelMode(TravelMode.DRIVING);
                                // Calculating the route between locations
                                getServices().getDirectionService().route(request, new DirectionsRouteCallback(getMap()) {
                                    @Override
                                    public void onRoute(DirectionsResult result, DirectionsStatus status) {
                                        // Checking of the operation status
                                        if (status == DirectionsStatus.OK) {
                                            // Drawing the calculated route on the map
                                            getMap().getDirectionsRenderer().setDirections(result);
                                        }
                                    }
                                });
                            }
                            
                        }						
                    });
                }
            }
        });
    }

}