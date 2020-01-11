package com.mpecel.services;

import com.mpecel.domain.Recipe;
import com.mpecel.repositories.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    @Autowired
    public RecipeServiceImpl(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        Iterable<Recipe> iterableRecipes = recipeRepository.findAll();
        iterableRecipes.iterator().forEachRemaining(recipe -> recipes.add(recipe)); // recipes::add
        return recipes;
    }
}
