package ca.karen.learning.errorhandlingcourse.fibonacci;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FibonacciService {
  int fibonacci(String n) throws FibonacciException;

  String createSequence(String n) throws IOException, FibonacciException;

  String getSequence(String fileName) throws FileNotFoundException;
}
