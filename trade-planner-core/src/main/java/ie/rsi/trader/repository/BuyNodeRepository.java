package ie.rsi.trader.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ie.rsi.trader.graph.Buy;

public interface BuyNodeRepository extends MongoRepository<Buy,String> {

    
    Buy findByName(String name);
    
    Buy findByLocationIdAndCommodityId(String name, String commodityId);

    List<Buy> findAllByCommodityId(String commodityId);
}
