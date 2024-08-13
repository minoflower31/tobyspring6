package tobyspring.hellospring.exrate;

import java.io.IOException;
import java.math.BigDecimal;
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
  public BigDecimal getExchangeDate(String currency) throws IOException {
    if(cachedExRate == null || cachedExpiryTime.isBefore(LocalDateTime.now())) {
      cachedExRate = this.target.getExchangeDate(currency);
      cachedExpiryTime = LocalDateTime.now().plusSeconds(3);

      System.out.println("Cache Updated");
    }

    return cachedExRate;
  }
}
