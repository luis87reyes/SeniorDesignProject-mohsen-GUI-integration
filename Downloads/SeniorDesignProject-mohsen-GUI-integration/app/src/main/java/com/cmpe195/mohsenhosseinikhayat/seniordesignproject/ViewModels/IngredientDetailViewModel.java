package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.ViewModels;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Ingredient;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Repositories.PantryRepository;

public class IngredientDetailViewModel {

    private PantryRepository repo;
    private String ingredientName;
    private Ingredient ingredient;

    /**
     * Constructor for the view model
     * @param ingredientName The name of the ingredient we are going to display
     */
    public IngredientDetailViewModel(String ingredientName)
    {
        repo = PantryRepository.getInstance();
        this.ingredientName  = ingredientName;

        ingredient = repo.getIngredientWithName(ingredientName);
    }

    /**
     * Fetches the ingredient to display
     * @return The ingredient to display
     */
    public Ingredient getIngredient() {
        return ingredient;
    }
}
