package ie.rsi.trader.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ie.rsi.trader.repository.CommodityRepository;
import ie.rsi.trader.service.CommodityService;
import ie.rsi.trader.trade.TradableCommodity;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityRepository commodityRepo;

    @Override
    public List<TradableCommodity> getcommodities() {
	return commodityRepo.findAll();
    }

    @Override
    public TradableCommodity getCommodity(String id) {
	TradableCommodity commodity = commodityRepo.findById(id).get();
	return commodity;
    }
    

}
