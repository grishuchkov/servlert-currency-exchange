package ru.grishuchkov.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CurrencyDto {
    private Long id;
    private String code;
    private String fullName;
    private String sign;
}
