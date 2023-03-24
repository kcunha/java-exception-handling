package ca.karen.learning.errorhandlingcourse.fibonacci;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class FibonacciServiceImpl implements FibonacciService {

  public static final String FILE_NAME = "fibonacci.txt";

  @Override
  public int fibonacci(String number) throws FibonacciException {
    int position;
    try {
      position = Integer.parseInt(number);
    } catch (NumberFormatException e) {
      throw new FibonacciException("Only numbers are accepted");
    }

    if (position <= 1) {
      return position;
    } else if (position > 15) {
      throw new FibonacciException("The position cannot be over 15");
    }

    return fibonacci(String.valueOf(position - 1)) + fibonacci(String.valueOf(position - 2));
  }

  @Override
  public String createSequence(String n) throws IOException, FibonacciException {
    return storeSequence(getFibonacciSequence(n));
  }

  @Override
  public String getSequence(String fileName) throws FileNotFoundException {
    return getSequenceFromFile(fileName);
  }

  private String getSequenceFromFile(String fileName) throws FileNotFoundException {
    BufferedReader reader = new BufferedReader(new FileReader(fileName));

    return reader.lines().collect(Collectors.joining());
  }

  private List<Integer> getFibonacciSequence(String number) throws FibonacciException {
    int n;
    try {
      n = Integer.parseInt(number);
    } catch (NumberFormatException e) {
      throw new FibonacciException("Only numbers are accepted");
    }

    List<Integer> sequence = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      sequence.add(fibonacci(String.valueOf(i)));
    }

    return sequence;
  }

  private String storeSequence(List<Integer> sequence) throws IOException {
    File fibonacciFile = new File(FILE_NAME);
    FileWriter fileWriter = new FileWriter(fibonacciFile);
    fileWriter.write(sequence.toString());
    fileWriter.flush();
    fileWriter.close();

    return FILE_NAME;
  }
}
