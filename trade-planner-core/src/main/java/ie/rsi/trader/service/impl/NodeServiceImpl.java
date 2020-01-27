package ie.rsi.trader.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.rsi.trader.graph.Buy;
import ie.rsi.trader.graph.Sell;
import ie.rsi.trader.repository.BuyNodeRepository;
import ie.rsi.trader.repository.SellNodeRepository;
import ie.rsi.trader.service.NodeService;

@Service
public class NodeServiceImpl implements NodeService {

    @Autowired
    private BuyNodeRepository buyNodeRepo;
    

    @Autowired
    private SellNodeRepository sellNodeRepo;
    
    @Override
    public Buy getBuyNode(String buyNodeId) {
	try {
	    return buyNodeRepo.findById(buyNodeId).get();
	} catch (NoSuchElementException e) {
	    return null;
	}
    }
    @Override
    public List<Buy> getBuyNodes() {
	return buyNodeRepo.findAll();
    }
    
    @Override
    public List<Buy> getBuyNodesByCommodityId(String commodityId) {
	return buyNodeRepo.findAllByCommodityId(commodityId);
    }
    
    @Override
    public List<Sell> getSellNodes() {
	return sellNodeRepo.findAll();
    }

    @Override
    public List<Sell> getSellNodesByCommodityId(String commodityId) {
	return sellNodeRepo.findAllByCommodityId(commodityId);
    }



}
