package com.example.credibanco.service.impl;

import com.example.credibanco.dto.CardRequestDTO;
import com.example.credibanco.dto.CardSaveRequestDTO;
import com.example.credibanco.entity.Card;
import com.example.credibanco.repository.CardRepository;
import com.example.credibanco.service.CardService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;



import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;


    @Override
    public String generateNumber(String productId) {
        return productId + this.generateRandom();
    }

    @Override
    @Transactional
    public String active(CardRequestDTO cardDTO) {
        //Integer cardId =  Integer.valueOf(cardDTO.getCardId().substring(0, 6));
        //Integer random = Integer.valueOf(cardDTO.getCardId().substring(6, 16));
        Card card = this.cardRepository.findById(cardDTO.getCardId()).orElseThrow(() -> new RuntimeException("Error buscando card, Not Found Card" + cardDTO.getCardId()));
        card.setState('A');

        this.cardRepository.save(card);
        return "Card Activa";
    }

    @Override
    @Transactional
    public String block(String cardId) {
        Card card = this.cardRepository.findById(cardId).orElseThrow(() -> new RuntimeException("Error buscando card, Not Found Card" + cardId));
        this.cardRepository.delete(card);
        return "Card Eliminada";
    }

    @Override
    @Transactional
    public String rechargeBalance(CardRequestDTO cardDTO) {
        Card card = this.cardRepository.findById(cardDTO.getCardId()).orElseThrow(() -> new RuntimeException("Error buscando card, Not Found Card" + cardDTO.getCardId()));
        card.setBalance(card.getBalance() + (double) Integer.parseInt(cardDTO.getBalance()));

        this.cardRepository.save(card);
        return "Card Recargada";
    }

    @Override
    @Transactional
    public CardRequestDTO checkBalance(String cardId) {
        Card card = this.cardRepository.findById(cardId).orElseThrow(() -> new RuntimeException("Error buscando card, Not Found Card" + cardId));
        CardRequestDTO cardDTO = CardRequestDTO.builder()
                .cardId(card.getCardId())
                .balance(card.getBalance().toString())
                .build();
        return cardDTO;
    }

    @Override
    @Transactional
    public String create(CardSaveRequestDTO cardRequestDTO) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 3);

        Card card = new Card().builder()
                .cardId(cardRequestDTO.getCardId())
                .nameHeadline(cardRequestDTO.getNameHeadline())
                .firstNameHeadline(cardRequestDTO.getFirstNameHeadline())
                .dateExpiration(calendar.getTime())
                .state('I')
                .balance(0.0)
                .build();
        this.cardRepository.save(card);
        return "Tarjeta creada con exito";
    }



    private static String generateRandom() {
        StringBuilder numString = new StringBuilder(10);
        for(int i=0; i<10; i++) {
            numString.append(new Random().nextInt(10));
        }
        return numString.toString();
    }
}
