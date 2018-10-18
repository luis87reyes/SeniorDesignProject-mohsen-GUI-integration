package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Adapters.RecommendationRecyclerAdapter;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.R;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.ViewModels.HomeViewModel;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

public class RecommendationFragment extends Fragment implements RecommendationRecyclerAdapter.ItemClickListener {
    private HomeViewModel model;

    private SuperRecyclerView recommendationRecyclerView;
    private RecommendationRecyclerAdapter recommendationRecyclerAdapter;

    public RecommendationFragment()
    {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_recommendation, container, false);
        model = new HomeViewModel();

        setupView(view);

        return view;
    }

    public void setupView(View view)
    {
        recommendationRecyclerView = (SuperRecyclerView) view.findViewById(R.id.recommendationRecyclerView);
        recommendationRecyclerAdapter = new RecommendationRecyclerAdapter(this.getActivity(), model.recommendRecipes());
        recommendationRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recommendationRecyclerAdapter.setOnClickListener(this);
        recommendationRecyclerView.setAdapter(recommendationRecyclerAdapter);
        recommendationRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume()
    {
        super.onResume();

        recommendationRecyclerAdapter = new RecommendationRecyclerAdapter(this.getActivity(), model.recommendRecipes());
        recommendationRecyclerAdapter.setOnClickListener(this);
        recommendationRecyclerView.setAdapter(recommendationRecyclerAdapter);
        recommendationRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(View view, int position) {
        //TODO: User should see recipe detail
    }
}
