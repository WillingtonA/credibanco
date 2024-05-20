package com.example.credibanco.service;

import com.example.credibanco.dto.TransactionRequestDTO;
import com.example.credibanco.dto.TransactionResponseDTO;

public interface TransactionService {

    String buy(TransactionRequestDTO transactionRequestDTO);

    TransactionResponseDTO check(String transactionId);

    String anulation(TransactionRequestDTO transactionRequestDTO);
}
