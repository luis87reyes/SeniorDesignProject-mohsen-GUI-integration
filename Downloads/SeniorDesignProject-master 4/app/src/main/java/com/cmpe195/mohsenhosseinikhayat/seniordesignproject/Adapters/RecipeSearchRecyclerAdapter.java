package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Recipe;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.R;
import com.malinskiy.superrecyclerview.swipe.BaseSwipeAdapter;

import java.util.ArrayList;

public class RecipeSearchRecyclerAdapter extends BaseSwipeAdapter<RecipeSearchRecyclerAdapter.ViewHolder> {

    private ItemClickListener itemClickListener;
    private ArrayList<Recipe> recipesList;
    private LayoutInflater inflater;

    int[] images = {R.mipmap.hamburger, R.mipmap.lasagna, R.mipmap.mushrooms,R.mipmap.potattoes,
            R.mipmap.salmon,R.mipmap.scallops,R.mipmap.shrimp2};

    public RecipeSearchRecyclerAdapter(Context context, ArrayList<Recipe> recipes)
    {
        this.inflater = LayoutInflater.from(context);
        this.recipesList = recipes;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Recipe currentRecipe = recipesList.get(position);

        holder.nameTextView.setText(currentRecipe.getName());
        holder.descriptionTextView.setText(currentRecipe.getDescription());
        holder.ratingBar.setNumStars(4);
        holder.caloriesTextView.setText(String.valueOf(currentRecipe.getCalories()));
        holder.imageView.setImageResource(images[position % images.length]);
        this.setOnClickListener(itemClickListener);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recipe_search_row_layout, parent, false);

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
        private TextView caloriesTextView;
        private ImageView imageView;
        private RatingBar ratingBar;

        private ViewHolder(View itemView)
        {
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.recipeNameTextView);
            descriptionTextView = (TextView) itemView.findViewById(R.id.recipeDescriptionTextView);
            caloriesTextView = (TextView) itemView.findViewById(R.id.recipeCaloriesTextView);
            imageView = (ImageView) itemView.findViewById(R.id.recipeImageView);
            ratingBar = (RatingBar) itemView.findViewById(R.id.recipeRatingBar);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null)
            {
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public void setOnClickListener(RecipeSearchRecyclerAdapter.ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
