package com.credorax.service;

import com.credorax.TestUtils;
import com.credorax.dto.CardHolderDto;
import com.credorax.dto.PaymentCardDto;
import com.credorax.dto.TransactionDto;
import com.credorax.entity.CardHolder;
import com.credorax.entity.PaymentCard;
import com.credorax.entity.Transaction;
import com.credorax.service.impl.TransactionServiceImpl;
import com.credorax.util.PCIEncryptUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TransactionServiceTest {

    @Autowired
    private TransactionServiceImpl service;

    @Test
    public void transactionSaveTest() {
        TransactionDto dto = TestUtils.getDtoDataForTest();
        Transaction transaction = service.saveTransaction(dto);

        Assert.assertEquals(transaction.getAmount(), dto.getAmount());
        Assert.assertEquals(transaction.getCurrency(), dto.getCurrency());
        Assert.assertEquals(transaction.getInvoiceNumber(), dto.getInvoice());

        CardHolder cardHolder = transaction.getCardHolder();
        CardHolderDto cardHolderDto = dto.getCardHolder();
        Assert.assertEquals(cardHolder.getEmail(), cardHolderDto.getEmail());
        Assert.assertEquals(cardHolder.getName(), PCIEncryptUtil.encrypt(cardHolderDto.getName()));

        PaymentCardDto cardDto = dto.getCard();
        PaymentCard paymentCard = transaction.getPaymentCard();
        Assert.assertEquals(paymentCard.getPan(), PCIEncryptUtil.encrypt(cardDto.getPan()));
        Assert.assertEquals(paymentCard.getExpiry(), PCIEncryptUtil.encrypt(cardDto.getExpiry()));


    }


}
