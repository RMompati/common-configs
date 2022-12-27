package com.eroldmr.common.configs.smtp.exception;

/**
 * @author Mompati 'Patco' Keetile
 * @created 27-12-2022 @ 20:44
 */
public class OperationFailedException extends RuntimeException {
  public OperationFailedException(String msg) {
    super(msg);
  }
}
