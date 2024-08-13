package tobyspring.hellospring.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.stereotype.Component;

@Component
public class PaymentService {

  private final ExRateProvider exRateProvider;


  public PaymentService(ExRateProvider exRateProvider) {
    this.exRateProvider = exRateProvider;
  }

  public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount)
      throws IOException {
    BigDecimal exchangeDate = exRateProvider.getExchangeDate(currency);
    BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exchangeDate);
    LocalDateTime validUntilDate = LocalDateTime.now().plusMinutes(30L);

    return new Payment(orderId, currency, foreignCurrencyAmount, exchangeDate, convertedAmount,
        validUntilDate);
  }
}
