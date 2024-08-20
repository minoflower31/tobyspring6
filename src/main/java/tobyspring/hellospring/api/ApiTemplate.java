package tobyspring.hellospring.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

public class ApiTemplate {

  private final ApiExecutor apiExecutor;
  private final ExRateExtractor exRateExtractor;

  public ApiTemplate() {
    this.apiExecutor = new HttpClientApiExecutor();
    this.exRateExtractor = new ErApiExRateExtractor();
  }

  public ApiTemplate(ApiExecutor apiExecutor, ExRateExtractor exRateExtractor) {
    this.apiExecutor = apiExecutor;
    this.exRateExtractor = exRateExtractor;
  }

  // 편리한 메소드
  public BigDecimal getExRate(String url) {
    return this.getExRate(url, this.apiExecutor, this.exRateExtractor);
  }

  public BigDecimal getExRate(String url, ApiExecutor apiExecutor) {
    return this.getExRate(url, apiExecutor, this.exRateExtractor);
  }

  public BigDecimal getExRate(String url, ExRateExtractor exRateExtractor) {
    return this.getExRate(url, this.apiExecutor, exRateExtractor);
  }

  public BigDecimal getExRate(String url, ApiExecutor apiExecutor,
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
