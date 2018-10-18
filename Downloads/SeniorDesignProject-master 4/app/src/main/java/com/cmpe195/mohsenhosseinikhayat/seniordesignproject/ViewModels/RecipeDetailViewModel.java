package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.ViewModels;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Ingredient;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Recipe;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.RecipeEvaluator;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Repositories.RecipeRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class RecipeDetailViewModel {

    private RecipeRepository repo;
    private Recipe recipe;
    private String recipeName;
    private Double overallCompletion;

    public RecipeDetailViewModel(String recipeName)
    {
        repo = RecipeRepository.getInstance();
        this.recipeName = recipeName;

        recipe = repo.getRecipeWithName(recipeName);
        RecipeEvaluator recipeEvaluator = new RecipeEvaluator(repo);

        overallCompletion = recipeEvaluator.evaluateRecipe(recipe);
    }


    public LinkedHashMap <Ingredient, Double> getRecipeIngredients() {
        return recipe.getIngredients();
    }

    public String getRecipeDescription()
    {
        return recipe.getDescription();
    }

    public boolean getFavoriteStatus()
    {
        return recipe.isFavorite();
    }

    public String[] getCookingSteps()
    {
        return recipe.getCookingSteps();
    }

    public String[] getTags()
    {
        return recipe.getTags();
    }

    public int getServings()
    {
        return recipe.getServings();
    }

    public int getCalories()
    {
        return recipe.getCalories();
    }

    public String getMealType()
    {
        StringBuilder mealType = new StringBuilder(recipe.getMealType().toString().substring(0,1).toUpperCase());
        mealType.append(recipe.getMealType().toString().substring(1));

        return mealType.toString();
    }

    public Double getOverallCompletion() {
        return overallCompletion;
    }

    public void toggleFavoriteStatus(boolean status)
    {
        recipe.setFavorite(status);
        repo.saveRecipes();
    }


}
