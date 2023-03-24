package ca.karen.learning.errorhandlingcourse.fibonacci;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class FibonacciServiceImplTest {
  private final FibonacciServiceImpl fibonacciService = new FibonacciServiceImpl();

  @Test
  void fibonacci() throws FibonacciException {
    Assertions.assertEquals(2, fibonacciService.fibonacci("3"));

    Exception ex = assertThrows(FibonacciException.class, () -> fibonacciService.fibonacci("23"));
    Assertions.assertEquals("The position cannot be over 15", ex.getMessage());

    Exception numbersException = assertThrows(FibonacciException.class, () -> fibonacciService.fibonacci("a"));
    Assertions.assertEquals("Only numbers are accepted", numbersException.getMessage());
  }

  @Test
  void createSequence()throws IOException, FibonacciException {
    Assertions.assertTrue(fibonacciService.createSequence("3").contains("fibonacci.txt"));
  }

  @Test
  void getSequence()throws FileNotFoundException {
    assertFalse(fibonacciService.getSequence("fibonacci.txt").isEmpty());

    assertThrows(FileNotFoundException.class, () -> fibonacciService.getSequence("a.txt"));
  }
}
