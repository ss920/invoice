package com.systena.invoice.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * メッセージ系定数クラス
 */
public class MessageConstant {

	/** 200系メッセージ一覧 */
	/** 請求書の取得成功メッセージ */
	public static final String MSGID_INVOICE_GET_SUCCESS = "IV_200_001";
	/** 請求書の登録成功メッセージ */
	public static final String MSGID_INVOICE_CREATE_SUCCESS = "IV_200_002";

	/** 400系メッセージ一覧 */
	/** 請求書の取得失敗メッセージ */
	public static final String MSGID_INVOICE_NOT_FOUNT = "IV_400_001";
	/** 入力チェックエラー（NotNull）メッセージ */
	public static final String MSGID_VALID_NOT_NULL = "IV_400_101";
	/** 入力チェックエラー（NotEmpty）メッセージ */
	public static final String MSGID_VALID_NOT_EMPTY = "IV_400_101";
	/** 入力チェックエラー（Pattern）メッセージ */
	public static final String MSGID_VALID_PATTEN = "IV_400_102";
	/** 入力チェックエラー（DateCheckValid）メッセージ */
	public static final String MSGID_VALID_DATE = "IV_400_103";

	/** 500系メッセージ一覧 */
	/** ハンドリングしていないエラーのメッセージ */
	public static final String MSGID_SERVER_ERROR = "IV_500_001";
	/** 請求書の登録失敗メッセージ */
	public static final String MSGID_INVOICE_CREATE_FAILURE = "IV_500_002";

	/** 入力チェックエラーになった処理一覧 */
	/** @NotNullでエラー */
	public static final String KEY_ERROR_NOT_NULL = "NotNull";
	/** @NotEmptyでエラー */
	public static final String KEY_ERROR_NOT_EMPTY = "NotEmpty";
	/** @Patternでエラー */
	public static final String KEY_ERROR_FORMAT = "Pattern";
	/** @DateCheckValidでエラー */
	public static final String KEY_ERROR_DATE_CHECK = "DateCheckValid";

	/**
	 * 入力チェックエラーとなった処理と、メッセージ内容をマッピング<br>
	 * key：入力チェックエラーとなったアノテーション名<br>
	 * value：メッセージID
	 */
	public static Map<String, String> validErrorMsgMap = new HashMap<String,String>(){
		{
			put( KEY_ERROR_NOT_NULL, MSGID_VALID_NOT_NULL );
			put( KEY_ERROR_NOT_EMPTY, MSGID_VALID_NOT_EMPTY );
			put( KEY_ERROR_FORMAT, MSGID_VALID_PATTEN );
			put( KEY_ERROR_DATE_CHECK, MSGID_VALID_DATE );
		}
	};

	/** 入力チェックを行う項目一覧 */
	/** 顧客管理番号(物理名) */
	public static final String KEY_CLIENT_NO = "clientNo";
	/** 請求期間開始日(物理名) */
	public static final String KEY_INVOICE_START_DATE = "invoiceStartDate";
	/** 請求期間終了日(物理名) */
	public static final String KEY_INVOICE_END_DATE = "invoiceEndDate";
	/** 登録者名(物理名) */
	public static final String KEY_CREATE_USER = "createUser";
	/** 請求書作成日(物理名) */
	public static final String KEY_INVOICE_CREATE_DATE = "invoiceCreateDate";
	/** 請求書管理番号(物理名) */
	public static final String KEY_INVOICE_NO = "invoiceNo";

	/** メッセージに設定するパラメータ一覧 */
	/** 顧客管理番号 */
	public static final String NAME_CLIENT_NO = "顧客管理番号";
	/** 請求期間開始日 */
	public static final String NAME_INVOICE_START_DATE = "請求期間開始日";
	/** 請求期間終了日 */
	public static final String NAME_INVOICE_END_DATE = "請求期間終了日";
	/** 登録者名 */
	public static final String NAME_CREATE_USER = "登録者名";
	/** 請求書作成日 */
	public static final String NAME_INVOICE_CREATE_DATE = "請求書作成日";
	/** 請求書管理番号 */
	public static final String NAME_INVOICE_NO = "請求書管理番号";
	/** 数字形式 */
	public static final String FORMAT_NUMBER = "数字形式";
	/** 日付形式 */
	public static final String FORMAT_DATETIME = "日付形式";

	/**
	 * 入力チェックエラーとなった処理と、メッセージに設定するパラメータをマッピング<br>
	 * key：入力チェックエラーとなったアノテーション名 + チェック項目<br>
	 * value：メッセージIDに設定するパラメータ
	 */
	public static Map<String, String[]> validErrorMsgParamMap = new HashMap<String,String[]>(){
		{
			// 必須チェックエラーのパラメータ
			put( KEY_ERROR_NOT_EMPTY + KEY_CLIENT_NO
					, new String[] {NAME_CLIENT_NO} );
			put( KEY_ERROR_NOT_EMPTY + KEY_INVOICE_START_DATE
					, new String[] {NAME_INVOICE_START_DATE} );
			put( KEY_ERROR_NOT_EMPTY + KEY_INVOICE_END_DATE
					, new String[] {NAME_INVOICE_END_DATE} );
			put( KEY_ERROR_NOT_EMPTY + KEY_CREATE_USER
					, new String[] {NAME_CREATE_USER} );
			put( KEY_ERROR_NOT_EMPTY + KEY_INVOICE_CREATE_DATE
					, new String[] {NAME_INVOICE_CREATE_DATE} );

			// 形式チェックエラーのパラメータ
			put( KEY_ERROR_FORMAT + KEY_CLIENT_NO
					, new String[] {NAME_CLIENT_NO, FORMAT_NUMBER} );
			put( KEY_ERROR_FORMAT + KEY_INVOICE_START_DATE
					, new String[] {NAME_INVOICE_START_DATE, FORMAT_DATETIME} );
			put( KEY_ERROR_FORMAT + KEY_INVOICE_END_DATE
					, new String[] {NAME_INVOICE_END_DATE, FORMAT_DATETIME} );
			put( KEY_ERROR_FORMAT + KEY_INVOICE_CREATE_DATE
					, new String[] {NAME_INVOICE_CREATE_DATE, FORMAT_DATETIME} );
			put( KEY_ERROR_FORMAT + KEY_INVOICE_NO
					, new String[] {NAME_INVOICE_NO, FORMAT_NUMBER} );

			// 日付妥当性チェックエラーのパラメータ
			put( KEY_ERROR_DATE_CHECK + KEY_INVOICE_END_DATE
					, new String[] {NAME_INVOICE_START_DATE, NAME_INVOICE_END_DATE} );
		}
	};


}
