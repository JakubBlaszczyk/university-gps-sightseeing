package com.attraction.signup;

public class ValidationException extends RuntimeException {
  public ValidationException() {
    super();
  }

  public ValidationException(String error) {
    super(error);
  }
}
