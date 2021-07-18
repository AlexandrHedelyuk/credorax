package com.credorax.controller;

import com.credorax.dto.TransactionDto;
import com.credorax.response.BaseResponse;
import com.credorax.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final TransactionService transactionServiceImpl;

    public PaymentController(TransactionService transactionService) {
        this.transactionServiceImpl = transactionService;
    }

    /**
     * POST  /payment/submit : Submit  a new payment.
     *
     * @param transactionDto the transaction to create
     * @return the ResponseEntity with status 200 (Ok) and with body  approved = true,
     * or with status 400 (Bad Request) with validation errors.
     */
    @PostMapping("/submit")
    public ResponseEntity<BaseResponse> submit(@Valid @RequestBody TransactionDto transactionDto) {
        transactionServiceImpl.saveTransaction(transactionDto);
        return ResponseEntity.ok().body(BaseResponse.approved());
    }
}
