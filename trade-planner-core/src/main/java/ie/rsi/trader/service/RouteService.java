package ie.rsi.trader.service;

import java.util.List;

import ie.rsi.trader.graph.Link;
import ie.rsi.trader.graph.Route;

public interface RouteService {
    
    Route findRoute();
    Route findRoute(String tradingNodeId);
    List<Link> getRoutes();
    List<Link> getRouteByCommodityId(String commodityId);

}
