package com.systena.invoice.ut.repository;

import com.systena.invoice.entity.InvoiceClientEntity;
import com.systena.invoice.repository.InvoiceFindRepository;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * The Class InvoiceFindRepositoryTest.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoiceFindRepositoryTest {

    /** The invoice find repository. */
    @Autowired
    InvoiceFindRepository invoiceFindRepository;

    /**
     * Test.
     */
    @Test
    public void test() {
        List<InvoiceClientEntity> invoiceList = invoiceFindRepository.findAll();
        Assert.assertEquals(invoiceList.get(0).getInvoiceNo(), 20000);

        InvoiceClientEntity invoiceClientEntity = invoiceFindRepository.findOne(20001);
        Assert.assertEquals(invoiceClientEntity.getInvoiceNo(), 20001);

        InvoiceClientEntity errorEntity = null;
        errorEntity = invoiceFindRepository.findOne(1);
        Assert.assertNull(errorEntity);

    }


}
