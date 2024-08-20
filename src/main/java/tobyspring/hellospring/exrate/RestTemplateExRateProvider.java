package tobyspring.hellospring.exrate;

import java.math.BigDecimal;
import org.springframework.web.client.RestTemplate;
import tobyspring.hellospring.payment.ExRateProvider;

public class RestTemplateExRateProvider implements ExRateProvider {

  private final RestTemplate restTemplate;

  public RestTemplateExRateProvider(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @Override
  public BigDecimal getExchangeRate(String currency) {
    final String url = "https://open.er-api.com/v6/latest/" + currency;

    return restTemplate.getForObject(url, ExchangeRateData.class)
        .rates().get("KRW");
  }
}
