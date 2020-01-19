package ie.rsi.trader.graph;

import ie.rsi.trader.trade.TradableCommodity;

public class TradingNode extends Node {
   
    private double price;
    
    private TradableCommodity commodity;
    
    private Location location;
    
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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }



}
