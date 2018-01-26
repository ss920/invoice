package com.systena.invoice.constant;

/**
 * 定数クラス
 */
public class Constant {

	/** 日付形式：yyyy-MM-dd */
	public static final String FORMAT_DATE = "yyyy-MM-dd";
	/** 日付形式：yyyy-MM-dd HH:mm:ss */
	public static final String FORMAT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
	/** 日付形式：yyyy/MM/dd */
	public static final String FORMAT_DATE_SLASH = "yyyy/MM/dd";
	/** 日付形式：yyyy/MM/dd HH:mm:ss */
	public static final String FORMAT_DATETIME_SLASH = "yyyy/MM/dd HH:mm:ss";

	/** 請求書ステータス　10: 新規作成 */
	public static final String INVOICE_STATUS_NEW = "10";
	/** 請求書ステータス　20: 送付済 */
	public static final String INVOICE_STATUS_SEND = "20";
	/** 請求書ステータス　30: 入金確認済 */
	public static final String INVOICE_STATUS_END = "30";
	/** 請求書ステータス　90: 廃棄 */
	public static final String INVOICE_STATUS_DUST = "90";

	/** 削除フラグ(TRUE) */
	public static final String DEL_FLG_TRUE = "1";
	/** 削除フラグ(FALSE) */
	public static final String DEL_FLG_FALSE = "0";

	/** 消費税（Key） */
	public static final String KEY_TAX = "tax";

	/** 正規表現：日時(yyyy/MM/dd) */
	public static final String REGEX_DATE = "\\d{4}\\/\\d{2}\\/\\d{2}";
	/** 正規表現：数値(1文字以上) */
	public static final String REGEX_NUMBER = "[0-9]+";

	/** ブランク */
	public static final String BLANK = "";
	/** 半角スラッシュ */
	public static final String SLASH = "/";
	/** 半角ハイフン */
	public static final String HYPHEN = "-";
	/** 半角チルダ */
	public static final String TILDE = "~";

	/** 請求書件名のデフォルト内容 */
	public static final String INVOICE_DEFAULT_TITLE = "の請求書";

}
