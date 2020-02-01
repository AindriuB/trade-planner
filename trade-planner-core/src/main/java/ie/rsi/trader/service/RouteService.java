package ie.rsi.trader.service;

import ie.rsi.trader.graph.Route;

public interface RouteService {
    
    Route findRoute();
    Route findRoute(String tradingNodeId);
}
