package com.example.credibanco.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tarjeta")
public class Card {

    @Id
    @Column(name = "id", length = 16)
    private String cardId;

    @Column(name = "nombre_titular")
    private String nameHeadline;

    @Column(name = "apellido_titular")
    private String firstNameHeadline;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "afecha_vencimiento")
    private Date dateExpiration;

    @Column(name = "estado", length = 1) // A:Active, C:Cancelada ...
    private char state;

    @Column(name = "saldo")
    private Double balance;


}
