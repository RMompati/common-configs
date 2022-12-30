package com.eroldmr.common.configs.smtp.config;

import com.eroldmr.common.configs.smtp.SMTPEmailService;
import com.eroldmr.common.configs.smtp.impl.SMTPEmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Mompati 'Patco' Keetile
 * @created 30-12-2022 @ 17:29
 */
@RequiredArgsConstructor
@Configuration
public class SMTPEmailConfig2 {

  private final SMTPEmailServiceImpl smtpEmailService;

  @Bean
  public SMTPEmailService smtpEmailService() {
    return smtpEmailService;
  }
}
