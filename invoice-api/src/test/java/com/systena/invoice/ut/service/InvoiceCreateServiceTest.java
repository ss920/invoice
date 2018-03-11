package com.systena.invoice.ut.service;

import com.systena.invoice.common.InvoiceProperties;
import com.systena.invoice.common.InvoiceStringUtils;
import com.systena.invoice.constant.Constant;
import com.systena.invoice.constant.MessageConstant;
import com.systena.invoice.dto.InvoiceCreateDto;
import com.systena.invoice.entity.InvoiceEntity;
import com.systena.invoice.entity.OrderEntity;
import com.systena.invoice.exception.InvoiceException;
import com.systena.invoice.form.InvoiceCreateForm;
import com.systena.invoice.repository.InvoiceCreateRepository;
import com.systena.invoice.repository.OrderRepository;
import com.systena.invoice.service.InvoiceCreateService;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The Class InvoiceCreateServiceTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceCreateServiceTest {

    /** The invoice create service. */
    @Autowired
    private InvoiceCreateService invoiceCreateService;

    /** The invoice create repository. */
    @Mock
    private InvoiceCreateRepository invoiceCreateRepository;

    /** The order repository. */
    @Mock
    private OrderRepository orderRepository;

    /** The invoice properties. */
    @Mock
    private InvoiceProperties invoiceProperties;

    /** The target. */
    @InjectMocks
    private InvoiceCreateService target;

    /**
     * Invoice create execute success test.
     *
     * @throws Exception the exception
     */
    @Test
    public void invoiceCreateExecute_success_test() throws Exception {

        // 取得する注文履歴を定義
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo(100);
        orderEntity.setClientNo(1000);
        orderEntity.setItemNo(100);
        orderEntity.setItemName("test name");
        orderEntity.setItemType("10");
        orderEntity.setItemPrice(1000);
        orderEntity.setItemCount(2);
        orderEntity.setCreateDatetime(new Timestamp(1));
        orderEntity.setUpdateDatetime(new Timestamp(1));
        orderEntity.setDelFlg("0");
        List<OrderEntity> orderList = new ArrayList<OrderEntity>();
        orderList.add(orderEntity);
        // SELECT文の返却値を定義
        Mockito.when(orderRepository.findByClientNoIsAndCreateDatetimeBetween(
                100, new Timestamp(1), new Timestamp(1))).thenReturn(orderList);

        // 作成する請求書を定義
        InvoiceEntity invoiceEntity = new InvoiceEntity();
        invoiceEntity.setInvoiceStatus("10");
        invoiceEntity.setInvoiceCreateDate(new Date(1));
        invoiceEntity.setInvoiceTitle("test title");
        invoiceEntity.setInvoiceAmt(10);
        invoiceEntity.setTaxAmt(10);
        invoiceEntity.setInvoiceStartDate(new Date(1));
        invoiceEntity.setInvoiceEndDate(new Date(1));
        invoiceEntity.setInvoiceNote("test note");
        invoiceEntity.setCreateUser("test user");
        invoiceEntity.setCreateDatetime(new Timestamp(1));
        invoiceEntity.setUpdateUser("test user");
        invoiceEntity.setUpdateDatetime(new Timestamp(1));
        invoiceEntity.setDelFlg("0");
        // insert文の返却値を定義
        Mockito.when(invoiceCreateRepository.save(new InvoiceEntity())).thenReturn(invoiceEntity);

        // リクエストボディを定義
        InvoiceCreateForm invoiceCreateForm = new InvoiceCreateForm(
                "1000",
                "2018/01/01",
                "2018/01/30",
                "test",
                "2018/02/01");

        InvoiceCreateDto invoiceCreateDto =
                invoiceCreateService.invoiceCreateExecute(invoiceCreateForm);
        Assert.assertNotNull(invoiceCreateDto.getInvoiceCreateInfoDto().getInvoiceNo());

    }

    /**
     * Invoice create execute not found test.
     *
     * @throws Exception the exception
     */
    @Test
    public void invoiceCreateExecute_notFound_test() throws Exception {

        // 取得する注文履歴を定義
        List<OrderEntity> orderList = new ArrayList<OrderEntity>();
        orderList.add(new OrderEntity());
        // SELECT文の返却値を定義
        Mockito.when(orderRepository.findByClientNoIsAndCreateDatetimeBetween(
                100, new Timestamp(1), new Timestamp(1))).thenReturn(orderList);

        // リクエストボディを定義
        InvoiceCreateForm invoiceCreateForm = new InvoiceCreateForm(
                "1000",
                "2018/01/01",
                "2018/01/30",
                "test",
                "2018/02/01");

        try {
            // テスト実行
            target.invoiceCreateExecute(invoiceCreateForm);
            // 正常終了したら失敗
            Assert.fail();
        } catch (InvoiceException e) {
            Assert.assertEquals(
                    e.getErrorId(),
                    MessageConstant.MSGID_INVOICE_NOT_FOUND);
        }

    }

    /**
     * Invoice create execute fail test.
     *
     * @throws Exception the exception
     */
    @Test
    public void invoiceCreateExecute_fail_test() throws Exception {

        final Timestamp startTime = InvoiceStringUtils.stringToTimestamp(
                "2018/01/01", Constant.FORMAT_DATE_SLASH);
        final Timestamp endTime = InvoiceStringUtils.stringToTimestamp(
                "2018/01/30", Constant.FORMAT_DATE_SLASH);

        // 取得する注文履歴を定義
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo(100);
        orderEntity.setClientNo(1000);
        orderEntity.setItemNo(100);
        orderEntity.setItemName("test name");
        orderEntity.setItemType("10");
        orderEntity.setItemPrice(1000);
        orderEntity.setItemCount(2);
        orderEntity.setCreateDatetime(new Timestamp(1));
        orderEntity.setUpdateDatetime(new Timestamp(1));
        orderEntity.setDelFlg("0");
        List<OrderEntity> orderList = new ArrayList<OrderEntity>();
        orderList.add(orderEntity);
        // SELECT文の返却値を定義
        Mockito.when(orderRepository.findByClientNoIsAndCreateDatetimeBetween(
                1000, startTime, endTime)).thenReturn(orderList);

        Mockito.when(invoiceProperties.getTax()).thenReturn("8");

        // 作成する請求書を定義
        InvoiceEntity invoiceEntity = null;
        // insert文の返却値を定義
        Mockito.when(invoiceCreateRepository.save(new InvoiceEntity())).thenReturn(invoiceEntity);

        // リクエストボディを定義
        InvoiceCreateForm invoiceCreateForm = new InvoiceCreateForm(
                "1000",
                "2018/01/01",
                "2018/01/30",
                "test",
                "2018/02/01");

        try {
            // テスト実行
            target.invoiceCreateExecute(invoiceCreateForm);
            // 正常終了したら失敗
            Assert.fail();
        } catch (InvoiceException e) {
            Assert.assertEquals(
                    e.getErrorId(),
                    MessageConstant.MSGID_INVOICE_CREATE_FAILURE);
        }

    }

}
