package tobyspring.hellospring;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import tobyspring.hellospring.api.ApiTemplate;
import tobyspring.hellospring.api.ErApiExRateExtractor;
import tobyspring.hellospring.api.SimpleApiExecutor;
import tobyspring.hellospring.exrate.CachedExRateProvider;
import tobyspring.hellospring.exrate.RestTemplateExRateProvider;
import tobyspring.hellospring.payment.ExRateProvider;
import tobyspring.hellospring.payment.PaymentService;

@Configuration
//@ComponentScan
public class PaymentConfig {

  @Bean
  public PaymentService paymentService() {
    return new PaymentService(cachedExRateProvider(),clock());
  }

  @Bean
  public Clock clock() {
    return Clock.systemDefaultZone();
  }

  @Bean
  public ExRateProvider exRateProvider() {
    return new RestTemplateExRateProvider(restTemplate());
  }

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate(new JdkClientHttpRequestFactory());
  }

  @Bean
  public ApiTemplate apiTemplate() {
    return new ApiTemplate(new SimpleApiExecutor(), new ErApiExRateExtractor());
  }

  @Bean
  public ExRateProvider cachedExRateProvider() {
    return new CachedExRateProvider(exRateProvider());
  }
}
