package tobyspring.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import tobyspring.hellospring.exrate.ExchangeRateData;

public class ErApiExRateExtractor implements ExRateExtractor {

  @Override
  public BigDecimal extract(String response) throws JsonProcessingException {
    ObjectMapper objectMapper = new ObjectMapper();
    ExchangeRateData data = objectMapper.readValue(response, ExchangeRateData.class);
    System.out.println("API ExRate: " + data.rates().get("KRW"));
    return data.rates().get("KRW");
  }
}
