package com.movieflix.demo.entities;

import jakarta.persistence.*;
import lombok.*;

//para usar builder precisar do construtor sem argumentos e com todos os argumentos use Lombok
@Builder //Builder e um padrão de projeto para facilitar a criação de objetos
@Getter
@Setter
@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;



}
