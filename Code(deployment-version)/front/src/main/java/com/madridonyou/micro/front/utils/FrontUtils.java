package com.madridonyou.micro.front.utils;

import java.util.LinkedList;
import java.util.List;

import com.madridonyou.micro.domain.outputs.osm.Element;
import com.madridonyou.micro.domain.outputs.osm.OSMResponse;
import com.madridonyou.micro.domain.rdf.Building;

public class FrontUtils {

	public static void processOSMElements (Building [] monuments) {

		List<OSMResponse> osmList;

		List<Element> pub 			=null	  ;
		List<Element> cafe 			=null	  ;
		List<Element> cinema 		=null	  ;
		List<Element> theatre 		=null	  ;
		List<Element> bar 			=null	  ;
		List<Element> restaurant 	=null	  ;
		List<Element> bicycleParking=null 	  ;
		List<Element> pharmacy 		=null	  ;
		List<Element> taxi 			=null	  ;
		List<Element> fastFood 		=null	  ;
		List<Element> bank 			=null	  ;
		List<Element> parking 		=null	  ;
		List<Element> busStop 		=null	  ;
		for (Building m : monuments)
		{
			osmList = m.getOsmresponse();
			for (OSMResponse osm : osmList)
			{
				List<Element> elements = osm.getElements();

				pub 				= new LinkedList<Element>();
				cafe 				= new LinkedList<Element>();
				cinema 			= new LinkedList<Element>();
				theatre 			= new LinkedList<Element>();
				bar 				= new LinkedList<Element>();
				restaurant 		= new LinkedList<Element>();
				bicycleParking 	= new LinkedList<Element>();
				pharmacy 			= new LinkedList<Element>();
				taxi 				= new LinkedList<Element>();
				fastFood 			= new LinkedList<Element>();
				bank 				= new LinkedList<Element>();
				parking 			= new LinkedList<Element>();
				busStop 			= new LinkedList<Element>();

				for (Element elem : elements) 
				{
					elem.setDistance(m.getLatitude(), m.getLongitude(), elem.getLat(), elem.getLon(), "K");
					elem.makeRepresentation();

					if (elem.getTags().getAdditionalProperties().get("amenity") != null)
					{
						if (elem.getTags().getAdditionalProperties().get("amenity").equals("pub") && elem.getRepresentation() != null)
							addSort(pub,elem);

						else if (elem.getTags().getAdditionalProperties().get("amenity").equals("cafe") && elem.getRepresentation() != null)
							addSort(cafe,elem);

						else if (elem.getTags().getAdditionalProperties().get("amenity").equals("theatre") && elem.getRepresentation() != null)
							addSort(theatre,elem);

						else if (elem.getTags().getAdditionalProperties().get("amenity").equals("bar") && elem.getRepresentation() != null)
							addSort(bar,elem);

						else if (elem.getTags().getAdditionalProperties().get("amenity").equals("restaurant") && elem.getRepresentation() != null)
							addSort(restaurant,elem);

						else if (elem.getTags().getAdditionalProperties().get("amenity").equals("bicycle_parking") && elem.getRepresentation() != null)
							addSort(bicycleParking,elem);

						else if (elem.getTags().getAdditionalProperties().get("amenity").equals("pharmacy") && elem.getRepresentation() != null)
							addSort(pharmacy,elem);

						else if (elem.getTags().getAdditionalProperties().get("amenity").equals("taxi") && elem.getRepresentation() != null)
							addSort(taxi,elem);

						else if (elem.getTags().getAdditionalProperties().get("amenity").equals("fast_food") && elem.getRepresentation() != null)
							addSort(fastFood,elem);

						else if (elem.getTags().getAdditionalProperties().get("amenity").equals("bank") && elem.getRepresentation() != null)
							addSort(bank,elem);

						else if (elem.getTags().getAdditionalProperties().get("amenity").equals("cinema") && elem.getRepresentation() != null)
							addSort(cinema,elem);

						else if (elem.getTags().getAdditionalProperties().get("amenity").equals("parking") && elem.getRepresentation() != null)
							addSort(parking,elem);
					}

					else busStop.add(elem);

				}
			}
			m.setPubAround(pub);
			m.setCafeAround(cafe);
			m.setCinemaAround(cinema);
			m.setTheatreAround(theatre);
			m.setBarAround(bar);
			m.setRestaurantAround(restaurant);
			m.setBicycleParkingAround(bicycleParking);
			m.setPharmacyAround(pharmacy);
			m.setTaxiAround(taxi);
			m.setFastFoodAround(fastFood);
			m.setBankAround(bank);
			m.setParkingAround(parking);
			m.setBusStopAround(busStop);
		}




	}

	public static List<List<Element>> partition (LinkedList<Element> elements, Integer size) {

		List<List<Element>> partition = new LinkedList<List<Element>>();
		int truesize = size;

		if (size > 0 && elements.size() > 0)
		{
			if (elements.size()/2 > 3)
				truesize = ((elements.size()/2 + 1) - 3) + size;

			for (int i = 0; i < truesize; i++) 
			{
				partition.add(new LinkedList<Element>());
			}

			int j = 0;
			int k = 0;
			for (int i = 0; i < elements.size(); i++) 
			{
				if (j < partition.size()) //ÑAPA - ARREGLAR BIEN
				{
					partition.get(j).add(elements.get(i));
					k++;
					if (k > truesize) {
						j++;
						k = 0;
					}
				}
			}
		}
		return partition;

	}

	private static void addSort (List<Element> elements, Element elem) {

		if (elements.size() == 0 
				||elements.get(elements.size()-1).getDistance() < elem.getDistance())
			elements.add(elem);
		else {
			boolean inserted = false;
			for (int i = 0; i < elements.size() && !inserted; i++)
			{
				if (elements.get(i).getDistance() > elem.getDistance()) {
					elements.add(i, elem);
					inserted = true;
				}
			}
		}
	}
}
