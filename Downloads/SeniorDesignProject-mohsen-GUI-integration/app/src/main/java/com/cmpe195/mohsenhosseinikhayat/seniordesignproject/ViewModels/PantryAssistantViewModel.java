package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.ViewModels;

import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.Ingredient;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.IngredientCommand;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models.MeasurementUnit;
import com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Repositories.PantryRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class PantryAssistantViewModel
{
    private HashMap<MeasurementUnit, MeasurementUnit[]> compatibleMeasurmentUnits;
    private HashMap<MeasurementUnit, HashMap<MeasurementUnit, Double>> unitConversionTables;
    private PantryRepository repo;
    private ArrayList<IngredientCommand> ingredientCommands;
    private String [] ingredientNames;

    /**
     * Constructor for view model
     */
    public PantryAssistantViewModel()
    {
        this.repo = PantryRepository.getInstance();
        setupCompatibleMeasurementUnitsMapping();
        setupConversionMapping();
        loadIngredientNames();
        ingredientCommands = new ArrayList<IngredientCommand>();
    }

    /**
     * Retrieves the names of all the ingredients we have
     */
    private void loadIngredientNames()
    {
        ingredientNames = repo.getIngredients().keySet().toArray(new String[0]);
    }

    /**
     * Parses speech and creates IngredientCommands from it
     * This is where we need to change later on to improve speech usefulness
     * @param speechText speech from DroidSpeech OnResults
     */
    public void parseSpeech(String speechText)
    {
        speechText = speechText.toLowerCase();
        ArrayList<String> splitSpeechText = new ArrayList<String>(Arrays.asList(speechText.split(" ")));
        double quantity = 0.0;

        Ingredient[] ingredientsInCommand = findIngredientName(splitSpeechText);

        for (int i = 0; i < ingredientsInCommand.length; i++)
        {
            Ingredient currentIngredient = ingredientsInCommand[i];
            IngredientCommand newCommand = new IngredientCommand(quantity, currentIngredient.getMeasuringUnit() , currentIngredient);
            if (!ingredientCommands.contains(newCommand)) {
                ingredientCommands.add(newCommand);
            }
        }
    }

    /**
     * Finds all the ingredients mentioned in speech
     * @param speechText Split array list containing each word from DroidSpeech
     * @return An array of all the ingredients that were found
     */
    private Ingredient[] findIngredientName (ArrayList<String> speechText)
    {
        ArrayList<Ingredient> ingredientsInCommand = new ArrayList<>();

        for (int i = 0; i < speechText.size(); i++)
        {
            Ingredient currentIngredient = repo.getIngredientWithName(speechText.get(i));
            if (currentIngredient != null)
            {
                ingredientsInCommand.add(currentIngredient);
            }
        }

        return ingredientsInCommand.toArray(new Ingredient[0]);
    }

    /**
     * Sets the ingredientCommands ready to be confirmed
     * @param ingredientCommands ingredient commands ready to be confirmed
     */
    public void setIngredientCommands(ArrayList<IngredientCommand> ingredientCommands) {
        this.ingredientCommands = ingredientCommands;
    }

    /**
     * Returns the ingredientCommands ready to be confirmed
     * @return ingredient commands ready to be confirmed
     */
    public ArrayList<IngredientCommand> getIngredientCommands() {
        return ingredientCommands;
    }

    /**
     * Gets all the possible words that DroidSpeech should look for
     * @return array of string values for possible words
     */
    public String[] getPossibleWords()
    {
        ArrayList<String> possibleWords = new ArrayList<String>();

        for (int i = 0; i < ingredientNames.length; i++)
        {
            possibleWords.add(ingredientNames[i].toLowerCase());
        }

        return possibleWords.toArray(new String [0]);
    }

    /**
     * Creates a measurement mapping so that convertable units can be available
     */
    private void setupCompatibleMeasurementUnitsMapping()
    {
        compatibleMeasurmentUnits = new HashMap<MeasurementUnit, MeasurementUnit[]>();

        compatibleMeasurmentUnits.put(MeasurementUnit.KILOGRAM,
                new MeasurementUnit[] {MeasurementUnit.KILOGRAM, MeasurementUnit.MILLIGRAM, MeasurementUnit.GRAM,
                        MeasurementUnit.POUND, MeasurementUnit.OUNCE});

        compatibleMeasurmentUnits.put(MeasurementUnit.GRAM,
                new MeasurementUnit[] {MeasurementUnit.GRAM, MeasurementUnit.OUNCE, MeasurementUnit.POUND, MeasurementUnit.MILLIGRAM, MeasurementUnit.KILOGRAM});

        compatibleMeasurmentUnits.put(MeasurementUnit.MILLIGRAM,
                new MeasurementUnit[] {MeasurementUnit.MILLIGRAM, MeasurementUnit.KILOGRAM, MeasurementUnit.GRAM, MeasurementUnit.POUND, MeasurementUnit.OUNCE});

        compatibleMeasurmentUnits.put(MeasurementUnit.POUND,
                new MeasurementUnit[] {MeasurementUnit.POUND, MeasurementUnit.OUNCE, MeasurementUnit.MILLIGRAM, MeasurementUnit.GRAM, MeasurementUnit.KILOGRAM});

        compatibleMeasurmentUnits.put(MeasurementUnit.OUNCE,
                new MeasurementUnit[] {MeasurementUnit.OUNCE, MeasurementUnit.GRAM, MeasurementUnit.POUND, MeasurementUnit.MILLIGRAM, MeasurementUnit.KILOGRAM});

        compatibleMeasurmentUnits.put(MeasurementUnit.TEASPOON,
                new MeasurementUnit[] {MeasurementUnit.TEASPOON, MeasurementUnit.TEASPOON, MeasurementUnit.CUP, MeasurementUnit.LITER, MeasurementUnit.MILLILITER});

        compatibleMeasurmentUnits.put(MeasurementUnit.CUP,
                new MeasurementUnit[] {MeasurementUnit.CUP, MeasurementUnit.TEASPOON, MeasurementUnit.TABLESPOON, MeasurementUnit.MILLILITER, MeasurementUnit.LITER});

        compatibleMeasurmentUnits.put(MeasurementUnit.TABLESPOON,
                new MeasurementUnit[] {MeasurementUnit.TABLESPOON, MeasurementUnit.TEASPOON, MeasurementUnit.CUP, MeasurementUnit.LITER, MeasurementUnit.MILLILITER});

        compatibleMeasurmentUnits.put(MeasurementUnit.LITER,
                new MeasurementUnit[] {MeasurementUnit.LITER, MeasurementUnit.MILLILITER, MeasurementUnit.CUP, MeasurementUnit.TABLESPOON, MeasurementUnit.TEASPOON});

        compatibleMeasurmentUnits.put(MeasurementUnit.MILLILITER,
                new MeasurementUnit[] {MeasurementUnit.MILLILITER, MeasurementUnit.LITER, MeasurementUnit.CUP, MeasurementUnit.TEASPOON, MeasurementUnit.TABLESPOON});
    }

    /**
     * Creates the conversion mapping we need to use for converting values
     */
    private void setupConversionMapping()
    {
        unitConversionTables = new HashMap<>();

        //Kilogram
        HashMap<MeasurementUnit, Double> forKilogram = new HashMap<>();
        forKilogram.put(MeasurementUnit.GRAM, 1000.0);
        forKilogram.put(MeasurementUnit.MILLIGRAM, Math.pow(10, 6));
        forKilogram.put(MeasurementUnit.POUND, 2.20462);
        forKilogram.put(MeasurementUnit.OUNCE, 35.2739199982575);
        unitConversionTables.put(MeasurementUnit.KILOGRAM, forKilogram);

        //Milligram
        HashMap<MeasurementUnit, Double> forMilligram =  new HashMap<>();
        forMilligram.put(MeasurementUnit.GRAM, 0.001);
        forMilligram.put(MeasurementUnit.KILOGRAM, Math.pow(10, -6));
        forMilligram.put(MeasurementUnit.POUND, 0.00220462);
        forMilligram.put(MeasurementUnit.OUNCE, 3.5273999999603 * Math.pow(10, -5));
        unitConversionTables.put(MeasurementUnit.MILLIGRAM, forMilligram);

        //Gram
        HashMap<MeasurementUnit, Double> forGram = new HashMap<>();
        forGram.put(MeasurementUnit.MILLIGRAM, 1000.0);
        forGram.put(MeasurementUnit.KILOGRAM, 0.001);
        forGram.put(MeasurementUnit.POUND, 0.00220462);
        forGram.put(MeasurementUnit.OUNCE, 0.035274);
        unitConversionTables.put(MeasurementUnit.GRAM, forGram);

        //Pound
        HashMap<MeasurementUnit, Double> forPound = new HashMap<>();
        forPound.put(MeasurementUnit.KILOGRAM, 0.453592);
        forPound.put(MeasurementUnit.GRAM, 453.592);
        forPound.put(MeasurementUnit.MILLIGRAM, 453592.0);
        forPound.put(MeasurementUnit.OUNCE, 16.0);
        unitConversionTables.put(MeasurementUnit.POUND, forPound);

        //Ounce
        HashMap<MeasurementUnit, Double> forOunce = new HashMap<>();
        forOunce.put(MeasurementUnit.KILOGRAM, 0.0283495);
        forOunce.put(MeasurementUnit.GRAM, 28.3495);
        forOunce.put(MeasurementUnit.POUND, 0.06249994901875);
        forOunce.put(MeasurementUnit.MILLIGRAM, 28349.5);
        unitConversionTables.put(MeasurementUnit.OUNCE, forOunce);

        //Cup
        HashMap<MeasurementUnit, Double> forCup = new HashMap<>();
        forCup.put(MeasurementUnit.TABLESPOON, 16.0);
        forCup.put(MeasurementUnit.TEASPOON, 48.0);
        forCup.put(MeasurementUnit.MILLILITER, 236.588);
        forCup.put(MeasurementUnit.LITER, 0.236588);
        unitConversionTables.put(MeasurementUnit.CUP, forCup);

        //Tablespoon
        HashMap<MeasurementUnit, Double> forTableSpoon = new HashMap<>();
        forTableSpoon.put(MeasurementUnit.CUP, 0.0625);
        forTableSpoon.put(MeasurementUnit.LITER, 0.0147868);
        forTableSpoon.put(MeasurementUnit.TEASPOON, 3.0);
        forTableSpoon.put(MeasurementUnit.MILLILITER, 14.7868);
        unitConversionTables.put(MeasurementUnit.TABLESPOON, forTableSpoon);

        //Teaspoon
        HashMap<MeasurementUnit, Double> forTeaSpoon = new HashMap<>();
        forTeaSpoon.put(MeasurementUnit.MILLILITER, 4.92892);
        forTeaSpoon.put(MeasurementUnit.TABLESPOON, 0.333333);
        forTeaSpoon.put(MeasurementUnit.LITER, 0.00492892);
        forTeaSpoon.put(MeasurementUnit.CUP, 0.0208333);
        unitConversionTables.put(MeasurementUnit.TEASPOON, forTeaSpoon);

        //Milliliter
        HashMap<MeasurementUnit, Double> forMilliliter = new HashMap<>();
        forMilliliter.put(MeasurementUnit.TEASPOON, 0.202884);
        forMilliliter.put(MeasurementUnit.TABLESPOON, 0.067628);
        forMilliliter.put(MeasurementUnit.CUP, 0.00422675);
        forMilliliter.put(MeasurementUnit.LITER, 0.001);
        unitConversionTables.put(MeasurementUnit.MILLILITER, forMilliliter);

        //Liter
        HashMap<MeasurementUnit, Double> forLiter = new HashMap<>();
        forLiter.put(MeasurementUnit.MILLILITER, 1000.0);
        forLiter.put(MeasurementUnit.CUP, 4.22675);
        forLiter.put(MeasurementUnit.TABLESPOON, 67.628);
        forLiter.put(MeasurementUnit.TEASPOON, 202.884);
        unitConversionTables.put(MeasurementUnit.LITER, forLiter);
    }

    /**
     * Returns the valid set of measuring units available for a specific ingredient
     * @param ingredientsUnit The ingredient's standard unit of measurement
     * @return A list of string representing the names of valid units
     */
    public String[] getValidMeasurementUnits(MeasurementUnit ingredientsUnit)
    {
        MeasurementUnit[] compatibles =  compatibleMeasurmentUnits.get(ingredientsUnit);
        String[] compatiblesNames = new String[compatibles.length];

        for (int i = 0; i < compatibles.length; i++)
        {
            compatiblesNames[i] = compatibles[i].toString().toLowerCase();
        }

        return compatiblesNames;
    }

    public void deleteIngredientCommand (int index)
    {
        ingredientCommands.remove(index);
    }

    public void confirmIngredientAddition()
    {
        ArrayList<IngredientCommand> invalidCommands = new ArrayList<>();

        for (int i = 0; i < ingredientCommands.size(); i++)
        {
            IngredientCommand currentCommand = ingredientCommands.get(i);

            //No quantity, not acceptable
            if (currentCommand.getQuantity() == 0.0)
            {
                invalidCommands.add(currentCommand);
                continue;
            }

            //If the measurement unit used is different, convert the quantity and proceed
            if (currentCommand.getMeasurementUnit() != currentCommand.getIngredient().getMeasuringUnit())
            {
               currentCommand.setQuantity(convertFromMeasuringUnitTo(currentCommand.getMeasurementUnit(),
                       currentCommand.getIngredient().getMeasuringUnit(),
                       currentCommand.getQuantity()));

               currentCommand.setMeasurementUnit(currentCommand.getIngredient().getMeasuringUnit());
            }

            currentCommand.setQuantity(currentCommand.getQuantity());

            repo.processCommand(currentCommand);
        }

        ingredientCommands = invalidCommands;
    }

    private double convertFromMeasuringUnitTo(MeasurementUnit from, MeasurementUnit to, double quantity)
    {
        double result;

        double multiplier = unitConversionTables.get(from).get(to);

        result = quantity * multiplier;

        return result;
    }
}
