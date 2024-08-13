package tobyspring.hellospring;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Sort {

  public static void main(String[] args) {
    List<String> scores = Arrays.asList("z", "xdsdvsvf", "asd", "kkkqdq");

    scores.sort(((o1, o2) -> {
      return o1.length() - o2.length();
    }));

    scores.forEach(System.out::println);
  }

}
