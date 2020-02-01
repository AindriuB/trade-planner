package ie.rsi.trader.graph;

import org.springframework.data.annotation.Id;

public class Link {
    

    @Id
    private String id;
    
    private Buy departingNode;
    
    private Sell destinationNode;
    
    
    private Profitability profitability;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Buy getDepartingNode() {
        return departingNode;
    }
    
    public Location getDepartingLocation() {
        return (departingNode == null)? null: departingNode.getLocation();
    }

    public void setDepartingNode(Buy departingNode) {
        this.departingNode = departingNode;
    }

    public Sell getDestinationNode() {
        return destinationNode;
    }
    
    public Location getDestinationLocation() {
        return (destinationNode == null)? null: destinationNode.getLocation();
    }

    public void setDestinationNode(Sell destinationNode) {
        this.destinationNode = destinationNode;
    }

    public Profitability getProfitability() {
        return profitability;
    }

    public void setProfitability(Profitability profitability) {
        this.profitability = profitability;
    }



    
    
}
