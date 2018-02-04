package com.systena.invoice.dto.detail;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class ClientInfoDto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientInfoDto implements Serializable {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /** The client no. */
    @JsonProperty("client_no")
    private String clientNo;

    /** The client charge last name. */
    @JsonProperty("client_charge_last_name")
    private String clientChargeLastName;

    /** The client charge first name. */
    @JsonProperty("client_charge_first_name")
    private String clientChargeFirstName;

    /** The client name. */
    @JsonProperty("client_name")
    private String clientName;

    /** The client address. */
    @JsonProperty("client_address")
    private String clientAddress;

    /** The client tel. */
    @JsonProperty("client_tel")
    private String clientTel;

    /** The client fax. */
    @JsonProperty("client_fax")
    private String clientFax;

    /** The create datetime. */
    @JsonProperty("create_datetime")
    private String createDatetime;

    /** The update datetime. */
    @JsonProperty("update_datetime")
    private String updateDatetime;

}
