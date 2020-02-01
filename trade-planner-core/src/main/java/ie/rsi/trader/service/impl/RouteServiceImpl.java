package ie.rsi.trader.service.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.print.attribute.standard.MediaSize.Engineering;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.rsi.trader.graph.Buy;
import ie.rsi.trader.graph.Link;
import ie.rsi.trader.graph.Location;
import ie.rsi.trader.graph.Route;
import ie.rsi.trader.graph.compare.LinkProfitabilityComparator;
import ie.rsi.trader.graph.compare.RouteProfitabilityComparator;
import ie.rsi.trader.service.LinkService;
import ie.rsi.trader.service.NodeService;
import ie.rsi.trader.service.RouteService;

@Service
public class RouteServiceImpl implements RouteService {

    private static Logger LOGGER = LoggerFactory.getLogger(RouteServiceImpl.class);

    private static final int DEFAULT_ROUTE_LENGTH = 3;

    @Autowired
    private NodeService nodeService;
    
    @Autowired
    private LinkService linkService;

    @Override
    public Route findRoute() {
	Route route = new Route();

	List<Link> links = linkService.getLinks();

	if (links.isEmpty()) {
	    return route;
	}

	Collections.sort(links, new LinkProfitabilityComparator());

	route.setStartingPoint(links.get(0).getDepartingNode());

	return route;
    }

    @Override
    public Route findRoute(String tradingNodeId) {

	Buy tradingNode = nodeService.getBuyNode(tradingNodeId);
	List<Route> possibleRoutes = new ArrayList<Route>();

	List<Link> links = linkService.getLinks();


	Map<Location, List<Link>> linksByBuyNode = links.stream()
		.collect(Collectors.groupingBy(Link::getDepartingLocation));
	Map<Location, List<Buy>> buyNodesByBuyNode = links.stream().map(Link::getDepartingNode).collect(Collectors.groupingBy(Buy::getLocation));
	
	
	
	Location startingLocation = tradingNode.getLocation();
	Buy currentlocation = tradingNode;
	Route bestRoute = new Route();
	bestRoute.setStartingPoint(currentlocation);
	int index = 0;
	while (index < DEFAULT_ROUTE_LENGTH) {
	    index++;
	    LOGGER.info("currently on node: {}", currentlocation);
	    

	    Collections.sort(possibleRoutes, new RouteProfitabilityComparator());
	    
	    if(!bestRoute.equals(possibleRoutes.get(0))){
		bestRoute = possibleRoutes.get(0);
		
	    }
	
	  

	}

	return possibleRoutes.get(0);
    }

}
