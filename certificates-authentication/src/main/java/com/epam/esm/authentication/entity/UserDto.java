package com.epam.esm.authentication.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;

    @NotNull
    @Size(max = 8)
    private String login;

    @NotNull
    @Size(max = 15)
    private String password;

    @PositiveOrZero
    @Digits(integer=11, fraction=2, message = "")
    private BigDecimal money;
}
