package com.systena.invoice.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * The Class InvoiceClientEntity.
 */
@Entity
@Data
@Table(name="t_invoice")
public class InvoiceClientEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The invoice no. */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="invoice_no")
	private int invoiceNo;

	/** The invoice status. */
	@Column(name="invoice_status")
	private String invoiceStatus;

	/** The invoice create date. */
	@Column(name="invoice_create_date")
	private Date invoiceCreateDate;

	/** The invoice title. */
	@Column(name="invoice_title")
	private String invoiceTitle;

	/** The invoice amt. */
	@Column(name="invoice_amt")
	private int invoiceAmt;

	/** The tax amt. */
	@Column(name="tax_amt")
	private int taxAmt;

	/** The invoice start date. */
	@Column(name="invoice_start_date")
	private Date invoiceStartDate;

	/** The invoice end date. */
	@Column(name="invoice_end_date")
	private Date invoiceEndDate;

	/** The invoice note. */
	@Column(name="invoice_note")
	private String invoiceNote;

	/** The create user. */
	@Column(name="create_user")
	private String createUser;

	/** The create datetime. */
	@Column(name="create_datetime")
	private Timestamp createDatetime;

	/** The update user. */
	@Column(name="update_user")
	private String updateUser;

	/** The update datetime. */
	@Column(name="update_datetime")
	private Timestamp updateDatetime;

	/** The del flg. */
	@Column(name="del_flg")
	private String delFlg;

	/** The client entity. */
	@OneToOne
	@JoinColumn(name = "client_no", insertable=false, updatable=false)
	private ClientEntity clientEntity;

}
