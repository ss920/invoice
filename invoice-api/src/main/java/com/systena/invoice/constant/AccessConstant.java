package com.systena.invoice.constant;

/**
 * URL系定数クラス
 */
public class AccessConstant {

	/** URL：アプリのルート */
	public static final String URL_ROOT = "/invoice";
	/** URL：アプリのバージョン */
	public static final String URL_VERSION = "/v1";
	/** URL：請求書 */
	public static final String URL_INVOICE = "/invoice";
	/** 請求書管理番号 */
	public static final String PARAM_INVOICE_NO = "invoiceNo";
	/** URL：請求書管理番号 */
	public static final String URL_INVOICE_NO = "/{" + PARAM_INVOICE_NO +"}";


}
