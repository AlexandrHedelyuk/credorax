package com.credorax.controller;

import com.credorax.dto.TransactionDto;
import com.credorax.entity.Transaction;
import com.credorax.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final Logger LOG = LoggerFactory.getLogger(TransactionController.class);

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    /**
     * GET  /transaction/:invoiceNumber : Getting transaction by invoice number.
     *
     * @param invoiceNumber id invoiceNumber  of the transaction to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the transaction, or with status 404 (Not Found)
     */
    @GetMapping("/{invoiceNumber}")
    public ResponseEntity<TransactionDto> findOneByInvoiceNumber(@PathVariable Long invoiceNumber) {
        Transaction transaction = service.findByInvoiceNumber(invoiceNumber);
        if (transaction != null) {
            return ResponseEntity.ok().body(TransactionDto.valueOf(transaction));
        }
        return ResponseEntity.notFound().build();
    }
}
