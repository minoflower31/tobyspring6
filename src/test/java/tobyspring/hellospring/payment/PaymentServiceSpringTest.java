package tobyspring.hellospring.payment;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.TestPaymentConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestPaymentConfig.class)
class PaymentServiceSpringTest {

  @Autowired
  PaymentService paymentService;

  @Autowired
  ExRateProviderStub exRateProviderStub;

  @Autowired
  Clock clock;

  @Test
  @DisplayName("prepare 메서드가 요구사항 3가지를 잘 충족하는지 검증한다.")
  void convertedAmount() {
    //exRate: 1000
    Payment payment = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    assertThat(payment.getExchangeRate()).isEqualByComparingTo(BigDecimal.valueOf(1000));
    assertThat(payment.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(10_000));

    //exRate: 1000
    exRateProviderStub.setExRate(BigDecimal.valueOf(500));
    Payment payment2 = paymentService.prepare(1L, "USD", BigDecimal.TEN);

    assertThat(payment2.getExchangeRate()).isEqualByComparingTo(BigDecimal.valueOf(500));
    assertThat(payment2.getConvertedAmount()).isEqualByComparingTo(BigDecimal.valueOf(5_000));
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
}