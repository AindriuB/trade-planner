package ie.rsi.trader.service;

import java.util.List;

import ie.rsi.trader.graph.Buy;
import ie.rsi.trader.graph.Sell;

public interface NodeService {
    

    Buy getBuyNode(String buyNodeId);
    List<Buy> getBuyNodes();
    List<Buy> getBuyNodesByCommodityId(String commodityId);
    
    List<Sell> getSellNodes();
    List<Sell> getSellNodesByCommodityId(String commodityId);

}
