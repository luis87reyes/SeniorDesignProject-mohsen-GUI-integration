package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.IngredientCommand;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.MeasurementUnit;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.ViewModels.PantryAssistantViewModel;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.R;

import java.util.List;

public class PantryAssistantRecyclerAdapter extends RecyclerView.Adapter<PantryAssistantRecyclerAdapter.ViewHolder>{

    private ItemClickListener itemClickListener;
    private List<IngredientCommand> ingredientCommandList;
    private LayoutInflater inflater;
    private PantryAssistantViewModel viewModel;
    private Context context;

    public PantryAssistantRecyclerAdapter(Context context, List<IngredientCommand> ingredientCommands, PantryAssistantViewModel viewModel) {
        this.inflater= LayoutInflater.from(context);
        this.ingredientCommandList = ingredientCommands;
        this.viewModel = viewModel;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return ingredientCommandList.size();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pantry_receipt_row_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final IngredientCommand ingredientCommand = ingredientCommandList.get(position);
        holder.nameHeaderTextView.setText(ingredientCommand.getIngredient().getName());


        final String[] validMeasurementUnits = viewModel.getValidMeasurementUnits(ingredientCommand.getIngredient().getMeasuringUnit());
        ArrayAdapter<String> measurementUnitSpinnerAdapter = new ArrayAdapter<String>(context,
                R.layout.support_simple_spinner_dropdown_item, validMeasurementUnits);
        holder.measurementUnitSpinner.setAdapter(measurementUnitSpinnerAdapter);


        final ViewHolder finHolder = holder;
        holder.measurementUnitSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ingredientCommand.setMeasurementUnit(MeasurementUnit.valueOf(validMeasurementUnits[position].toUpperCase()));
                toggleKeyboard(ingredientCommand, finHolder.quantityEditText);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        holder.quantityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (finHolder.quantityEditText.hasFocus()) {
                    if (s.length() == 0) {
                        ingredientCommand.setQuantity(0);
                    } else {
                        ingredientCommand.setQuantity(Double.parseDouble(s.toString()));
                    }
                }
            }
        });
    }

    /**
     * Toggle keyboard to pre-validate user input
     * @param ingredientCommand The ingredient command selected
     * @param editText The row's edit text
     */
    private void toggleKeyboard(IngredientCommand ingredientCommand, EditText editText) {

        switch (ingredientCommand.getMeasurementUnit()) {
            case POUND:
                ingredientCommand.setMeasurementUnit(MeasurementUnit.POUND);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                editText.getText().clear();
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
                break;
            case TEASPOON:
                ingredientCommand.setMeasurementUnit(MeasurementUnit.TEASPOON);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.getText().clear();
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
                break;
            case GRAM:
                ingredientCommand.setMeasurementUnit(MeasurementUnit.GRAM);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.getText().clear();
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
                break;
            case CUP:
                ingredientCommand.setMeasurementUnit(MeasurementUnit.CUP);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                editText.getText().clear();
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
                break;
            case MILLIGRAM:
                ingredientCommand.setMeasurementUnit(MeasurementUnit.MILLIGRAM);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.getText().clear();
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
                break;
            case KILOGRAM:
                ingredientCommand.setMeasurementUnit(MeasurementUnit.KILOGRAM);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                editText.getText().clear();
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
                break;
            case OUNCE:
                ingredientCommand.setMeasurementUnit(MeasurementUnit.OUNCE);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
                editText.getText().clear();
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
                break;
            case TABLESPOON:
                ingredientCommand.setMeasurementUnit(MeasurementUnit.TABLESPOON);
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                editText.getText().clear();
                editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(3)});
                break;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView nameHeaderTextView;
        private Spinner measurementUnitSpinner;
        private EditText quantityEditText;

        public ViewHolder(View itemView) {
            super(itemView);
            nameHeaderTextView = (TextView) itemView.findViewById(R.id.ingredientNameHeaderTextView);
            quantityEditText = (EditText) itemView.findViewById(R.id.quantityEditText);
            measurementUnitSpinner = (Spinner) itemView.findViewById(R.id.measurementUnitSpinner);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (itemClickListener != null)
            {
                itemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public IngredientCommand getItem(int id)
    {
        return ingredientCommandList.get(id);
    }

    public void setClickListener(ItemClickListener itemClickListener)
    {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
