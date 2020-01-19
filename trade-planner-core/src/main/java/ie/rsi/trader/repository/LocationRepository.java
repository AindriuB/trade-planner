package ie.rsi.trader.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import ie.rsi.trader.graph.Location;

public interface LocationRepository extends MongoRepository<Location, String> {

    
    List<Location> findAllByParentId(String parentId);
    
    Location findByName(String name);
    
}
