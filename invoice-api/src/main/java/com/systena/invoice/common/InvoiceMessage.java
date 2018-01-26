package com.systena.invoice.common;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.systena.invoice.constant.MessageConstant;
import com.systena.invoice.dto.detail.MessageDto;


/**
 * The Class InvoiceMessage.
 */
@Component
public class InvoiceMessage {

	/** The msg. */
	@Autowired
	private MessageSource msg;

	/**
	 * Sets the message.
	 *
	 * @param messageDto the message dto
	 * @param messageId the message id
	 * @param messageParam the message param
	 */
	public void setMessage( MessageDto messageDto, String messageId, String[] messageParam ) {

		if( StringUtils.isEmpty( messageId ) ) {
			// メッセージIDを正しく指定できていないので、500エラーを設定
			messageDto.setMessageId( MessageConstant.MSGID_SERVER_ERROR );
			messageDto.setMessageContent(
					msg.getMessage( MessageConstant.MSGID_SERVER_ERROR, null, Locale.JAPAN ) );
		}
		else {
			messageDto.setMessageId( messageId );
			// 第1引数はメッセージキー、第2引数は置換文字列（ないならnull）、第3引数はロケール
			messageDto.setMessageContent( msg.getMessage( messageId, messageParam, Locale.JAPAN ) );
		}
	}

	/**
	 * Find valid msg id.
	 *
	 * @param errorCode the error code
	 * @return the string
	 */
	public String findValidMsgId( String errorCode ) {

		if( MessageConstant.validErrorMsgMap.containsKey( errorCode ) ) {
			return MessageConstant.validErrorMsgMap.get( errorCode );
		}
		else {
			return null;
		}
	}


	/**
	 * Find valid msg param.
	 *
	 * @param errorCode the error code
	 * @param errorField the error field
	 * @return the string[]
	 */
	public String[] findValidMsgParam( String errorCode, String errorField ) {

		if( MessageConstant.validErrorMsgParamMap.containsKey( errorCode+errorField ) ) {
			return MessageConstant.validErrorMsgParamMap.get( errorCode+errorField );
		}
		else {
			return null;
		}
	}


}
