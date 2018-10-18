package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Adapters.RecipeSearchRecyclerAdapter;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.MeasurementUnit;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Recipe;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.R;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.ViewModels.RecipeSearchViewModel;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;

public class RecipeSearchFragment extends Fragment
{
    private SearchView recipeSearchView;
    private SuperRecyclerView recipeSearchRecyclerView;
    private RecipeSearchRecyclerAdapter recipeSearchRecyclerAdapter;
    private RecipeSearchViewModel model;
    private ArrayList<Recipe> resultRecipes;
    private Spinner recipeSearchFilterSpinner;
    private String searchOption = "Name";


    public RecipeSearchFragment()
    {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_recipe_search, container, false);
        model = new RecipeSearchViewModel();
        setupView(view);


        return view;
    }


    private void setupView(View view)
    {
        resultRecipes = new ArrayList<>();

        // Setup the SearchView
        recipeSearchView = (SearchView) view.findViewById(R.id.recipeSearchView);
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
        recipeSearchRecyclerView = (SuperRecyclerView) view.findViewById(R.id.recipeSearchRecyclerView);
        recipeSearchRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recipeSearchRecyclerAdapter = new RecipeSearchRecyclerAdapter(this.getActivity(), resultRecipes);
        recipeSearchRecyclerView.setAdapter(recipeSearchRecyclerAdapter);
        recipeSearchRecyclerAdapter.notifyDataSetChanged();


        recipeSearchFilterSpinner = (Spinner) view.findViewById(R.id.recipeSearchFilterSpinner);
        // TODO: Remove this array
        final String [] filters = {"Name", "Ingredient", "Tag", "Calories"};
        ArrayAdapter<String> searchFilterSpinnerAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.support_simple_spinner_dropdown_item, filters);

        recipeSearchFilterSpinner.setAdapter(searchFilterSpinnerAdapter);
        recipeSearchFilterSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                searchOption = filters[position];
                recipeSearchView.setQuery("", false);
                recipeSearchView.setIconified(true);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * Perform a search for the user
     */
    private void performSearch()
    {
        // This will be determined by a drop down or something else later

        String queryString = recipeSearchView.getQuery().toString();

        switch (searchOption)
        {
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

        recipeSearchRecyclerAdapter = new RecipeSearchRecyclerAdapter(this.getActivity(), resultRecipes);
        recipeSearchRecyclerAdapter.notifyDataSetChanged();
        recipeSearchRecyclerView.setAdapter(recipeSearchRecyclerAdapter);
    }
}
