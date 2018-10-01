package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Recipe;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.R;
import com.malinskiy.superrecyclerview.swipe.BaseSwipeAdapter;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class RecommendationRecyclerAdapter extends BaseSwipeAdapter<RecommendationRecyclerAdapter.ViewHolder> {

    private ItemClickListener itemClickListener;
    private ArrayList<Recipe> recipesList;
    private LayoutInflater inflater;
    private LinkedHashMap<Recipe, Double> ingredients;


    public RecommendationRecyclerAdapter(Context context, LinkedHashMap<Recipe, Double> ingredients)
    {
        this.inflater = LayoutInflater.from(context);
        this.ingredients = ingredients;
        this.recipesList = new ArrayList<>(ingredients.keySet());
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe currentRecipe = recipesList.get(position);

        holder.nameTextView.setText(currentRecipe.getName());
        holder.descriptionTextView.setText(currentRecipe.getDescription());
        holder.completionTextView.setText(ingredients.get(currentRecipe).toString());
        this.setOnClickListener(itemClickListener);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recommendation_row_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }


    public class ViewHolder extends BaseSwipeAdapter.BaseSwipeableViewHolder implements View.OnClickListener
    {
        private TextView nameTextView;
        private TextView descriptionTextView;
        private TextView completionTextView;

        public ViewHolder(View itemView)
        {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.recipeNameTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.recipeDescriptionTextView);
            completionTextView = (TextView) itemView.findViewById(R.id.recipeCompletionTextView);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null)
            {
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public void setOnClickListener(RecommendationRecyclerAdapter.ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}

