package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {


  public static void main(String[] args) {
    PaymentService paymentService = new SimpleExRatePaymentService();

    try {
      Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
      System.out.println(payment);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
