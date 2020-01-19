package ie.rsi.trader.graph;

import org.springframework.data.annotation.Id;

public class Node {


    @Id
    private String id;
    private String name;
    
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
}
