package com.systena.invoice.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.systena.invoice.common.InvoiceMessage;
import com.systena.invoice.common.InvoiceStringUtils;
import com.systena.invoice.constant.Constant;
import com.systena.invoice.constant.MessageConstant;
import com.systena.invoice.dto.InvoiceFindAllDto;
import com.systena.invoice.dto.InvoiceFindDto;
import com.systena.invoice.dto.detail.ClientInfoDto;
import com.systena.invoice.dto.detail.InvoiceFindResultDto;
import com.systena.invoice.dto.detail.InvoiceInfoDto;
import com.systena.invoice.dto.detail.MessageDto;
import com.systena.invoice.entity.ClientEntity;
import com.systena.invoice.entity.InvoiceClientEntity;
import com.systena.invoice.exception.InvoiceException;
import com.systena.invoice.repository.InvoiceFindRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class InvoiceFindService.
 */
@Service
@Slf4j
public class InvoiceFindService {

	/** The invoice find repository. */
	@Resource
	InvoiceFindRepository invoiceFindRepository;

	/** The invoice message. */
	@Autowired
	InvoiceMessage invoiceMessage;

	/**
	 * Gets the invoice execute.
	 *
	 * @return the invoice execute
	 * @throws InvoiceException the invoice exception
	 */
	public InvoiceFindAllDto getInvoiceExecute() throws InvoiceException {

		// 請求書情報の全取得
		List<InvoiceClientEntity> invoiceClientList = selectInvoiceAll();
		// 請求書を取得できたか確認
		if( invoiceClientList == null || invoiceClientList.size() == 0 ) {
			throw new InvoiceException(
					MessageConstant.MSGID_INVOICE_NOT_FOUNT
					, null
					, HttpStatus.NOT_FOUND
					);
		}
		log.info( "selectInvoiceAll : "+ invoiceClientList.toString() );

		List<InvoiceFindResultDto> resultList = new ArrayList<InvoiceFindResultDto>();
		for ( InvoiceClientEntity invoiceClientEntity : invoiceClientList ) {
			// 顧客情報・請求書情報の設定
			resultList.add(
					new InvoiceFindResultDto(
							setClientInfo( invoiceClientEntity.getClientEntity() )
							, setInvoiceInfo( invoiceClientEntity )
							)
					);
		}

		// 請求書取得のメッセージを設定
		List<MessageDto> messageList = new ArrayList<MessageDto>();
		MessageDto messageDto = new MessageDto();
		invoiceMessage.setMessage( messageDto, MessageConstant.MSGID_INVOICE_GET_SUCCESS, null );
		messageList.add(messageDto);

		return new InvoiceFindAllDto(messageList, resultList);
	}

	/**
	 * Gets the invoice one execute.
	 *
	 * @param invoiceNo the invoice no
	 * @return the invoice one execute
	 * @throws InvoiceException the invoice exception
	 */
	public InvoiceFindDto getInvoiceOneExecute( String invoiceNo ) throws InvoiceException {

		// 請求書情報の全取得
		InvoiceClientEntity invoiceClientEntity = selectInvoiceOne( Integer.valueOf( invoiceNo ) );
		// 請求書を取得できたか確認
		if( invoiceClientEntity == null ) {
			throw new InvoiceException(
					MessageConstant.MSGID_INVOICE_NOT_FOUNT
					, null
					, HttpStatus.NOT_FOUND
					);
		}
		log.info( "selectInvoiceOne : "+ invoiceClientEntity.toString() );

		// 顧客情報・請求書情報の設定
		InvoiceFindResultDto resultDto = new InvoiceFindResultDto(
				setClientInfo( invoiceClientEntity.getClientEntity() )
				, setInvoiceInfo( invoiceClientEntity )
				);

		// 請求書取得のメッセージを設定
		List<MessageDto> messageList = new ArrayList<MessageDto>();
		MessageDto messageDto = new MessageDto();
		invoiceMessage.setMessage( messageDto, MessageConstant.MSGID_INVOICE_GET_SUCCESS, null );
		messageList.add(messageDto);

		return new InvoiceFindDto(messageList, resultDto);
	}



	/**
	 * Sets the client info.
	 *
	 * @param clientEntity the client entity
	 * @return the client info dto
	 */
	public ClientInfoDto setClientInfo( ClientEntity clientEntity ) {

		if( clientEntity != null ) {
			return new ClientInfoDto(
					String.valueOf( clientEntity.getClientNo() )
					, clientEntity.getClientChargeLastName()
					, clientEntity.getClientChargeFirstName()
					, clientEntity.getClientName()
					, clientEntity.getClientAddress()
					, clientEntity.getClientTel()
					, clientEntity.getClientFax()
					, InvoiceStringUtils.TimestampToString( clientEntity.getCreateDatetime(), Constant.FORMAT_TIMESTAMP )
					, InvoiceStringUtils.TimestampToString( clientEntity.getUpdateDatetime(), Constant.FORMAT_TIMESTAMP )
					);
		} else {
			return new ClientInfoDto();
		}

	}

	/**
	 * Sets the invoice info.
	 *
	 * @param invoiceClientEntity the invoice client entity
	 * @return the invoice info dto
	 */
	public InvoiceInfoDto setInvoiceInfo( InvoiceClientEntity invoiceClientEntity ) {

		if( invoiceClientEntity != null ) {
			return new InvoiceInfoDto(
					String.valueOf( invoiceClientEntity.getInvoiceNo() )
					, invoiceClientEntity.getInvoiceStatus()
					, InvoiceStringUtils.DateToString( invoiceClientEntity.getInvoiceCreateDate(), Constant.FORMAT_DATE )
					, invoiceClientEntity.getInvoiceTitle()
					, String.valueOf( invoiceClientEntity.getInvoiceAmt() )
					, String.valueOf( invoiceClientEntity.getTaxAmt() )
					, InvoiceStringUtils.DateToString( invoiceClientEntity.getInvoiceStartDate(), Constant.FORMAT_DATE )
					, InvoiceStringUtils.DateToString( invoiceClientEntity.getInvoiceEndDate(), Constant.FORMAT_DATE )
					, invoiceClientEntity.getInvoiceNote()
					, invoiceClientEntity.getCreateUser()
					, InvoiceStringUtils.TimestampToString( invoiceClientEntity.getCreateDatetime(), Constant.FORMAT_TIMESTAMP )
					, invoiceClientEntity.getUpdateUser()
					, InvoiceStringUtils.TimestampToString( invoiceClientEntity.getUpdateDatetime(), Constant.FORMAT_TIMESTAMP )
					);
		}
		else {
			return new InvoiceInfoDto();
		}
	}

	/**
	 * Select invoice all.
	 *
	 * @return the list
	 */
	public List<InvoiceClientEntity> selectInvoiceAll(){
		return invoiceFindRepository.findAll();
	}

	/**
	 * Select invoice one.
	 *
	 * @param invoiceNo the invoice no
	 * @return the invoice client entity
	 */
	public InvoiceClientEntity selectInvoiceOne( int invoiceNo ){
		return invoiceFindRepository.findOne( invoiceNo );
	}

}
