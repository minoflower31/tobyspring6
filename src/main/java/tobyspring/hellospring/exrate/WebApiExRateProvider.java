package tobyspring.hellospring.exrate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import org.springframework.stereotype.Component;
import tobyspring.hellospring.api.ApiExecutor;
import tobyspring.hellospring.api.ErApiExRateExtractor;
import tobyspring.hellospring.api.ExRateExtractor;
import tobyspring.hellospring.api.SimpleApiExecutor;
import tobyspring.hellospring.payment.ExRateProvider;

@Component
public class WebApiExRateProvider implements ExRateProvider {

  @Override
  public BigDecimal getExchangeRate(String currency) {
    final String url = "https://open.er-api.com/v6/latest/" + currency;

    return runApiForExRate(url, new SimpleApiExecutor(), new ErApiExRateExtractor());
  }

  private static BigDecimal runApiForExRate(String url, ApiExecutor apiExecutor,
      ExRateExtractor exRateExtractor) {
    URI uri;
    try {
      uri = new URI(url);
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    }

    String response;
    try {
      // 바뀌는 코드를 기준으로 리팩토링
      // try, catch 구문은 바뀌지 않으니 그대로 둠
      response = apiExecutor.execute(uri);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try {
      return exRateExtractor.extract(response);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }
}
