package com.credorax.entity;

import com.credorax.dto.PaymentCardDto;
import com.credorax.util.PCIEncryptUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "PAYMENT_CARD")
@Accessors(chain = true)
@Data
@EntityListeners({AuditingEntityListener.class})
public class PaymentCard extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_card_generator")
    @SequenceGenerator(
            name = "payment_card_generator",
            sequenceName = "payment_card_id_seq",
            allocationSize = 1)
    private Long id;

    @Column(name = "PAN")
    private String pan;

    @Column(name = "EXPIRY")
    private String expiry;


    public static PaymentCard valueOf(PaymentCardDto dto) {
        return new PaymentCard()
                .setExpiry(PCIEncryptUtil.encrypt(dto.getExpiry()))
                .setPan(PCIEncryptUtil.encrypt(dto.getPan()));
    }
}
