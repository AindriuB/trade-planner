package ie.rsi.trader.search;

import ie.rsi.trader.graph.Link;
import ie.rsi.trader.graph.TraversalCost;

public interface Heuristic {
    
    TraversalCost calculateCost(Link link);

}
