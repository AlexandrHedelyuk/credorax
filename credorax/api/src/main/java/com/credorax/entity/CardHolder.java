package com.credorax.entity;

import com.credorax.dto.CardHolderDto;
import com.credorax.util.PCIEncryptUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "CARD_HOLDER")
@Accessors(chain = true)
@Data
@EntityListeners({AuditingEntityListener.class})
public class CardHolder extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_holder_generator")
    @SequenceGenerator(
            name = "card_holder_generator",
            sequenceName = "card_holder_id_seq",
            allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    public static CardHolder valueOf(CardHolderDto dto) {
        return new CardHolder()
                .setEmail(dto.getEmail())
                .setName(PCIEncryptUtil.encrypt(dto.getName()));
    }
}
