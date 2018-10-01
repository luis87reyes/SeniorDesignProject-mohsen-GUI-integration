package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.GridView;
import android.widget.TextView;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.R;

public class RecipeDetailActivity extends AppCompatActivity {

    private TextView recipeNameTextView;
    private GridView ingredientsGridView;
    private RecyclerView cookingStepsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setupView()
    {
        setContentView(R.layout.activity_recipe_detail);

        recipeNameTextView = (TextView) findViewById(R.id.recipeNameTextView);
        ingredientsGridView = (GridView) findViewById(R.id.ingredientsGridView);
        cookingStepsRecyclerView = (RecyclerView) findViewById(R.id.cookingStepsRecyclerView);
    }

}
