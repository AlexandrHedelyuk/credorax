package com.credorax.service;

import com.credorax.dto.TransactionDto;
import com.credorax.entity.Transaction;

public interface TransactionService {

    Transaction saveTransaction(TransactionDto transactionDto);

    Transaction findByInvoiceNumber(Long invoiceNumber);
}
