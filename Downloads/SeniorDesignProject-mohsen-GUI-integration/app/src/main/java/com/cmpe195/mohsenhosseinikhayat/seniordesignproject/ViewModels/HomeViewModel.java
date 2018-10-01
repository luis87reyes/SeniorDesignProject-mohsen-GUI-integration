package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.ViewModels;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Ingredient;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Recipe;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.RecipeEvaluator;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Repositories.PantryRepository;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Repositories.RecipeRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class HomeViewModel
{
    private RecipeRepository recipeRepo;
    private PantryRepository pantryRepo;
    private RecipeEvaluator recipeEvaluator;

    public HomeViewModel()
    {
        recipeRepo = RecipeRepository.getInstance();
        pantryRepo = PantryRepository.getInstance();
        recipeEvaluator = new RecipeEvaluator(recipeRepo);
    }

    /**
     * Returns a list of recipes, the recipes are sorted by percentage completion
     * @return
     */
    public LinkedHashMap<Recipe, Double> recommendRecipes()
    {
        return recipeEvaluator.getRecipeScoresHashMap();
    }

    /**
     * TODO: later on we will provide some recipes for discovery based on popularity
     * @return
     */
    public ArrayList<Recipe> discoverRecipes()
    {
        return new ArrayList<Recipe>();
    }


    public void toggleFavoriteStatusForRecipe(Recipe recipe, boolean status)
    {
        recipe.setFavorite(status);
        recipeRepo.saveRecipes();
    }
}
