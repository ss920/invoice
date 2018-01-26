package com.systena.invoice.form;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.systena.invoice.constant.Constant;
import com.systena.invoice.validate.DateCheckValid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// TODO: 自動生成された Javadoc
/* (非 Javadoc)
 * @see java.lang.Object#toString()
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@DateCheckValid(startDateProperty="invoiceStartDate",endDateProperty="invoiceEndDate")
public class InvoiceCreateForm implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The client no. */
	@JsonProperty("client_no")
	@NotEmpty
	@Pattern(regexp=Constant.REGEX_NUMBER)
	private String clientNo;

	/** The invoice start date. */
	@JsonProperty("invoice_start_date")
	@NotEmpty
	@Pattern(regexp=Constant.REGEX_DATE)
	private String invoiceStartDate;

	/** The invoice end date. */
	@JsonProperty("invoice_end_date")
	@NotEmpty
	@Pattern(regexp=Constant.REGEX_DATE)
	private String invoiceEndDate;

	/** The create user. */
	@JsonProperty("create_user")
	@NotEmpty
	private String createUser;

	/** The invoice create date. */
	@JsonProperty("invoice_create_date")
	@NotEmpty
	@Pattern(regexp=Constant.REGEX_DATE)
	private String invoiceCreateDate;


}
