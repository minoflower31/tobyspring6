package tobyspring.hellospring.payment;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import tobyspring.hellospring.api.ApiTemplate;
import tobyspring.hellospring.exrate.WebApiExRateProvider;

/**
 * 적용 환율 원화 환산 금액 원화 환산 금액 유효시간
 */
class PaymentServiceTest {

  @Test
  @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족하는지 검증한다.")
  void prepare() {
    //Given
    Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    ExRateProvider webApiExRateProvider = new WebApiExRateProvider(new ApiTemplate());
    PaymentService paymentService = new PaymentService(webApiExRateProvider, clock);

    //When
    // 예외가 발생하여 던지면 테스트가 실패됨
    Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    //Then
    Assertions.assertThat(payment).isNotNull();
    Assertions.assertThat(payment.getExchangeRate()).isNotNull();
    Assertions.assertThat(payment.getConvertedAmount())
        .isEqualTo(payment.getExchangeRate().multiply(payment.getForeignCurrencyAmount()));
//    Assertions.assertThat(payment.getValidUntilDate())
//        .isBefore(LocalDateTime.now(clock).plusMinutes(30));
  }
}