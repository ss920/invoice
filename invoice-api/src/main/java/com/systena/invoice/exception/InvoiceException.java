package com.systena.invoice.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * The Class InvoiceException.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class InvoiceException extends Exception {

	/** The error id. */
	private String errorId;

	/** The error param. */
	private String[] errorParam;

	/** The http staus. */
	private HttpStatus httpStaus;

}
