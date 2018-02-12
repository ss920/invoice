package com.systena.invoice.ut.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.systena.invoice.constant.AccessConstant;
import com.systena.invoice.constant.Constant;
import com.systena.invoice.constant.MessageConstant;
import com.systena.invoice.controller.InvoiceController;
import com.systena.invoice.dto.InvoiceCreateDto;
import com.systena.invoice.dto.InvoiceFindAllDto;
import com.systena.invoice.dto.InvoiceFindDto;
import com.systena.invoice.dto.detail.InvoiceCreateInfoDto;
import com.systena.invoice.dto.detail.InvoiceFindResultDto;
import com.systena.invoice.dto.detail.MessageDto;
import com.systena.invoice.form.InvoiceCreateForm;
import com.systena.invoice.service.InvoiceCreateService;
import com.systena.invoice.service.InvoiceFindService;
import com.systena.invoice.validate.Validate;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceControllerTest {

    @Autowired
    private ObjectMapper mapper;

    @InjectMocks
    private InvoiceController target;

    @Mock
    private InvoiceFindService invoiceFindService;

    @Mock
    private InvoiceCreateService invoiceCreateService;

    @Mock
    private Validate validate;

    private MockMvc mvc;

    @Before
    public void before() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(target).build();
    }

    @Test
    public void getInvoiceExecuteTest() throws Exception {
        String accessUrl =
                AccessConstant.URL_ROOT + AccessConstant.URL_VERSION + AccessConstant.URL_INVOICE;

        // Serviceからの戻り値を定義
        List<MessageDto> messageList = new ArrayList<MessageDto>();
        messageList.add(new MessageDto(MessageConstant.MSGID_INVOICE_GET_SUCCESS, Constant.BLANK));
        InvoiceFindAllDto result =
                new InvoiceFindAllDto(messageList,new ArrayList<InvoiceFindResultDto>());

        Mockito.when(invoiceFindService.getInvoiceExecute()).thenReturn(result);

        mvc.perform(MockMvcRequestBuilders.get(accessUrl))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(result)));
    }

    @Test
    public void getInvoiceOneTest() throws Exception {
        String accessUrl =
                AccessConstant.URL_ROOT
                + AccessConstant.URL_VERSION
                + AccessConstant.URL_INVOICE
                + AccessConstant.URL_INVOICE_NO;

        String param = "20000";

        // Serviceからの戻り値を定義
        List<MessageDto> messageList = new ArrayList<MessageDto>();
        messageList.add(new MessageDto(MessageConstant.MSGID_INVOICE_GET_SUCCESS, Constant.BLANK));
        InvoiceFindDto result = new InvoiceFindDto(messageList,new InvoiceFindResultDto());

        Mockito.when(invoiceFindService.getInvoiceOneExecute(param)).thenReturn(result);

        mvc.perform(MockMvcRequestBuilders.get(accessUrl, param))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(result)));
    }

    @Test
    public void createInvoiceTest() throws Exception {
        // リクエストボディを定義
        InvoiceCreateForm invoiceCreateForm = new InvoiceCreateForm(
                "1000",
                "2018/01/01",
                "2018/01/30",
                "test",
                "2018/02/01");

        // オブジェクトを文字列に変換
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestBody = ow.writeValueAsString(invoiceCreateForm);

        // Serviceからの戻り値を定義
        List<MessageDto> messageList = new ArrayList<MessageDto>();
        messageList.add(new MessageDto(MessageConstant.MSGID_INVOICE_GET_SUCCESS, Constant.BLANK));
        InvoiceCreateInfoDto invoiceCreateInfoDto = new InvoiceCreateInfoDto("20001");
        InvoiceCreateDto result = new InvoiceCreateDto(messageList, invoiceCreateInfoDto);

        Mockito.when(invoiceCreateService.invoiceCreateExecute(invoiceCreateForm))
            .thenReturn(result);

        String accessUrl = AccessConstant.URL_ROOT
                + AccessConstant.URL_VERSION
                + AccessConstant.URL_INVOICE;

        mvc.perform(MockMvcRequestBuilders.post(accessUrl)
                .contentType("application/json")
                .content(requestBody))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(MockMvcResultMatchers.content().json(mapper.writeValueAsString(result)));
    }

}
