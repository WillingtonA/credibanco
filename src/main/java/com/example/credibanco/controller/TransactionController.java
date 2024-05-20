package com.example.credibanco.controller;

import com.example.credibanco.dto.TransactionRequestDTO;
import com.example.credibanco.dto.TransactionResponseDTO;
import com.example.credibanco.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    /**
     * 6. Transacción de compra
     * @param transactionRequestDTO
     * @return
     */
    /*
    {
        "cardId": "1020301234567801",
        "price": 100
    }
     */
    @PostMapping("/purchase")
    public ResponseEntity<String> buy(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        return new ResponseEntity<>(this.transactionService.buy(transactionRequestDTO), HttpStatus.OK);
    }

    /**
     * 7 Consultar transacción
     * @param transactionId
     * @return
     */
    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResponseDTO> check(@PathVariable("transactionId") String transactionId) {
        return new ResponseEntity<>(this.transactionService.check(transactionId), HttpStatus.OK);
    }

    /*
    {
        "cardId": "1020301234567801",
         "transactionId": "102030"
    }
    */
    @PostMapping("/anulation")
    public ResponseEntity<String> anulation(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        return new ResponseEntity<>(this.transactionService.anulation(transactionRequestDTO), HttpStatus.OK);
    }
}
