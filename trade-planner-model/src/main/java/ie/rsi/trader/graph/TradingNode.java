package ie.rsi.trader.graph;

import ie.rsi.trader.trade.TradableCommodity;

public class TradingNode extends Node {
   
    private double price;
    
    private TradableCommodity commodity;
    
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public TradableCommodity getCommodity() {
        return commodity;
    }

    public void setCommodity(TradableCommodity commodity) {
        this.commodity = commodity;
    }



}
