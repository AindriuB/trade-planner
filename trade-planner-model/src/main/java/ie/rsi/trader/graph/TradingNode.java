package ie.rsi.trader.graph;

import ie.rsi.trader.trade.TradableCommodity;

public class TradingNode extends Node {
    
    public static enum Type {SELLER, BUYER};
    
    private double price;
    
    private TradableCommodity commodity;
    
    private Type type;
    
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


}
