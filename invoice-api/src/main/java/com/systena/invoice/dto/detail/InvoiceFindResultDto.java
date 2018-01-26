package com.systena.invoice.dto.detail;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class InvoiceFindResultDto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceFindResultDto implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The client info dto. */
	@JsonProperty("client")
	ClientInfoDto clientInfoDto = null;

	/** The invoice info dto. */
	@JsonProperty("invoice")
	InvoiceInfoDto invoiceInfoDto = null;

}
