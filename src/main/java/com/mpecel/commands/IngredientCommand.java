package com.mpecel.commands;

import com.mpecel.domain.UnitOfMeasure;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class IngredientCommand {
    private long id;
    private String description;
    private BigDecimal amount;
    private UnitOfMeasure unitOfMeasure;
}
