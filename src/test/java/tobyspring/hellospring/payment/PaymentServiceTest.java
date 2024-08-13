package tobyspring.hellospring.payment;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tobyspring.hellospring.exrate.WebApiExRateProvider;

/**
 * 적용 환율 원화 환산 금액 원화 환산 금액 유효시간
 */
class PaymentServiceTest {

  @Test
  @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족하는지 검증한다.")
  void prepare() throws IOException {
    //Given
    ExRateProvider webApiExRateProvider = new WebApiExRateProvider();
    PaymentService paymentService = new PaymentService(webApiExRateProvider);

    //When
    // 예외가 발생하여 던지면 테스트가 실패됨
    Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    //Then
    Assertions.assertThat(payment).isNotNull();
    Assertions.assertThat(payment.getExchangeRate()).isNotNull();
    Assertions.assertThat(payment.getConvertedAmount())
        .isEqualTo(payment.getExchangeRate().multiply(payment.getForeignCurrencyAmount()));
    Assertions.assertThat(payment.getValidUntilDate())
        .isBefore(LocalDateTime.now().plusMinutes(30));
  }
}