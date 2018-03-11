package com.systena.invoice.constant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * The Class MessageConstant.
 */
public class MessageConstant {

    /** The Constant MSGID_INVOICE_GET_SUCCESS. */
    public static final String MSGID_INVOICE_GET_SUCCESS = "IV_200_001";

    /** The Constant MSGID_INVOICE_CREATE_SUCCESS. */
    public static final String MSGID_INVOICE_CREATE_SUCCESS = "IV_200_002";

    /** The Constant MSGID_INVOICE_NOT_FOUND. */
    public static final String MSGID_INVOICE_NOT_FOUND = "IV_400_001";

    /** The Constant MSGID_VALID_NOT_NULL. */
    public static final String MSGID_VALID_NOT_NULL = "IV_400_101";

    /** The Constant MSGID_VALID_NOT_EMPTY. */
    public static final String MSGID_VALID_NOT_EMPTY = "IV_400_101";

    /** The Constant MSGID_VALID_PATTEN. */
    public static final String MSGID_VALID_PATTEN = "IV_400_102";

    /** The Constant MSGID_VALID_DATE. */
    public static final String MSGID_VALID_DATE = "IV_400_103";

    /** The Constant MSGID_SERVER_ERROR. */
    public static final String MSGID_SERVER_ERROR = "IV_500_001";

    /** The Constant MSGID_INVOICE_CREATE_FAILURE. */
    public static final String MSGID_INVOICE_CREATE_FAILURE = "IV_500_002";

    /** The Constant KEY_ERROR_NOT_NULL. */
    public static final String KEY_ERROR_NOT_NULL = "NotNull";

    /** The Constant KEY_ERROR_NOT_EMPTY. */
    public static final String KEY_ERROR_NOT_EMPTY = "NotEmpty";

    /** The Constant KEY_ERROR_FORMAT. */
    public static final String KEY_ERROR_FORMAT = "Pattern";

    /** The Constant KEY_ERROR_DATE_CHECK. */
    public static final String KEY_ERROR_DATE_CHECK = "DateCheckValid";

    /** The valid error msg map. */
    public static final Map<String, String> validErrorMsgMap = Collections.unmodifiableMap(
            new HashMap<String,String>() {
                {
                    put(KEY_ERROR_NOT_NULL, MSGID_VALID_NOT_NULL);
                    put(KEY_ERROR_NOT_EMPTY, MSGID_VALID_NOT_EMPTY);
                    put(KEY_ERROR_FORMAT, MSGID_VALID_PATTEN);
                    put(KEY_ERROR_DATE_CHECK, MSGID_VALID_DATE);
                }
            }
            );

    /** The Constant KEY_CLIENT_NO. */
    public static final String KEY_CLIENT_NO = "clientNo";

    /** The Constant KEY_INVOICE_START_DATE. */
    public static final String KEY_INVOICE_START_DATE = "invoiceStartDate";

    /** The Constant KEY_INVOICE_END_DATE. */
    public static final String KEY_INVOICE_END_DATE = "invoiceEndDate";

    /** The Constant KEY_CREATE_USER. */
    public static final String KEY_CREATE_USER = "createUser";

    /** The Constant KEY_INVOICE_CREATE_DATE. */
    public static final String KEY_INVOICE_CREATE_DATE = "invoiceCreateDate";

    /** The Constant KEY_INVOICE_NO. */
    public static final String KEY_INVOICE_NO = "invoiceNo";

    /** The Constant NAME_CLIENT_NO. */
    public static final String NAME_CLIENT_NO = "顧客管理番号";

    /** The Constant NAME_INVOICE_START_DATE. */
    public static final String NAME_INVOICE_START_DATE = "請求期間開始日";

    /** The Constant NAME_INVOICE_END_DATE. */
    public static final String NAME_INVOICE_END_DATE = "請求期間終了日";

    /** The Constant NAME_CREATE_USER. */
    public static final String NAME_CREATE_USER = "登録者名";

    /** The Constant NAME_INVOICE_CREATE_DATE. */
    public static final String NAME_INVOICE_CREATE_DATE = "請求書作成日";

    /** The Constant NAME_INVOICE_NO. */
    public static final String NAME_INVOICE_NO = "請求書管理番号";

    /** The Constant FORMAT_NUMBER. */
    public static final String FORMAT_NUMBER = "数字形式";

    /** The Constant FORMAT_DATETIME. */
    public static final String FORMAT_DATETIME = "日付形式";

    /** The valid error msg param map. */
    public static final Map<String, String[]> validErrorMsgParamMap = Collections.unmodifiableMap(
            new HashMap<String,String[]>() {
                {
                    // 必須チェックエラーのパラメータ
                    put(KEY_ERROR_NOT_EMPTY + KEY_CLIENT_NO,
                            new String[] {NAME_CLIENT_NO});
                    put(KEY_ERROR_NOT_EMPTY + KEY_INVOICE_START_DATE,
                            new String[] {NAME_INVOICE_START_DATE});
                    put(KEY_ERROR_NOT_EMPTY + KEY_INVOICE_END_DATE,
                            new String[] {NAME_INVOICE_END_DATE});
                    put(KEY_ERROR_NOT_EMPTY + KEY_CREATE_USER,
                            new String[] {NAME_CREATE_USER});
                    put(KEY_ERROR_NOT_EMPTY + KEY_INVOICE_CREATE_DATE,
                            new String[] {NAME_INVOICE_CREATE_DATE});

                    // 形式チェックエラーのパラメータ
                    put(KEY_ERROR_FORMAT + KEY_CLIENT_NO,
                            new String[] {NAME_CLIENT_NO, FORMAT_NUMBER});
                    put(KEY_ERROR_FORMAT + KEY_INVOICE_START_DATE,
                            new String[] {NAME_INVOICE_START_DATE, FORMAT_DATETIME});
                    put(KEY_ERROR_FORMAT + KEY_INVOICE_END_DATE,
                            new String[] {NAME_INVOICE_END_DATE, FORMAT_DATETIME});
                    put(KEY_ERROR_FORMAT + KEY_INVOICE_CREATE_DATE,
                            new String[] {NAME_INVOICE_CREATE_DATE, FORMAT_DATETIME});
                    put(KEY_ERROR_FORMAT + KEY_INVOICE_NO,
                            new String[] {NAME_INVOICE_NO, FORMAT_NUMBER});

                    // 日付妥当性チェックエラーのパラメータ
                    put(KEY_ERROR_DATE_CHECK + KEY_INVOICE_END_DATE,
                            new String[] {NAME_INVOICE_START_DATE, NAME_INVOICE_END_DATE});
                }
            }

            );

}
