package ca.karen.learning.errorhandlingcourse.controller;

import ca.karen.learning.errorhandlingcourse.fibonacci.FibonacciException;
import ca.karen.learning.errorhandlingcourse.fibonacci.FibonacciService;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fibonacci")
public class FibonacciController {
  FibonacciService fibonacci;

  FibonacciController(FibonacciService fibonacci) {
    this.fibonacci = fibonacci;
  }

  @GetMapping("findNumber")
  public ResponseEntity<String> findNumber(@RequestParam String n) {
    int fibonacciNumber;
    try {
      fibonacciNumber = fibonacci.fibonacci(n);
    } catch (FibonacciException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    return ResponseEntity.ok(Integer.toString(fibonacciNumber));
  }

  @PostMapping("createSequence")
  public ResponseEntity<String> createSequence(@RequestParam String n) {
    String fileName;
    try {
      fileName = fibonacci.createSequence(n);
    } catch (FibonacciException | IOException e) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(fileName);
  }

  @GetMapping("getSequence")
  public ResponseEntity<String> getSequence(@RequestParam String fileName) {
    String sequence;
    try {
      sequence = fibonacci.getSequence(fileName);
    } catch (FileNotFoundException e) {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
    }
    return ResponseEntity.ok(sequence);
  }
}
