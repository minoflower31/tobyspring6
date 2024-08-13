package tobyspring.hellospring;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class WebApiExRateProvider implements ExRateProvider {

  @Override
  public BigDecimal getExchangeDate(String currency) throws IOException {
    URL url = new URL("https://open.er-api.com/v6/latest/" + currency);
    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
    BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
    String response = br.lines().collect(Collectors.joining());
    br.close();

    ObjectMapper objectMapper = new ObjectMapper();
    ExchangeRateData data = objectMapper.readValue(response, ExchangeRateData.class);

    System.out.println("API ExRate: " + data.rates().get("KRW"));

    return data.rates().get("KRW");
  }
}
