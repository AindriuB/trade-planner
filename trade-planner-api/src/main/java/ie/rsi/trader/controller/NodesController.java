package ie.rsi.trader.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ie.rsi.trader.graph.Node;
import ie.rsi.trader.service.NodeService;

@RestController
public class NodesController {

    private static Logger LOGGER = LoggerFactory.getLogger(NodesController.class);
    
    
    @Autowired
    private NodeService nodeService;
    
    @RequestMapping(path = "/nodes", method = {RequestMethod.GET})
    public List<Node> getNodes() {
	LOGGER.info("Getting nodes");
	return nodeService.getNodes();
    }
}
