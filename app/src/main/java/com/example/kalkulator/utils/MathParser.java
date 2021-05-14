package com.example.kalkulator.utils;

import java.io.Serializable;
import java.util.Stack;

public class MathParser implements Serializable {
  Stack<Double> output = new Stack<Double>();
  Stack<MathOperations> operations = new Stack<MathOperations>();

  public double addNumber(double number) {
    if (number == -0.0) {
      output.push(0.0);
    } else {
      output.push(number);
    }

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

  public double calculateSingleNumberOperation(double number, MathOperations operation) {
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
      case SQUARED:
        return Math.pow(number, 2);
      case SQRT:
        return Math.sqrt(number);
    }
    return 0;
  }

  public double getOutput() {
    if (output.size() > 0) {
      return output.peek();
    } else {
      return 0;
    }
  }

  public MathOperations getOperation() {
    if (operations.size() > 0) {
      return operations.peek();
    } else {
      return MathOperations.NOTHING;
    }
  }

  public void clear() {
    output.clear();
    operations.clear();
  }
}
