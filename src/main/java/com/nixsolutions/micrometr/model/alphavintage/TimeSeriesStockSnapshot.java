package com.nixsolutions.micrometr.model.alphavintage;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document(collection = "stock_snapshot")
@Data
public class TimeSeriesStockSnapshot implements Serializable {
  @Id
  private long id;

  private Date timeStamp;

  @JsonProperty("1. open")
  private double open;

  @JsonProperty("2. high")
  private double low;

  @JsonProperty("3. low")
  private double high;

  @JsonProperty("4. close")
  private double close;

  @JsonProperty("5. volume")
  private int volume;
}
