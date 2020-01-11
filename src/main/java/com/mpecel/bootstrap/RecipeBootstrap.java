package com.mpecel.bootstrap;

import com.mpecel.domain.*;
import com.mpecel.repositories.CategoryRepository;
import com.mpecel.repositories.RecipeRepository;
import com.mpecel.repositories.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    private final CategoryRepository categoryRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public RecipeBootstrap(CategoryRepository categoryRepository, RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        recipeRepository.saveAll(getRecipes());
    }

    private List<Recipe> getRecipes() {

        List<Recipe> recipes = new ArrayList<>(2);

        // get UOM
        Optional<UnitOfMeasure> teaSpoonUomOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        if(!teaSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }

        Optional<UnitOfMeasure> tableSpoonUomOptional = unitOfMeasureRepository.findByDescription("Tablespoon");

        if(!tableSpoonUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }

        Optional<UnitOfMeasure> cupUomOptional = unitOfMeasureRepository.findByDescription("Cup");

        if(!cupUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }

        Optional<UnitOfMeasure> pinchUomOptional = unitOfMeasureRepository.findByDescription("Pinch");

        if(!pinchUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }

        Optional<UnitOfMeasure> ounceUomOptional = unitOfMeasureRepository.findByDescription("Ounce");

        if(!ounceUomOptional.isPresent()) {
            throw new RuntimeException("Expected UOM not found!");
        }

        // get Optionals
        UnitOfMeasure teaSpoonUom = teaSpoonUomOptional.get();
        UnitOfMeasure tableSpoonUom = tableSpoonUomOptional.get();
        UnitOfMeasure cupUom = cupUomOptional.get();
        UnitOfMeasure pinchUom = pinchUomOptional.get();
        UnitOfMeasure ounceUom  = ounceUomOptional.get();


        // get Categories

        Optional<Category> americanCategoryOptional = categoryRepository.findByDescription("American");
        if(!americanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found!");
        }

        Optional<Category> mexicanCategoryOptional = categoryRepository.findByDescription("Mexican");
        if(!mexicanCategoryOptional.isPresent()) {
            throw new RuntimeException("Expected Category not found!");
        }

        Category americanCategory = americanCategoryOptional.get();
        Category mexicanCategory = mexicanCategoryOptional.get();


        // Yummy Guac
        Recipe guacRecipe = new Recipe();
        guacRecipe.setDescription("Perfect Guacamole");
        guacRecipe.setPrepTime(10);
        guacRecipe.setCookTime(0);
        guacRecipe.setDifficulty(Difficulty.EASY);
        guacRecipe.setDirections("Guacamole Directions tbd...");
        Notes guacNotes = new Notes();
        guacNotes.setRecipeNotes("Guacamole notes tbd...");

        // needed for bi-directional (should be one method call)
        guacRecipe.setNotes(guacNotes);

        // helper method needed
        guacRecipe.addIngredient(new Ingredient("ripe avocados", new BigDecimal(2), teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("kosher salt", new BigDecimal(5), teaSpoonUom));
        guacRecipe.addIngredient(new Ingredient("fresh lime juice or lemon juice", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("minced red onion", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("serrano chiles", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("cilantro", new BigDecimal(2), tableSpoonUom));
        guacRecipe.addIngredient(new Ingredient("black pepper", new BigDecimal(2), pinchUom));
        guacRecipe.addIngredient(new Ingredient("ripe tomato", new BigDecimal(".5"), cupUom));

        guacRecipe.getCategories().add(americanCategory);
        guacRecipe.getCategories().add(mexicanCategory);

        // Yummy Tacos (tbd...)
        Recipe tacoRecipe = new Recipe();
        tacoRecipe.setDescription("Perfect Taco");
        tacoRecipe.setPrepTime(11);
        tacoRecipe.setCookTime(4);
        tacoRecipe.setDifficulty(Difficulty.HARD);
        tacoRecipe.setDirections("Taco Directions tbd...");
        Notes tacoNotes = new Notes();
        tacoNotes.setRecipeNotes("Taco notes tbd...");

        tacoRecipe.setNotes(tacoNotes);

        tacoRecipe.addIngredient(new Ingredient("aaa", new BigDecimal(1), teaSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("bbb", new BigDecimal(2), teaSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("ccc", new BigDecimal(3), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("ddd", new BigDecimal(4), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("eee", new BigDecimal(5), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("fff", new BigDecimal(6), tableSpoonUom));
        tacoRecipe.addIngredient(new Ingredient("ggg", new BigDecimal(7), pinchUom));
        tacoRecipe.addIngredient(new Ingredient("hhh", new BigDecimal(".8"), cupUom));

        tacoRecipe.getCategories().add(americanCategory);
        tacoRecipe.getCategories().add(mexicanCategory);

        ///////////////////////////////////

        recipes.add(guacRecipe);
        recipes.add(tacoRecipe);

        return recipes;
    }

}

