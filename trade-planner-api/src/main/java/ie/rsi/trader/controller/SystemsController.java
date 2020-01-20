package ie.rsi.trader.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ie.rsi.trader.graph.Location;
import ie.rsi.trader.service.LocationService;

@RequestMapping(path = "/systems")
@RestController
public class SystemsController {
    
    private static Logger LOGGER = LoggerFactory.getLogger(SystemsController.class);
    @Autowired
    private LocationService locationService;

    @RequestMapping(path = "/", method = {RequestMethod.GET})
    public List<Location> getLocations() {
	LOGGER.info("Getting systems");
	return locationService.getLocations();
    }
}
