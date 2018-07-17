package com.nixsolutions.micrometr.persistence;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import com.nixsolutions.micrometr.model.alphavintage.TimeSeriesStockSnapshot;

@Repository
public interface TimeSeriesStockSnapShotMongoRepository extends ReactiveMongoRepository<TimeSeriesStockSnapshot, Long>
{

}
