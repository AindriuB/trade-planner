package ie.rsi.trader.repository.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import ie.rsi.trader.exception.NotFoundException;
import ie.rsi.trader.graph.Node;
import ie.rsi.trader.repository.NodeRepository;

@Repository
public class InMemoryNodeRepository implements NodeRepository {

    private static Map<Integer, Node> nodeRepo;
    
 
    @PostConstruct
    public void init() {
	nodeRepo = new HashMap<Integer, Node>();
    }
    
    @Override
    public List<Node> getNodes() {
	return new ArrayList<Node>(nodeRepo.values());
    }

    @Override
    public Node getNode(Integer id) throws NotFoundException{
	if(!nodeRepo.containsKey(id)) {
	    throw new NotFoundException();
	}
	return nodeRepo.get(id);
    }

    @Override
    public Integer saveNode(Node node) {
	Integer id = null;
	if(nodeRepo.isEmpty()) {
	    id = Integer.valueOf(1);
	} else {
	    List<Integer> ids = new ArrayList<Integer>(nodeRepo.keySet());
	    Collections.sort(ids, Collections.reverseOrder());
	    
	    id = ids.get(0) + 1; 
	}
	node.setId(id);
	nodeRepo.put(id, node);
	
	return id;
    }

}
