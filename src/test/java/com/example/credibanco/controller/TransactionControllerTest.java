package com.example.credibanco.controller;

import com.example.credibanco.dto.TransactionRequestDTO;
import com.example.credibanco.dto.TransactionResponseDTO;
import com.example.credibanco.service.TransactionService;
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

public class TransactionControllerTest {

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("6. Transacción de compra")
    public void buyTest() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        TransactionRequestDTO transactionRequestDTO = TransactionRequestDTO.builder()
                .cardId("1020301234567801")
                .price("100")
                .build();

        when(this.transactionService.buy(any(TransactionRequestDTO.class))).thenReturn(String.valueOf(new ResponseEntity<String>(HttpStatus.OK)));
        ResponseEntity<String> response = this.transactionController.buy(transactionRequestDTO);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @DisplayName("7. Consultar transacción")
    public void checkTest() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        String transactionId = "1";

        when(this.transactionService.check(any(String.class))).thenReturn(new ResponseEntity<TransactionResponseDTO>(HttpStatus.OK).getBody());
        ResponseEntity<TransactionResponseDTO> response = this.transactionController.check(transactionId);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    @DisplayName("Anulacion")
    public void anulationTest() {
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

        TransactionRequestDTO transactionRequestDTO = TransactionRequestDTO.builder()
                .transactionId("1")
                .cardId("1020301234567801")
                .build();

        when(this.transactionService.anulation(any(TransactionRequestDTO.class))).thenReturn(String.valueOf(new ResponseEntity<String>(HttpStatus.OK)));
        ResponseEntity<String> response = this.transactionController.anulation(transactionRequestDTO);

        assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

}
