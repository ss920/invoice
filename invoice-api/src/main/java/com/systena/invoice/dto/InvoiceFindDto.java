package com.systena.invoice.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.systena.invoice.dto.detail.InvoiceFindResultDto;
import com.systena.invoice.dto.detail.MessageDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class InvoiceFindDto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceFindDto implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The message list. */
	@JsonProperty("message")
	List<MessageDto> messageList;

	/** The result. */
	@JsonProperty("results")
	InvoiceFindResultDto result;

}
