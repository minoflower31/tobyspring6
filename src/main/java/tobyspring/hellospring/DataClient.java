package tobyspring.hellospring;

import java.math.BigDecimal;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import tobyspring.hellospring.data.JpaOrderRepository;
import tobyspring.hellospring.order.Order;

public class DataClient {

  public static void main(String[] args) {
    BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);

    JpaOrderRepository jpaOrderRepository = beanFactory.getBean(JpaOrderRepository.class);
    JpaTransactionManager transactionManager = beanFactory.getBean(JpaTransactionManager.class);

    try {
      new TransactionTemplate(transactionManager).execute(status -> {
        Order order = new Order("110", BigDecimal.TEN);
        jpaOrderRepository.save(order);

        return null;
      });
    } catch (DataIntegrityViolationException e) {
      System.out.println("주민번호 충돌을 복구함");


    }
  }
}
