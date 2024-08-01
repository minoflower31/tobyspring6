package tobyspring.hellospring;

import java.io.IOException;
import java.math.BigDecimal;

public interface ExRateProvider {

  BigDecimal getExchangeDate(String currency) throws IOException;
}
