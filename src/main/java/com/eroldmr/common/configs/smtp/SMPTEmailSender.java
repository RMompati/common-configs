package com.eroldmr.common.configs.smtp;

import com.eroldmr.common.configs.smtp.exception.OperationFailedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import java.util.Arrays;

/** Creates a simple method to send an email, the class can be initialized in your EmailService class
 * in your Spring Boot project.
 *
 * @author Mompati 'Patco' Keetile
 * @created 27-12-2022 @ 20:38
 */
@RequiredArgsConstructor
@Slf4j
public class SMPTEmailSender {

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
}
