package ie.rsi.trader.service;

import java.util.List;

import ie.rsi.trader.graph.Buy;
import ie.rsi.trader.graph.Sell;

public interface NodeService {
    
    
    List<Buy> getBuyNodes();
    List<Sell> getSellNodes();

}
