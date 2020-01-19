package ie.rsi.trader.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import ie.rsi.trader.graph.Buy;
import ie.rsi.trader.graph.Location;
import ie.rsi.trader.graph.Sell;
import ie.rsi.trader.repository.BuyNodeRepository;
import ie.rsi.trader.repository.CommodityRepository;
import ie.rsi.trader.repository.LocationRepository;
import ie.rsi.trader.repository.SellNodeRepository;
import ie.rsi.trader.service.DataLoadingService;
import ie.rsi.trader.trade.TradableCommodity;

@Service
public class FlatFileDataLoadingServiceImpl implements DataLoadingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(FlatFileDataLoadingServiceImpl.class);

    private static Pattern HEADER_PATTERN = Pattern.compile("^[^,]*$");
    private static Pattern LINE_ITEM_PATTERN = Pattern.compile("(.*),(.*),(.*)");
    private static Pattern SYSTEMS_PATTERN = Pattern.compile("(.*),(.*)");

    @Value("${market-data.path}")
    private Resource[] marketResources;

    @Autowired
    private BuyNodeRepository buyNodeRepository;

    @Autowired
    private SellNodeRepository sellNodeRepository;

    @Autowired
    private CommodityRepository commodityRepository;
    
    
    @PostConstruct
    public void init() {
	loadMarketData();
    }

    @Override
    public void loadMarketData() {

	if (marketResources != null) {
	    LOGGER.info("Reading {} files", marketResources.length);
	    for (Resource resource : marketResources) {
		InputStream inputStream = null;
		try {
		    inputStream = resource.getInputStream();
		    byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
		    String data = new String(bdata, StandardCharsets.UTF_8);
		    processFile(data);
		} catch (IOException e) {
		    LOGGER.error("IOException", e);
		} finally {
		    try {
			inputStream.close();
		    } catch (IOException e) {
			LOGGER.error("IOException", e);
		    }
		}
	    }
	} else {
	    LOGGER.warn("No files to be read");
	    return;
	}

    }

    private void processFile(String file) {
	String system = null;
	String[] rows = file.split("\r\n");
	if (HEADER_PATTERN.matcher(rows[0]).matches()) {
	    system = rows[0];
	    LOGGER.info("Location: {}", system);
	} else {
	    system = "UNKNOWN";
	}

	for (String line : file.split("\r\n")) {
	    if (LINE_ITEM_PATTERN.matcher(line).matches()) {
		String[] lineItem = line.split(",");
		String commodity = lineItem[0];
		double buyPrice = Double.valueOf(lineItem[1]);
		double sellPrice = Double.valueOf(lineItem[2]);
		LOGGER.info("Commodity: {}, Buy: {}, Sell: {}", commodity, buyPrice, sellPrice);
		
		TradableCommodity tradableCommodity = commodityRepository.findByName(commodity);
		
		
		if(tradableCommodity == null) {
		  tradableCommodity = new TradableCommodity();
		  tradableCommodity.setName(commodity);
		  tradableCommodity = commodityRepository.save(tradableCommodity);
		} 
	
		Buy existingBuyNode = buyNodeRepository.findByNameAndCommodityId(system, tradableCommodity.getId());
		if (buyPrice >= 0) {
		    Buy node = new Buy();
		    if(existingBuyNode != null) {
			node.setId(existingBuyNode.getId());
		    }
		    node.setName(system);
		    node.setCommodity(tradableCommodity);
		    node.setPrice(buyPrice);

		    buyNodeRepository.save(node);
		}
		Sell existingSellNode = sellNodeRepository.findByNameAndCommodityId(system, tradableCommodity.getId());
		
		if(sellPrice >= 0) {
		    Sell node = new Sell();
		    if(existingSellNode != null) {
			node.setId(existingSellNode.getId());
		    }
		    node.setName(system);
		    node.setCommodity(tradableCommodity);
		    node.setPrice(sellPrice);
		    sellNodeRepository.save(node);
		}
		
	   }
	}
    }

}
