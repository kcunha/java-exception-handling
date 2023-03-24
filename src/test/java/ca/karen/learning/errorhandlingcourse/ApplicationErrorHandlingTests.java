package ca.karen.learning.errorhandlingcourse;

import ca.karen.learning.errorhandlingcourse.controller.FibonacciController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationErrorHandlingTests {

  @Autowired FibonacciController controller;

  @Test
  void contextLoads() {
    Assertions.assertTrue(controller.findNumber("3").getStatusCode().is2xxSuccessful());
    Assertions.assertTrue(controller.findNumber("33").getStatusCode().is4xxClientError());
    Assertions.assertTrue(controller.createSequence("3").getStatusCode().is2xxSuccessful());
    Assertions.assertTrue(controller.createSequence("33").getStatusCode().is4xxClientError());
    Assertions.assertTrue(controller.getSequence("fibonacci.txt").getStatusCode().is2xxSuccessful());
    Assertions.assertTrue(controller.getSequence("fib.txt").getStatusCode().is4xxClientError());
  }
}
