package ie.rsi.trader.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ie.rsi.trader.graph.Buy;
import ie.rsi.trader.graph.Sell;
import ie.rsi.trader.service.NodeService;

@RequestMapping(path = "/nodes")
@RestController
public class NodesController {

    private static Logger LOGGER = LoggerFactory.getLogger(NodesController.class);
    
    
    @Autowired
    private NodeService nodeService;
    

  
    
    @RequestMapping(path = "/buy", method = {RequestMethod.GET})
    public List<Buy> getBuyNodes() {
	LOGGER.info("Getting nodes");
	return nodeService.getBuyNodes();
    }
    
    @RequestMapping(path = "/buy/{id}", method = {RequestMethod.GET})
    public List<Buy> getBuyNodesByCommodityId(@PathVariable("id") String commodityId) {
	LOGGER.info("Getting commodity");
	return nodeService.getBuyNodesByCommodityId(commodityId);
    }
  
    /**
     * @return
     */
    @RequestMapping(path = "/sell", method = {RequestMethod.GET})
    public List<Sell> getSellNodes() {
	LOGGER.info("Getting nodes");
	return nodeService.getSellNodes();
    }
    
    @RequestMapping(path = "/sell/{id}", method = {RequestMethod.GET})
    public List<Sell> getSellNodesByCommodityId(@PathVariable("id") String commodityId) {
	LOGGER.info("Getting commodity");
	return nodeService.getSellNodesByCommodityId(commodityId);
    }
  
}
