package com.systena.invoice.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * The Class InvoiceProperties.
 */
@Component
@PropertySource(value = "classpath:settings.properties")
@ConfigurationProperties(prefix = "app")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceProperties {

  /** The tax. */
  private String tax;
}
