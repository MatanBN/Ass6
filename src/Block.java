/**
 * The Block is a collidable object that can block moving objects.
 * @author Matan Ben Noach Nir Ben Shalom
 * @version 1.0 19 May 2016
 */

public class Block implements Collidable {
    private Rectangle rectangle;

    /**
     * Block creates a new rectangle block.
     * @param r is a given rectangle.
     */
    public Block (Rectangle r){
        this.rectangle=r;
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
