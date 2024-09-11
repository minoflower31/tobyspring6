package tobyspring.hellospring.order;

import java.math.BigDecimal;

public class Order {

  private Long id;

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

  public void setId(Long id) {
    this.id = id;
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
