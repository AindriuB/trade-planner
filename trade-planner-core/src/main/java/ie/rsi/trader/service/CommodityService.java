package ie.rsi.trader.service;

import java.util.List;

import ie.rsi.trader.trade.TradableCommodity;

public interface CommodityService {
    
    List<TradableCommodity> getcommodities();
    
    TradableCommodity getCommodity(String id);

}
