package tobyspring.hellospring.payment;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 적용 환율 원화 환산 금액 원화 환산 금액 유효시간
 * <p>
 * 1. 우리가 제어할수 없는 외부 시스템에 문제가 생기면? 2. ExRateProvider가 제공하는 환율값으로 계산? 3. 환율 유효 시간 계산은 정확해?
 */
class PaymentServiceV2Test {

  Clock clock;

  @BeforeEach
  void beforeEach() {
    this.clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
  }

  @Test
  @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족하는지 검증한다.")
  void convertedAmount() {
    testAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000), this.clock);
    testAmount(BigDecimal.valueOf(1_000), BigDecimal.valueOf(10_000), this.clock);
    testAmount(BigDecimal.valueOf(100), BigDecimal.valueOf(1_000), this.clock);
  }

  @Test
  void validUntil() {
    //Given
    ExRateProvider exRateProvider = new ExRateProviderStub(BigDecimal.valueOf(500));
    PaymentService paymentService = new PaymentService(exRateProvider, clock);

    //When
    Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    //Then
    assertThat(payment.getValidUntilDate())
        .isEqualTo(LocalDateTime.now(this.clock).plusMinutes(30));
  }

  private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount, Clock clock) {
    //Given
    ExRateProvider exRateProvider = new ExRateProviderStub(exRate);
    PaymentService paymentService = new PaymentService(exRateProvider, clock);

    //When
    // 예외가 발생하여 던지면 테스트가 실패됨
    Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    assertThat(payment.getExchangeRate()).isEqualByComparingTo(exRate);
    assertThat(payment.getConvertedAmount()).isEqualByComparingTo(convertedAmount);
  }
}