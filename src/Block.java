/**
 * The Block is a collidable object that can block moving objects.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 19 May 2016
 */
import biuoop.DrawSurface;

import java.awt.Color;

public class Block implements Collidable {
    private Rectangle rectangle;

    /**
     * Block creates a new rectangle block by a given rectangle.
     * @param r is a given rectangle.
     */
    public Block (Rectangle r){
        this.rectangle=r;
    }

    /**
     * Block creates a new rectangle block by it's width and height.
     * @param width is the rectangle's width.
     * @param height is the rectangle's height.
     */
    public Block (int width, int height){
        this.rectangle = new Rectangle(width, height, Color.blue);
    }

    /**
     * Block creates a new rectangle block by it's left corner coordinates, width and height.
     * @param x is the x coordinate of left corner.
     * @param y is the y coordinate of left corner.
     * @param width is the rectangle's width.
     * @param height is the rectangle's height.
     */
    public Block (int x, int y, int width, int height){
        this.rectangle = new Rectangle(x, y, width, height, Color.blue);
    }

    public void drawOn (DrawSurface d){
        rectangle.drawOn(d);
    }

    /**
     * getCollisionRectangle return the block's shape.
     * @return the given rectangle.
     */
    public Rectangle getCollisionRectangle(){
        return this.rectangle;
    }

    // Notify the object that we collided with it at collisionPoint with
    // a given velocity.
    // The return is the new velocity expected after the hit (based on
    // the force the object inflicted on us).
    /**
     * hit return the new velocity after the hit based on force the object inflicted on us.
     * @param collisionPoint is the collision point of an object with the block.
     * @param currentVelocity is the current velocity of the object that will collide with the block.
     * @return the new velocity after the hit.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity){
        Velocity newVelocity = currentVelocity;
        //checks whether the collision point is on the right or left edges
        if (rectangle.getLeftEdge().inSegment(collisionPoint.getX(),collisionPoint.getY())
            || rectangle.getRightEdge().inSegment(collisionPoint.getX(),collisionPoint.getY())) {
             newVelocity.setDx(-currentVelocity.getDx());
        }
        //checks whether the collision point is on the top or bottom edges
        if (rectangle.getTopEdge().inSegment(collisionPoint.getX(),collisionPoint.getY())
            || rectangle.getBottomEdge().inSegment(collisionPoint.getX(),collisionPoint.getY())){
            newVelocity.setDy (-currentVelocity.getDy());
        }
        return newVelocity;
    }

}
