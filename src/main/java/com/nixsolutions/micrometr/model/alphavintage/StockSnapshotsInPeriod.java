package com.nixsolutions.micrometr.model.alphavintage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "stock_time_series")
public class StockSnapshotsInPeriod {
  private StockSnapshotMetaData metaData;

  private List<StockSnapshot> stockSnapShots;
}
