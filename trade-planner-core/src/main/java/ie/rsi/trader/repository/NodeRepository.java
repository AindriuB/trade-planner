package ie.rsi.trader.repository;

import java.util.List;

import ie.rsi.trader.exception.NotFoundException;
import ie.rsi.trader.graph.Node;

public interface NodeRepository {

    List<Node> getNodes();
    
    Node getNode(Integer id) throws NotFoundException;
    
    Integer saveNode(Node node);
}
