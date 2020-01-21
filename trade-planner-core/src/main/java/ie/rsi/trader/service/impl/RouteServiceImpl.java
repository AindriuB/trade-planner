package ie.rsi.trader.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.rsi.trader.graph.Buy;
import ie.rsi.trader.graph.Link;
import ie.rsi.trader.graph.Profitability;
import ie.rsi.trader.graph.Sell;
import ie.rsi.trader.graph.TraversalCost;
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
		.filter(buy -> (buy.getPrice().compareTo(BigDecimal.ZERO) > 0))
		.forEach(buy -> sellNodes.stream()
					.filter(sell -> (sell.getPrice().compareTo(BigDecimal.ZERO) > 0))
					.forEach(sell -> {
					    if(sell.getPrice().compareTo(buy.getPrice()) > 0) {
                                        	  Link link = new Link();
                                        	  link.setDepartingNode(buy);
                                        	  link.setDestinationNode(sell);
                                        	  link.setProfitability(calculateProfitablity(link));
                                        	  links.add(link);
					    }
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

    private Profitability calculateProfitablity(Link link) {
	Profitability profitability = new Profitability();
	Buy depart = link.getDepartingNode();
	Sell destination = link.getDestinationNode();
	
	BigDecimal buyPrice = depart.getPrice();
	BigDecimal sellPrice = destination.getPrice();
	
	if(buyPrice.compareTo(BigDecimal.ZERO) != 0) {
        	
        	BigDecimal profit = sellPrice.subtract(buyPrice);
        	BigDecimal profitMargin = profit.divide(buyPrice, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));
        
        	TraversalCost traversalCost = new TraversalCost();
        	traversalCost.setCost(profitMargin);
        	profitability.setProfit(profit);
        	profitability.setProfitability(profitMargin);
	
	}
	return profitability;
    }
}
