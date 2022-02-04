package csc3a.miniproject.gui;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.teamdev.jxmaps.MapViewOptions;

import csc3a.miniproject.Models.Graph;
import csc3a.miniproject.Models.Hospital;
import csc3a.miniproject.Models.SinglyLinkedList;

public class UIFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private Graph<Hospital> hospGraph;
	private SinglyLinkedList<Hospital> hospitals;
	private JButton btnDisplayInfo;
	private JButton btnRemPart;
	private JButton btnPartner;
	private String[] unique_services;
	
	private JTextArea txtAreaHospitalList;
	private JScrollPane scPane;
	private JButton btnViewGraph;
	private JButton btnInsight;
	private JComboBox<String> servicesList;
	private SinglyLinkedList<Hospital> hospitalList;
	private GraphView mapView;
	private String[] hospital_services;
	private String userLoc;
	private JFrame graphView;
	private double min = Double.MAX_VALUE;
	private double distance = 0.0;
	private String desthosp = "";
	private JButton btnRefresh;
	private JComboBox<String> combouserloc;
	private JButton btnRemHosp;
	private JButton btnAddHospital;
	private int count;
	private MapViewOptions options;
	private JTextArea txtMessage;
	private JButton btnShortPath;
	private JTextArea txtShort;
	
	
	public UIFrame(String title) {
		
		this.setTitle(title);
		
		btnInsight = new JButton("Show Hospital Insight");
		btnRemPart = new JButton("Remove Partnership");
		btnDisplayInfo = new  JButton("Display Hospitals");
		btnPartner = new JButton("Create Partnership");
		btnViewGraph = new JButton("View Graph");
		txtAreaHospitalList = new JTextArea();
		scPane = new JScrollPane(txtAreaHospitalList);
		btnRefresh = new JButton("Refresh");
		btnRemHosp = new JButton("Delete Hospital");
		btnAddHospital = new JButton("Add a Hosital");
		btnShortPath = new JButton("Display Nearest Hosp");
		txtShort = new JTextArea();
		
		//Adding to Panel
		
		JPanel pnlTop = new JPanel();
		pnlTop.setLayout(new BorderLayout());
		pnlTop.setSize(600, 80);
		pnlTop.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlTop.setBackground(Color.pink);
		
		JLabel lblTitle = new JLabel();
		lblTitle.setText("Hospital Resource Allocation and Finder");
		lblTitle.setForeground(Color.WHITE);
		pnlTop.add(lblTitle, BorderLayout.NORTH);


		JPanel pnlButton = new JPanel();
		pnlButton.setLayout(new GridLayout(3,6));
		pnlButton.setBorder(new EmptyBorder(10,10,10,10));
		pnlButton.add(btnDisplayInfo);
		pnlButton.add(btnInsight);
		pnlButton.add(btnRemPart);
		pnlButton.add(btnPartner);
		pnlButton.add(btnRemHosp);
		pnlButton.add(btnAddHospital);
       	JPanel pnltxt = new JPanel();
		
		pnltxt.setLayout(new GridLayout(1,1));
		txtAreaHospitalList.setEditable(false);
		txtAreaHospitalList.setSize(240, 320);
		txtAreaHospitalList.setMargin(new Insets(10,10,10,10));
		pnltxt.add(scPane);
		pnltxt.setBorder(new EmptyBorder(10,10,0,10));
		
		//Instantiate Wrapper panel.
		JPanel pnlWrapper = new JPanel();
		
		pnlWrapper.setLayout(new BorderLayout());
		
		pnlButton.setSize(20, 640);
		pnlWrapper.add(pnlButton, BorderLayout.NORTH);
		pnlWrapper.add(pnltxt, BorderLayout.CENTER);
		
		JPanel pnlBottom = new JPanel();
		
        compareEachHospital();

		servicesList = new JComboBox<String>(unique_services);
        servicesList.setVisible(true);
        servicesList.setLightWeightPopupEnabled(false);
        //User locations
        ArrayList<String> userlocations = new ArrayList<>();
        userlocations.add("AucklandPark: -26.182704 28.004080");
        userlocations.add("MelvilleJohannesburg: -26.174981 28.008269");
        userlocations.add("Westdene: -26.180601 27.986958");
        userlocations.add("Johannesburg: -26.205075 28.038672");
        userlocations.add("Milpark: -26.179172 28.015649");
        combouserloc = new JComboBox<String>(userlocations.toArray(new String[userlocations.size()]));
        combouserloc.setVisible(true);
        combouserloc.setLightWeightPopupEnabled(false);
        
        pnlBottom.setLayout(new GridLayout(3,6));
        pnlBottom.setBorder(new EmptyBorder(10,10,10,10));
		pnlBottom.add(servicesList);
		pnlBottom.add(combouserloc);
		pnlBottom.add(btnViewGraph);
		pnlBottom.add(btnRefresh);
		pnlBottom.add(btnShortPath);
		pnlBottom.add(txtShort);

		
		add(pnlTop, BorderLayout.NORTH);
		add(pnlWrapper, BorderLayout.CENTER);
		add(pnlBottom, BorderLayout.SOUTH);
		
		
		//Action Listeners
		
		btnDisplayInfo.addActionListener(new ActionListener() {
			
			private String region;

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				txtAreaHospitalList.setText("");
				region = "Hospitals";
				if(hospGraph == null) CreateGaph(region);
				else displayVertices();
			}
		});
		
		//button to view graph
		btnViewGraph.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				InitGraph();
				
				txtMessage = new JTextArea("The Closest Hopital to you is: " + desthosp + " and it is " + distance + "km from " + userLoc);
		        graphView = new JFrame("Map Displaying Locations");
		        	
		        graphView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        graphView.add(mapView, BorderLayout.CENTER);
		        graphView.add(txtMessage, BorderLayout.SOUTH);
		        graphView.setSize(700, 500);
		        graphView.setLocationRelativeTo(null);

		        //Add windowLister to graphView Frame.
				graphView.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						new Thread() {
							public void run() {
								//Disable btnVisualRep after it has been clicked 
								btnViewGraph.setEnabled(true);
							}
						}.start();
						
					}
				});
				
				graphView.setVisible(true);
				btnViewGraph.setEnabled(false);
			}
		});

		btnInsight.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(hospGraph == null) {
					JOptionPane.showMessageDialog(null, "No Hospital selected!", "Warning", JOptionPane.WARNING_MESSAGE);
				}else {
					String hCode = JOptionPane.showInputDialog("Enter Hospital's CODE").toUpperCase();
					viewInsight(hCode);
				}
			}
		});
		
		btnPartner.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Hospital hos1 = null;
				Hospital hos2 = null;
				if(hospGraph != null) {
					String c1 = JOptionPane.showInputDialog(" 1st Hospital CODE").toUpperCase();
					String c2 = JOptionPane.showInputDialog(" 2nd Hospital CODE").toUpperCase();
					for(Hospital h : hospGraph.getAllVertices()) {
						if(h.getHospCode().equals(c1)) hos1 = h;
						else if(h.getHospCode().equals(c2)) hos2 = h;
					}
					
					if(c1.equals(c2)) {
						JOptionPane.showMessageDialog(null, "A Hospital cannot be partner with itself!", "Warning", JOptionPane.WARNING_MESSAGE);
					}else {
						addEdgeHosp(hos1, hos2);
					}	
				}
			}
		});
		
		btnRemPart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Hospital hos1 = null;
				Hospital hos2 = null;
				if(hospGraph != null) {
					String c1 = JOptionPane.showInputDialog(" 1st Hospital CODE").toUpperCase();
					String c2 = JOptionPane.showInputDialog(" 2nd Hospital CODE").toUpperCase();
					for(Hospital h : hospGraph.getAllVertices()) {
						if(h.getHospCode().equals(c1)) hos1 = h;
						else if(h.getHospCode().equals(c2)) hos2 = h;
					}
					if(c1.equals(c2)) {
						JOptionPane.showMessageDialog(null, "A Hospital cannot be partner to itself!", "Warning", JOptionPane.WARNING_MESSAGE);
					}else {
						removeEdgeHosp(hos1, hos2);
					}	
				}
			}
		});
		
		btnRemHosp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(hospGraph != null) {
					String hCode = JOptionPane.showInputDialog(" Hospital CODE").toUpperCase();
					Hospital hos = null;
					for(Hospital h : hospGraph.getAllVertices()) {
						if(h.getHospCode().equals(hCode)) hos = h;
					}
					hospGraph.removeVertex(hos);
					JOptionPane.showMessageDialog(null, hos.getHospitalName()+"("+hos.getHospCode()+") "
							+ "has been successfully removed!", "Confirmation Message", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		btnAddHospital.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				count++;
				String hCode = "newHosp" + count;
				String name = JOptionPane.showInputDialog(" Hospital name");
				Double Otime = Double.valueOf(JOptionPane.showInputDialog(" Openning Time"));
				Double ctime = Double.valueOf(JOptionPane.showInputDialog(" Closing Time"));
				String specCare = JOptionPane.showInputDialog(" Speciality Care");
				String contact = JOptionPane.showInputDialog(" Contact");
				Double Lat = Double.valueOf(JOptionPane.showInputDialog(" Latitude location"));
				Double lon = Double.valueOf(JOptionPane.showInputDialog(" Longitude Time"));
				Hospital hosp = new Hospital(hCode, name, specCare, Lat, lon, Otime, ctime, contact);
				hospGraph.addVertex(hosp);
				JOptionPane.showMessageDialog(null, hosp.getHospitalName()+"("+hosp.getHospCode()+") "
						+ "has been successfully Added!", "Confirmation Message", JOptionPane.INFORMATION_MESSAGE);
				viewInsight(hCode);
			}
		});
		
		btnRefresh.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				userLoc = combouserloc.getSelectedItem().toString();
				graphView.getContentPane().remove(mapView);
				graphView.getContentPane().remove(txtMessage);

				mapView = null;
				mapView = new GraphView(options, userLoc);
				mapView.setUserLoc(userLoc);
				DetermineShortestPath(servicesList.getSelectedItem().toString());
				mapView.setStrDest(desthosp);
				txtMessage.setText("The Closest Hopital to you is: " + desthosp + " and it is " + distance + "km from " + userLoc);
				graphView.add(mapView, BorderLayout.CENTER);
				graphView.add(txtMessage, BorderLayout.SOUTH);
				graphView.revalidate();
				graphView.repaint();
			}
		});
		
		btnShortPath.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DetermineShortestPath(servicesList.getSelectedItem().toString());
				txtShort.setText(desthosp + "\n and it is " + distance + "km away");
			}
		});
	}
	
	/**
	 * this method displays detail of a particular hospital
	 * @param hCode
	 */
	private void viewInsight(String hCode) {
		Hospital targetHosp = null;
		for(Hospital c : hospGraph.getAllVertices()) {
			String Code = c.getHospCode();
			if(Code.equals(hCode)) {
				targetHosp = c;
			}
		}
		
		if(targetHosp.getHospCode().equals(hCode)) {
			txtAreaHospitalList.setText("");
			txtAreaHospitalList.append(targetHosp.toString());
			txtAreaHospitalList.append("\n");
			txtAreaHospitalList.append("Partners: ");
			//Display list of partners for a specific country.
			for(Hospital n : hospGraph.getNeighbors(targetHosp)) {
				txtAreaHospitalList.append("\t"+n.getHospitalName()+"("+n.getHospCode()+") \n");
			}
			txtAreaHospitalList.append("\n");
			txtAreaHospitalList.append("Potential Partners: \n");
			for(Hospital n : hospGraph.getNeighbors(targetHosp)) {
				//Display list of potential partners for a specific country(Partners of partner).
				for(Hospital p : hospGraph.getNeighbors(n)) {
					//Exclude current country and its partners.
					if(p.compareTo(targetHosp) != 0 ) {
						if(!hospGraph.isAdjacent(p, targetHosp)) {
							txtAreaHospitalList.append("\t"+p.getHospitalName()+"("+p.getHospCode()+") \n");
						}
					}
				}
			}
			txtAreaHospitalList.append("\n\n");
		}else {
			JOptionPane.showMessageDialog(null, hCode+" was not found in the current region.", "Search result", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	/**
	 * Initialising the graph
	 */
	private void InitGraph() {
		userLoc = combouserloc.getSelectedItem().toString();
		options = new MapViewOptions();
		options.importPlaces();
        options.setApiKey("AIzaSyBNhjXGI7RTG4ZQMZv33o3PvX8JGd0_E-M");
        String destServ = servicesList.getSelectedItem().toString();
		mapView = new GraphView(options, userLoc);
		DetermineShortestPath(destServ);
		mapView.setStrDest(desthosp);
	}
	
	/**
	 * comparing hospital to extract hospital services
	 */
	@SuppressWarnings("unlikely-arg-type")
	private void compareEachHospital() {
		ArrayList<String> hospital_services_arrayList = new ArrayList<>();
	        
        hospitalList = FileReader.readFile("HospitalFile.txt");
		for(Hospital h : hospitalList)
        {
        	hospital_services_arrayList.add(h.getSpecialityCare());
        }
        //Put ArrayList Data into Array
        hospital_services = hospital_services_arrayList.toArray(new String[hospital_services_arrayList.size()]);
		int uniqueelements = hospital_services.length;
        //Comparing each element with all other elements
        for (int i = 0; i < uniqueelements; i++) 
        {
            for (int j = i+1; j < uniqueelements; j++)
            {
                //If any two elements are found equal
                if(hospital_services.equals(hospital_services[j]))
                {
                    //Replace duplicate element with last unique element
                	hospital_services[j] = hospital_services[uniqueelements-1];
                    uniqueelements--;                     
                    j--;
                }
            }
        }
       
		unique_services = Arrays.copyOf(hospital_services, uniqueelements);
        Arrays.sort(unique_services);
	}
	
	
	/**
	 * this method creates the graph with its vertices and edges
	 * @param region
	 */
	private void CreateGaph(String region) {
		hospGraph = new Graph<Hospital>();
		hospitals = new SinglyLinkedList<Hospital>();
		
		hospitals = FileReader.readFile("HospitalFile.txt");
		
		for(Hospital h : hospitals) {
			hospGraph.addVertex(h);
		}
		displayVertices();
	}
	
	/**
	 * removing the relationship between 2 hospitals in the graph
	 * @param h1
	 * @param h2
	 */
	private void removeEdgeHosp(Hospital h1, Hospital h2) {
		int result =JOptionPane.showConfirmDialog(null, "Do you really want to end partnership between "
					+h1.getHospitalName()+"\n and "+h2.getHospitalName()+"?");
		if(result==JOptionPane.YES_OPTION) {
			if(hospGraph.isAdjacent(h1, h2)) {
				hospGraph.removeEdge(h1, h2);
				JOptionPane.showMessageDialog(null, h1.getHospitalName()+"("+h1.getHospCode()+") and "+
						h1.getHospitalName()+"("+h1.getHospCode()+") are no longer partners!", "Confirmation Message",
															JOptionPane.INFORMATION_MESSAGE);
			}
		}		
		displayVertices();
	}
	
	/**
	 * Adding a relationship between 2 hospitals in the graph
	 * @param h1
	 * @param h2
	 */
	private void addEdgeHosp(Hospital h1, Hospital h2) {
		
		if(!hospGraph.isAdjacent(h1, h2)) {
			hospGraph.addEdge(h1, h2);
			JOptionPane.showMessageDialog(null, h1.getHospitalName()+"("+h1.getHospCode()+") and "+
					h2.getHospitalName()+"("+h2.getHospCode()+") are now partners!", "Confirmation Message",
													JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, h1.getHospitalName()+"("+h1.getHospCode()+") and "+
					h2.getHospitalName()+"("+h2.getHospCode()+") are already in a partnership!", "Warning Message",
																JOptionPane.WARNING_MESSAGE);
		}
		displayVertices();
	}
	
	/**
	 * Method to determining the best hospital based on the service wanted and shortest path
	 * @param strService
	 */
	private void DetermineShortestPath(String strService) {
		String pos = combouserloc.getSelectedItem().toString();
		
		StringTokenizer st = new StringTokenizer(pos);
		st.nextToken();
		// Latitude		
		double latitude = Double.parseDouble(st.nextToken());
		// Longitude
		double longitude = Double.parseDouble(st.nextToken());
		//System.out.println(latitude);
		distance = min;
		for(Hospital h : hospGraph.getAllVertices()) {
			min = FileReader.calcDistance(latitude, h.getDblLatitude(), longitude, h.getDblLongitude());
			if( min < distance) {
				desthosp = h.getHospitalName();
				distance = min;
				desthosp = h.getHospitalName();
			}
		}
		
	}
	
	/**
	 * Displaying vertices from the graph in the textbox
	 */
	private void displayVertices() {
		txtAreaHospitalList.setText("");
		txtAreaHospitalList.append("\t Current Region: Johannesburg \n\n");
		for(Hospital h : hospGraph.getAllVertices()) {
			
			txtAreaHospitalList.append("Hospital: " + h.getHospitalName() + "Code: " + h.getHospCode() + "\n"
			+ "Opening time: " + h.getDblStartTime() + "Closing time: " + h.getDblCloseTime() + "\n"
			+ "Speciality Care: " + h.getSpecialityCare() + "Contact: " + h.getStrContact() + "\n"
			+ "Longitude: " + h.getDblLongitude() + "Lattitude: " + h.getDblCloseTime());
			txtAreaHospitalList.append("Neighbors: \n");
			//Display list on neighbors.
			ArrayList<String> neighbouringHosp = new ArrayList<>();
			for(Hospital hos : hospGraph.getNeighbors(h)) {
				neighbouringHosp.add(hos.toString());
			}
			for(String specHos : neighbouringHosp) {
				txtAreaHospitalList.append("\t"+specHos+"\n");
			}
			txtAreaHospitalList.append("\n");
			
			txtAreaHospitalList.append("Partners: ");
			//Display list of partners for a specific country.
			for(Hospital n : hospGraph.getNeighbors(h)) {
				txtAreaHospitalList.append("\t"+n.getHospitalName()+"("+n.getHospCode()+") -"+n.getSpecialityCare()+"\n");
			}
			txtAreaHospitalList.append("\n\n");
			
		}
	}
	
}
