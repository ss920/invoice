package com.systena.invoice.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.systena.invoice.dto.detail.InvoiceCreateInfoDto;
import com.systena.invoice.dto.detail.MessageDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class InvoiceCreateDto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceCreateDto implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The message list. */
	@JsonProperty("message")
	List<MessageDto> messageList;

	/** The invoice create info dto. */
	@JsonProperty("invoice")
	InvoiceCreateInfoDto invoiceCreateInfoDto;

}
