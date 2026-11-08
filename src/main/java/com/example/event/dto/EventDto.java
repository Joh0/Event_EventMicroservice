package com.example.event.dto;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.NumberFormat;

import java.math.BigDecimal;

@Data
public class EventDto {

    @NotEmpty(message = "Event Reference must not be empty")
    private String eventReference;

    @NotEmpty(message = "Event Type must not be empty")
    private String eventType;

    @NotNull
    @DecimalMin(value = "0.01")
    @Digits(integer = 15, fraction = 5)
    private BigDecimal payoutRatio;
}
