package com.example.credibanco.service.impl;

import com.example.credibanco.dto.TransactionRequestDTO;
import com.example.credibanco.dto.TransactionResponseDTO;
import com.example.credibanco.entity.Card;
import com.example.credibanco.entity.Transaction;
import com.example.credibanco.repository.CardRepository;
import com.example.credibanco.repository.TransactionRepository;
import com.example.credibanco.service.TransactionService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final CardRepository cardRepository;

    @Override
    @Transactional
    public String buy(TransactionRequestDTO transactionRequestDTO) {

        //1 Consultar tarjeta, su estado, su saldo y vencimiento
        Card card = this.cardRepository.findById(transactionRequestDTO.getCardId()).orElseThrow(() -> new RuntimeException("Error buscando card, Not Found Card" + transactionRequestDTO.getCardId()));
        if(card.getState() != 'A') {
            return "No se puede realizar la transaccion, la tarjeta se encuentra " + card.getState();
        }

        if(card.getBalance() < Integer.parseInt(transactionRequestDTO.getPrice())) {
            return "No se puede realizar la transaccion, la tarjeta no cuenta con fondos sificientes";
        }

        //2 Actualizar el valor de la compra a la tarjeta
        card.setBalance(card.getBalance() - Integer.parseInt(transactionRequestDTO.getPrice()));
        this.cardRepository.save(card);

        // 3 Guardar transaccion
        Transaction transaction = Transaction.builder()
                .card(card)
                .price((double) Integer.parseInt(transactionRequestDTO.getPrice()))
                .dateEjecution(new Date())
                .state('A')
                .build();
        this.transactionRepository.save(transaction);

        return "Transaccion exitosa";
    }

    @Override
    @Transactional
    public TransactionResponseDTO check(String transactionId) {
        Transaction transaction = this.transactionRepository.findById(Integer.parseInt(transactionId)).orElseThrow(() -> new RuntimeException("Error buscando transaction, Not Found transaction" + transactionId));
        return TransactionResponseDTO.builder()
                .price(transaction.getPrice())
                .cardId(transaction.getCard().getCardId())
                .build();
    }

    @Override
    public String anulation(TransactionRequestDTO transactionRequestDTO) {

        Transaction transaction = this.transactionRepository.findById(Integer.parseInt(transactionRequestDTO.getTransactionId()))
                .orElseThrow(() -> new RuntimeException("Error buscando transaction, Not Found transaction" + transactionRequestDTO.getTransactionId()));

        // 1 actualizar la transaccion en estado de anulacion
        transaction.setState('E');

        // 2 actualizar card en su saldo sumandole lo de la transaccion
        Card card = this.cardRepository.findById(transactionRequestDTO.getCardId()).orElseThrow(() -> new RuntimeException("Error buscando card, Not Found card" + transactionRequestDTO.getCardId()));
        card.setBalance(card.getBalance() + transaction.getPrice());
        this.cardRepository.save(card);
        return "Transaccion anulada";
    }
}
