package com.systena.invoice.it;

import com.systena.invoice.common.InvoiceStringUtils;
import com.systena.invoice.constant.AccessConstant;
import com.systena.invoice.constant.Constant;
import com.systena.invoice.dto.InvoiceCreateDto;
import com.systena.invoice.dto.InvoiceFindAllDto;
import com.systena.invoice.dto.InvoiceFindDto;
import com.systena.invoice.form.InvoiceCreateForm;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * The Class InvoiceIntegrationTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest

/** The Constant log. */
@Slf4j
public class InvoiceIntegrationTest {

    /** The Constant CONNECT_URL. */
    public static final String CONNECT_URL = "http://localhost:8090";

    /**
     * Invoice find all success test.
     *
     * @throws Exception the exception
     */
    @Test
    public void invoiceFindAll_success_Test() throws Exception {
        // 接続先URL
        String url = CONNECT_URL
                + AccessConstant.URL_ROOT
                + AccessConstant.URL_VERSION
                + AccessConstant.URL_INVOICE;
        // 接続開始
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InvoiceFindAllDto> responseEntity =
                restTemplate.getForEntity(url, InvoiceFindAllDto.class);
        // 結果をログ出力
        log.info("status code : " + responseEntity.getStatusCode());
        log.info("header : " + responseEntity.getHeaders());
        log.info("body : " + responseEntity.getBody());
        // HTTPステータスコードが「200」なら成功
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    /**
     * Invoice find one success test.
     *
     * @throws Exception the exception
     */
    @Test
    public void invoiceFindOne_success_Test() throws Exception {
        // 取得する請求書番号
        String testNo = "/20000";
        // 接続先URL
        String url = CONNECT_URL
                + AccessConstant.URL_ROOT
                + AccessConstant.URL_VERSION
                + AccessConstant.URL_INVOICE
                + testNo;

        // 接続開始
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InvoiceFindDto> responseEntity =
                restTemplate.getForEntity(url, InvoiceFindDto.class);
        // 取得結果をログ出力
        log.info("status code : " + responseEntity.getStatusCode());
        log.info("header : " + responseEntity.getHeaders());
        log.info("body : " + responseEntity.getBody());
        // HTTPステータスコードが「200」なら成功
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }


    /**
     * Invoice find one not found test.
     *
     * @throws Exception the exception
     */
    @Test
    public void invoiceFindOne_notFound_Test() throws Exception {
        // 取得する請求書番号
        String testNo = "/1";
        // 接続先URL
        String url = CONNECT_URL
                + AccessConstant.URL_ROOT
                + AccessConstant.URL_VERSION
                + AccessConstant.URL_INVOICE
                + testNo;

        try {
            // 接続開始
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForEntity(url, InvoiceFindDto.class);
            // 指定した請求書番号が存在していたら失敗
            Assert.fail();
        } catch (HttpClientErrorException e) {
            log.info("status code : " + e.getStatusCode());
            log.info("header : " + e.getResponseHeaders());
            log.info("body : " + e.getResponseBodyAsString());
            // HTTPステータスコードが「404」なら成功
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Invoice find one valid error test.
     *
     * @throws Exception the exception
     */
    @Test
    public void invoiceFindOne_validError_Test() throws Exception {
        // 取得する請求書番号
        String testNo = "/test";
        // 接続先URL
        String url = CONNECT_URL
                + AccessConstant.URL_ROOT
                + AccessConstant.URL_VERSION
                + AccessConstant.URL_INVOICE
                + testNo;

        try {
            // 接続開始
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getForEntity(url, InvoiceFindDto.class);
            // 値の取得が行えたら失敗
            Assert.fail();
        } catch (HttpClientErrorException e) {
            log.info("status code : " + e.getStatusCode());
            log.info("header : " + e.getResponseHeaders());
            log.info("body : " + e.getResponseBodyAsString());
            // HTTPステータスコードが「400」なら成功
            Assert.assertEquals(e.getStatusCode(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Invoice create success test.
     *
     * @throws Exception the exception
     */
    @Test
    public void invoiceCreate_success_Test() throws Exception {
        // 接続先URL
        String url = CONNECT_URL
                + AccessConstant.URL_ROOT
                + AccessConstant.URL_VERSION
                + AccessConstant.URL_INVOICE;

        // リクエストのbodyを作成
        InvoiceCreateForm invoiceCreateForm = new InvoiceCreateForm(
                "1000",
                "2018/01/01",
                "2018/01/30",
                "IT user",
                InvoiceStringUtils.dateToString(
                        new Date(System.currentTimeMillis()), Constant.FORMAT_DATE_SLASH)
                );

        // リクエスト情報を設定
        RequestEntity<InvoiceCreateForm> requestEntity = RequestEntity
                .post(URI.create(url))
                .contentType(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .body(invoiceCreateForm);

        // 接続開始
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<InvoiceCreateDto> responseEntity =
                restTemplate.exchange(requestEntity, InvoiceCreateDto.class);
        // 取得結果をログ出力
        log.info("status code : " + responseEntity.getStatusCode());
        log.info("header : " + responseEntity.getHeaders());
        log.info("body : " + responseEntity.getBody());
        // HTTPステータスコードが「200」なら成功
        Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }


    /**
     * Invoice create valid error test.
     *
     * @throws Exception the exception
     */
    @Test
    public void invoiceCreate_validError_Test() throws Exception {
        // 接続先URL
        String url = CONNECT_URL
                + AccessConstant.URL_ROOT
                + AccessConstant.URL_VERSION
                + AccessConstant.URL_INVOICE;

        // リクエストのbodyを作成
        InvoiceCreateForm invoiceCreateForm = new InvoiceCreateForm(
                "1000",
                "2018/01/20",
                "2018/01/02",
                "IT user",
                InvoiceStringUtils.dateToString(
                        new Date(System.currentTimeMillis()), Constant.FORMAT_DATE_SLASH)
                );

        // リクエスト情報を設定
        RequestEntity<InvoiceCreateForm> requestEntity = RequestEntity
                .post(URI.create(url))
                .contentType(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .body(invoiceCreateForm);

        try {
            // 接続開始
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.exchange(requestEntity, InvoiceCreateDto.class);
            // 値の取得が行えたら失敗
            Assert.fail();
        } catch (HttpClientErrorException e) {
            log.info("status code : " + e.getStatusCode());
            log.info("header : " + e.getResponseHeaders());
            log.info("body : " + e.getResponseBodyAsString());
            // HTTPステータスコードが「400」なら成功
            Assert.assertEquals(e.getStatusCode(), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Invoice create not found test.
     *
     * @throws Exception the exception
     */
    @Test
    public void invoiceCreate_notFound_Test() throws Exception {
        // 接続先URL
        String url = CONNECT_URL
                + AccessConstant.URL_ROOT
                + AccessConstant.URL_VERSION
                + AccessConstant.URL_INVOICE;

        // リクエストのbodyを作成
        InvoiceCreateForm invoiceCreateForm = new InvoiceCreateForm(
                "1000",
                "2017/10/03",
                "2017/10/10",
                "IT user",
                InvoiceStringUtils.dateToString(
                        new Date(System.currentTimeMillis()), Constant.FORMAT_DATE_SLASH)
                );

        // リクエスト情報を設定
        RequestEntity<InvoiceCreateForm> requestEntity = RequestEntity
                .post(URI.create(url))
                .contentType(MediaType.APPLICATION_JSON)
                .acceptCharset(StandardCharsets.UTF_8)
                .body(invoiceCreateForm);

        try {
            // 接続開始
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.exchange(requestEntity, InvoiceCreateDto.class);
            // 値の取得が行えたら失敗
            Assert.fail();
        } catch (HttpClientErrorException e) {
            log.info("status code : " + e.getStatusCode());
            log.info("header : " + e.getResponseHeaders());
            log.info("body : " + e.getResponseBodyAsString());
            // HTTPステータスコードが「400」なら成功
            Assert.assertEquals(e.getStatusCode(), HttpStatus.NOT_FOUND);
        }
    }

}
