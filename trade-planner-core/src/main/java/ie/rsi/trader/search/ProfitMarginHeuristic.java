package ie.rsi.trader.search;

import java.math.BigDecimal;

import ie.rsi.trader.graph.Buy;
import ie.rsi.trader.graph.Link;
import ie.rsi.trader.graph.Sell;
import ie.rsi.trader.graph.TraversalCost;

public class ProfitMarginHeuristic implements Heuristic{

    @Override
    public TraversalCost calculateCost(Link link) {
	Buy depart = link.getDepartingNode();
	Sell destination = link.getDestinationNode();
	
	BigDecimal buyPrice = depart.getPrice();
	BigDecimal sellPrice = destination.getPrice();
	BigDecimal profit = sellPrice.subtract(buyPrice);
	BigDecimal profitMargin = profit.divide(buyPrice, BigDecimal.ROUND_HALF_UP).multiply(BigDecimal.valueOf(100));

	TraversalCost traversalCost = new TraversalCost();
	traversalCost.setCost(profitMargin);
	return traversalCost;
    }

}
