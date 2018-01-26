package com.systena.invoice.validate;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.systena.invoice.common.InvoiceMessage;
import com.systena.invoice.constant.Constant;
import com.systena.invoice.constant.MessageConstant;
import com.systena.invoice.exception.InvoiceException;

import lombok.extern.slf4j.Slf4j;


/**
 * The Class Validate.
 */
@Component
@Slf4j
public class Validate {

	/** The invoice message. */
	@Autowired
	private InvoiceMessage invoiceMessage;

	/**
	 * Valid format number.
	 *
	 * @param number the number
	 * @param field the field
	 * @throws InvoiceException the invoice exception
	 */
	public void validFormatNumber( String number, String field ) throws InvoiceException {

		boolean result = false;
		try {
			if( StringUtils.isNotEmpty( number ) ) {
				// 正規表現に一致したらtrue
				Pattern pattern = Pattern.compile( Constant.REGEX_NUMBER );
				if( pattern.matcher(number).matches() ) {
					result = true;
				}
			}
		}
		catch( Exception e ) {
			log.error("[ERROR]:"+ e);
		}

		if( !result ) {
			throw new InvoiceException(
					invoiceMessage.findValidMsgId( MessageConstant.KEY_ERROR_FORMAT )
					, invoiceMessage.findValidMsgParam( MessageConstant.KEY_ERROR_FORMAT, field )
					, HttpStatus.BAD_REQUEST
					);
		}
	}

}
