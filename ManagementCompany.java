/**
 * Represents Management Company Object
 *Class: CMSC203 CRN 21787 
 *Program: Assignment #4 
 *Instructor: Gary Thai 
 *Summary of Description: Definition for a management class. Variables
 * includes name of the management company, taxID, management fee, an array containing their properties,
 * a plot that represents the companies plot, and the number of properties they have.
 *Due Date: 11/3/2025  
 *Integrity Pledge: I pledge that I have completed the programming assignment independently. 
 *I have not copied the code from a student or any source. 
 * @author Yousif Aluobaidy
 */
public class ManagementCompany {

    // Fields
    private String name;
    private String taxID;
    private double mgmFee;
    private Property[] properties;
    private Plot plot;
    private int numberOfProperties; // To track count in the array

    public static final int MAX_PROPERTY = 5;
    public static final int MGMT_DEPTH = 10;
    public static final int MGMT_WIDTH = 10;

    /**
     * Creates a ManagementCompany object using empty strings,
     * creates a default Plot with maximum width and depth and
     * it initializes the properties array.
     */
    public ManagementCompany() {
        this.name = "";
        this.taxID = "";
        this.mgmFee = 0;
        // creates a default Plot with maximum width and depth
        this.plot = new Plot(0, 0, MGMT_WIDTH, MGMT_DEPTH);
        this.properties = new Property[MAX_PROPERTY];
        this.numberOfProperties = 0;
    }

    /**
     * Creates a ManagementCompany object using the given values,
     * creates a default Plot with maximum width and depth and
     * it initializes the properties array.
     *
     * @param name   management Company name
     * @param taxID  tax Id
     * @param mgmFee management Fee
     */
    public ManagementCompany(String name, String taxID, double mgmFee) {
        this.name = name;
        this.taxID = taxID;
        this.mgmFee = mgmFee;
        // creates a default Plot with maximum width and depth
        this.plot = new Plot(0, 0, MGMT_WIDTH, MGMT_DEPTH);
        this.properties = new Property[MAX_PROPERTY];
        this.numberOfProperties = 0;
    }

    /**
     * Creates a ManagementCompany object using the given values
     * creates a Plot using the given values and
     * it initializes the properties array.
     *
     * @param name   management Company name
     * @param taxID  tax Id
     * @param mgmFee management Fee
     * @param x      x-coordinate of the plot
     * @param y      y-coordinate of the plot
     * @param width  width of the plot
     * @param depth  depth of the plot
     */
    public ManagementCompany(String name, String taxID, double mgmFee, int x, int y, int width, int depth) {
        this.name = name;
        this.taxID = taxID;
        this.mgmFee = mgmFee;
        this.plot = new Plot(x, y, width, depth);
        this.properties = new Property[MAX_PROPERTY];
        this.numberOfProperties = 0;
    }

    /**
     * Creates a new ManagementCompany copy of the given ManagementCompany.
     * This constructor creates a deep copy of the plot.
     *
     * @param otherCompany the company to copy
     */
    public ManagementCompany(ManagementCompany otherCompany) {
        this.name = otherCompany.name;
        this.taxID = otherCompany.taxID;
        this.mgmFee = otherCompany.mgmFee;
        // Create a deep copy of the plot
        this.plot = new Plot(otherCompany.plot);
        this.properties = new Property[MAX_PROPERTY];
        this.numberOfProperties = 0;
    }

    /**
     * Creates a property object from the given parameters and adds it to the
     * properties array.
     *
     * @param name  property name
     * @param city  city where the property is located
     * @param rent  rent amount
     * @param owner the owner's name
     * @return -1 if array is full, -2 if property is null,
     * -3 if plot is not encompassed, -4 if plot overlaps,
     * or the index where the property was added.
     */
    public int addProperty(String name, String city, double rent, String owner) {
        // This calls the next overloaded method with a default plot
        return addProperty(name, city, rent, owner, 0, 0, 1, 1);
    }

