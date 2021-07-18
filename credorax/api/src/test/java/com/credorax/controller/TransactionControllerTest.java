package com.credorax.controller;

import com.credorax.TestUtils;
import com.credorax.dto.TransactionDto;
import com.credorax.response.BaseResponse;
import com.credorax.response.ValidationErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void validationSuccessTest() throws Exception {
        TransactionDto transactionDto = TestUtils.getDtoDataForTest();
        mockMvc.perform(post("/payment/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionDto))
        ).andExpect(status().isOk()).
                andExpect(content().json(objectMapper.writeValueAsString(BaseResponse.approved())));
    }

    @Test
    public void validationWithIncorrectNameAndPanFailTest() throws Exception {
        TransactionDto transactionDto = TestUtils.getDtoDataForTest();
        transactionDto.getCardHolder().setName("");
        transactionDto.getCard().setPan("4111111111111110");
        ValidationErrorResponse response = TestUtils.getValidationErrorResponseWithIncorrectNameAndPan();

        mockMvc.perform(post("/payment/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionDto))
        ).andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void validationWithIncorrectExpiryDateFailTest() throws Exception {
        TransactionDto transactionDto = TestUtils.getDtoDataForTest();
        transactionDto.getCard().setExpiry("0200");
        ValidationErrorResponse response = TestUtils.getValidationErrorResponseWithIncorrectExpiryDate();

        mockMvc.perform(post("/payment/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionDto))
        ).andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void validationWithMissedEmailAndNameAndInvoiceFailTest() throws Exception {
        TransactionDto transactionDto = TestUtils.getDtoDataForTest();
        transactionDto.setInvoice(null);
        transactionDto.getCardHolder()
                .setName(null)
                .setEmail(null);
        ValidationErrorResponse response = TestUtils.getValidationErrorResponseWithMissedInvoiceAndNameAndEmail();

        mockMvc.perform(post("/payment/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionDto))
        ).andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void validationWithIncorrectAmountFailTest() throws Exception {
        TransactionDto transactionDto = TestUtils.getDtoDataForTest();
        transactionDto.setAmount(BigDecimal.ZERO);
        ValidationErrorResponse response = TestUtils.getValidationErrorResponseWithIncorrectAmount();

        mockMvc.perform(post("/payment/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionDto))
        ).andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

    @Test
    public void validationWithIncorrectEmailFailTest() throws Exception {
        TransactionDto transactionDto = TestUtils.getDtoDataForTest();
        transactionDto.getCardHolder()
                .setEmail("test");
        ValidationErrorResponse response = TestUtils.getValidationErrorResponseWithIncorrectEmail();

        mockMvc.perform(post("/payment/submit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionDto))
        ).andExpect(status().isBadRequest())
                .andExpect(content().json(objectMapper.writeValueAsString(response)));
    }

}
