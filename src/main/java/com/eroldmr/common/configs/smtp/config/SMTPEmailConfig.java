package com.eroldmr.common.configs.smtp.config;

import com.eroldmr.common.configs.smtp.SMTPEmailCredentials;
import com.eroldmr.common.configs.smtp.SMTPEmailService;
import com.eroldmr.common.configs.smtp.impl.SMTPEmailServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

/**
 * A Simple SMTP Email configuration. To Utilize this, define a class annotated with @Configuration
 * in your Spring Boot project. Create a @Bean method of return type JavaMailSender and return a call of
 * the method javaMailSender().
 *
 * @author Mompati 'Patco' Keetile
 * @created 27-12-2022 @ 20:16
 */
@RequiredArgsConstructor
@Configuration
public class SMTPEmailConfig {

  private final SMTPEmailCredentials smtpEmailCredentials;

  @Bean
  public JavaMailSender javaMailSender() {
    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
    mailSender.setHost("smtp.gmail.com");
    mailSender.setPort(587);

    Properties properties = mailSender.getJavaMailProperties();
    properties.put("mail.transport.protocol", "smtp");
    properties.put("mail.smtp.auth", "true");
    properties.put("mail.smtp.starttls.enable", "true");

    if (smtpEmailCredentials.debug()) {
      properties.put("mail.debug", "true");
    }

    mailSender.setUsername(smtpEmailCredentials.username());
    mailSender.setPassword(smtpEmailCredentials.password());

    return mailSender;
  }
}
