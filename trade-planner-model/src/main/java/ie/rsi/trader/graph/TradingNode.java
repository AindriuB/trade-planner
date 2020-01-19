package ie.rsi.trader.graph;

import ie.rsi.trader.trade.TradableGoods;

public class TradingNode extends Node {
    
    private double price;
    
    private TradableGoods goods;
    
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TradableGoods getGoods() {
        return goods;
    }

    public void setGoods(TradableGoods goods) {
        this.goods = goods;
    }
}
