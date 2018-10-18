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

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Adapters.PantryAssistantRecyclerAdapter;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.R;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.ViewModels.PantryAssistantViewModel;
import com.vikramezhil.droidspeech.DroidSpeech;
import com.vikramezhil.droidspeech.OnDSListener;

import java.util.List;

public class PantryAssistantFragment extends Fragment implements PantryAssistantRecyclerAdapter.ItemClickListener, OnDSListener
{
    private DroidSpeech droidSpeech;
    private Button speechButton;
    private Button confirmButton;
    private PantryAssistantViewModel model;
    private RecyclerView pantryReceiptRecyclerView;
    private PantryAssistantRecyclerAdapter pantryReceiptArrayAdapter;

    public PantryAssistantFragment()
    {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_pantry_assistant, container, false);
        model = new PantryAssistantViewModel();
        setupView(view);

        return view;
    }

    private void setupView(View view)
    {
        //Setup droidSpeech
        droidSpeech = new DroidSpeech(this.getActivity(), null, model.getPossibleWords());
        droidSpeech.setOnDroidSpeechListener(this);
        droidSpeech.setListeningMsg("Listening for ingredients");
        droidSpeech.setContinuousSpeechRecognition(true);
        droidSpeech.setPreferredLanguage("en-US");
        droidSpeech.setOneStepVerifyConfirmTextColor(R.color.white_color);
        droidSpeech.setShowRecognitionProgressView(true);

        //Setup speechButton
        speechButton = (Button) view.findViewById(R.id.speechButton);
        speechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechButtonClicked();
            }
        });

        //Setup confirmButton
        confirmButton = (Button) view.findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmButtonClicked();
            }
        });

        //Setup pantryReceiptRecyclerView and adapter
        pantryReceiptRecyclerView = (RecyclerView) view.findViewById(R.id.pantryReceiptRecyclerView);
        pantryReceiptArrayAdapter = new PantryAssistantRecyclerAdapter
                (this.getActivity(), model.getIngredientCommands(), model);
        pantryReceiptRecyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        pantryReceiptRecyclerView.setAdapter(pantryReceiptArrayAdapter);
        pantryReceiptArrayAdapter.setClickListener(this);
        pantryReceiptArrayAdapter.notifyDataSetChanged();

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                model.deleteIngredientCommand(viewHolder.getAdapterPosition());
                pantryReceiptArrayAdapter.notifyDataSetChanged();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);

        itemTouchHelper.attachToRecyclerView(pantryReceiptRecyclerView);
    }

    /**
     * Handler for confirmButton
     */
    private void confirmButtonClicked() {
        model.confirmIngredientAddition();
        pantryReceiptArrayAdapter = new PantryAssistantRecyclerAdapter
                (this.getActivity(), model.getIngredientCommands(), model);
        pantryReceiptRecyclerView.setAdapter(pantryReceiptArrayAdapter);
        pantryReceiptArrayAdapter.setClickListener(this);
        pantryReceiptArrayAdapter.notifyDataSetChanged();
    }


    /**
     * Event handler for speechButton
     */
    public void speechButtonClicked()
    {
        droidSpeech.startDroidSpeechRecognition();
    }


    @Override
    public void onDroidSpeechSupportedLanguages(String currentSpeechLanguage, List<String> supportedSpeechLanguages) {

    }

    @Override
    public void onDroidSpeechRmsChanged(float rmsChangedValue) {

    }

    @Override
    public void onDroidSpeechLiveResult(String liveSpeechResult) {
    }

    @Override
    public void onDroidSpeechFinalResult(String finalSpeechResult) {

        //Parse speech, create an adapter and update the list view
        model.parseSpeech(finalSpeechResult);
        pantryReceiptArrayAdapter = new PantryAssistantRecyclerAdapter
                (this.getActivity(), model.getIngredientCommands(), model);
        pantryReceiptRecyclerView.setAdapter(pantryReceiptArrayAdapter);
        pantryReceiptArrayAdapter.setClickListener(this);
        pantryReceiptArrayAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDroidSpeechClosedByUser() {
    }

    @Override
    public void onDroidSpeechError(String errorMsg) {

    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
