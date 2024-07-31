package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public abstract class PaymentService {

  public Payment prepare(Long orderId, String currency, BigDecimal foreignCurrencyAmount)
      throws IOException {
    BigDecimal exchangeDate = getExchangeDate(currency);
    BigDecimal convertedAmount = foreignCurrencyAmount.multiply(exchangeDate);
    LocalDateTime validUntilDate = LocalDateTime.now().plusMinutes(30L);

    return new Payment(orderId, currency, foreignCurrencyAmount, exchangeDate, convertedAmount,
        validUntilDate);
  }

  abstract BigDecimal getExchangeDate(String currency) throws IOException;
}
