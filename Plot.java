/**
 *This class represents a Plot object.
 *Class: CMSC203 CRN 21787 
 *Program: Assignment #4 
 *Instructor: Gary Thai 
 *Due Date: 11/3/2025  
 *Integrity Pledge: I pledge that I have completed the programming assignment independently. 
 *I have not copied the code from a student or any source. 
 * @author Yousif Aluobaidy
 */
public class Plot {
    private int depth;
    private int width;
    private int x;
    private int y;

    /**
     * Creates a default Plot with width and depth of 1.
     * x and y are implicitly 0.
     */
    public Plot() {
        this.x = 0;
        this.y = 0;
        this.width = 1;
        this.depth = 1;
    }

    /**
     * Creates a Plot using the given values.
     *
     * @param x     the x coordinate of the plot
     * @param y     the y coordinate of the plot
     * @param width the width coordinate of the plot
     * @param depth the depth coordinate of the plot
     */
    public Plot(int x, int y, int width, int depth) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.depth = depth;
    }

    /**
     * Creates a new plot given another plot. This constructor calls the
     * appropriate existing constructor.
     *
     * @param otherPlot the plot to make a copy of
     */
    public Plot(Plot otherPlot) {
        // Calls the (int, int, int, int) constructor
        this(otherPlot.x, otherPlot.y, otherPlot.width, otherPlot.depth);
    }

    /**
     * Gets the depth.
     * @return the depth
     */
    public int getDepth() {
        return this.depth;
    }

    /**
     * Gets the width.
     * @return the width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Gets the x.
     * @return the x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the y.
     * @return the y
     */
    public int getY() {
        return this.y;
    }

    /**
     * Sets the depth.
     * @param depth the new depth
     */
    public void setDepth(int depth) {
        this.depth = depth;
    }

    /**
     * Sets the width.
     * @param width the new width
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Sets the x.
     * @param x the new x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y.
     * @param y the new y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Determines if the given plot is encompassed by (is contained by) this plot.
     *
     * @param otherPlot the plot to test against
     * @return true if the given plot is encompassed by this plot, false otherwise
     */
    public boolean encompasses(Plot otherPlot) {
        // Check if otherPlot's corners are within this.plot's boundaries
        boolean leftEdgeInside = otherPlot.x >= this.x;
        boolean topEdgeInside = otherPlot.y >= this.y;
        boolean rightEdgeInside = (otherPlot.x + otherPlot.width) <= (this.x + this.width);
        boolean bottomEdgeInside = (otherPlot.y + otherPlot.depth) <= (this.y + this.depth);

        return leftEdgeInside && topEdgeInside && rightEdgeInside && bottomEdgeInside;
    }

    /**
     * Determines if the given plot instance is overlapped by the current plot.
     *
     * @param otherPlot the plot to test against
     * @return true if the two plots overlap, false otherwise
     */
    public boolean overlaps(Plot otherPlot) {
        // Check if the plots are separate. If they are not separate, they overlap.
        
        // This plot is to the LEFT of otherPlot
        if (this.x + this.width <= otherPlot.x) {
            return false;
        }
        // This plot is to the RIGHT of otherPlot
        if (this.x >= otherPlot.x + otherPlot.width) {
            return false;
        }
        // This plot is ABOVE otherPlot
        if (this.y + this.depth <= otherPlot.y) {
            return false;
        }
        // This plot is BELOW otherPlot
        if (this.y >= otherPlot.y + otherPlot.depth) {
            return false;
        }

        // If none of the above are true, the plots must overlap
        return true;
    }

    /**
     * Represents a Plot object in the following String format: x,y,width,depth
     *
     * @return the string representation of a plot.
     */
    @Override
    public String toString() {
        return x + "," + y + "," + width + "," + depth;
    }
}