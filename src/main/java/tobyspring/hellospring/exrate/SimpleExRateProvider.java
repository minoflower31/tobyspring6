package tobyspring.hellospring.exrate;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;
import tobyspring.hellospring.payment.ExRateProvider;

@Component
public class SimpleExRateProvider implements ExRateProvider {

  @Override
  public BigDecimal getExchangeRate(String currency) {
    if (currency.equals("USD")) {
      return BigDecimal.valueOf(1000);
    }

    throw new IllegalArgumentException("지원되지 않는 통화입니다.");
  }
}
