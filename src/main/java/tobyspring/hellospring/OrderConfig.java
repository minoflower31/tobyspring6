package tobyspring.hellospring;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tobyspring.hellospring.data.JdbcOrderRepository;
import tobyspring.hellospring.order.OrderRepository;
import tobyspring.hellospring.order.OrderService;
import tobyspring.hellospring.order.OrderServiceImpl;
import tobyspring.hellospring.order.OrderServiceTxProxy;

@Configuration
@Import(DataConfig.class)
@EnableTransactionManagement
public class OrderConfig {

  public OrderService orderServicePrev(
      PlatformTransactionManager platformTransactionManager,
      OrderRepository orderRepository
  ) {
    return new OrderServiceTxProxy(new OrderServiceImpl(orderRepository),
        platformTransactionManager);
  }

  @Bean
  public OrderRepository orderRepository(DataSource dataSource) {
    return new JdbcOrderRepository(dataSource);
  }
}
