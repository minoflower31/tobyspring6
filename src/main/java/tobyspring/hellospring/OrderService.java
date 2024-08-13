package tobyspring.hellospring;

public class OrderService {

  private final ExRateProvider exRateProvider;

  public OrderService(ExRateProvider exRateProvider) {
    this.exRateProvider = exRateProvider;
  }
}
