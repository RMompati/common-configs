package com.eroldmr.common.configs.smtp.impl;

import com.eroldmr.common.configs.smtp.SMTPEmailCredentials;
import com.eroldmr.common.configs.smtp.SMTPEmailInfo;
import com.eroldmr.common.configs.smtp.SMTPEmailService;
import com.eroldmr.common.configs.smtp.exception.OperationFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

/**
 * @author Mompati 'Patco' Keetile
 * @created 30-12-2022 @ 17:14
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SMTPEmailServiceImpl implements SMTPEmailService {
  private final JavaMailSender mailSender;
  private final SMTPEmailCredentials smtpEmailCredentials;

  public void sendEmail(SMTPEmailInfo smtpEmailInfo) {
    MimeMessagePreparator preparator = mimeMessage -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
      messageHelper.setFrom(smtpEmailCredentials.username());
      if (smtpEmailInfo.getRecipient() != null) {
        messageHelper.setTo(smtpEmailInfo.getRecipient());
      }
      if (smtpEmailInfo.getRecipients() != null && smtpEmailInfo.getRecipients().length != 0) {
        messageHelper.setTo(smtpEmailInfo.getRecipients());
      }
      messageHelper.setSubject(smtpEmailInfo.getSubject());
      messageHelper.setText(smtpEmailInfo.getBody(), smtpEmailInfo.getHtml());
    };

    try {
      log.info("Sending email.");
      mailSender.send(preparator);
      log.info("Email sent.");
    } catch (MailException exception) {
      String errMsg = "An exception occurred when attempting to send an email";
      log.error(errMsg);
      throw new OperationFailedException(errMsg);
    }
  }

  public void sendEmail(String recipient, String subject, String body) {
    sendEmail(
        SMTPEmailInfo
            .builder()
            .recipient(recipient)
            .subject(subject)
            .body(body)
            .html(false)
            .build()
    );
  }
  public void sendEmail(String[] recipients, String subject, String body) {
    sendEmail(
        SMTPEmailInfo
            .builder()
            .recipients(recipients)
            .subject(subject)
            .body(body)
            .html(false)
            .build()
    );
  }

  public void sendEmail(String recipient, String subject, String body, boolean html) {
    sendEmail(
        SMTPEmailInfo
            .builder()
            .recipient(recipient)
            .subject(subject)
            .body(body)
            .html(html)
            .build()
    );
  }
  public void sendEmail(String[] recipients, String subject, String body, boolean html) {
    sendEmail(
        SMTPEmailInfo
            .builder()
            .recipients(recipients)
            .subject(subject)
            .body(body)
            .html(html)
            .build()
    );
  }
}
