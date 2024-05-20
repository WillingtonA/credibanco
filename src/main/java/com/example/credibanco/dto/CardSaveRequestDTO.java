package com.example.credibanco.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardSaveRequestDTO {

    private String cardId;
    private String nameHeadline;
    private String firstNameHeadline;
}
