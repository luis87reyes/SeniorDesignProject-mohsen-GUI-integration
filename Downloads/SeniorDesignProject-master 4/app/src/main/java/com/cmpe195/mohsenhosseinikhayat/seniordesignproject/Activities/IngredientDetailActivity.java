package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Ingredient;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.ViewModels.IngredientDetailViewModel;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.R;

public class IngredientDetailActivity extends AppCompatActivity {

    private TextView ingredientNameTextView;
    private TextView ingredientQuantityTextView;
    private TextView ingredientMeasurementUnitTextView;
    private IngredientDetailViewModel viewModel;
    private Ingredient ingredient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String nameOfIngredient = getIntent().getStringExtra("ingredientName");
        viewModel = new IngredientDetailViewModel(nameOfIngredient);
        ingredient = viewModel.getIngredient();
        setupView();
    }

    /**
     * Retrieves views and performs their setup
     */
    private void setupView() {
        setContentView(R.layout.fragment_ingredient_detail);
        ingredientNameTextView = (TextView) findViewById(R.id.ingredientNameTextView);
        ingredientMeasurementUnitTextView = (TextView) findViewById(R.id.ingredientMeasurementUnitTextView);
        ingredientQuantityTextView = (TextView) findViewById(R.id.ingredientQuantityTextView);

        ingredientNameTextView.setText(ingredient.getName());
        ingredientMeasurementUnitTextView.setText(ingredient.getMeasuringUnit().toString().toLowerCase());
        ingredientQuantityTextView.setText(String.valueOf(ingredient.getQuantity()));
    }
}
