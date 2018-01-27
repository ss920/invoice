package com.systena.invoice.exception;

import com.systena.invoice.common.InvoiceMessage;
import com.systena.invoice.constant.MessageConstant;
import com.systena.invoice.dto.ExceptionDto;
import com.systena.invoice.dto.detail.MessageDto;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * The Class ErrorHandler.
 */
@ControllerAdvice
@Slf4j
public class ErrorHandler {


  /** The invoice message. */
  @Autowired
  InvoiceMessage invoiceMessage;

  /**
   * Invoice exception handle.
   *
   * @param e the e
   * @return the response entity
   */
  @ExceptionHandler({ InvoiceException.class })
  public ResponseEntity<ExceptionDto> invoiceExceptionHandle(final InvoiceException e) {
    // エラー内容のログ出力
    log.error("[ERROR]: " + e);
    // エラーメッセージを設定
    List<MessageDto> messageList = new ArrayList<MessageDto>();
    MessageDto messageDto = new MessageDto();
    HttpStatus returnStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    // エラーID・HTTPステータスが設定されていれば、エラーIDに対応するメッセージを取得する
    if (StringUtils.isNotEmpty(e.getErrorId()) && e.getHttpStaus() != null) {
      // メッセージIDからメッセージ内容を取得
      invoiceMessage.setMessage(messageDto, e.getErrorId(), e.getErrorParam());
      messageList.add(messageDto);
      returnStatus = e.getHttpStaus();
    } else {
      // 500エラーのメッセージを取得
      invoiceMessage.setMessage(messageDto, MessageConstant.MSGID_SERVER_ERROR, null);
      messageList.add(messageDto);
    }
    // エラーメッセージを返却（HTTPステータスは可変)
    return ResponseEntity
        .status(returnStatus)
        .body(new ExceptionDto(messageList));
  }

  /**
   * Valid exception handle.
   *
   * @param e the e
   * @return the response entity
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionDto> validExceptionHandle(
      final MethodArgumentNotValidException e) {

    List<MessageDto> messageList = new ArrayList<MessageDto>();
    MessageDto messageDto = null;
    HttpStatus returnStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    // MethodArgumentNotValidExceptionから入力チェックエラーになった項目を取得する
    if (e != null
        && e.getBindingResult() != null
        && e.getBindingResult().getFieldErrors() != null) {
      List<FieldError> fieldErrorList = e.getBindingResult().getFieldErrors();
      for (FieldError fieldError : fieldErrorList) {

        log.error("[ERROR]: 入力チェック処理でエラーになった項目: " + fieldError.getField()
            + ", リクエストで受け取った値: " + fieldError.getRejectedValue()
            + ", エラーになったチェック内容: " + fieldError.getCode());

        messageDto = new MessageDto();
        invoiceMessage.setMessage(
            messageDto,
            invoiceMessage.findValidMsgId(fieldError.getCode()),
            invoiceMessage.findValidMsgParam(fieldError.getCode(), fieldError.getField()));

        log.info("取得したエラーメッセージ内容: " + messageDto.toString());
        messageList.add(messageDto);
      }
      returnStatus = HttpStatus.BAD_REQUEST;
    } else {
      // MethodArgumentNotValidExceptionの中身がない場合、500エラーを設定する
      messageDto = new MessageDto();
      invoiceMessage.setMessage(messageDto, MessageConstant.MSGID_SERVER_ERROR, null);
      messageList.add(messageDto);
    }

    return ResponseEntity
        .status(returnStatus)
        .body(new ExceptionDto(messageList));
  }


  /**
   * Exception handle.
   *
   * @param e the e
   * @return the response entity
   */
  @ExceptionHandler({ Exception.class })
  public ResponseEntity<ExceptionDto> exceptionHandle(final Exception e) {
    // エラー内容のログ出力
    log.error("[ERROR]: " + e);
    // エラーメッセージを設定
    List<MessageDto> messageList = new ArrayList<MessageDto>();
    MessageDto messageDto = new MessageDto();
    // メッセージIDからメッセージ内容を取得
    invoiceMessage.setMessage(messageDto, MessageConstant.MSGID_SERVER_ERROR, null);
    messageList.add(messageDto);
    // エラーメッセージを返却（HTTPステータスは500)
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(new ExceptionDto(messageList));
  }

}
