package com.systena.invoice.dto.detail;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class InvoiceCreateInfoDto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceCreateInfoDto implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The invoice no. */
    @JsonProperty("invoice_no")
    private String invoiceNo;

}
