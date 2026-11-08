package com.example.event.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter @Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="event_reference")
    private String eventReference;

    @Column(name="event_type")
    private String eventType;

    @Column(
            precision = 15,
            scale = 5,
            unique = true,
            nullable = false,
            name="payout_ratio"
    )
    private BigDecimal payoutRatio;

}
