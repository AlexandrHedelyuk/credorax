package com.credorax.entity;

import com.credorax.audit.AuditListener;
import com.credorax.dto.TransactionDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "TRANSACTION")
@Accessors(chain = true)
@Data
@EntityListeners({AuditingEntityListener.class, AuditListener.class})
public class Transaction extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_generator")
    @SequenceGenerator(
            name = "transaction_generator",
            sequenceName = "transaction_id_seq",
            allocationSize = 1)
    private Long id;

    @Column(name = "INVOICE_NUMBER")
    private Long invoiceNumber;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "CURRENCY")
    private String currency;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("cardHolderId")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CARD_HOLDER_ID")
    private CardHolder cardHolder;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("paymentCardId")
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "PAYMENT_CARD_ID")
    private PaymentCard paymentCard;


    public static Transaction valueOf(TransactionDto dto) {
        return new Transaction()
                .setAmount(dto.getAmount())
                .setCurrency(dto.getCurrency())
                .setInvoiceNumber(dto.getInvoice());
    }
}
