package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Adapters.PantryAssistantRecyclerAdapter;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.ViewModels.PantryAssistantViewModel;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.R;
import com.vikramezhil.droidspeech.DroidSpeech;
import com.vikramezhil.droidspeech.OnDSListener;

import java.util.List;

public class PantryAssistantActivity extends AppCompatActivity implements PantryAssistantRecyclerAdapter.ItemClickListener
        , OnDSListener
{
    private DroidSpeech droidSpeech;
    private Button speechButton;
    private Button confirmButton;
    private PantryAssistantViewModel viewModel;
    private RecyclerView pantryReceiptRecyclerView;
    private PantryAssistantRecyclerAdapter pantryReceiptArrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new PantryAssistantViewModel();
        setupView();
    }

    /**
     * Performs view setup
     */
    private void setupView()
    {
        setContentView(R.layout.activity_pantry_assistant);

        //Setup droidSpeech
        droidSpeech = new DroidSpeech(this, null, viewModel.getPossibleWords());
        droidSpeech.setOnDroidSpeechListener(this);
        droidSpeech.setListeningMsg("Listening for ingredients");
        droidSpeech.setContinuousSpeechRecognition(true);
        droidSpeech.setPreferredLanguage("en-US");
        droidSpeech.setOneStepVerifyConfirmTextColor(R.color.white_color);
        droidSpeech.setShowRecognitionProgressView(true);

        //Setup speechButton
        speechButton = (Button) findViewById(R.id.speechButton);
        speechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                speechButtonClicked();
            }
        });

        //Setup confirmButton
        confirmButton = (Button) findViewById(R.id.confirmButton);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmButtonClicked();
            }
        });

        //Setup pantryReceiptRecyclerView and adapter
        pantryReceiptRecyclerView = (RecyclerView) findViewById(R.id.pantryReceiptRecyclerView);
        pantryReceiptArrayAdapter = new PantryAssistantRecyclerAdapter
                (this, viewModel.getIngredientCommands(), viewModel);
        pantryReceiptRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
                viewModel.deleteIngredientCommand(viewHolder.getAdapterPosition());
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
        viewModel.confirmIngredientAddition();
        pantryReceiptArrayAdapter = new PantryAssistantRecyclerAdapter
                (this, viewModel.getIngredientCommands(), viewModel);
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
        viewModel.parseSpeech(finalSpeechResult);
        pantryReceiptArrayAdapter = new PantryAssistantRecyclerAdapter
                (this, viewModel.getIngredientCommands(), viewModel);
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