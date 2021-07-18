package com.credorax.repository;

import com.credorax.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long>, JpaRepository<Transaction, Long> {

    Transaction findOneByInvoiceNumber(Long invoiceNumber);

}
