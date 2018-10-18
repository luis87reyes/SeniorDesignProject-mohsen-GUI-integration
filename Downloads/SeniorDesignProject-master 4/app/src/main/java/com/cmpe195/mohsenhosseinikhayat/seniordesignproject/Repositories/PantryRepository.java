package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Repositories;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Ingredient;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.IngredientCommand;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.MeasurementUnit;

import java.util.ArrayList;
import java.util.HashMap;

public class PantryRepository {
    private static PantryRepository ourInstance = new PantryRepository();

    private HashMap<String, Ingredient> ingredientsHashMap;
    private ArrayList<Ingredient> myIngredients;

    /**
     * Constructor for pantry repo
     */
    public PantryRepository()
    {
        //Need to load these stuff, from json
        if (ourInstance != null)
        {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }

        myIngredients = new ArrayList<>();
        ingredientsHashMap = new HashMap<String, Ingredient>();
        tempCreateIngredientsForTesting();
    }


    /**
     * Add the ingredient and its quantity to the pantry
     * @param command command confirmed
     */
    public void processCommand(IngredientCommand command)
    {
        Ingredient currentIngredient = command.getIngredient();
        currentIngredient.setQuantity(command.getQuantity());

        if (myIngredients.indexOf(currentIngredient) == -1)
        {
            myIngredients.add(currentIngredient);
        }
    }

    /**
     * Returns the ingredients we have in the app (the cached ingredients, needed for speech recognizer to look through)
     * @return an array of ingredients in the app
     */
    public HashMap<String, Ingredient> getIngredients()
    {
        return ingredientsHashMap;
    }

    /**
     * Temporary dummy list of ingredients, replaced later by deserializng cached ingredients
     */
    private void tempCreateIngredientsForTesting()
    {
        String [] names = {"apple", "banana", "onion", "cucumber", "pomegranate", "water", "flour", "beef", "chicken", "fish", "pepperoni", "potato"};
        MeasurementUnit[] measurementUnits = {MeasurementUnit.POUND,
                MeasurementUnit.KILOGRAM,
                MeasurementUnit.GRAM,
                MeasurementUnit.POUND,
                MeasurementUnit.MILLIGRAM,
                MeasurementUnit.CUP,
                MeasurementUnit.TEASPOON,
                MeasurementUnit.POUND,
                MeasurementUnit.KILOGRAM,
                MeasurementUnit.OUNCE,
                MeasurementUnit.GRAM,
                MeasurementUnit.KILOGRAM};

        ingredientsHashMap = new HashMap<String, Ingredient>();

        for (int i = 0; i < names.length; i++)
        {
            ingredientsHashMap.put(names[i], new Ingredient(names[i], measurementUnits[i], 0.0));
        }
    }

    /**
     * The ingredients in the user's pantry, a subset of all the ingredients available
     * @return A list of all ingredients user has declared
     */
    public ArrayList<Ingredient> getMyIngredients() {
        return myIngredients;
    }


    /**
     * Fetch the repo instance, if non existent, creates one
     * @return The instance of the repository
     */
    public static PantryRepository getInstance() {
        if (ourInstance == null)
        {
            ourInstance = new PantryRepository();
        }

        return ourInstance;
    }

    /**
     * Returns a specific ingredient, used in IngredientDetailViewModel
     * @param ingredientName The name of the ingredient
     * @return The specific ingredient
     */
    public Ingredient getIngredientWithName(String ingredientName) {

        return ingredientsHashMap.get(ingredientName);
    }
}
