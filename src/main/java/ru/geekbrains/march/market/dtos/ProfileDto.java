package ru.geekbrains.march.market.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProfileDto {

    private String username;
    private String email;

}
