package ie.rsi.trader.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ie.rsi.trader.graph.Buy;

public interface BuyNodeRepository extends MongoRepository<Buy,String> {

    
    Buy findByName(String name);
    
    
    Buy findByNameAndCommodityId(String name, String commodityId);

}
