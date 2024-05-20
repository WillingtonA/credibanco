package com.example.credibanco.service;

import com.example.credibanco.dto.CardRequestDTO;
import com.example.credibanco.dto.CardSaveRequestDTO;

public interface CardService {

    String generateNumber(String productId);

    String active(CardRequestDTO cardDTO);

    String block(String cardId);

    String rechargeBalance(CardRequestDTO cardDTO);

    CardRequestDTO checkBalance(String cardId);

    String create(CardSaveRequestDTO cardRequestDTO);
}
