package com.eroldmr.common.configs.smtp;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/** Creates a simple method to send an email, the class can be initialized in your EmailService class
 * in your Spring Boot project.
 *
 * @author Mompati 'Patco' Keetile
 * @created 27-12-2022 @ 20:38
 */
@Service
public interface SMTPEmailService {
  @Async
  void sendEmail(SMTPEmailInfo smtpEmailInfo);
  @Async
  void sendEmail(String recipient, String subject, String body);
  @Async
  void sendEmail(String[] recipients, String subject, String body);
  @Async
  void sendEmail(String recipient, String subject, String body, boolean html);
  @Async
  void sendEmail(String[] recipients, String subject, String body, boolean html);
}
