package com.systena.invoice.common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class InvoiceProperties.
 */
@Component
@PropertySource(value = "classpath:settings.properties")
@ConfigurationProperties(prefix="app")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceProperties {

	/** The tax. */
	private String tax;
}
