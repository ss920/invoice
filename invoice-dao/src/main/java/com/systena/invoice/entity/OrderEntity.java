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
 * The Class OrderEntity.
 */
@Entity
@Data
@Table(name = "t_order")
public class OrderEntity implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The order no. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "order_no")
  private int orderNo;

  /** The client no. */
  @Column(name = "client_no")
  private int clientNo;

  /** The item no. */
  @Column(name = "item_no")
  private int itemNo;

  /** The item name. */
  @Column(name = "item_name")
  private String itemName;

  /** The item type. */
  @Column(name = "item_type")
  private String itemType;

  /** The item price. */
  @Column(name = "item_price")
  private int itemPrice;

  /** The item count. */
  @Column(name = "item_count")
  private int itemCount;

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
