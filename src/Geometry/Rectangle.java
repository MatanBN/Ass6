package Geometry;

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The Geometry.Rectangle class has two coordinates, a width, a height and a color. The
 * Geometry.Rectangle has methods to calculate its area and to draw the rectangle.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 9 April 2016
 */
public class Rectangle {
    private Point upperLeft; // The upperLeft corner of the rectangle.
    private int width; // The width of the rectangle.
    private int height; // The height of the rectangle
    private Color color; // The color of the rectangle

    /**
     * Geometry.Rectangle is the constructor and creates the rectangle with upper left
     * point at (0,0) and initialize the width and height according to the user
     * parameters.
     * @param width the desired width of the rectangle.
     * @param height the desired height of the rectangle.
     */
    public Rectangle(int width, int height) {
        this.upperLeft = new Point(0, 0);
        this.width = width;
        this.height = height;
    }

    /**
     * Geometry.Rectangle is the constructor and creates the rectangle with upper left
     * point at (0,0) and initialize the width and height according to the user
     * parameters.
     * @param width the desired width of the rectangle.
     * @param height the desired height of the rectangle.
     * @param c the desired color of the rectangle.
     */
    public Rectangle(int width, int height, Color c) {
        this.upperLeft = new Point(0, 0);
        this.width = width;
        this.height = height;
        this.color = c;
    }

    /**
     * Geometry.Rectangle is the constructor and creates the rectangle according to the
     * user parameters.
     * @param x the x coordinate to start the rectangle from.
     * @param y the y coordinate to start the rectangle from.
     * @param width the desired width of the rectangle.
     * @param height the desired height of the rectangle.
     */
    public Rectangle(int x, int y, int width, int height) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
    }

    /**
     * Geometry.Rectangle is the constructor and creates the rectangle according to the
     * user parameters.
     * @param x the x coordinate to start the rectangle from.
     * @param y the y coordinate to start the rectangle from.
     * @param width the desired width of the rectangle.
     * @param height the desired height of the rectangle.
     * @param c the desired color of the rectangle.
     */
    public Rectangle(int x, int y, int width, int height, Color c) {
        this.upperLeft = new Point(x, y);
        this.width = width;
        this.height = height;
        this.color = c;
    }

    /**
     * Geometry.Rectangle is the constructor and creates the rectangle according to the
     * user parameters.
     * @param upperLeft the x,y coordinates to start the rectangle from.
     * @param width the desired width of the rectangle.
     * @param height the desired height of the rectangle.
     * @param c the desired color of the rectangle.
     */
    public Rectangle(Point upperLeft, int width, int height, Color c) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.color = c;
    }

    /**
     * getWidth method returns the width of the rectangle.
     * @return the width of the rectangle.
     */
    public int getWidth() {
        return width;
    }

    /**
     * getHeight method returns the height of the rectangle.
     * @return the height of the rectangle.
     */
    public int getHeight() {
        return height;
    }

    /**
     * getX method returns the left x of the rectangle.
     * @return the left x of the rectangle.
     */
    public int getX() {
        return (int) upperLeft.getX();
    }

    /**
     * getY method returns the upper y of the rectangle.
     * @return the upper y of the rectangle.
     */
    public int getY() {
        return (int) upperLeft.getY();
    }

    /**
     * getMaxX method returns the maximum x coordinate that the rectangle
     * reaches.
     * @return the maximum x coordinate that the rectangle reaches.
     */
    public int getMaxX() {
        return this.width + (int) upperLeft.getX();
    }

    /**
     * getMaxY method returns the maximum y coordinate that the rectangle
     * reaches.
     * @return the maximum y coordinate that the rectangle reaches.
     */
    public int getMaxY() {
        return this.getHeight() + (int) upperLeft.getY();
    }

    /**
     * getArea method returns the area of the rectangle.
     * @return the area of the rectangle.
     */
    public int getArea() {
        return this.height * this.width;
    }

    /**
     * setColor method sets the color of the rectangle.
     * @param c the desired color for the rectangle.
     */
    public void setColor(Color c) {
        this.color = c;
    }

    /**
     * drawOn method draws the rectangle.
     * @param d the DrawSurface to draw the rectangle on.
     * @throws NullPointerException if the color hasn't been initialized.
     */
    public void drawOn(DrawSurface d) throws NullPointerException {
        try {
            d.setColor(this.color);
            d.fillRectangle(this.getX(), this.getY(), this.width, this.height);
            d.setColor(Color.black);
            d.drawRectangle(this.getX(), this.getY(), this.width, this.height);
            d.setColor(Color.WHITE);
            // d.fillRectangle(this.getX(), this.getY(), this.width,
            // this.height);
            // If the color of the rectangle hasn't been initialized yet.
        } catch (NullPointerException e) {
            System.out.println("You must set a color for the rectangle in order to draw it.");
        }
    }

    /**
     * intersectionPoints method finds all of the intersection points of the
     * rectangle with a line.
     * @param line which will be the line to check the intersection with.
     * @return A list of all of the intersection points.
     */
    public List intersectionPoints(Line line) {
        List<Object> intersectionList = new ArrayList<Object>();
        // Store each edge of the rectangle in an array list.
        List<Object> linesList = new ArrayList<Object>();
        linesList.add(getLeftEdge());
        linesList.add(getRightEdge());
        linesList.add(getTopEdge());
        linesList.add(getBottomEdge());

        // Find all of the intersection points.
        for (Object l : linesList) {
            if (((Line) l).isIntersecting(line)) {
                intersectionList.add(((Line) l).intersectionWith(line));
            }
        }
        return intersectionList;
    }

    /**
     * getLeftEdge method returns the left edge of the rectangle.
     * @return The left edge of the rectangle.
     */
    public Line getLeftEdge() {
        return new Line(this.getX(), this.getY(), this.getX(), this.getMaxY());
    }

    /**
     * getRightEdge method returns the right edge of the rectangle.
     * @return The right edge of the rectangle.
     */
    public Line getRightEdge() {
        return new Line(this.getMaxX(), this.getY(), this.getMaxX(), this.getMaxY());
    }

    /**
     * getTopEdge method returns the top edge of the rectangle.
     * @return The top edge of the rectangle.
     */
    public Line getTopEdge() {
        return new Line(this.getX(), this.getY(), this.getMaxX(), this.getY());
    }

    /**
     * getBottomEdge method returns the bottom edge of the rectangle.
     * @return The bottom edge of the rectangle.
     */
    public Line getBottomEdge() {
        return new Line(this.getX(), this.getMaxY(), this.getMaxX(), this.getMaxY());
    }

    /**
     * getUpperLeft method returns the upper left corner of the rectangle.
     * @return the upper left corner of the rectangle..
     */
    public Point getUpperLeft() {
        return upperLeft;
    }

    /**
     * getColor method returns the color of the rectangle.
     * @return the color of the rectangle.
     */
    public Color getColor() {
        return color;
    }
}
