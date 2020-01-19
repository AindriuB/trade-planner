package ie.rsi.trader.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.rsi.trader.graph.Node;
import ie.rsi.trader.repository.NodeRepository;
import ie.rsi.trader.service.NodeService;

@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private NodeRepository nodeRepo;
    
    
    @Override
    public List<Node> getNodes() {
	return nodeRepo.getNodes();
    }

}
