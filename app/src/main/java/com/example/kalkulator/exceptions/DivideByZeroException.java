package com.example.kalkulator.exceptions;

public class DivideByZeroException extends RuntimeException {

  public DivideByZeroException() {
    super("Can't divide by 0");
  }

  public DivideByZeroException(String message) {
    super(message);
  }
}
