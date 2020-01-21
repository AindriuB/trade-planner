package ie.rsi.trader.graph;

import java.math.BigDecimal;

import ie.rsi.trader.trade.TradableCommodity;

public class TradingNode extends Node {
   
    private BigDecimal price;
    
    private TradableCommodity commodity;
    
    private Location location;
    
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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
