package ie.rsi.trader.service;

import java.util.List;

import ie.rsi.trader.graph.Link;

public interface RouteService {
    

    List<Link> getRoutes();
    List<Link> getRouteByCommodityId(String commodityId);


}
