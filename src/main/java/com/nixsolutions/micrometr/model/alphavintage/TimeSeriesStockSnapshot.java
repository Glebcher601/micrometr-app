package com.nixsolutions.micrometr.model.alphavintage;

import java.io.Serializable;
import java.util.Date;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "stock_snapshot")
@Data
public class TimeSeriesStockSnapshot implements Serializable
{
  @Id
  private long id;

  private Date timeStamp;

  private double open;

  private double low;

  private double high;

  private double close;

  private int volume;
}
