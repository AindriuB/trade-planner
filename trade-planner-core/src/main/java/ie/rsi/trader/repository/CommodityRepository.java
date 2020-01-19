package ie.rsi.trader.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ie.rsi.trader.trade.TradableCommodity;

public interface CommodityRepository extends MongoRepository<TradableCommodity, String> {

    public TradableCommodity findByName(String name);

}
