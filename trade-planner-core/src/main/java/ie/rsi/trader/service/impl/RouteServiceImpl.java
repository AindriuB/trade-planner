package ie.rsi.trader.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.rsi.trader.graph.Buy;
import ie.rsi.trader.graph.Link;
import ie.rsi.trader.graph.Sell;
import ie.rsi.trader.service.CommodityService;
import ie.rsi.trader.service.NodeService;
import ie.rsi.trader.service.RouteService;
import ie.rsi.trader.trade.TradableCommodity;

@Service
public class RouteServiceImpl implements RouteService {
    
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private NodeService nodeService;

    @Override
    public List<Link> getRouteByCommodityId(String commodityId) {
	List<Buy> buyNodes = nodeService.getBuyNodesByCommodityId(commodityId);
	List<Sell> sellNodes = nodeService.getSellNodesByCommodityId(commodityId);
	List<Link> links = new ArrayList<Link>();
	buyNodes
		.stream()
		.filter(buy -> (buy.getPrice() > 0.0d))
		.forEach(buy -> sellNodes.stream()
					.filter(sell -> (sell.getPrice() > 0.0d))
					.forEach(sell -> {
                                    	  Link link = new Link();
                                    	  link.setDepartingNode(buy);
                                    	  link.setDestinationNode(sell);
                                    	  links.add(link);
					})
			);
	
	return links;
    }

    @Override
    public List<Link> getRoutes() {
	List<TradableCommodity> commodities = commodityService.getcommodities();
	List<Link> links = new ArrayList<Link>();
	
	commodities.stream().forEach(commodity -> {
	    links.addAll(getRouteByCommodityId(commodity.getId()));
	});
	
	return links;
	
    }

}
