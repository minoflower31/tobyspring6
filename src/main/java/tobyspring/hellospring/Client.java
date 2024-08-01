package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

public class Client {

  public static void main(String[] args) {
    ObjectFactory objectFactory = new ObjectFactory();
    PaymentService paymentService = objectFactory.paymentService();

    try {
      Payment payment = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
      System.out.println(payment);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
