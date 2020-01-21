package com.mpecel.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@EqualsAndHashCode(exclude = {"recipe"})
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne // no cascade, owned by Recipe, if we delete this, Recipe is not deleted (inversed true)
    private Recipe recipe;

    @Lob // large
    private String recipeNotes;

}
