package ie.rsi.trader.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ie.rsi.trader.graph.Node;

public interface NodeRepository extends MongoRepository<Node,String> {

    
    Node findByName(String name);

}
