package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SearchView;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Adapters.RecipeSearchRecyclerAdapter;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Recipe;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.R;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.ViewModels.RecipeSearchViewModel;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;

public class RecipeSearchActivity extends AppCompatActivity {

    private SearchView recipeSearchView;
    private SuperRecyclerView recipeSearchRecyclerView;
    private RecipeSearchRecyclerAdapter recipeSearchRecyclerAdapter;
    private RecipeSearchViewModel model;
    private ArrayList<Recipe> resultRecipes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new RecipeSearchViewModel();
        setupView();
    }

    /**
     * Setup the view components
     */
    private void setupView()
    {
        setContentView(R.layout.activity_recipe_search);

        resultRecipes = new ArrayList<>();

        // Setup the SearchView
        recipeSearchView = (SearchView) findViewById(R.id.recipeSearchView);
        recipeSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                performSearch();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        // Setup the RecyclerView
        recipeSearchRecyclerView = (SuperRecyclerView) findViewById(R.id.recipeSearchRecyclerView);
        recipeSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recipeSearchRecyclerAdapter = new RecipeSearchRecyclerAdapter(this, resultRecipes);
        recipeSearchRecyclerView.setAdapter(recipeSearchRecyclerAdapter);
        recipeSearchRecyclerAdapter.notifyDataSetChanged();
    }

    /**
     * Perform a search for the user
     */
    private void performSearch()
    {
        // This will be determined by a drop down or something else later
        String searchOption = "Name";
        String queryString = recipeSearchView.getQuery().toString();

        switch (searchOption) {
            case "Ingredient":
                resultRecipes = model.SearchRecipeByIngredient(queryString);
                break;
            case "Tag":
                resultRecipes = model.SearchRecipeByTag(queryString);
                break;
            case "Calories":
                resultRecipes = model.SearchRecipeByCalories(queryString);
                break;
            default:
                resultRecipes = model.SearchRecipeByName(queryString);
                break;
        }

        recipeSearchRecyclerAdapter = new RecipeSearchRecyclerAdapter(this, resultRecipes);
        recipeSearchRecyclerAdapter.notifyDataSetChanged();
        recipeSearchRecyclerView.setAdapter(recipeSearchRecyclerAdapter);
    }
}