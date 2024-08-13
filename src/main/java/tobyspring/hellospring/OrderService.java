package tobyspring.hellospring;

import tobyspring.hellospring.payment.ExRateProvider;

public class OrderService {

  private final ExRateProvider exRateProvider;

  public OrderService(ExRateProvider exRateProvider) {
    this.exRateProvider = exRateProvider;
  }
}
