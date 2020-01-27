package ie.rsi.trader.graph;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private TradingNode startingPoint;
    
    private List<Link> route;
    
    public Route() {
	route = new ArrayList<Link>();
    }
    
    public Route(TradingNode startingPoint) {
	this.startingPoint = startingPoint;
	route = new ArrayList<Link>();
    }

    public TradingNode getStartingPoint() {
        return startingPoint;
    }

    public void setStartingPoint(TradingNode startingPoint) {
        this.startingPoint = startingPoint;
    }

    public List<Link> getRoute() {
        return route;
    }

    public void setRoute(List<Link> route) {
        this.route = route;
    }
    
    

}
