package com.systena.invoice.controller;

import com.systena.invoice.constant.AccessConstant;
import com.systena.invoice.constant.MessageConstant;
import com.systena.invoice.dto.InvoiceCreateDto;
import com.systena.invoice.dto.InvoiceFindAllDto;
import com.systena.invoice.dto.InvoiceFindDto;
import com.systena.invoice.exception.InvoiceException;
import com.systena.invoice.form.InvoiceCreateForm;
import com.systena.invoice.service.InvoiceCreateService;
import com.systena.invoice.service.InvoiceFindService;
import com.systena.invoice.validate.Validate;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Class InvoiceController.
 */
@RestController
@RequestMapping(
    value = AccessConstant.URL_ROOT + AccessConstant.URL_VERSION + AccessConstant.URL_INVOICE)
@Slf4j
public class InvoiceController {

  /** The invoice find service. */
  @Autowired
  private InvoiceFindService invoiceFindService;

  /** The invoice create service. */
  @Autowired
  private InvoiceCreateService invoiceCreateService;

  /** The validate. */
  @Autowired
  private Validate validate;

  /**
   * Gets the invoice all.
   *
   * @return the invoice all
   * @throws InvoiceException the invoice exception
   */
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<InvoiceFindAllDto> getInvoiceAll() throws InvoiceException {
    // 請求書取得処理の開始
    InvoiceFindAllDto dto = invoiceFindService.getInvoiceExecute();
    return ResponseEntity.ok(dto);
  }

  /**
   * Gets the invoice one.
   *
   * @param invoiceNo the invoice no
   * @return the invoice one
   * @throws InvoiceException the invoice exception
   */
  @RequestMapping(value = AccessConstant.URL_INVOICE_NO, method = RequestMethod.GET)
  public ResponseEntity<InvoiceFindDto> getInvoiceOne(
      @PathVariable(AccessConstant.PARAM_INVOICE_NO) String invoiceNo) throws InvoiceException {
    // 請求書管理番号の入力チェック
    validate.validFormatNumber(invoiceNo, MessageConstant.KEY_INVOICE_NO);
    // 請求書取得処理の開始
    InvoiceFindDto dto = invoiceFindService.getInvoiceOneExecute(invoiceNo);
    return ResponseEntity.ok(dto);
  }

  /**
   * Creates the invoice.
   *
   * @param invoiceCreateForm the invoice create form
   * @return the response entity
   * @throws InvoiceException the invoice exception
   */
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<InvoiceCreateDto> createInvoice(
      @RequestBody @Valid InvoiceCreateForm invoiceCreateForm) throws InvoiceException {

    log.info(invoiceCreateForm.toString());
    InvoiceCreateDto dto = invoiceCreateService.invoiceCreateExecute(invoiceCreateForm);
    log.info("InvoiceCreateDto : " + dto.toString());
    return ResponseEntity.ok(dto);
  }


}
