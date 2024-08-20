package tobyspring.hellospring.exrate;

import java.math.BigDecimal;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import org.springframework.stereotype.Component;
import tobyspring.hellospring.api.ApiTemplate;
import tobyspring.hellospring.api.ErApiExRateExtractor;
import tobyspring.hellospring.api.HttpClientApiExecutor;
import tobyspring.hellospring.api.SimpleApiExecutor;
import tobyspring.hellospring.payment.ExRateProvider;

@Component
public class WebApiExRateProvider implements ExRateProvider {

  private final ApiTemplate apiTemplate;

  public WebApiExRateProvider(ApiTemplate apiTemplate) {
    this.apiTemplate = apiTemplate;
  }

  @Override
  public BigDecimal getExchangeRate(String currency) {
    final String url = "https://open.er-api.com/v6/latest/" + currency;
    return apiTemplate.getExRate(url);
  }

}
