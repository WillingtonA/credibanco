package com.example.credibanco.controller;

import com.example.credibanco.dto.CardRequestDTO;
import com.example.credibanco.dto.CardSaveRequestDTO;
import com.example.credibanco.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    private CardService cardService;

    /**
     * 1 Generar número de tarjeta
     * @param productId
     * @return
     */
    @GetMapping("/{productId}/number")
    public ResponseEntity<String> generateNumber(@PathVariable("productId") String productId) {
        return new ResponseEntity<>(this.cardService.generateNumber(productId), HttpStatus.OK);
    }

    /**
     * 2 Activar tarjeta
     * @param cardDTO
     * @return String tarjeta cativada con exito
     * {
     * "cardId": "1020301234567801"
     * }
     */
    @PostMapping("/enroll")
    public ResponseEntity<String> active(@RequestBody CardRequestDTO cardDTO) {
        return new ResponseEntity<>(this.cardService.active(cardDTO), HttpStatus.OK);
    }

    /**
     * 3 Bloquear tarjeta
     * @param cardId
     * @return
     */
    @DeleteMapping("/{cardId}")
    public ResponseEntity<String> block(@PathVariable("cardId") String cardId) {
        return new ResponseEntity<>(this.cardService.block(cardId), HttpStatus.OK);
    }

    /**
     * 4 Recargar saldo
     * @param cardDTO
     * @return
     */
    /*
    {
        "cardId": "1234561234567890",
        "balance": "10000"
    }
     */
    @PostMapping("/balance")
    public ResponseEntity<String> rechargeBalance(@RequestBody CardRequestDTO cardDTO) {
        return new ResponseEntity<>(this.cardService.rechargeBalance(cardDTO), HttpStatus.OK);
    }

    /**
     * 5 Consulta de saldo
     * @param cardId
     * @return
     */
    @GetMapping("/balance/{cardId}")
    public ResponseEntity<CardRequestDTO> checkBalance(@PathVariable("cardId") String cardId) {
        return new ResponseEntity<>(this.cardService.checkBalance(cardId), HttpStatus.OK);
    }



    /*
    {
        "cardId": "1234561234567890",
        "firstNameHeadline": "Aguiar",
        "nameHeadline": "Willington"
    }*/
    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CardSaveRequestDTO cardRequestDTO) {
        return new ResponseEntity<>(this.cardService.create(cardRequestDTO), HttpStatus.OK);
    }

}
