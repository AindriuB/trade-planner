package ie.rsi.trader.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ie.rsi.trader.service.CommodityService;
import ie.rsi.trader.trade.TradableCommodity;

@RequestMapping(path = "/commodities")
@RestController
public class CommoditiesController {

    private static Logger LOGGER = LoggerFactory.getLogger(CommoditiesController.class);
    
    
    @Autowired
    private CommodityService commodityService;
    
    @RequestMapping(path = "/", method = {RequestMethod.GET})
    public List<TradableCommodity> getCommodities() {
	LOGGER.info("Getting commodities");
	return commodityService.getcommodities();
    }
    
    
}
