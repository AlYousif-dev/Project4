/**
 * Represents Property Object
 *Class: CMSC203 CRN 21787 
 *Program: Assignment #4 
 *Instructor: Gary Thai 
 *Due Date: 11/3/2025  
 *Integrity Pledge: I pledge that I have completed the programming assignment independently. 
 *I have not copied the code from a student or any source. 
 * @author Yousif Aluobaidy
 */
public class Property {
    private String name;
    private String city;
    private double rentalAmount;
    private String owner;
    private Plot plot;

    /**
     * Creates a new Property using empty strings.
     * It also creates a default Plot.
     */
    public Property() {
        this.name = "";
        this.city = "";
        this.owner = "";
        this.rentalAmount = 0;
        this.plot = new Plot();
    }

    /**
     * Creates a new Property object using given values.
     * It also creates a default Plot.
     *
     * @param propertyName property name
     * @param city         city where the property is located
     * @param rentAmount   rent amount
     * @param owner        the owner's name
     */
    public Property(String propertyName, String city,
                    double rentAmount, String owner) {
        this.name = propertyName;
        this.city = city;
        this.rentalAmount = rentAmount;
        this.owner = owner;
        this.plot = new Plot();
    }

    /**
     * Creates a new Property object using given values.
     * It also creates a Plot using given values of a plot.
     *
     * @param propertyName property name
     * @param city         city where the property is located
     * @param rentAmount   rent amount
     * @param owner        the owner's name
     * @param x            the x coordinate of the plot
     * @param y            the y coordinate of the plot
     * @param width        the width coordinate of the plot
     * @param depth        the depth coordinate of the plot
     */
    public Property(String propertyName, String city,
                    double rentAmount, String owner, int x, int y,
                    int width, int depth) {
        this.name = propertyName;
        this.city = city;
        this.rentalAmount = rentAmount;
        this.owner = owner;
        this.plot = new Plot(x, y, width, depth);
    }

    /**
     * Creates a new copy of the given property object.
     *
     * @param otherProperty the Property object to make a copy of
     */
    public Property(Property otherProperty) {
        this.name = otherProperty.name;
        this.city = otherProperty.city;
        this.rentalAmount = otherProperty.rentalAmount;
        this.owner = otherProperty.owner;
        this.plot = new Plot(otherProperty.plot);
    }

    /**
     * Gets the city.
     * @return the city
     */
    public String getCity() {
        return this.city;
    }

    /**
     * Gets the owner.
     * @return the owner
     */
    public String getOwner() {
        return this.owner;
    }

    /**
     * Gets the plot.
     * @return the plot
     */
    public Plot getPlot() {
        return this.plot;
    }

    /**
     * Gets the property name.
     * @return the property name
     */
    public String getPropertyName() {
        return this.name;
    }

    /**
     * Gets the rent amount.
     * @return the rentAmount
     */
    public double getRentAmount() {
        return this.rentalAmount;
    }

    /**
     * Represents a Property object in the following String format:
     * propertyName,city,owner,rentAmount
     *
     * @return the string representation of a Property object
     */
    @Override
    public String toString() {
        //propertyName,city,owner,rentAmount
        return name + "," + city + "," + owner + "," + rentalAmount;
    }
}