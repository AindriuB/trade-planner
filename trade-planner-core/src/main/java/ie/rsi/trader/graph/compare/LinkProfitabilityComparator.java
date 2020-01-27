package ie.rsi.trader.graph.compare;

import java.math.BigDecimal;
import java.util.Comparator;

import ie.rsi.trader.graph.Link;
import ie.rsi.trader.graph.Profitability;

public class LinkProfitabilityComparator implements Comparator<Link> {

    @Override
    public int compare(Link o1, Link o2) {
	Profitability po1 = o1.getProfitability();
	Profitability po2 = o2.getProfitability();
	
	if(po1 != null && po2 != null) {
	    return  po1.getProfitability().compareTo(po2.getProfitability());
	} else if (po1 != null) {
	    return po1.getProfitability().compareTo(BigDecimal.ZERO);
	} else {
	    return po2.getProfitability().compareTo(BigDecimal.ZERO);
	}
    }

}
