package ie.rsi.trader.service;

import java.util.List;

import ie.rsi.trader.graph.Link;
import ie.rsi.trader.graph.Route;

public interface LinkService {
    
    List<Link> getLinks();
    List<Link> getLinksByCommodityId(String commodityId);

}
