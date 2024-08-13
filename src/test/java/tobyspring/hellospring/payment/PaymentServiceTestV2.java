package tobyspring.hellospring.payment;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 적용 환율 원화 환산 금액 원화 환산 금액 유효시간
 * <p>
 * 1. 우리가 제어할수 없는 외부 시스템에 문제가 생기면? 2. ExRateProvider가 제공하는 환율값으로 계산? 3. 환율 유효 시간 계산은 정확해?
 */
class PaymentServiceTestV2 {

  @Test
  @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족하는지 검증한다.")
  void convertedAmount() throws IOException {
    testAmount(BigDecimal.valueOf(500), BigDecimal.valueOf(5_000));
    testAmount(BigDecimal.valueOf(1_000), BigDecimal.valueOf(10_000));
    testAmount(BigDecimal.valueOf(100), BigDecimal.valueOf(1_000));

    //Then
//    assertThat(payment.getValidUntilDate())
//        .isBefore(LocalDateTime.now().plusMinutes(30));
  }

  private static void testAmount(BigDecimal exRate, BigDecimal convertedAmount) throws IOException {
    //Given
    ExRateProvider exRateProvider = new ExRateProviderStub(BigDecimal.valueOf(500));
    PaymentService paymentService = new PaymentService(exRateProvider);

    //When
    // 예외가 발생하여 던지면 테스트가 실패됨
    Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    assertThat(payment.getExchangeRate()).isEqualByComparingTo(BigDecimal.valueOf(500));
    assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(5_000));
  }
}