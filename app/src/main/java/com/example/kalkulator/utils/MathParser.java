package com.example.kalkulator.utils;

import java.util.Stack;

public class MathParser {
  Stack<Double> output = new Stack<Double>();
  Stack<MathOperations> operations = new Stack<MathOperations>();

  public double addNumber(double number) {
    output.push(number);

    calculate();

    return output.peek();
  }

  public double addOperation(MathOperations operation) {
    operations.push(operation);

    calculate();

    return output.peek();
  }

  void calculate() {
    if (output.size() == 2 && operations.size() == 1) {
      double secondNum = output.pop();
      double firstNum = output.pop();
      MathOperations operation = operations.pop();
      switch (operation) {
        case ADD:
          output.push(firstNum + secondNum);
          break;
        case SUBTRACT:
          output.push(firstNum - secondNum);
          break;
        case DIVIDE:
          output.push(firstNum / secondNum);
          break;
        case MULITPLY:
          output.push(firstNum * secondNum);
          break;
        case EXPONENT:
          output.push(Math.pow(firstNum, secondNum));
          break;
        case PERCENT:
          output.push(firstNum * (secondNum / 100));
          break;
      }
    }
  }

  double calculateSingleNumberOperation(double number, MathOperations operation) {
    switch (operation) {
      case SIN:
        return Math.sin(number);
      case COS:
        return Math.cos(number);
      case TAN:
        return Math.tan(number);
      case LN:
        return Math.log(number);
      case LOG:
        return Math.log10(number);
    }
    return 0;
  }

  public double getOutput() {
    return output.peek();
  }

  public void clear() {
    output.clear();
    operations.clear();
  }

  private boolean checkValue(String value) {
    return value.matches("-?\\d+(\\.\\d+)?");
  }
}
