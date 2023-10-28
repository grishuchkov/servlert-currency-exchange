package ru.grishuchkov.application.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Currency {
    private Long id;
    private String code;
    private String name;
    private String sign;
}
