package com.movieflix.demo.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "movie")
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title; //Nota: esse é o título do filme, não o título original do filme

    private String description; //Nota: essa é a descrição do filme, não a sinopse do filme

    @Column(name = "release_date")
    private LocalDate releaseDate; //Nota: essa é a data de lançamento do filme, não a data de quando ele foi adicionado à plataforma

    private Double rating; //Nota: essa é a avaliação média do filme, não a avaliação individual de um usuário

    @CreationTimestamp //Nota: essa anotação é usada para preencher automaticamente a data de criação do filme, ou seja, quando ele foi adicionado à plataforma
    @Column(name = "created_at")
    private LocalDate createdAt; //Nota: essa é a data de quando o filme foi adicionado à plataforma, não a data de lançamento do filme

    @UpdateTimestamp //Nota: essa anotação é usada para preencher automaticamente a data de atualização do filme, ou seja, quando ele foi atualizado pela última vez na plataforma
    @Column(name = "updated_at")
    private LocalDate updatedAt; //Nota: essa é a data de quando o filme foi atualizado pela última vez na plataforma, não a data de lançamento do filme

    @ManyToMany
    @JoinTable(name = "movie_category",
    joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToMany
    @JoinTable(name = "movie_streaming",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "streaming_id")
    )
    private List<Streaming> streamings;
}
