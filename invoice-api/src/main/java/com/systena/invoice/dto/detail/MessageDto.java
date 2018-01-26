package com.systena.invoice.dto.detail;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class MessageDto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The message id. */
	@JsonProperty("message_id")
	private String messageId;

	/** The message content. */
	@JsonProperty("message_content")
	private String messageContent;

}
