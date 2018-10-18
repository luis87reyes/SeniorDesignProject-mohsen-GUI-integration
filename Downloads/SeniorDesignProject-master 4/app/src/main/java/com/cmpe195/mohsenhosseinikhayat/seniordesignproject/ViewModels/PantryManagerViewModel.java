package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.ViewModels;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Ingredient;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Repositories.PantryRepository;

import java.util.ArrayList;

public class PantryManagerViewModel
{
    private PantryRepository repo;
    private ArrayList<Ingredient> myIngredients;

    public PantryManagerViewModel()
    {
        this.repo = PantryRepository.getInstance();
        myIngredients = repo.getMyIngredients();
    }

    /**
     * Fetch list of users ingredient which originate from repo
     * @return The list of users ingredients
     */
    public ArrayList<Ingredient> getMyIngredients() {
        return myIngredients;
    }

    /**
     * Delete ingredient from users list
     * @param index the index of the ingredient to delete
     */
    public void deleteIngredient(int index)
    {
        myIngredients.remove(index);
    }
}
