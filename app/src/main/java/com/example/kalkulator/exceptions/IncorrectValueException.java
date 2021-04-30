package com.example.kalkulator.exceptions;

public class IncorrectValueException extends RuntimeException {

  public IncorrectValueException() {
    super("Incorrect value");
  }

  public IncorrectValueException(String message) {
    super(message);
  }
}
