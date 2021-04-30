package com.example.kalkulator.utils;

import com.example.kalkulator.exceptions.DivideByZeroException;
import com.example.kalkulator.exceptions.IncorrectValueException;

public class MathParser {
  double value;
  double result;

  public double calculate(String newResult, String newValue, MathOperations operation) {
    if (!checkValue(newValue)) {
      throw new IncorrectValueException();
    }

    switch (operation) {
      case NEGATE:
        value = Double.parseDouble(newValue);
        if (value == 0) {
          return 0;
        }
        return Double.parseDouble(newValue) * -1;

      case DIVIDE:
        result = Double.parseDouble(newResult);
        value = Double.parseDouble(newValue);
        if (value == 0) {
          throw new DivideByZeroException();
        }

        return result / value;

      case MULITPLY:
        result = Double.parseDouble(newResult);
        value = Double.parseDouble(newValue);
        return result * value;

      case ADD:
        result = Double.parseDouble(newResult);
        value = Double.parseDouble(newValue);
        return result + value;

      case SUBTRACT:
        result = Double.parseDouble(newResult);
        value = Double.parseDouble(newValue);
        return result - value;
    }
    return 0;
  }

  private boolean checkValue(String value) {
    return value.matches("-?\\d+(\\.\\d+)?");
  }
}
