package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models;

public class IngredientCommand
{
    private double quantity;
    private Ingredient ingredient;
    private MeasurementUnit measurementUnit;

    public IngredientCommand(double quantity, MeasurementUnit measurementUnit, Ingredient ingredient)
    {
        this.quantity = quantity;
        this.measurementUnit = measurementUnit;
        this.ingredient = ingredient;
    }

    /**
     * Assembles command info into a string
     * @return command info in string format
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append(quantity);
        sb.append(" ");
        sb.append(measurementUnit);
        sb.append(" ");
        sb.append(this.ingredient.getName());
        sb.append(" ");

        return sb.toString();
    }

    /**
     * Get quantity of ingredient
     * @return quantity of ingredient
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Get ingredient being modified
     * @return ingredient being modified
     */
    public Ingredient getIngredient() {
        return ingredient;
    }

    /**
     * Get measurment unit used
     * @return measurement unit being used
     */
    public MeasurementUnit getMeasurementUnit() {
        return measurementUnit;
    }

    /**
     * Set the quantity of the ingredient
     * @param quantity quantity of the ingredient
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * Set the ingredient modified in command
     * @param ingredient the ingredient being modified
     */
    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    /**
     * Set the measurement unit being used
     * @param measurementUnit measurement unit used
     */
    public void setMeasurementUnit(MeasurementUnit measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    @Override
    public boolean equals(Object obj)
    {
        IngredientCommand that = (IngredientCommand) obj;

        return this.ingredient.equals(that.ingredient);
    }
}
