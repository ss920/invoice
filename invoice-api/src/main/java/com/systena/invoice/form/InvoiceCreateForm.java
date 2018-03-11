package com.systena.invoice.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.systena.invoice.constant.Constant;
import com.systena.invoice.validate.DateCheckValid;
import java.io.Serializable;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * The Class InvoiceCreateForm.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@DateCheckValid(startDateProperty = "invoiceStartDate",endDateProperty = "invoiceEndDate")
public class InvoiceCreateForm implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The client no. */
    @JsonProperty("client_no")
    @NotEmpty
    @Pattern(regexp = Constant.REGEX_NUMBER)
    private String clientNo;

    /** The invoice start date. */
    @JsonProperty("invoice_start_date")
    @NotEmpty
    @Pattern(regexp = Constant.REGEX_DATE)
    private String invoiceStartDate;

    /** The invoice end date. */
    @JsonProperty("invoice_end_date")
    @NotEmpty
    @Pattern(regexp = Constant.REGEX_DATE)
    private String invoiceEndDate;

    /** The create user. */
    @JsonProperty("create_user")
    @NotEmpty
    private String createUser;

    /** The invoice create date. */
    @JsonProperty("invoice_create_date")
    @NotEmpty
    @Pattern(regexp = Constant.REGEX_DATE)
    private String invoiceCreateDate;


}
