package com.systena.invoice.common;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class InvoiceStringUtils.
 */
@Slf4j
public class InvoiceStringUtils {

  /**
   * Timestamp ⇒ String変換.
   *
   * @param timestamp 日付(Timestamp型)
   * @param format 日付形式
   * @return 日付(String型)
   */
  public static String timestampToString(Timestamp timestamp, String format) {

    String str = null;
    if (timestamp != null) {
      try {
        str = new SimpleDateFormat(format).format(timestamp);
      } catch (Exception e) {
        log.error("[ERROR]:" + e);
      }
    }
    return str;
  }

  /**
   * String ⇒ Timestamp変換.
   *
   * @param datetime 日付(String型)
   * @param format 日付形式
   * @return 日付(Timestamp型)
   */
  public static Timestamp stringToTimestamp(String datetime, String format) {

    Timestamp time = null;
    if (datetime != null) {
      try {
        time = new Timestamp(
            new SimpleDateFormat(format).parse(datetime).getTime());
      } catch (Exception e) {
        log.error("[ERROR]:" + e);
      }
    }
    return time;
  }

  /**
   * Date ⇒ String変換.
   *
   * @param date 日付(date型)
   * @param format 日付形式
   * @return 日付(String型)
   */
  public static String dateToString(Date date, String format) {

    String str = null;
    if (date != null) {
      try {
        str = new SimpleDateFormat(format).format(date);
      } catch (Exception e) {
        log.error("[ERROR]:" + e);
      }
    }

    return str;
  }

  /**
   * String ⇒ Date変換.
   *
   * @param date 日付(String型)
   * @param format 日付形式
   * @return 日付(date型)
   */
  public static Date stringToDate(String date, String format) {

    Date convertDate = null;
    if (date != null) {
      try {
        convertDate = new Date(
            new SimpleDateFormat(format).parse(date).getTime());
      } catch (Exception e) {
        log.error("[ERROR]:" + e);
      }
    }
    return convertDate;
  }

}
