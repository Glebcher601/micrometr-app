package com.nixsolutions.micrometr.model.alphavintage;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

import static java.util.Spliterator.ORDERED;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.StreamSupport.stream;

@Component
public class StockSnapshotsInPeriodDeserializer extends StdDeserializer<StockSnapshotsInPeriod> {
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

  private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:MM:ss");

  public StockSnapshotsInPeriodDeserializer() {
    this(null);
  }

  public StockSnapshotsInPeriodDeserializer(Class<?> vc) {
    super(vc);
  }

  @Override
  public StockSnapshotsInPeriod deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
    Iterator<Map.Entry<String, JsonNode>> fields = ((JsonNode) parser.getCodec().readTree(parser)).fields();

    //TODO clumsy to read from iterator
    StockSnapshotMetaData metaData = OBJECT_MAPPER.treeToValue(fields.next().getValue(), StockSnapshotMetaData.class);
    List<StockSnapshot> stockSnapshots = parseTimeSeries(fields.next().getValue(), metaData);

    return new StockSnapshotsInPeriod(metaData, stockSnapshots);
  }

  private List<StockSnapshot> parseTimeSeries(JsonNode jsonNode, StockSnapshotMetaData metaData) {
    return stream(spliteratorUnknownSize(jsonNode.fields(), ORDERED), false)
        .map(entry -> createStockSnapshot(
            entry.getKey(),
            TimeZone.getTimeZone(metaData.getTimeZone()),
            entry.getValue()))
        .collect(Collectors.toList());
  }

  //TODO default timeZone
  private StockSnapshot createStockSnapshot(String timeStamp, TimeZone timeZone, JsonNode stockData) {
    StockSnapshot stockSnapshot;
    try {
      stockSnapshot = OBJECT_MAPPER.treeToValue(stockData, StockSnapshot.class);
      format.setTimeZone(timeZone);
      stockSnapshot.setTimeStamp(format.parse(timeStamp));
      return stockSnapshot;
    } catch (JsonProcessingException | ParseException e) {
      throw new RuntimeException(e);
    }
  }

  public SimpleDateFormat getFormat() {
    return format;
  }
}
