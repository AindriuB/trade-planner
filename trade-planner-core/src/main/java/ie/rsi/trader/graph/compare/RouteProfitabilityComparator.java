package ie.rsi.trader.graph.compare;

import java.math.BigDecimal;
import java.util.Comparator;

import ie.rsi.trader.graph.Route;

public class RouteProfitabilityComparator implements Comparator<Route> {

    @Override
    public int compare(Route o1, Route o2) {
	BigDecimal po1 = o1.getTotalCost();
	BigDecimal po2 = o2.getTotalCost();
	return po1.compareTo(po2);
    }

}
