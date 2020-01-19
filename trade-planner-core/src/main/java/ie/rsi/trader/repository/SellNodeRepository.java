package ie.rsi.trader.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ie.rsi.trader.graph.Sell;

public interface SellNodeRepository extends MongoRepository<Sell,String> {

    
    Sell findByName(String name);
    
    
    Sell findByNameAndCommodityId(String name, String commodityId);


    List<Sell> findAllByCommodityId(String commodityId);
}
