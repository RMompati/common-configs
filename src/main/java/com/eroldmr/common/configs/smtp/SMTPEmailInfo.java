package com.eroldmr.common.configs.smtp;

import lombok.Builder;
import lombok.Data;

/** A group of the necessary JavaMailSender email properties.
 *
 * @author Mompati 'Patco' Keetile
 * @created 27-12-2022 @ 20:22
 */
@Data
@Builder
public class SMTPEmailInfo {
  private String subject;
  private String recipient;
  private String[] recipients;
  private String body;
  private Boolean html = false;
}
