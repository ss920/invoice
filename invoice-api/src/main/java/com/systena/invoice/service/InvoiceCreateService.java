package com.systena.invoice.service;

import com.systena.invoice.common.InvoiceMessage;
import com.systena.invoice.common.InvoiceProperties;
import com.systena.invoice.common.InvoiceStringUtils;
import com.systena.invoice.constant.Constant;
import com.systena.invoice.constant.MessageConstant;
import com.systena.invoice.dto.InvoiceCreateDto;
import com.systena.invoice.dto.detail.InvoiceCreateInfoDto;
import com.systena.invoice.dto.detail.MessageDto;
import com.systena.invoice.entity.InvoiceEntity;
import com.systena.invoice.entity.OrderEntity;
import com.systena.invoice.exception.InvoiceException;
import com.systena.invoice.form.InvoiceCreateForm;
import com.systena.invoice.repository.InvoiceCreateRepository;
import com.systena.invoice.repository.OrderRepository;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The Class InvoiceCreateService.
 */
@Service
@Slf4j
public class InvoiceCreateService {

    /** The order repository. */
    @Resource
    OrderRepository orderRepository;

    /** The invoice create repository. */
    @Resource
    InvoiceCreateRepository invoiceCreateRepository;

    /** The invoice message. */
    @Autowired
    InvoiceMessage invoiceMessage;

    /** The invoice properties. */
    @Autowired
    InvoiceProperties invoiceProperties;

    /**
     * Invoice create execute.
     *
     * @param invoiceCreateForm the invoice create form
     * @return the invoice create dto
     * @throws InvoiceException the invoice exception
     */
    @Transactional(rollbackFor = Exception.class)
    public InvoiceCreateDto invoiceCreateExecute(
            final InvoiceCreateForm invoiceCreateForm) throws InvoiceException {

        // リクエストで設定されている検索日時をもとに、注文履歴を取得
        List<OrderEntity> orderList = selectOrderAll(
                Integer.parseInt(invoiceCreateForm.getClientNo()),
                InvoiceStringUtils.stringToTimestamp(
                        invoiceCreateForm.getInvoiceStartDate(), Constant.FORMAT_DATE_SLASH),
                InvoiceStringUtils.stringToTimestamp(
                        invoiceCreateForm.getInvoiceEndDate(), Constant.FORMAT_DATE_SLASH));

        if (CollectionUtils.isEmpty(orderList)) {
            throw new InvoiceException(
                    MessageConstant.MSGID_INVOICE_NOT_FOUND,
                    null,
                    HttpStatus.NOT_FOUND);
        }
        log.info("selectOrderAll : " + orderList.toString());
        // 新規登録する請求書情報を作成
        InvoiceEntity invoiceEntity = createInvoiceInfo(orderList, invoiceCreateForm);
        // 請求書を新規登録
        InvoiceEntity newInvoiceEntity = createInvoice(invoiceEntity);

        if (Objects.isNull(newInvoiceEntity)) {
            throw new InvoiceException(
                    MessageConstant.MSGID_INVOICE_CREATE_FAILURE,
                    null,
                    HttpStatus.SERVICE_UNAVAILABLE);
        }
        log.info("new invoiceInfo : " + newInvoiceEntity.toString());
        // 作成した請求書番号を設定
        InvoiceCreateInfoDto createInfoDto = new InvoiceCreateInfoDto(
                String.valueOf(newInvoiceEntity.getInvoiceNo()));

        // 請求書作成成功のメッセージを設定
        List<MessageDto> messageList = invoiceMessage.getMessage(
                MessageConstant.MSGID_INVOICE_CREATE_SUCCESS);

        return new InvoiceCreateDto(messageList, createInfoDto);
    }

    /**
     * Creates the invoice info.
     *
     * @param orderList the order list
     * @param invoiceCreateForm the invoice create form
     * @return the invoice entity
     */
    public InvoiceEntity createInvoiceInfo(
            final List<OrderEntity> orderList, final InvoiceCreateForm invoiceCreateForm) {

        // 請求金額を作成
        int invoiceAmt = 0;
        for (OrderEntity orderEntity : orderList) {
            invoiceAmt += orderEntity.getItemPrice() * orderEntity.getItemCount();
        }
        // 請求書件名を作成
        String title = invoiceCreateForm.getInvoiceStartDate()
                + Constant.TILDE
                + invoiceCreateForm.getInvoiceEndDate()
                + Constant.INVOICE_DEFAULT_TITLE;

        return new InvoiceEntity(
                null,
                Integer.parseInt(invoiceCreateForm.getClientNo()),
                Constant.INVOICE_STATUS_NEW,
                InvoiceStringUtils.stringToDate(
                        invoiceCreateForm.getInvoiceCreateDate(), Constant.FORMAT_DATE_SLASH),
                title,
                invoiceAmt,
                Integer.parseInt(invoiceProperties.getTax()),
                InvoiceStringUtils.stringToDate(
                        invoiceCreateForm.getInvoiceStartDate(), Constant.FORMAT_DATE_SLASH),
                InvoiceStringUtils.stringToDate(
                        invoiceCreateForm.getInvoiceEndDate(), Constant.FORMAT_DATE_SLASH),
                Constant.BLANK,
                invoiceCreateForm.getCreateUser(),
                new Timestamp(System.currentTimeMillis()),
                invoiceCreateForm.getCreateUser(),
                new Timestamp(System.currentTimeMillis()),
                Constant.DEL_FLG_FALSE
                );
    }



    /**
     * Select order all.
     *
     * @param clientNo the client no
     * @param findStartTime the find start time
     * @param findEndTime the find end time
     * @return the list
     */
    public List<OrderEntity> selectOrderAll(
            final int clientNo, final Timestamp findStartTime, final Timestamp findEndTime) {
        return orderRepository.findByClientNoIsAndCreateDatetimeBetween(
                clientNo, findStartTime, findEndTime);
    }

    /**
     * Creates the invoice.
     *
     * @param invoiceEntity the invoice entity
     * @return the invoice entity
     */
    public InvoiceEntity createInvoice(final InvoiceEntity invoiceEntity) {
        return invoiceCreateRepository.save(invoiceEntity);
    }

}
