package tobyspring.hellospring;

import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SortTest {
  Sort sort;

  @BeforeEach
  void setUp() {
    sort = new Sort();
    System.out.println(this);
  }

  @Test
  void sort_2Items() {
    // Given
    List<String> stringList = Arrays.asList("aa", "b");

    // When
    //참고: List.of() 는 MutableList 를 만들기 때문에 sort 안됨
    List<String> result = sort.sortByLength(stringList);

    // Then
    Assertions.assertThat(result).isEqualTo(List.of("b", "aa"));
  }

  @Test
  void sort_3Items() {
    // Given
    List<String> stringList = Arrays.asList("aa", "ccc", "b");

    // When
    //참고: List.of() 는 MutableList 를 만들기 때문에 sort 안됨
    List<String> result = sort.sortByLength(stringList);

    // Then
    Assertions.assertThat(result).isEqualTo(List.of("b", "aa", "ccc"));
  }

  @Test
  void already_sort_Items() {
    // Given
    List<String> stringList = Arrays.asList("b", "aa", "ccc");

    // When
    //참고: List.of() 는 MutableList 를 만들기 때문에 sort 안됨
    List<String> result = sort.sortByLength(stringList);

    // Then
    Assertions.assertThat(result).isEqualTo(List.of("b", "aa", "ccc"));
  }
}