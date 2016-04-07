/**
 * The Block is a collidable object that can block moving objects.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 19 May 2016
 */
import biuoop.DrawSurface;

import java.awt.Color;

public class Block implements Collidable, Sprite {
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

    /**
     * drawOn method draws the block on a given surface.
     * @param d is the surface to draw the blocks on.
     */
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

    /**
     * hit return the new velocity after the hit based on force the object inflicted on us.
     * @param collisionPoint is the collision point of an object with the block.
     * @param currentVelocity is the current velocity of the object that will collide with the block.
     * @return the new velocity after the hit.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity){
        Velocity newVelocity = new Velocity(currentVelocity.getDx(),currentVelocity.getDy());
        //checks whether the collision point is on the right or left edges
        if (checkCollisionSide(collisionPoint,rectangle.getLeftEdge())){
            newVelocity.setDx(-currentVelocity.getDx());
        }
        else if (checkCollisionSide(collisionPoint,rectangle.getRightEdge())){
            newVelocity.setDx(-currentVelocity.getDx());
        }
        //checks whether the collision point is on the top or bottom edges
        if (checkCollisionSide(collisionPoint,rectangle.getTopEdge())){
            newVelocity.setDy(-currentVelocity.getDy());
        }
        else if (checkCollisionSide(collisionPoint,rectangle.getBottomEdge())){
            newVelocity.setDy(-currentVelocity.getDy());
        }
        return newVelocity;
    }

    /**
     * checkCollisionSide gets a collision point and an edge and checks
     * whether the collision point is inside the given edge.
     * @param collisionPoint is the collision point of the ball with the object
     * @param edge is a given edge of an object
     * @return true if the collision point is inside the edge and false otherwise
     */
    public boolean checkCollisionSide(Point collisionPoint, Line edge){
        if (edge.inSegment(collisionPoint.getX(),collisionPoint.getY())){
            return true;
        }
        return false;
    }

    /**
     *
     */
    public void timePassed(){

    }

    /**
     * addToGame is in charge of adding the block as a sprite
     * and as a collidable to the game's suitable lists.
     * @param g is the game object we created.
     */
    public void addToGame (Game g){
        g.addCollidable(this);
        g.addSprite(this);
    }

}
