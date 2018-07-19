package com.nixsolutions.micrometr.model.alphavintage;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;

import static java.util.Collections.singletonList;
import static org.junit.Assert.assertEquals;

@Configuration
@ComponentScan(value = {"com.nixsolutions.micrometr.model"})
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {StockSnapshotsInPeriodDeserializerTest.class})
public class StockSnapshotsInPeriodDeserializerTest {

  @Autowired
  private StockSnapshotsInPeriodDeserializer deserializer;

  private ObjectMapper mapper;

  @Before
  public void setUp() throws Exception {
    this.mapper = createMapper();
  }

  @Test
  public void shouldDeserializeCorrectly() throws Exception {
    String stockTest = IOUtils.toString(getClass().getResourceAsStream("test-stock.json"));
    JsonNode jsonNode = mapper.readValue(stockTest, JsonNode.class);

    StockSnapshotsInPeriod stockSnapshotsInPeriod = mapper.treeToValue(jsonNode, StockSnapshotsInPeriod.class);
    assertEquals(createExpectedStockSnapshotInPeriod(), stockSnapshotsInPeriod);
  }

  private StockSnapshotsInPeriod createExpectedStockSnapshotInPeriod() {
    try {
      return new StockSnapshotsInPeriod(createStockMetadata(), singletonList(createStockSnapShot()));
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }

  private StockSnapshotMetaData createStockMetadata() {
    StockSnapshotMetaData metaData = new StockSnapshotMetaData();

    metaData.setInformation("Intraday");
    metaData.setInterval("1min");
    metaData.setLastRefreshed("2018-07-18 11:10:00");
    metaData.setOutputSize("Compact");
    metaData.setSymbol("MSFT");
    metaData.setTimeZone("US/Eastern");

    return metaData;
  }

  private StockSnapshot createStockSnapShot() throws ParseException {
    StockSnapshot stockSnapshot = new StockSnapshot();
    stockSnapshot.setTimeStamp(deserializer.getFormat().parse("2018-07-18 11:10:00"));
    stockSnapshot.setClose(1);
    stockSnapshot.setHigh(1);
    stockSnapshot.setLow(1);
    stockSnapshot.setOpen(1);
    stockSnapshot.setVolume(1);
    return stockSnapshot;
  }

  private ObjectMapper createMapper() {
    ObjectMapper mapper = new ObjectMapper();
    SimpleModule module = new SimpleModule("Test modeule",
        new Version(1, 0, 0, null, null, null));
    module.addDeserializer(StockSnapshotsInPeriod.class, deserializer);
    mapper.registerModule(module);
    return mapper;
  }
}