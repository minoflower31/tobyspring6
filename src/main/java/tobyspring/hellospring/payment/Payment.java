package tobyspring.hellospring.payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

  private Long orderId;
  private String currency;
  private BigDecimal foreignCurrencyAmount;
  private BigDecimal exchangeRate;
  private BigDecimal convertedAmount;
  private LocalDateTime validUntilDate;

  public Payment(Long orderId, String currency, BigDecimal foreignCurrencyAmount,
      BigDecimal exchangeRate, BigDecimal convertedAmount, LocalDateTime validUntilDate) {
    this.orderId = orderId;
    this.currency = currency;
    this.foreignCurrencyAmount = foreignCurrencyAmount;
    this.exchangeRate = exchangeRate;
    this.convertedAmount = convertedAmount;
    this.validUntilDate = validUntilDate;
  }

  public Long getOrderId() {
    return orderId;
  }

  public String getCurrency() {
    return currency;
  }

  public BigDecimal getForeignCurrencyAmount() {
    return foreignCurrencyAmount;
  }

  public BigDecimal getExchangeRate() {
    return exchangeRate;
  }

  public BigDecimal getConvertedAmount() {
    return convertedAmount;
  }

  public LocalDateTime getValidUntilDate() {
    return validUntilDate;
  }

  @Override
  public String toString() {
    return "Payment{" +
        "orderId=" + orderId +
        ", currency='" + currency + '\'' +
        ", foreignCurrencyAmount=" + foreignCurrencyAmount +
        ", exchangeRate=" + exchangeRate +
        ", convertedAmount=" + convertedAmount +
        ", validUntilDate=" + validUntilDate +
        '}';
  }
}
