package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Adapters.PantryManagerRecyclerAdapter;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.R;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.ViewModels.PantryManagerViewModel;

public class PantryManagerFragment extends Fragment implements PantryManagerRecyclerAdapter.ItemClickListener {
    private Button goToAssistantButton;
    private RecyclerView pantryManagerRecyclerView;
    private PantryManagerViewModel model;
    private PantryManagerRecyclerAdapter pantryManagerRecyclerAdapter;

    public PantryManagerFragment()
    {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.pantry_pantry_manager, container, false);
        model = new PantryManagerViewModel();
        setupView(view);

        return view;
    }

    /**
     * Retrieves views and performs related setup
     */
    private void setupView(View view)
    {
        goToAssistantButton = (Button) view.findViewById(R.id.goToAssistantButton);
        goToAssistantButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //TODO: Pr #2, Redirect user to pantry assistant
            }
        });

        pantryManagerRecyclerView = (RecyclerView) view.findViewById(R.id.pantryManagerRecyclerView);
        pantryManagerRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        pantryManagerRecyclerAdapter = new PantryManagerRecyclerAdapter(this.getActivity(), model.getMyIngredients());
        pantryManagerRecyclerAdapter.setOnClickListener(this);
        pantryManagerRecyclerView.setAdapter(pantryManagerRecyclerAdapter);
        pantryManagerRecyclerAdapter.notifyDataSetChanged();

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                model.deleteIngredient(viewHolder.getAdapterPosition());
                pantryManagerRecyclerAdapter.notifyDataSetChanged();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        itemTouchHelper.attachToRecyclerView(pantryManagerRecyclerView);
    }

    @Override
    public void onItemClick(View view, int position) {
        //TODO: Redirect user to ingredient detail
    }
}
