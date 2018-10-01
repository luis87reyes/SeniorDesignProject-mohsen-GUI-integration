package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.ViewModels;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Recipe;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Repositories.RecipeRepository;

import java.util.ArrayList;

public class RecipeSearchViewModel
{
    private RecipeRepository repo;

    public RecipeSearchViewModel()
    {
        this.repo = RecipeRepository.getInstance();
    }

    /**
     * Search for recipes by ingredient
     * @param queryString The ingredient queried
     * @return List of results containing the ingredient
     */
    public ArrayList<Recipe> SearchRecipeByIngredient(String queryString)
    {
        return repo.SearchRecipeByIngredient(queryString);
    }

    /**
     * Search for recipes by name
     * @param queryString The name of the recipe
     * @return List of results that match or partially match that name
     */
    public ArrayList<Recipe> SearchRecipeByName(String queryString)
    {
        return repo.SearchRecipesByName(queryString);
    }

    /**
     * Search for recipes by how many calories they have
     * @param queryString The max calorie value
     * @return List of results that have less than or equal calories to value queried
     */
    public ArrayList<Recipe> SearchRecipeByCalories(String queryString)
    {
        int value = Integer.parseInt(queryString);
        return repo.SearchRecipeByCalories(value);
    }

    /**
     * Search for recipes by tag
     * @param queryString Tag queried
     * @return List of results that have said tag
     */
    public ArrayList<Recipe> SearchRecipeByTag(String queryString)
    {
        return repo.SearchRecipeByTag(queryString);
    }

    public void toggleFavoriteStatusForRecipe(Recipe recipe, boolean status)
    {
        recipe.setFavorite(status);
        repo.saveRecipes();
    }


}
