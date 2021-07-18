package com.credorax.service.impl;

import com.credorax.dto.TransactionDto;
import com.credorax.entity.CardHolder;
import com.credorax.entity.PaymentCard;
import com.credorax.entity.Transaction;
import com.credorax.repository.TransactionRepository;
import com.credorax.service.TransactionService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }


    public Transaction saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = Transaction.valueOf(transactionDto);
        CardHolder cardHolder = CardHolder.valueOf(transactionDto.getCardHolder());
        transaction.setCardHolder(cardHolder);
        PaymentCard paymentCard = PaymentCard.valueOf(transactionDto.getCard());
        transaction.setPaymentCard(paymentCard);
        return repository.save(transaction);
    }

    public Transaction findByInvoiceNumber(Long invoiceNumber) {
        return repository.findOneByInvoiceNumber(invoiceNumber);
    }
}
