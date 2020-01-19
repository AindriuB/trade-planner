package ie.rsi.trader.graph;

import org.springframework.data.annotation.Id;

public class Link {
    

    @Id
    private String id;
    
    private Node x;
    
    private Node y;

    public Node getX() {
        return x;
    }

    public void setX(Node x) {
        this.x = x;
    }

    public Node getY() {
        return y;
    }

    public void setY(Node y) {
        this.y = y;
    }
    
    
    
}
