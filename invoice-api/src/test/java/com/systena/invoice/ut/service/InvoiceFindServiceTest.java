package com.systena.invoice.ut.service;

import com.systena.invoice.constant.MessageConstant;
import com.systena.invoice.dto.InvoiceFindAllDto;
import com.systena.invoice.dto.InvoiceFindDto;
import com.systena.invoice.entity.ClientEntity;
import com.systena.invoice.entity.InvoiceClientEntity;
import com.systena.invoice.exception.InvoiceException;
import com.systena.invoice.repository.InvoiceFindRepository;
import com.systena.invoice.service.InvoiceFindService;
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
 * The Class InvoiceFindServiceTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceFindServiceTest {

    /** The invoice find service. */
    @Autowired
    private InvoiceFindService invoiceFindService;

    /** The target. */
    @InjectMocks
    private InvoiceFindService target;

    /** The invoice find repository. */
    @Mock
    private InvoiceFindRepository invoiceFindRepository;

    /**
     * Gets the invoice execute success test.
     *
     * @throws Exception the exception
     */
    @Test
    public void getInvoiceExecute_success_Test() throws Exception {

        final int testNo = 20000;
        // 検索結果を定義
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientNo(1000);
        clientEntity.setClientChargeFirstName("test name");
        clientEntity.setClientChargeLastName("test name");
        clientEntity.setClientName("test name");
        clientEntity.setClientAddress("test address");
        clientEntity.setClientTel("011-0011-0011");
        clientEntity.setClientFax("011-0011-0011");
        clientEntity.setCreateDatetime(new Timestamp(1));
        clientEntity.setUpdateDatetime(new Timestamp(1));
        clientEntity.setDelFlg("0");
        InvoiceClientEntity invoiceClientEntity = new InvoiceClientEntity();
        invoiceClientEntity.setInvoiceNo(testNo);
        invoiceClientEntity.setInvoiceStatus("10");
        invoiceClientEntity.setInvoiceCreateDate(new Date(1));
        invoiceClientEntity.setInvoiceTitle("test title");
        invoiceClientEntity.setInvoiceAmt(10);
        invoiceClientEntity.setTaxAmt(10);
        invoiceClientEntity.setInvoiceStartDate(new Date(1));
        invoiceClientEntity.setInvoiceEndDate(new Date(1));
        invoiceClientEntity.setInvoiceNote("test note");
        invoiceClientEntity.setCreateUser("test user");
        invoiceClientEntity.setCreateDatetime(new Timestamp(1));
        invoiceClientEntity.setUpdateUser("test user");
        invoiceClientEntity.setUpdateDatetime(new Timestamp(1));
        invoiceClientEntity.setDelFlg("0");
        invoiceClientEntity.setClientEntity(clientEntity);

        // Repositryからの戻り値を定義
        List<InvoiceClientEntity> entityList = new ArrayList<InvoiceClientEntity>();
        entityList.add(invoiceClientEntity);
        Mockito.when(invoiceFindRepository.findAll()).thenReturn(entityList);
        // テスト実行
        InvoiceFindAllDto result = invoiceFindService.getInvoiceExecute();
        Assert.assertEquals(
                result.getResultList().get(0).getInvoiceInfoDto().getInvoiceNo(),
                String.valueOf(testNo));

    }

    /**
     * Gets the invoice execute not found test.
     *
     * @throws Exception the exception
     */
    @Test
    public void getInvoiceExecute_notFound_Test() throws Exception {

        // Repositryからの戻り値を定義
        List<InvoiceClientEntity> entityList = new ArrayList<InvoiceClientEntity>();
        Mockito.when(invoiceFindRepository.findAll()).thenReturn(entityList);

        try {
            // テスト実行
            target.getInvoiceExecute();
            // 正常終了したら失敗
            Assert.fail();
        } catch (InvoiceException e) {
            Assert.assertEquals(
                    e.getErrorId(),
                    MessageConstant.MSGID_INVOICE_NOT_FOUND);
        }
    }

    /**
     * Gets the invoice one execute success test.
     *
     * @throws Exception the exception
     */
    @Test
    public void getInvoiceOneExecute_success_Test() throws Exception {

        final int testNo = 20000;
        // 検索結果を定義
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setClientNo(1000);
        clientEntity.setClientChargeFirstName("test name");
        clientEntity.setClientChargeLastName("test name");
        clientEntity.setClientName("test name");
        clientEntity.setClientAddress("test address");
        clientEntity.setClientTel("011-0011-0011");
        clientEntity.setClientFax("011-0011-0011");
        clientEntity.setCreateDatetime(new Timestamp(1));
        clientEntity.setUpdateDatetime(new Timestamp(1));
        clientEntity.setDelFlg("0");
        InvoiceClientEntity invoiceClientEntity = new InvoiceClientEntity();
        invoiceClientEntity.setInvoiceNo(testNo);
        invoiceClientEntity.setInvoiceStatus("10");
        invoiceClientEntity.setInvoiceCreateDate(new Date(1));
        invoiceClientEntity.setInvoiceTitle("test title");
        invoiceClientEntity.setInvoiceAmt(10);
        invoiceClientEntity.setTaxAmt(10);
        invoiceClientEntity.setInvoiceStartDate(new Date(1));
        invoiceClientEntity.setInvoiceEndDate(new Date(1));
        invoiceClientEntity.setInvoiceNote("test note");
        invoiceClientEntity.setCreateUser("test user");
        invoiceClientEntity.setCreateDatetime(new Timestamp(1));
        invoiceClientEntity.setUpdateUser("test user");
        invoiceClientEntity.setUpdateDatetime(new Timestamp(1));
        invoiceClientEntity.setDelFlg("0");
        invoiceClientEntity.setClientEntity(clientEntity);

        // Repositryからの戻り値を定義
        Mockito.when(invoiceFindRepository.findOne(testNo)).thenReturn(invoiceClientEntity);
        // テスト実行
        InvoiceFindDto result = invoiceFindService.getInvoiceOneExecute(String.valueOf(testNo));
        Assert.assertEquals(
                result.getResult().getInvoiceInfoDto().getInvoiceNo(),
                String.valueOf(testNo));
    }

    /**
     * Gets the invoice one execute not found test.
     *
     * @throws Exception the exception
     */
    @Test
    public void getInvoiceOneExecute_notFound_Test() throws Exception {

        InvoiceClientEntity invoiceClientEntity = null;

        // Repositryからの戻り値を定義
        Mockito.when(invoiceFindRepository.findOne(20000)).thenReturn(invoiceClientEntity);
        try {
            // テスト実行
            target.getInvoiceOneExecute(String.valueOf(20000));
            // 正常終了したら失敗
            Assert.fail();
        } catch (InvoiceException e) {
            Assert.assertEquals(
                    e.getErrorId(),
                    MessageConstant.MSGID_INVOICE_NOT_FOUND);
        }
    }

}
