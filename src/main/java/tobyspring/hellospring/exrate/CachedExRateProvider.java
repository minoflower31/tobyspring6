package tobyspring.hellospring.exrate;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;
import tobyspring.hellospring.payment.ExRateProvider;

@Component
public class CachedExRateProvider implements ExRateProvider {

  private final ExRateProvider target;

  private BigDecimal cachedExRate;
  private LocalDateTime cachedExpiryTime;

  public CachedExRateProvider(ExRateProvider target) {
    this.target = target;
  }

  @Override
  public BigDecimal getExchangeRate(String currency) {
    if (cachedExRate == null || cachedExpiryTime.isBefore(LocalDateTime.now())) {
      cachedExRate = this.target.getExchangeRate(currency);
      cachedExpiryTime = LocalDateTime.now().plusSeconds(3);

      System.out.println("Cache Updated");
    }

    return cachedExRate;
  }
}
