package com.systena.invoice.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * The Class ClientEntity.
 */
@Entity
@Data
@Table(name = "m_client")
public class ClientEntity implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The client no. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "client_no")
  private int clientNo;

  /** The client charge last name. */
  @Column(name = "client_charge_last_name")
  private String clientChargeLastName;

  /** The client charge first name. */
  @Column(name = "client_charge_first_name")
  private String clientChargeFirstName;

  /** The client name. */
  @Column(name = "client_name")
  private String clientName;

  /** The client address. */
  @Column(name = "client_address")
  private String clientAddress;

  /** The client tel. */
  @Column(name = "client_tel")
  private String clientTel;

  /** The client fax. */
  @Column(name = "client_fax")
  private String clientFax;

  /** The create datetime. */
  @Column(name = "create_datetime")
  private Timestamp createDatetime;

  /** The update datetime. */
  @Column(name = "update_datetime")
  private Timestamp updateDatetime;

  /** The del flg. */
  @Column(name = "del_flg")
  private String delFlg;
}
