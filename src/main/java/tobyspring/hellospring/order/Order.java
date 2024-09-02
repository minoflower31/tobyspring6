package tobyspring.hellospring.order;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false, unique = true)
  private String orderNumber;

  private BigDecimal totalPrice;

  public Order() {
  }

  public Order(String orderNumber, BigDecimal totalPrice) {
    this.orderNumber = orderNumber;
    this.totalPrice = totalPrice;
  }

  public Long getId() {
    return id;
  }

  public String getOrderNumber() {
    return orderNumber;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  @Override
  public String toString() {
    return "Order{" +
        "id=" + id +
        ", orderNumber='" + orderNumber + '\'' +
        ", totalPrice=" + totalPrice +
        '}';
  }
}
