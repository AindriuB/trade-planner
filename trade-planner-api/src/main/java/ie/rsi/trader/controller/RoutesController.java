package ie.rsi.trader.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ie.rsi.trader.graph.Link;
import ie.rsi.trader.service.RouteService;

@RequestMapping(path = "/routes")
@RestController
public class RoutesController {

    private static Logger LOGGER = LoggerFactory.getLogger(RoutesController.class);
    
    
    @Autowired
    private RouteService routeService;

    @RequestMapping(path = "/", method = {RequestMethod.GET})
    public List<Link> getBuyNodesByCommodityId() {
	LOGGER.info("Getting routess");
	return routeService.getRoutes();
    }
 
  
    @RequestMapping(path = "/{id}", method = {RequestMethod.GET})
    public List<Link> getRoutesByCommodityId(@PathVariable("id") String commodityId) {
	LOGGER.info("Getting routes for commodityId {}", commodityId);
	return routeService.getRouteByCommodityId(commodityId);
    }
 
  
}
