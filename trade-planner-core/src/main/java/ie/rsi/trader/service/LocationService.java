package ie.rsi.trader.service;

import java.util.List;

import ie.rsi.trader.graph.Location;

public interface LocationService {
    
    Location getLocation(String id);

    List<Location> getLocations();
    
    List<Location> getLocationsBySystem(String id);

}
