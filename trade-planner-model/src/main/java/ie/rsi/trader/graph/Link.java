package ie.rsi.trader.graph;

import org.springframework.data.annotation.Id;

public class Link {
    

    @Id
    private String id;
    
    private Node departingNode;
    
    private Node destinationNode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Node getDepartingNode() {
        return departingNode;
    }

    public void setDepartingNode(Node departingNode) {
        this.departingNode = departingNode;
    }

    public Node getDestinationNode() {
        return destinationNode;
    }

    public void setDestinationNode(Node destinationNode) {
        this.destinationNode = destinationNode;
    }

    
    
}
