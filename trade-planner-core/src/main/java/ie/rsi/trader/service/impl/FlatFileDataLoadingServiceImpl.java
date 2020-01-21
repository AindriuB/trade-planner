package ie.rsi.trader.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
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

    private static Pattern HEADER_PATTERN = Pattern.compile("(.*),(.*)");
    private static Pattern LINE_ITEM_PATTERN = Pattern.compile("(.*),(.*),(.*)");

    @Value("${data.market}")
    private Resource[] marketResources;

    @Value("${data.systems}")
    private Resource[] systemsResources;

    
    @Autowired
    private BuyNodeRepository buyNodeRepository;

    @Autowired
    private SellNodeRepository sellNodeRepository;

    @Autowired
    private CommodityRepository commodityRepository;
    

    @Autowired
    private LocationRepository locationRepository;
    @PostConstruct
    public void init() {
	//loadSystemsData();
	//loadMarketData();
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
		    processMarketData(data);
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
    
    @Override
    public void loadSystemsData() {

	if (systemsResources != null) {
	    LOGGER.info("Reading {} files", systemsResources.length);
	    for (Resource resource : systemsResources) {
		InputStream inputStream = null;
		try {
		    inputStream = resource.getInputStream();
		    byte[] bdata = FileCopyUtils.copyToByteArray(inputStream);
		    String data = new String(bdata, StandardCharsets.UTF_8);
		    processSystemsData(data);
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

    private void processMarketData(String file) {
	String system = null;
	String[] rows = file.split("\r\n");
	Location location = null;
	if (HEADER_PATTERN.matcher(rows[0]).matches()) {
	    system = rows[0];
	    String[] systemParts = system.split(",");
	    location = locationRepository.findById(systemParts[1]).get();
	    LOGGER.info("Location: {}", system);
	} else {
	    system = "UNKNOWN";
	    location = new Location();
	    location.setName(system);
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
	
		Buy existingBuyNode = buyNodeRepository.findByLocationIdAndCommodityId(location.getId(), tradableCommodity.getId());
		if (buyPrice >= 0) {
		    Buy node = new Buy();
		    if(existingBuyNode != null) {
			node.setId(existingBuyNode.getId());
		    }
		    node.setName(location.getName());
		    node.setCommodity(tradableCommodity);
		    node.setPrice(BigDecimal.valueOf(buyPrice));
		    node.setLocation(location);

		    buyNodeRepository.save(node);
		}
		Sell existingSellNode = sellNodeRepository.findByLocationIdAndCommodityId(location.getId(), tradableCommodity.getId());
		
		if(sellPrice >= 0) {
		    Sell node = new Sell();
		    if(existingSellNode != null) {
			node.setId(existingSellNode.getId());
		    }
		    node.setName(location.getName());
		    node.setCommodity(tradableCommodity);
		    node.setPrice(BigDecimal.valueOf(sellPrice));
		    node.setLocation(location);
		    sellNodeRepository.save(node);
		}
		
	   }
	}
    }
    
    private void processSystemsData(String file) {
	String[] rows = file.split("\r\n");
	for (String row : rows) {
	    String[] locations = row.split(",");
	    Location base = null;
	    if (locations.length > 0) {
		base = setLocation(locations[0], null);
		for (int i = 1; i < locations.length; i++) {
		    setLocation(locations[i], locations[i - 1]);
		}

	    }
	}
    }

    private Location setLocation(String systemName, String parentSystemName) {
	Location location = locationRepository.findByName(systemName);
	if(location == null) {
	    location = new Location();
	} 
	location.setName(systemName);
	if(parentSystemName != null) {
	    Location parentSystem = locationRepository.findByName(parentSystemName);
	    if(parentSystem != null) {
		location.setParentId(parentSystem.getId());
	    }
	}
	
	return locationRepository.save(location);
    }
}