    /**
     * Creates a property object from the given parameters and adds it to the
     * properties array.
     *
     * @param name   property name
     * @param city   city where the property is located
     * @param rent   rent amount
     * @param owner  the owner's name
     * @param x      The x coordinate of the plot
     * @param y      The y coordinate of the plot
     * @param width  The width coordinate of the plot
     * @param depth  The depth coordinate of the plot
     * @return -1 if array is full, -2 if property is null,
     * -3 if plot is not encompassed, -4 if plot overlaps,
     * or the index where the property was added.
     */
    public int addProperty(String name, String city, double rent, String owner, int x, int y, int width, int depth) {
        // Creates a new Property object and calls the addProperty(Property) method
        Property property = new Property(name, city, rent, owner, x, y, width, depth);
        return addProperty(property);
    }

    /**
     * Creates a property object by copying from another property and adds it
     * to the properties array.
     *
     * @param property the property to be added
     * @return -1 if array is full, -2 if property is null,
     * -3 if plot is not encompassed, -4 if plot overlaps,
     * or the index where the property was added.
     */
    public int addProperty(Property property) {
        if (isPropertiesFull()) {
            return -1; // Array is full
        }

        if (property == null) {
            return -2; // Property object is null
        }

        if (!this.plot.encompasses(property.getPlot())) {
            return -3; // Plot is not encompassed by the management company's plot
        }

        // Check for overlaps with existing properties
        for (int i = 0; i < numberOfProperties; i++) {
            if (properties[i].getPlot().overlaps(property.getPlot())) {
                return -4; // Plot overlaps an existing property
            }
        }

        // Add a copy of the property to the array
        properties[numberOfProperties] = new Property(property);
        numberOfProperties++;
        return (numberOfProperties - 1); // Return the index where added
    }

    /**
     * Removes (nullifies) the LAST property in the properties array
     */
    public void removeLastProperty() {
        if (numberOfProperties > 0) {
            properties[numberOfProperties - 1] = null;
            numberOfProperties--;
        }
    }

    /**
     * Checks if the properties array has reached the maximum capacity
     *
     * @return true if properties array is full, false otherwise
     */
    public boolean isPropertiesFull() {
        return numberOfProperties == MAX_PROPERTY;
    }

    /**
     * Gets the number of existing properties in the array
     *
     * @return the number of existing properties in the array
     */
    public int getPropertiesCount() {
        return numberOfProperties;
    }

    /**
     * Returns the total rent of the properties in the properties array
     *
     * @return total rent
     */
    public double getTotalRent() {
        double totalRent = 0.0;
        for (int i = 0; i < numberOfProperties; i++) {
            totalRent += properties[i].getRentAmount();
        }
        return totalRent;
    }

    /**
     * Gets the property in the array with the maximum amount of rent
     *
     * @return the property in the array which has the highest amount of rent
     */
    public Property getHighestRentPropperty() {
        if (numberOfProperties == 0) {
            return null;
        }

        Property highest = properties[0];
        for (int i = 1; i < numberOfProperties; i++) {
            if (properties[i].getRentAmount() > highest.getRentAmount()) {
                highest = properties[i];
            }
        }
        // Javadoc doesn't specify deep copy, so return reference from array
        return highest;
    }

    /**
     * Checks if the management company has a valid (between 0-100) fee
     *
     * @return true of the management fee is valid (between 0-100), false otherwise
     */
    public boolean isMangementFeeValid() {
        return (mgmFee >= 0 && mgmFee <= 100);
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the tax ID.
     *
     * @return the tax ID
     */
    public String getTaxID() {
        return taxID;
    }

    /**
     * Gets the properties array.
     *
     * @return the properties array
     */
    public Property[] getProperties() {
        return properties;
    }

    /**
     * Gets the management fee percentage.
     *
     * @return the mgmFee
     */
    public double getMgmFeePer() {
        return mgmFee;
    }

    /**
     * Gets the plot.
     *
     * @return the plot
     */
    public Plot getPlot() {
        return plot;
    }
    /**
     * Represents the information of all the properties in the properties array.
     *
     * @return information of ALL the properties within this management company.
     */
    @Override
    public String toString() {
        String output = String.format("List of the properties for %s, taxID: %s\n", name, taxID);
        output += "______________________________________________________\n";

        for (int i = 0; i < numberOfProperties; i++) {
            output += properties[i].toString() + "\n";
        }

        output += "______________________________________________________\n";
        
        output += "\n";
        
        double totalFee = (getTotalRent() * mgmFee) / 100.0;
        
        output += String.format(" total management Fee: %.2f", totalFee);

        return output;
    }
}
