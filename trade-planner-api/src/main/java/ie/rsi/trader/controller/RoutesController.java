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
import ie.rsi.trader.graph.Route;
import ie.rsi.trader.service.LinkService;
import ie.rsi.trader.service.RouteService;

@RequestMapping(path = "/api/routes")
@RestController
public class RoutesController {

    private static Logger LOGGER = LoggerFactory.getLogger(RoutesController.class);
    
    @Autowired
    private LinkService linkService;
    
    @Autowired
    private RouteService routeService;

    @RequestMapping(path = "/links/", method = {RequestMethod.GET})
    public List<Link> getBuyNodesByCommodityId() {
	LOGGER.info("Getting routess");
	return linkService.getLinks();
    }
 
  
    @RequestMapping(path = "/links/{id}", method = {RequestMethod.GET})
    public List<Link> getLinksByCommodityId(@PathVariable("id") String commodityId) {
	LOGGER.info("Getting routes for commodityId {}", commodityId);
	return linkService.getLinksByCommodityId(commodityId);
    }
 
    @RequestMapping(path = "/{id}", method = {RequestMethod.GET})
    public Route getRoutesByFrom(@PathVariable("id") String locaitonId) {
	LOGGER.info("Getting routes from {}", locaitonId);
	return routeService.findRoute(locaitonId);
    }
 
  
}
