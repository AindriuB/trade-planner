package ie.rsi.trader.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.rsi.trader.graph.Location;
import ie.rsi.trader.repository.LocationRepository;
import ie.rsi.trader.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService{
    
    @Autowired
    private LocationRepository locationRepostiory;

    @Override
    public Location getLocation(String id) {
	return locationRepostiory.findById(id).get();
    }

    @Override
    public List<Location> getLocations() {
	return locationRepostiory.findAll();
    }

    @Override
    public List<Location> getLocationsBySystem(String id) {
	List<Location> locations = new ArrayList<Location>();
	Location system = locationRepostiory.findById(id).get();
	
	if(system != null) {
	    locations.add(system);
	    locations.addAll(locationRepostiory.findAllByParentId(id));
	}
	
	return locations;
    }

}
