package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Repositories.PantryRepository;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Repositories.RecipeRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class RecipeEvaluator {

    private LinkedHashMap<Recipe, Double> recipeScoresHashMap;
    private ArrayList<Recipe> allRecipes;

    public RecipeEvaluator(RecipeRepository recipeRepo)
    {
        recipeRepo = RecipeRepository.getInstance();
        recipeScoresHashMap = new LinkedHashMap<>();
        allRecipes = recipeRepo.getAllRecipes();
    }

    /**
     * Returns a mapping of recipes to their ingredient requirement completion rate
     * @return A hashmap of recipe to completion rate
     */
    public LinkedHashMap<Recipe, Double> getRecipeScoresHashMap() {
        evaluateRecipes();
        return recipeScoresHashMap;
    }

    /**
     * Evaluate all recipes, create the mapping
     */
    private void evaluateRecipes()
    {
        for (Recipe recipe: allRecipes)
        {
            recipeScoresHashMap.put(recipe, evaluateRecipe(recipe));
        }

        ArrayList<Double> values = new ArrayList<>(recipeScoresHashMap.values());
        ArrayList<Recipe> keys = new ArrayList<>(recipeScoresHashMap.keySet());

        LinkedHashMap<Recipe, Double> sortedHashMap = new LinkedHashMap<>();

        for (int i = 0; i < recipeScoresHashMap.keySet().size(); i++)
        {
            double maxValue = Collections.max(values);
            int index = values.indexOf(maxValue);
            values.remove(index);

            sortedHashMap.put(keys.get(index), recipeScoresHashMap.get(keys.get(index)));
            keys.remove(index);
        }

        recipeScoresHashMap = sortedHashMap;
    }


    /**
     * Evaluates the completion rate for a single recipe
     * @param recipe The recipe to evaluate
     * @return A double value representing how close the user is to being able to make that recipe
     */
    public double evaluateRecipe(Recipe recipe)
    {
        double result = 0;
        double [] percentages = new double[recipe.getIngredients().size()];
        int index = 0;

        for (Ingredient ingredient: recipe.getIngredients().keySet())
        {
            percentages[index++] = Math.min(ingredient.getQuantity() / recipe.getIngredients().get(ingredient), 1);
        }

        for (int i = 0; i < percentages.length; i++) { result += percentages[i];}

        result = result / percentages.length;

        return result;
    }
}
