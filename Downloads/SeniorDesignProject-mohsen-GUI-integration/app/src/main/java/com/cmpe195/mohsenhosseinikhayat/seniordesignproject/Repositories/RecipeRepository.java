package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Repositories;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Ingredient;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.MealType;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.concurrent.RecursiveAction;

public class RecipeRepository {
    private static RecipeRepository ourInstance = new RecipeRepository();
    private ArrayList<Recipe> allRecipes;

    private RecipeRepository() {
        if (ourInstance != null)
        {
            throw new RuntimeException("Use getInstance() method to get the single instance of this class.");
        }

        allRecipes =  new ArrayList<>(Arrays.asList(FakeRecipesForMohsen()));
    }


    public ArrayList<Recipe> getAllRecipes()
    {
        return allRecipes;
    }

    /**
     * Find recipe with given name
     * @param recipeName The name of the recipe to retrieve
     * @return The recipe that has a matching name
     */
    public Recipe getRecipeWithName(String recipeName)
    {
        Recipe result = null;

        for (Recipe recipe : allRecipes)
        {
            if (recipe.getName().equals(recipeName))
            {
                result = recipe;
                break;
            }
        }

        return result;
    }

    /**
     * Search for recipe by name
     * @param queryString User input
     * @return Recipes that match the name
     */
    public ArrayList<Recipe> SearchRecipesByName(String queryString)
    {
        ArrayList<Recipe> results = new ArrayList<>();

        for (int i = 0; i< allRecipes.size(); i++)
        {
            Recipe currentRecipe = allRecipes.get(i);

            if (currentRecipe.getName().contains(queryString))
            {
                results.add(currentRecipe);
            }
        }

        return results;
    }


    /**
     * Search for recipe by ingredient
     * @param queryString User input
     * @return Recipes that contain that ingredient
     */
    public ArrayList<Recipe> SearchRecipeByIngredient(String queryString)
    {
        ArrayList<Recipe> results = new ArrayList<>();

        for (int i = 0; i < allRecipes.size(); i++)
        {
            Recipe currentRecipe = allRecipes.get(i);

            if (Arrays.asList(currentRecipe.getIngredients()).contains(queryString))
            {
                results.add(currentRecipe);
            }
        }

        return results;
    }

    /**
     * Search for recipe by calories
     * @param calories Calories target by user
     * @return Recipes that have less or equal calories to user target
     */
    public ArrayList<Recipe> SearchRecipeByCalories(int calories)
    {
        ArrayList<Recipe> results = new ArrayList<>();

        for (int i = 0; i < allRecipes.size(); i++)
        {
            Recipe currentRecipe = allRecipes.get(i);

            if (currentRecipe.getCalories() < calories)
            {
                results.add(currentRecipe);
            }
        }

        return results;
    }

    /**
     * Search for recipes by tags
     * @param queryString User tag input
     * @return Recipes that contain said tag
     */
    public ArrayList<Recipe> SearchRecipeByTag(String queryString)
    {
        ArrayList<Recipe> results = new ArrayList<>();

        for (int i = 0; i < allRecipes.size(); i++)
        {
            Recipe currentRecipe = allRecipes.get(i);
            if (Arrays.asList(currentRecipe.getTags()).contains(queryString))
            {
                results.add(currentRecipe);
            }
        }

        return results;
    }

    /**
     * Fake recipes for testing
     * @return A bunch of recipes
     */
    private Recipe[] FakeRecipesForMohsen()
    {
        Recipe recipe1 = new Recipe("chicken shawarma", "shawarma with chicken", tempCreateIngredientHashMaps(new String[] {"chicken", "banana"}),
                new String[] {"step1", "step2"}, 2, new String [] {"tag1"}, MealType.LUNCH, 300);

        Recipe recipe2 = new Recipe("beef shawarma", "shawarma with beef", tempCreateIngredientHashMaps(new String[] {"beef", "banana"}),
                new String[] {"step1", "step3"}, 2, new String [] {"tag1"}, MealType.LUNCH, 300);

        Recipe recipe3 = new Recipe("combo shawarma", "shawarma with beef and chicken", tempCreateIngredientHashMaps(new String[] {"beef", "chicken","banana"}),
                new String[] {"step1", "step2", "step3"}, 2, new String [] {"tag2", "tag3"}, MealType.LUNCH, 450);

        Recipe recipe4 = new Recipe("apple pie", "pie with apple", tempCreateIngredientHashMaps(new String[] {"apple","flour"}),
                new String[] {"step4", "step2"}, 3, new String [] {"tag5", "tag4"}, MealType.APPETIZER, 107);

        Recipe recipe5 = new Recipe("onion rings", "onions with rings", tempCreateIngredientHashMaps(new String[] {"onion","flour", "water"}),
                new String[] {"step4", "step7", "step1"}, 5, new String [] {"tag5"}, MealType.SNACK, 259);

        Recipe recipe6 = new Recipe("water", "plain water", tempCreateIngredientHashMaps(new String[] {"water"}),
                new String[] {"step4", "step9", "step3"}, 10, new String [] {"tag4"}, MealType.DRINK, 170);

        Recipe recipe7 = new Recipe("pepperoni pizza", "pepperoni on pizza", tempCreateIngredientHashMaps(new String[] {"pepperoni", "flour", "onion"}),
                new String[] {"step2", "step5"}, 6, new String [] {"tag7"}, MealType.DINNER, 560);

        Recipe recipe8 = new Recipe("cucumber salad", "salad with cucumber", tempCreateIngredientHashMaps(new String[] {"cucumber", "onion"}),
                new String[] {"step1", "step4", "step6"}, 3, new String [] {"tag8", "tag1"}, MealType.APPETIZER, 68);

        Recipe recipe9 = new Recipe("fish", "fish, i dont like fish", tempCreateIngredientHashMaps(new String[] {"fish", "water", "onion"}),
                new String[] {"step5", "step3"}, 2, new String [] {"tag4", "tag8"}, MealType.LUNCH, 325);

        Recipe recipe10 = new Recipe("fish and chips", "fish and chips", tempCreateIngredientHashMaps(new String[] {"fish", "onion", "potato"}),
                new String[] {"step5", "step3", "step7"}, 4, new String [] {"tag3", "tag1"}, MealType.LUNCH, 385);

        return new Recipe[] {recipe1, recipe2, recipe3, recipe4, recipe5, recipe6, recipe7, recipe8, recipe9, recipe10};
    }


    /**
     * Temp method for testing, takes a list of names and creates ingredient requirements for them
     * @param recipeNames the names of the recipes
     * @return A hashmap of ingredient to requirements
     */
    private LinkedHashMap<Ingredient, Double> tempCreateIngredientHashMaps(String[] recipeNames)
    {
        LinkedHashMap<Ingredient, Double> result = new LinkedHashMap<>();
        Random random = new Random();
        int limit = 100;


        PantryRepository pantryRepo = PantryRepository.getInstance();

        for (String recipeName : recipeNames)
        {
            result.put(pantryRepo.getIngredientWithName(recipeName),
                     Math.round(random.nextDouble() * 100) / 100.0);
        }

        return result;
    }


    /**
     * Fetch the repo instance, if non existent, creates one
     * @return The instance of the repository
     */
    public static RecipeRepository getInstance() {
        if (ourInstance == null)
        {
            ourInstance = new RecipeRepository();
        }

        return ourInstance;
    }

    public void saveRecipes()
    {
        // For now the only thing that changes on recipes is their favorite status
        //Serialize allRecipes here.
    }
}
