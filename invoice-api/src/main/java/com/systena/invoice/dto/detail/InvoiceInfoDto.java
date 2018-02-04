package com.systena.invoice.dto.detail;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class InvoiceInfoDto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceInfoDto implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The invoice no. */
    @JsonProperty("invoice_no")
    private String invoiceNo;

    /** The invoice status. */
    @JsonProperty("invoice_status")
    private String invoiceStatus;

    /** The invoice create date. */
    @JsonProperty("invoice_create_date")
    private String invoiceCreateDate;

    /** The invoice title. */
    @JsonProperty("invoice_title")
    private String invoiceTitle;

    /** The invoice amt. */
    @JsonProperty("invoice_amt")
    private String invoiceAmt;

    /** The tax amt. */
    @JsonProperty("tax_amt")
    private String taxAmt;

    /** The invoice start date. */
    @JsonProperty("invoice_start_date")
    private String invoiceStartDate;

    /** The invoice end date. */
    @JsonProperty("invoice_end_date")
    private String invoiceEndDate;

    /** The invoice note. */
    @JsonProperty("invoice_note")
    private String invoiceNote;

    /** The create user. */
    @JsonProperty("create_user")
    private String createUser;

    /** The create datetime. */
    @JsonProperty("create_datetime")
    private String createDatetime;

    /** The update user. */
    @JsonProperty("update_user")
    private String updateUser;

    /** The update datetime. */
    @JsonProperty("update_datetime")
    private String updateDatetime;

}
