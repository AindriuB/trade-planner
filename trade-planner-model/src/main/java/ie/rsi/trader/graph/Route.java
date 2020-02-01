package ie.rsi.trader.graph;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Route {

    private TradingNode startingPoint;
    
    private List<Link> route;
    
    private BigDecimal totalCost;
    
    public Route() {
	route = new ArrayList<Link>();
	totalCost =  BigDecimal.ZERO;
    }
    
    public Route(TradingNode startingPoint) {
	this.startingPoint = startingPoint;
	route = new ArrayList<Link>();
	totalCost =  BigDecimal.ZERO;
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

    public void addLink(Link link) {
	this.route.add(link);
	calculateTotalCost();
    }
    
    public void setRoute(List<Link> route) {
        this.route = route;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
    
    public Location getLastTraversedLocation() {
	if(route != null && !route.isEmpty()) {
	    return route.get(route.size() - 1).getDepartingLocation();
	} else {
	    return null;
	}
    }
    
    public BigDecimal calculateTotalCost() {
	if(route != null) {
	    this.totalCost =  route.stream().map(Link::getProfitability).map(Profitability::getProfit).reduce(BigDecimal.ZERO, BigDecimal::add);
	}
	return totalCost;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((route == null) ? 0 : route.hashCode());
	result = prime * result + ((startingPoint == null) ? 0 : startingPoint.hashCode());
	result = prime * result + ((totalCost == null) ? 0 : totalCost.hashCode());
	return result;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	Route other = (Route) obj;
	if (route == null) {
	    if (other.route != null)
		return false;
	} else if (!route.equals(other.route))
	    return false;
	if (startingPoint == null) {
	    if (other.startingPoint != null)
		return false;
	} else if (!startingPoint.equals(other.startingPoint))
	    return false;
	if (totalCost == null) {
	    if (other.totalCost != null)
		return false;
	} else if (!totalCost.equals(other.totalCost))
	    return false;
	return true;
    }
    
    

}
