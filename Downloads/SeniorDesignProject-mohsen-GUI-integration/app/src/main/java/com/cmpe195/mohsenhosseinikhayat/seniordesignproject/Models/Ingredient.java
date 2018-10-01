package com.cmpe195.mohsenhosseinikhayat.seniordesignproject.Models;

public class Ingredient
{
    private String name;
    private double price;
    private MeasurementUnit measuringUnit;
    private double quantity;
    private double alertQuantity;

    public Ingredient(String name, MeasurementUnit measuringUnit, double quantity)
    {
        this.name = name;
        this.price = 0.0;
        this.measuringUnit = measuringUnit;
        this.quantity = quantity;
        this.alertQuantity = 0;
    }

    /**
     * Get name of ingredient
     * @return name of ingredient
     */
    public String getName() {
        return name;
    }

    /**
     * Get price of ingredient
     * @return price of ingredient
     */
    public double getPrice() {
        return price;
    }

    /**
     * Get quantity of ingredient
     * @return quantity of ingredient
     */
    public double getQuantity() {
        return quantity;
    }

    /**
     * Get the quantity at which the user needs to be alerted
     * @return the alert quantity
     */
    public double getAlertQuantity() {
        return alertQuantity;
    }

    /**
     *Get the standard measuring unit for the ingredient
     * @return the standard measuring unit
     */
    public MeasurementUnit getMeasuringUnit() {
        return measuringUnit;
    }

    /**
     * Set the quantity at which the user should be alerted
     * @param alertQuantity the alert quantity
     */
    public void setAlertQuantity(double alertQuantity) {
        this.alertQuantity = alertQuantity;
    }

    /**
     * Set the standard measuring unit for the ingredient
     * @param measuringUnit the standard measuring unit
     */
    public void setMeasuringUnit(MeasurementUnit measuringUnit) {
        this.measuringUnit = measuringUnit;
    }

    /**
     * Sets the name of the ingeredient
     * @param name the name of the ingredient
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the price of the ingredient
     * @param price price of the ingredient
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the quantity of the ingredient
     * @param quantity quantity of ingredient
     */
    public void setQuantity(double quantity) {
        this.quantity += quantity;
    }

    @Override
    public boolean equals(Object obj)
    {
       Ingredient that = (Ingredient) obj;

       return this.name.equals(that.name);
    }
}
