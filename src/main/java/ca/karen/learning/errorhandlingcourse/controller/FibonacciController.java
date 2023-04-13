package ca.karen.learning.errorhandlingcourse.controller;

import ca.karen.learning.errorhandlingcourse.fibonacci.FibonacciException;
import ca.karen.learning.errorhandlingcourse.fibonacci.FibonacciService;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("fibonacci")
public class FibonacciController {
  FibonacciService fibonacci;

  private Logger log = LoggerFactory.getLogger(FibonacciController.class);

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

  @GetMapping("findRation")
  public ResponseEntity<String> getRatio(@RequestParam String n){
    String ratio;
    try{
      ratio = fibonacci.getRatio(n);
    }catch (FibonacciException e){
      return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(e.getMessage());
    }

    return ResponseEntity.ok(ratio);
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({Exception.class})
  public ResponseEntity<String> genericError(Exception ex) {
    log.error(ex.getMessage());

    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Oh no! Reach out our support a@a.com");
  }
}
