package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.payment.Payment;
import tobyspring.hellospring.payment.PaymentService;

public class Client {

  public static void main(String[] args) {

    BeanFactory beanFactory = new AnnotationConfigApplicationContext(PaymentConfig.class);
    PaymentService paymentService = beanFactory.getBean(PaymentService.class);

    try {
      Payment payment1 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
      System.out.println("payment1 = " + payment1);

      Payment payment2 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
      System.out.println("payment2 = " + payment2);

      TimeUnit.SECONDS.sleep(3);

      Payment payment3 = paymentService.prepare(100L, "USD", BigDecimal.valueOf(50.7));
      System.out.println("payment3 = " + payment3);
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

}
