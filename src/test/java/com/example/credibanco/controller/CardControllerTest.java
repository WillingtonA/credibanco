package com.example.credibanco.controller;

import com.example.credibanco.dto.CardRequestDTO;
import com.example.credibanco.dto.CardSaveRequestDTO;
import com.example.credibanco.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CardControllerTest {

    @InjectMocks
    private CardController cardController;

    @Mock
    private CardService cardService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("1. Generar número de tarjeta")
    //@Disabled("se desactiva por un momento")
    public void generateNumberTest() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        String productId = "123456";

        when(this.cardService.generateNumber(any(String.class))).thenReturn(String.valueOf(new ResponseEntity<String>(HttpStatus.OK)));
        ResponseEntity<String> response = this.cardController.generateNumber(productId);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @DisplayName("2. Activar tarjeta")
    public void activeTest() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        CardRequestDTO cardDTO = CardRequestDTO.builder()
                .cardId("1020301234567801")
                .build();

        when(this.cardService.active(any(CardRequestDTO.class))).thenReturn(String.valueOf(new ResponseEntity<String>(HttpStatus.OK)));
        ResponseEntity<String> response = this.cardController.active(cardDTO);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @DisplayName("3. Bloquear tarjeta")
    public void blockTest() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        String cardId = "1234561234567890";

        when(this.cardService.block(any(String.class))).thenReturn(String.valueOf(new ResponseEntity<String>(HttpStatus.OK)));
        ResponseEntity<String> response = this.cardController.block(cardId);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @DisplayName("4. Recargar saldo")
    public void rechargeBalanceTest() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        CardRequestDTO cardDTO = CardRequestDTO.builder()
                .cardId("1234561234567890")
                .balance("10000")
                .build();

        when(this.cardService.rechargeBalance(any(CardRequestDTO.class))).thenReturn(String.valueOf(new ResponseEntity<String>(HttpStatus.OK)));
        ResponseEntity<String> response = this.cardController.rechargeBalance(cardDTO);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @DisplayName("5. Consulta de saldo")
    public void checkBalanceTest() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        String cardId = "1234561234567890";

        when(this.cardService.checkBalance(any(String.class))).thenReturn(new ResponseEntity<CardRequestDTO>(HttpStatus.OK).getBody());
        ResponseEntity<CardRequestDTO> response = this.cardController.checkBalance(cardId);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @DisplayName("Creacion")
    public void createTest() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        CardSaveRequestDTO cardRequestDTO = CardSaveRequestDTO.builder()
                .cardId("1234561234567890")
                .nameHeadline("Willington")
                .firstNameHeadline("Aguiar")
                .build();

        when(this.cardService.create(any(CardSaveRequestDTO.class))).thenReturn(String.valueOf(new ResponseEntity<String>(HttpStatus.OK)));
        ResponseEntity<String> response = this.cardController.create(cardRequestDTO);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
}
