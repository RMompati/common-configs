package com.eroldmr.common.configs.smtp;

import org.springframework.stereotype.Component;

/**
 * A simple interface that holds SMTP email credentials.
 *
 * @author Mompati 'Patco' Keetile
 * @created 27-12-2022 @ 20:18
 */
@Component
public interface SMTPEmailCredentials {

  public String username();
  public String password();
  public Boolean debug();
}
